package haxor.graphics.mesh;
import haxor.context.EngineContext;
import haxor.core.Console;
import haxor.core.Resource;
import haxor.core.Enums.MeshMode;
import haxor.core.Enums.MeshPrimitive;
import haxor.io.Buffer;
import haxor.io.FloatArray;
import haxor.io.UInt16Array;
import haxor.math.AABB3;
import haxor.platform.Types.Float32;
import haxor.platform.Types.MeshBufferId;

#if !ie8
import haxor.context.MeshContext;
import haxor.graphics.GL;
#end



/**
 * Class that describes a Mesh as an attribute container.
 * @author Eduardo Pons - eduardo@thelaborat.org
 */
@:allow(haxor)
class Mesh extends Resource
{
	
	/**
	 * List of indexes that describes the access of this mesh attributes.
	 */
	public var topology(get_topology, set_topology) : UInt16Array;
	private function get_topology():UInt16Array
	{
		if (m_topology_attrib.data == null) return new UInt16Array(0);		
		return cast m_topology_attrib.data;
	}
	private function set_topology(v:UInt16Array):UInt16Array
	{ 
		if (v == null) 
		{ 
			m_topology_attrib.data = null;
			m_indexed = false; 
			EngineContext.mesh.RemoveAttrib(m_topology_attrib,this);			
		}
		else
		{			
			m_topology_attrib.data   = v;
			m_indexed = true;
			EngineContext.mesh.UpdateAttrib(m_topology_attrib,m_mode,true,this);
		}		
		return v;
	}	
	private var m_topology_attrib : MeshAttrib;
	
	
	/**
	 * Flag that indicates if this mesh uses index buffers.
	 */
	public var indexed(get_indexed, never) : Bool;
	private inline function get_indexed():Bool { return m_indexed; }
	private var m_indexed : Bool;
	
	/**
	 * Flag that indicates the type of buffer usage.
	 */
	public var mode(get_mode, set_mode):Int;	
	private inline function get_mode():Int { return m_mode; }
	private function set_mode(v:Int):Int 
	{		
		if (m_mode == v) return v;
		m_mode = v;	
		if(m_indexed) EngineContext.mesh.UpdateAttrib(m_topology_attrib,m_mode,true,this);
		for (i in 0...m_attribs.length) EngineContext.mesh.UpdateAttrib(m_attribs[i],m_mode,false,this);
		return v;
	}
	private var m_mode : Int;
	
	/**
	 * The primitive this mesh will draw.
	 */
	public var primitive : Int;
	
	/**
	 * List of attibutes that defines this mesh.
	 */
	public var attribs(get_attribs, null):Array<String>;
	private function get_attribs():Array<String> { var l : Array<String> = []; for (i in 0...m_attribs.length) l.push(m_attribs[i].name); return l; }
	private var m_attribs : Array<MeshAttrib>;
	
	/**
	 * Returns the vertex count of this mesh.
	 */
	public var vcount(get, never):Int;
	private inline function get_vcount():Int { return m_vcount; }
	private var m_vcount : Int;
	
	/**
	 * Reference to the bounding box of this mesh.
	 */
	public var bounds(get_bounds, set_bounds) : AABB3;	
	private function get_bounds():AABB3	{ return m_bounds; }
	private function set_bounds(v:AABB3):AABB3 { return m_bounds.SetAABB3(v); }
	private var m_bounds : AABB3;
	
	/**
	 * Creates a new Mesh object.
	 */
	public function new(p_name:String=""):Void
	{
		super(p_name);		
		m_attribs 	= [];
		m_indexed	= false;
		m_vcount    = 0;
		m_bounds	= AABB3.empty;
		m_mode		= MeshMode.StaticDraw;
		primitive	= MeshPrimitive.Triangles;
		m_topology_attrib 		 = new MeshAttrib();
		m_topology_attrib.m_name = "$topology";
		m_topology_attrib.offset = 1;
		EngineContext.mesh.Create(this);
	}
	
	/**
	 * Clears all attributes from this mesh. If the 'gpu' flag is set, it alsos removes the attrib buffers from gpu.
	 * This way the mesh can be clean only for GC collection or both GPU and GC collection.
	 * Either way the local data will be lost.
	 * @param	p_from_gpu
	 */
	public function Clear(p_from_gpu:Bool=true):Void
	{
		for (i in 0...m_attribs.length)
		{
			m_attribs[i].data   = null;			
			m_attribs[i].m_name = "";
			if (p_from_gpu) EngineContext.mesh.RemoveAttrib(m_attribs[i],this);
		}		
		//m_attribs = [];
		m_vcount  = 0;
		if (p_from_gpu)
		{
			topology  = null;
		}
		else
		{
			m_topology_attrib.data = null;
		}
	}
	
	/**
	 * Checks if a given attribute exists.
	 * @param	p_name
	 * @return
	 */
	public function Exists(p_name:String):Bool
	{
		return GetAttribute(p_name) != null;
	}
	
	/**
	 * Returns the attribute data contents.
	 */
	public function Get<T>(p_name : String) : T
	{
		var a : MeshAttrib = GetAttribute(p_name);
		return a == null ? null : (cast a.data);
	}
	
	/**
	 * 
	 * @param	p_name
	 * @return
	 */
	public function GetAttribute(p_name :String) : MeshAttrib
	{
		for (i in 0...m_attribs.length) if (m_attribs[i].name == p_name) return m_attribs[i];
		return null;
	}

	
	/**
	 * Removes the attribute from the mesh internal list and destroy its data from the GPU.
	 * @param	p_name
	 */
	public function Remove(p_name : String):Void
	{
		var a : MeshAttrib = GetAttribute(p_name);
		if (a == null) return;
		m_attribs.remove(a);
		EngineContext.mesh.RemoveAttrib(a,this);
	}
	
	/**
	 * Sets a given attribute data content. If the attribute does not exists, it will be created.
	 * The offset parameter specifies the size of the tuple inside the BufferView.
	 * @param	p_name
	 * @param	p_data
	 * @param	p_offset
	 */		
	public function Set(p_name : String, p_data : Buffer,p_offset : Int = 0):MeshAttrib
	{	
		if (p_data == null) 	{ Console.Log("Mesh> [" + name+"] tried to set null array.",1); return null; }		
		//if (p_data.length <= 0)	{ Console.Log("Mesh> [" + name+"] tried to set empty array.", 1); return;	}		
		var a : MeshAttrib = GetAttribute(p_name);
		if (a == null)
		{
			a 		 = new MeshAttrib();			
			a.m_name = p_name;			
			m_attribs.push(a);
		}		
		if (p_offset > 0) a.offset = p_offset;		
		a.data   = p_data;		
		m_vcount = m_attribs[0].count;		
		for (i in 1...m_attribs.length)
		{
			var c: Int = m_attribs[i].count;
			m_vcount = m_vcount < c ? m_vcount : c;			
		}	
		//Console.Log("Mesh> Set attrib[" + a.name+"] offset["+a.offset+"]",5);
		EngineContext.mesh.UpdateAttrib(a, m_mode, false,this);
		return a;
	}
	
	/**
	 * Generates a bounding box using a given attrib of this mesh.
	 * @param	p_attrib
	 * @return
	 */
	public function GenerateAttribBounds(p_attrib:String,p_result:AABB3=null):AABB3
	{
		var b : AABB3 = p_result == null ? AABB3.empty : p_result;		
		var a : MeshAttrib = GetAttribute(p_attrib);		
		if (a == null) return b.Set(0, 0, 0, 0, 0, 0);		
		var step : Int = a.offset;		
		if (step <= 0) return b.Set(0, 0, 0, 0, 0, 0);		
		var i:Int = step;			
		var f : FloatArray = cast a.data;
		var vx : Float32 = step > 0 ? f.Get(0) : 0;
		var vy : Float32 = step > 1 ? f.Get(1) : 0;
		var vz : Float32 = step > 2 ? f.Get(2) : 0;				
		b.Set(vx, vx, vy, vy, vz, vz);
		while (i < f.length)
		{
			vx = step > 0 ? f.Get(i)   : 0;			
			vy = step > 1 ? f.Get(i+1) : 0;
			vz = step > 2 ? f.Get(i+2) : 0;
			b.Encapsulate3(vx, vy, vz);
			i += step;
		}		
		return b;
	}
	
	/**
	 * Callback called when the mesh is destroyed.
	 */
	override public function OnDestroy():Void 
	{		
		EngineContext.mesh.Destroy(this);
	}
	
	
}


/**
 * Class that describes a mesh attribute holder. It will handle the raw buffer which will contains (Float, Vector3, Color,...) data.
 */
@:allow(haxor)
class MeshAttrib
{
	
	/**
	 * Unique id used to sample cached buffer ids.
	 */
	private var __cid : Int;
	
	/**
	 * Shader location if this attrib is part of the default attribs.
	 */
	private var _loc_ : Int;
	
	/**
	 * Attribute Name.
	 */
	public var name(get_name, never) : String;
	private inline function get_name():String { return m_name; }
	private var m_name : String;
	
	/**
	 * Attribute Data.
	 */
	public var data : Buffer;
		
	/**
	 * Data offset (1 = Floats, 3 = Vector3,...)
	 */
	public var offset : Int;
	
	/**
	 * Returns the primitive count based on the array length and the offset for each element.
	 */
	public var count(get_count, never):Int;
	private function get_count():Int { return data == null ? 0 : Std.int(data.length / offset); }
	
	public function new():Void
	{
		__cid  = EngineContext.mesh.aid.id;
		_loc_  = -1;
		m_name = "";
		data   = null;
		offset = 0;
	}
}

