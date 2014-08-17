package haxor.context;
import haxor.context.Process.BaseProcess;
import haxor.core.Console;
import haxor.core.IDisposable;
import haxor.core.IRenderable;
import haxor.core.IResizeable;
import haxor.core.IUpdateable;
import haxor.core.Resource;

/**
 * ...
 * @author Eduardo Pons - eduardo@thelaborat.org
 */
@:allow(haxor)
class EngineContext
{
	
	/**
	 * Unique id counter.
	 */
	static private var uid : Int 		= 0;
	
	/**
	 * Max elements in the process lists.
	 */
	static private var maxNodes : Int 	= 1000;
	
	/**
	 * Objects per frame that will be collected after Destroy.
	 */
	static private var collectRate : Int = 10;
	
	/**
	 * List of IUpdateable nodes.
	 */
	static private var update : Process<IUpdateable>;
	
	/**
	 * List of IResizeable nodes.
	 */
	static private var resize : Process<IResizeable>;
	
	/**
	 * List of IRenderable nodes.
	 */
	static private var render : Process<IRenderable>;
		
	/**
	 * List of IDisposable nodes.
	 */
	static private var resources : Process<Resource>;
	
	/**
	 * List of elements marked for disposing.
	 */
	static private var disposables : Process<IDisposable>;
	
	/**
	 * Reference to the mesh context.
	 */
	static private var mesh : MeshContext;
	
	/**
	 * Reference to the Material context.
	 */
	static private var material : MaterialContext;
	
	/**
	 * List of all processes.
	 */
	static private var list : Array<BaseProcess>;
	
	/**
	 * Initializes the Haxor context.
	 */
	static private function Initialize():Void
	{
		
		Console.Log("Haxor> Engine Context Initialize.",3);
		update      = new Process("process.update", 	 maxNodes);
		render      = new Process("process.render", 	 maxNodes);
		resize      = new Process("process.resize", 	 maxNodes);
		resources   = new Process("process.resources",   maxNodes);
		disposables = new Process("process.disposables", maxNodes);
		
		list = [update, render, resize, resources, disposables];
		
		mesh 		= new MeshContext();
		material	= new MaterialContext();
	}
	
	/**
	 * Method called to initialize contexts that uses the graphics context.
	 */
	static private function Build():Void
	{
		mesh.Initialize();
		material.Initialize();
	}
	
	/**
	 * Handles the destruction of resources of the engine.
	 * @param	p_resource
	 */
	static private function Destroy(p_resource:Resource):Void
	{
		if (p_resource.m_destroyed) return;
		p_resource.m_destroyed = true;		
		for (i in 0...list.length) list[i].Remove(p_resource);		
		EngineContext.disposables.Add(p_resource);		
	}
	
}