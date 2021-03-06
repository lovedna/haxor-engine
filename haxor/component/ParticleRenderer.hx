package haxor.component;
import haxor.core.Entity;
import haxor.core.Enums.AnimationWrap;
import haxor.core.IUpdateable;
import haxor.core.Resource;
import haxor.core.Time;
import haxor.graphics.mesh.MeshLayout.Mesh3;
import haxor.graphics.texture.Texture;
import haxor.graphics.texture.Texture2D;
import haxor.math.AABB3;
import haxor.math.Color;
import haxor.math.Mathf;
import haxor.math.Vector3;
import haxor.platform.Types.Float32;
import haxor.thread.ParticleKernel;


class ParticleAttribute<T>
{
	public var start  : T;	
	public var end    : T;	
	public var curve  : Float32;	
	public var random : Bool;	
	public function new(p_start:T,p_end:T,p_curve:Float32=1.0,p_random:Bool=false)
	{
		start 	= p_start;
		end  	= p_end;
		curve 	= p_curve;		
		random  = p_random;
	}
	
}

class ParticleStart
{
	public var life 		: ParticleAttribute<Float32>;	
	public var speed 		: ParticleAttribute<Float32>;	
	public var size     	: ParticleAttribute<Vector3>;	
	public var rotation 	: ParticleAttribute<Vector3>;	
	public var color 		: Texture;	
	
	public function new()
	{
		life     = new ParticleAttribute<Float32>(1.0,1.0);
		speed    = new ParticleAttribute<Float32>(1.0, 1.0);
		size     = new ParticleAttribute<Vector3>(Vector3.one,Vector3.one);
		rotation = new ParticleAttribute<Vector3>(Vector3.zero,Vector3.zero);
		color    = Texture2D.white;
	}
	
}

class ParticleLife
{
	public var speed 	: ParticleAttribute<Float32>;	
	public var motion 	: ParticleAttribute<Vector3>;	
	public var size 	: ParticleAttribute<Vector3>;	
	public var rotation : ParticleAttribute<Vector3>;	
	public var color 	: Texture;
	
	public function new()
	{		
		speed    = new ParticleAttribute<Float32>(1.0, 1.0);
		motion   = new ParticleAttribute<Vector3>(Vector3.one, Vector3.one);
		size     = new ParticleAttribute<Vector3>(Vector3.one,Vector3.one);
		rotation = new ParticleAttribute<Vector3>(Vector3.zero,Vector3.zero);
		color    = Texture2D.white;
	}
	
}

class ParticleSheet
{
	public var width  : Float32;
	public var height : Float32;
	public var length : Int;
	public var fps    : Float32;
	public var frame  : ParticleAttribute<Float32>;
	public var wrap	  : AnimationWrap;
	public var reverse:Bool;
	
	public function new()
	{
		
		width   = 0.0;
		height  = 0.0;
		length  = 0;
		fps     = 60.0;
		frame   = new ParticleAttribute<Float32>(0.0, 0.0);
		wrap    = AnimationWrap.Clamp;
		reverse = false;
	}
}

enum ParticleSystemState
{
	None;
	Reset;
	Update;
}

/**
 * ...
 * @author Eduardo Pons
 */
@:allow(haxor)
class ParticleRenderer extends MeshRenderer implements IUpdateable
{
	
	/**
	 * 
	 */
	public var emitted (get_emitted, never) : Float32;	
	function get_emitted():Float32 { return m_emitted_count; 	}	
	private var m_emitted_start : Float32;
	private var m_emitted_count : Float32;
	
	/**
	 * 
	 */
	public var playing (get_playing, never) : Bool;	
	function get_playing():Bool 		{ return m_playing; }
	private var m_playing : Bool;
	
	/**
	 * 
	 */
	public var bounds (get_bounds, set_bounds) : AABB3;	
	function get_bounds():AABB3 		{ return m_mesh.bounds; }
	function set_bounds(v:AABB3):AABB3 		{ m_mesh.bounds = v; return v; }
	
	
	
	/**
	 * 
	 */
	public var count (get_count, set_count) : Int;	
	function get_count():Int 		{ return m_count; }
	function set_count(v:Int):Int 	{ m_count = v; Reset(); UpdateMesh(); return v; }
	private var m_count : Int;
	
	/**
	 * 
	 */
	public var elapsed 	: Float32;	
	
	/**
	 * 
	 */
	public var duration : Float32;
	
	/**
	 * 
	 */
	public var billboard: Bool;
	
	/**
	 * 
	 */
	public var local 	: Bool;	
	
	/**
	 * 
	 */
	public var loop 	: Bool;	
	
	/**
	 * 
	 */
	public var emitter 	: ParticleEmitter;	
	
	/**
	 * 
	 */
	public var start	: ParticleStart;
	
	/**
	 * 
	 */
	public var life		: ParticleLife;
	
	/**
	 * 
	 */
	public var sheet	: ParticleSheet;
	
	/**
	 * 
	 */
	public var rate 	: ParticleAttribute<Float32>;
	
	/**
	 * 
	 */
	public var force 	: Vector3;
	
	private var m_particles : Mesh3;	
	private var m_state 	: ParticleSystemState;	
	private var m_kernel 	: ParticleKernel;
	
	override function OnBuild():Void 
	{
		super.OnBuild();
		
		
		
		m_state 		 = ParticleSystemState.Reset;
		m_kernel 		 = new ParticleKernel(this);
		elapsed			 = 0.0;
		m_playing		 = false;
		m_emitted_count  = 0.0;
		m_emitted_start  = 0.0;
		
		count 			 = 10;
		
		duration 		 = 1.0;
				
		emitter			 = new SphereEmitter(1.0);
		
		sheet			 = new ParticleSheet();
		
		local 			 = true;		
		loop			 = true;
		billboard        = true;
				
		start			 = new ParticleStart();
		life			 = new ParticleLife();
		
		rate			 = new ParticleAttribute<Float>(1.0, 1.0);
		
		force			 = new Vector3();
	}
	
	/**
	 * 
	 * @param	p_emit_count
	 */
	public function Emit(p_emit_count : Float32=1.0):Void
	{
		var remain  : Float32 = count - emitted;
		var c 		: Float32 = Mathf.Min(p_emit_count, remain);
		var d		: Float32 = p_emit_count - c;				
		if (loop) m_emitted_start += d;			
		m_emitted_count += c;				
		m_playing = true;
	}
	
	/**
	 * 
	 */
	public function Play():Void
	{	
		m_playing = true;
	}
	
	/**
	 * 
	 */
	public function Pause():Void
	{		
		m_playing = false;
	}
	
	/**
	 * 
	 */
	public function Reset():Void
	{		
		//m_state   = ParticleSystemState.Reset;
		elapsed = 0.0;
		m_emitted_count = 0.0;
		m_emitted_start = 0.0;
	}
	
	public function Stop():Void
	{
		elapsed = 0.0;
		m_emitted_count = 0.0;
		m_emitted_start = 0.0;
		m_state   = ParticleSystemState.Reset;
		m_playing = false;
	}
	
	private function Simulate():Void
	{
		switch(m_state)
		{
			case ParticleSystemState.None:
				
				if (m_playing) m_state = ParticleSystemState.Update;
			
				
			case ParticleSystemState.Reset:
				//m_kernel.Execute();
				m_state = m_playing ? ParticleSystemState.Update : ParticleSystemState.None;
						
			case ParticleSystemState.Update:
				
				if (!m_playing) { m_state = ParticleSystemState.None; return; }
				
				//m_kernel.Execute();
				
				var max_life : Float32 = Mathf.Max(start.life.start, start.life.end);
				if (elapsed >= (duration+max_life)) if(!loop) return;
				var dt : Float32 = Time.delta;
				var r  : Float32 = Mathf.Clamp01(duration<=0.0 ? 0.0 : (elapsed / duration));
				
				
				elapsed += dt;
				if (elapsed >= (duration+max_life)) elapsed = (duration+max_life);
				
				var er : Float32 = dt * Mathf.Lerp(rate.start,rate.end,rate.random ? Math.random() : Mathf.Pow(r,rate.curve));		
				Emit(er);
				
				//if((Time.frame % 30)==0) trace(m_emitted_start + " " + m_emitted_count);
				
		}
	}
	
	public function OnUpdate():Void
	{	
		/*
		if (Input.Hit(KeyCode.Q)) Play();
		if (Input.Hit(KeyCode.W)) Pause();
		if (Input.Hit(KeyCode.E)) Reset();
		if (Input.Hit(KeyCode.R)) Emit();		
		if (Input.IsDown(KeyCode.T)) Emit(Time.deltaTime * 5.0);
		if (Input.IsDown(KeyCode.Y)) Emit(Time.deltaTime * 300.0);
		if (Input.Hit(KeyCode.U)) Emit(500.0);
		if (Input.Hit(KeyCode.I)) Emit(2000.0);
		if (Input.Hit(KeyCode.P)) Emit(10000.0);
		if (Input.Hit(KeyCode.O)) local = !local;
		//*/
		
		Simulate();
		//*/
	}
	
	private function UpdateMesh():Void
	{
		if (m_particles != null) Resource.Destroy(m_particles);
		m_particles		   = new Mesh3();
		m_particles.name   = "ParticleMesh" + uid;
		m_particles.bounds = AABB3.FromCenter(0, 0, 0, 1, 1, 1);
		
		mesh = m_particles;
		
		var vl : Array<Vector3> = [];						
		
		var s : Float32 = 0.5;
		var plane : Array<Vector3> = 
		[
			new Vector3(-s,  s, 0),
			new Vector3(-s, -s, 0),						
			new Vector3( s, -s, 0),			
			new Vector3(-s,  s, 0),
			new Vector3( s, -s, 0),			
			new Vector3( s,  s, 0)			
		];	
		
		//var cl : Array<Color>   = [];
		//var wc:Color = Color.white;
		
		for (i in 0...count)
		{
			for (j in 0...plane.length)
			{
				var v : Vector3 = plane[j].clone; 
				v.z = i;
				vl.push(v);
				//cl.push(wc);
			}
		}
		//*/
		m_particles.vertex = vl;
		//m_particles.color  = cl;
		
	}
	
}

@:allow(haxor)
class ParticleEmitter
{
	
	public var surface(get_surface,set_surface) : Bool;
	private function get_surface():Bool { return m_data[1] > 0.0; }
	private function set_surface(v:Bool):Bool { m_data[1] = v ? 1.0 : 0.0; return v; }
	
	public var random(get_random,set_random) : Bool;
	private function get_random():Bool { return m_data[2] > 0.0; }
	private function set_random(v:Bool):Bool { m_data[2] = v ? 1.0 : 0.0; return v; }
	
	public var ranges : Array<Float32>;
	
	private var m_data : Array<Float32>;
	
	public function new()
	{
		m_data = [0, 0, 0, 0, 0, 0, 0];
		surface = false;
		random  = false;
		ranges  = [ -1000, 1000, -1000, 1000, -1000, 1000];
	}
}

class SphereEmitter extends ParticleEmitter
{
	public var radius(get_radius, set_radius):Float;
	private function get_radius():Float { return m_data[3]; }
	private function set_radius(v:Float):Float { m_data[3] = v; return v; }
	
	public function new(p_radius : Float=1.0)
	{
		super();
		m_data[0] = 0.0;
		radius 	  = p_radius;
	}
}

class BoxEmitter extends ParticleEmitter
{
	public var width(get_width, set_width):Float;
	private function get_width():Float { return m_data[3]; }
	private function set_width(v:Float):Float { m_data[3] = v; return v; }
	
	public var height(get_height, set_height):Float;
	private function  get_height():Float { return m_data[4]; }
	private function  set_height(v:Float):Float { m_data[4] = v; return v; }
	
	public var depth (get_depth, set_depth):Float;
	private function  get_depth():Float { return m_data[5]; }
	private function  set_depth(v:Float):Float { m_data[5] = v; return v; }
	
	public function new(p_width : Float=1.0,p_height:Float=1.0,p_depth:Float=1.0)
	{
		super();
		m_data[0] = 1.0;
		width  = p_width;
		height = p_height;
		depth  = p_depth;
	}
}

class ConeEmitter extends ParticleEmitter
{
	public var angle(get_angle, set_angle):Float;
	private function get_angle():Float { return m_data[3]; }
	private function set_angle(v:Float):Float { m_data[3] = v; return v; }
	
	public var height(get_height, set_height):Float;
	private function  get_height():Float { return m_data[4]; }
	private function  set_height(v:Float):Float { m_data[4] = v; return v; }
	
	public function new(p_angle : Float=45.0,p_height:Float=1.0)
	{
		super();
		m_data[0] = 2.0;
		angle  = p_angle;
		height = p_height;
	}
}

class CylinderEmitter extends ParticleEmitter
{
	public var radius(get_radius, set_radius):Float;
	private function get_radius():Float { return m_data[3]; }
	private function set_radius(v:Float):Float { m_data[3] = v; return v; }
	
	public var height(get_height, set_height):Float;
	private function  get_height():Float { return m_data[4]; }
	private function  set_height(v:Float):Float { m_data[4] = v; return v; }
	
	public function new(p_radius : Float=1.0,p_height:Float=1.0)
	{
		super();
		m_data[0] = 3.0;
		radius  = p_radius;
		height  = p_height;
	}
}