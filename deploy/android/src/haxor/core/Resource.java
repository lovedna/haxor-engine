package haxor.core;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Resource extends haxe.lang.HxObject implements haxor.core.IDisposable
{
	public    Resource(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Resource(java.lang.String p_name)
	{
		haxor.core.Resource.__hx_ctor_haxor_core_Resource(this, p_name);
	}
	
	
	public static   void __hx_ctor_haxor_core_Resource(haxor.core.Resource __temp_me54050, java.lang.String p_name)
	{
		if (( p_name == null )) 
		{
			p_name = "";
		}
		
		__temp_me54050.m_uid = haxor.context.EngineContext.uid++;
		__temp_me54050.m_destroyed = false;
		__temp_me54050._cid_ = 0;
		__temp_me54050.m_pid = new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{-1, -1, -1, -1, -1, -1});
		__temp_me54050.m_name = p_name;
		__temp_me54050.m_is_behaviour = ( __temp_me54050 instanceof haxor.component.Behaviour );
		__temp_me54050.m_type_class = ((java.lang.Class) (haxe.root.Type.getClass(__temp_me54050)) );
		__temp_me54050.m_type_full_name = haxe.root.Type.getClassName(__temp_me54050.m_type_class);
		haxe.root.Array<java.lang.String> nt = haxe.lang.StringExt.split(__temp_me54050.m_type_full_name, ".");
		nt.reverse();
		__temp_me54050.m_type_name = nt.__get(0);
		if (haxe.lang.Runtime.valEq(p_name, "")) 
		{
			__temp_me54050.m_name = ( __temp_me54050.m_type_name + __temp_me54050.m_uid );
		}
		 else 
		{
			__temp_me54050.m_name = p_name;
		}
		
		haxor.context.EngineContext.resources.Add(__temp_me54050);
	}
	
	
	public static   void Destroy(haxor.core.Resource p_target)
	{
		haxor.context.EngineContext.Destroy(p_target);
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxor.core.Resource(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxor.core.Resource(haxe.lang.Runtime.toString(arr.__get(0)));
	}
	
	
	public  haxor.core.Application application;
	
	public   haxor.core.Application get_application()
	{
		return ((haxor.core.Application) (haxor.core.BaseApplication.m_instance) );
	}
	
	
	public  int uid;
	
	public   int get_uid()
	{
		return this.m_uid;
	}
	
	
	public  int m_uid;
	
	
	
	public   java.lang.String get_name()
	{
		return this.m_name;
	}
	
	
	public   java.lang.String set_name(java.lang.String v)
	{
		this.m_name = v;
		return v;
	}
	
	
	public  java.lang.String m_name;
	
	public  boolean destroyed;
	
	public final   boolean get_destroyed()
	{
		return this.m_destroyed;
	}
	
	
	public  boolean m_destroyed;
	
	public  int _cid_;
	
	public  haxe.root.Array<java.lang.Object> m_pid;
	
	public  java.lang.String m_type_name;
	
	public  java.lang.String m_type_full_name;
	
	public  java.lang.Class m_type_class;
	
	public  boolean m_is_behaviour;
	
	public final   java.lang.Class GetType()
	{
		return this.m_type_class;
	}
	
	
	public final   java.lang.String GetTypeName()
	{
		return this.m_type_name;
	}
	
	
	public final   java.lang.String GetTypeFullName()
	{
		return this.m_type_full_name;
	}
	
	
	public   void OnDestroy()
	{
		{
		}
		
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef54182 = true;
			switch (field.hashCode())
			{
				case 90787904:
				{
					if (field.equals("_cid_")) 
					{
						__temp_executeDef54182 = false;
						this._cid_ = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 115792:
				{
					if (field.equals("uid")) 
					{
						__temp_executeDef54182 = false;
						this.uid = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 103609726:
				{
					if (field.equals("m_uid")) 
					{
						__temp_executeDef54182 = false;
						this.m_uid = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54182) 
			{
				return super.__hx_setField_f(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef54183 = true;
			switch (field.hashCode())
			{
				case 1202714234:
				{
					if (field.equals("m_is_behaviour")) 
					{
						__temp_executeDef54183 = false;
						this.m_is_behaviour = haxe.lang.Runtime.toBool(value);
						return value;
					}
					
					break;
				}
				
				
				case 1554253136:
				{
					if (field.equals("application")) 
					{
						__temp_executeDef54183 = false;
						this.application = ((haxor.core.Application) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1178371909:
				{
					if (field.equals("m_type_class")) 
					{
						__temp_executeDef54183 = false;
						this.m_type_class = ((java.lang.Class) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 115792:
				{
					if (field.equals("uid")) 
					{
						__temp_executeDef54183 = false;
						this.uid = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -684621432:
				{
					if (field.equals("m_type_full_name")) 
					{
						__temp_executeDef54183 = false;
						this.m_type_full_name = haxe.lang.Runtime.toString(value);
						return value;
					}
					
					break;
				}
				
				
				case 103609726:
				{
					if (field.equals("m_uid")) 
					{
						__temp_executeDef54183 = false;
						this.m_uid = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 731066142:
				{
					if (field.equals("m_type_name")) 
					{
						__temp_executeDef54183 = false;
						this.m_type_name = haxe.lang.Runtime.toString(value);
						return value;
					}
					
					break;
				}
				
				
				case 3373707:
				{
					if (field.equals("name")) 
					{
						__temp_executeDef54183 = false;
						this.set_name(haxe.lang.Runtime.toString(value));
						return value;
					}
					
					break;
				}
				
				
				case 103604921:
				{
					if (field.equals("m_pid")) 
					{
						__temp_executeDef54183 = false;
						this.m_pid = ((haxe.root.Array<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1083281635:
				{
					if (field.equals("m_name")) 
					{
						__temp_executeDef54183 = false;
						this.m_name = haxe.lang.Runtime.toString(value);
						return value;
					}
					
					break;
				}
				
				
				case 90787904:
				{
					if (field.equals("_cid_")) 
					{
						__temp_executeDef54183 = false;
						this._cid_ = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 1986762265:
				{
					if (field.equals("destroyed")) 
					{
						__temp_executeDef54183 = false;
						this.destroyed = haxe.lang.Runtime.toBool(value);
						return value;
					}
					
					break;
				}
				
				
				case -2096321337:
				{
					if (field.equals("m_destroyed")) 
					{
						__temp_executeDef54183 = false;
						this.m_destroyed = haxe.lang.Runtime.toBool(value);
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54183) 
			{
				return super.__hx_setField(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef54184 = true;
			switch (field.hashCode())
			{
				case 602652923:
				{
					if (field.equals("OnDestroy")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("OnDestroy"))) );
					}
					
					break;
				}
				
				
				case 1554253136:
				{
					if (field.equals("application")) 
					{
						__temp_executeDef54184 = false;
						if (handleProperties) 
						{
							return this.get_application();
						}
						 else 
						{
							return this.application;
						}
						
					}
					
					break;
				}
				
				
				case -1268017814:
				{
					if (field.equals("GetTypeFullName")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("GetTypeFullName"))) );
					}
					
					break;
				}
				
				
				case -1900568089:
				{
					if (field.equals("get_application")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_application"))) );
					}
					
					break;
				}
				
				
				case -1225705925:
				{
					if (field.equals("GetTypeName")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("GetTypeName"))) );
					}
					
					break;
				}
				
				
				case 115792:
				{
					if (field.equals("uid")) 
					{
						__temp_executeDef54184 = false;
						if (handleProperties) 
						{
							return this.get_uid();
						}
						 else 
						{
							return this.uid;
						}
						
					}
					
					break;
				}
				
				
				case 1589546896:
				{
					if (field.equals("GetType")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("GetType"))) );
					}
					
					break;
				}
				
				
				case -74782745:
				{
					if (field.equals("get_uid")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_uid"))) );
					}
					
					break;
				}
				
				
				case 1202714234:
				{
					if (field.equals("m_is_behaviour")) 
					{
						__temp_executeDef54184 = false;
						return this.m_is_behaviour;
					}
					
					break;
				}
				
				
				case 103609726:
				{
					if (field.equals("m_uid")) 
					{
						__temp_executeDef54184 = false;
						return this.m_uid;
					}
					
					break;
				}
				
				
				case 1178371909:
				{
					if (field.equals("m_type_class")) 
					{
						__temp_executeDef54184 = false;
						return this.m_type_class;
					}
					
					break;
				}
				
				
				case 3373707:
				{
					if (field.equals("name")) 
					{
						__temp_executeDef54184 = false;
						return this.get_name();
					}
					
					break;
				}
				
				
				case -684621432:
				{
					if (field.equals("m_type_full_name")) 
					{
						__temp_executeDef54184 = false;
						return this.m_type_full_name;
					}
					
					break;
				}
				
				
				case 1976486356:
				{
					if (field.equals("get_name")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_name"))) );
					}
					
					break;
				}
				
				
				case 731066142:
				{
					if (field.equals("m_type_name")) 
					{
						__temp_executeDef54184 = false;
						return this.m_type_name;
					}
					
					break;
				}
				
				
				case 1415373896:
				{
					if (field.equals("set_name")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("set_name"))) );
					}
					
					break;
				}
				
				
				case 103604921:
				{
					if (field.equals("m_pid")) 
					{
						__temp_executeDef54184 = false;
						return this.m_pid;
					}
					
					break;
				}
				
				
				case -1083281635:
				{
					if (field.equals("m_name")) 
					{
						__temp_executeDef54184 = false;
						return this.m_name;
					}
					
					break;
				}
				
				
				case 90787904:
				{
					if (field.equals("_cid_")) 
					{
						__temp_executeDef54184 = false;
						return this._cid_;
					}
					
					break;
				}
				
				
				case 1986762265:
				{
					if (field.equals("destroyed")) 
					{
						__temp_executeDef54184 = false;
						if (handleProperties) 
						{
							return this.get_destroyed();
						}
						 else 
						{
							return this.destroyed;
						}
						
					}
					
					break;
				}
				
				
				case -2096321337:
				{
					if (field.equals("m_destroyed")) 
					{
						__temp_executeDef54184 = false;
						return this.m_destroyed;
					}
					
					break;
				}
				
				
				case -1793364880:
				{
					if (field.equals("get_destroyed")) 
					{
						__temp_executeDef54184 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_destroyed"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54184) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   double __hx_getField_f(java.lang.String field, boolean throwErrors, boolean handleProperties)
	{
		{
			boolean __temp_executeDef54185 = true;
			switch (field.hashCode())
			{
				case 90787904:
				{
					if (field.equals("_cid_")) 
					{
						__temp_executeDef54185 = false;
						return ((double) (this._cid_) );
					}
					
					break;
				}
				
				
				case 115792:
				{
					if (field.equals("uid")) 
					{
						__temp_executeDef54185 = false;
						if (handleProperties) 
						{
							return ((double) (this.get_uid()) );
						}
						 else 
						{
							return ((double) (this.uid) );
						}
						
					}
					
					break;
				}
				
				
				case 103609726:
				{
					if (field.equals("m_uid")) 
					{
						__temp_executeDef54185 = false;
						return ((double) (this.m_uid) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54185) 
			{
				return super.__hx_getField_f(field, throwErrors, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_invokeField(java.lang.String field, haxe.root.Array dynargs)
	{
		{
			boolean __temp_executeDef54186 = true;
			switch (field.hashCode())
			{
				case 602652923:
				{
					if (field.equals("OnDestroy")) 
					{
						__temp_executeDef54186 = false;
						this.OnDestroy();
					}
					
					break;
				}
				
				
				case -1900568089:
				{
					if (field.equals("get_application")) 
					{
						__temp_executeDef54186 = false;
						return this.get_application();
					}
					
					break;
				}
				
				
				case -1268017814:
				{
					if (field.equals("GetTypeFullName")) 
					{
						__temp_executeDef54186 = false;
						return this.GetTypeFullName();
					}
					
					break;
				}
				
				
				case -74782745:
				{
					if (field.equals("get_uid")) 
					{
						__temp_executeDef54186 = false;
						return this.get_uid();
					}
					
					break;
				}
				
				
				case -1225705925:
				{
					if (field.equals("GetTypeName")) 
					{
						__temp_executeDef54186 = false;
						return this.GetTypeName();
					}
					
					break;
				}
				
				
				case 1976486356:
				{
					if (field.equals("get_name")) 
					{
						__temp_executeDef54186 = false;
						return this.get_name();
					}
					
					break;
				}
				
				
				case 1589546896:
				{
					if (field.equals("GetType")) 
					{
						__temp_executeDef54186 = false;
						return this.GetType();
					}
					
					break;
				}
				
				
				case 1415373896:
				{
					if (field.equals("set_name")) 
					{
						__temp_executeDef54186 = false;
						return this.set_name(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case -1793364880:
				{
					if (field.equals("get_destroyed")) 
					{
						__temp_executeDef54186 = false;
						return this.get_destroyed();
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54186) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("m_is_behaviour");
		baseArr.push("m_type_class");
		baseArr.push("m_type_full_name");
		baseArr.push("m_type_name");
		baseArr.push("m_pid");
		baseArr.push("_cid_");
		baseArr.push("m_destroyed");
		baseArr.push("destroyed");
		baseArr.push("m_name");
		baseArr.push("name");
		baseArr.push("m_uid");
		baseArr.push("uid");
		baseArr.push("application");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}

