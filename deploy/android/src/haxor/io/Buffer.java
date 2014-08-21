package haxor.io;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Buffer extends haxe.lang.HxObject
{
	public    Buffer(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Buffer(int p_length)
	{
		haxor.io.Buffer.__hx_ctor_haxor_io_Buffer(this, p_length);
	}
	
	
	public static   void __hx_ctor_haxor_io_Buffer(haxor.io.Buffer __temp_me54108, int p_length)
	{
		__temp_me54108.m_length = p_length;
		int len = ( __temp_me54108.m_length * __temp_me54108.get_bytesPerElement() );
		__temp_me54108.m_offset = 0;
		__temp_me54108.m_buffer = java.nio.ByteBuffer.allocateDirect(((int) (len) )).order(((java.nio.ByteOrder) (java.nio.ByteOrder.nativeOrder()) ));
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxor.io.Buffer(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxor.io.Buffer(((int) (haxe.lang.Runtime.toInt(arr.__get(0))) ));
	}
	
	
	public  java.nio.ByteBuffer buffer;
	
	public final   java.nio.ByteBuffer get_buffer()
	{
		return this.m_buffer;
	}
	
	
	public  java.nio.ByteBuffer m_buffer;
	
	public  java.nio.ByteBuffer m_original;
	
	public  int m_offset;
	
	public  int byteLength;
	
	public final   int get_byteLength()
	{
		return ( this.m_length * this.get_bytesPerElement() );
	}
	
	
	public  int bytesPerElement;
	
	public   int get_bytesPerElement()
	{
		return 1;
	}
	
	
	public  int length;
	
	public final   int get_length()
	{
		return this.m_length;
	}
	
	
	public  int m_length;
	
	public   int GetByte(int p_index)
	{
		return ((int) (this.m_buffer.get(((int) (p_index) ))) );
	}
	
	
	public   void SetByte(int p_index, int p_value)
	{
		this.m_buffer.put(((int) (p_index) ), ((byte) (p_value) ));
	}
	
	
	public   void SetViewSlice(int p_start, int p_length)
	{
		int i0 = ( p_start * this.get_bytesPerElement() );
		int i1 = ( i0 + ( p_length * this.get_bytesPerElement() ) );
		this.m_original = this.m_buffer;
		this.m_buffer.position(((int) (i0) ));
		this.m_buffer.limit(((int) (i1) ));
		this.m_buffer = this.m_buffer.slice();
	}
	
	
	public   void ResetSlice()
	{
		this.m_buffer = this.m_original;
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef54363 = true;
			switch (field.hashCode())
			{
				case -1705094408:
				{
					if (field.equals("m_length")) 
					{
						__temp_executeDef54363 = false;
						this.m_length = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1618510683:
				{
					if (field.equals("m_offset")) 
					{
						__temp_executeDef54363 = false;
						this.m_offset = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1106363674:
				{
					if (field.equals("length")) 
					{
						__temp_executeDef54363 = false;
						this.length = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 281453070:
				{
					if (field.equals("byteLength")) 
					{
						__temp_executeDef54363 = false;
						this.byteLength = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1062817258:
				{
					if (field.equals("bytesPerElement")) 
					{
						__temp_executeDef54363 = false;
						this.bytesPerElement = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54363) 
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
			boolean __temp_executeDef54364 = true;
			switch (field.hashCode())
			{
				case -1705094408:
				{
					if (field.equals("m_length")) 
					{
						__temp_executeDef54364 = false;
						this.m_length = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -1378118592:
				{
					if (field.equals("buffer")) 
					{
						__temp_executeDef54364 = false;
						this.buffer = ((java.nio.ByteBuffer) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1106363674:
				{
					if (field.equals("length")) 
					{
						__temp_executeDef54364 = false;
						this.length = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -1976849326:
				{
					if (field.equals("m_buffer")) 
					{
						__temp_executeDef54364 = false;
						this.m_buffer = ((java.nio.ByteBuffer) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1062817258:
				{
					if (field.equals("bytesPerElement")) 
					{
						__temp_executeDef54364 = false;
						this.bytesPerElement = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 1524426083:
				{
					if (field.equals("m_original")) 
					{
						__temp_executeDef54364 = false;
						this.m_original = ((java.nio.ByteBuffer) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 281453070:
				{
					if (field.equals("byteLength")) 
					{
						__temp_executeDef54364 = false;
						this.byteLength = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -1618510683:
				{
					if (field.equals("m_offset")) 
					{
						__temp_executeDef54364 = false;
						this.m_offset = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54364) 
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
			boolean __temp_executeDef54365 = true;
			switch (field.hashCode())
			{
				case 305726883:
				{
					if (field.equals("ResetSlice")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("ResetSlice"))) );
					}
					
					break;
				}
				
				
				case -1378118592:
				{
					if (field.equals("buffer")) 
					{
						__temp_executeDef54365 = false;
						if (handleProperties) 
						{
							return this.get_buffer();
						}
						 else 
						{
							return this.buffer;
						}
						
					}
					
					break;
				}
				
				
				case 119371723:
				{
					if (field.equals("SetViewSlice")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("SetViewSlice"))) );
					}
					
					break;
				}
				
				
				case 702559561:
				{
					if (field.equals("get_buffer")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_buffer"))) );
					}
					
					break;
				}
				
				
				case -645846934:
				{
					if (field.equals("SetByte")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("SetByte"))) );
					}
					
					break;
				}
				
				
				case -1976849326:
				{
					if (field.equals("m_buffer")) 
					{
						__temp_executeDef54365 = false;
						return this.m_buffer;
					}
					
					break;
				}
				
				
				case 1589010782:
				{
					if (field.equals("GetByte")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("GetByte"))) );
					}
					
					break;
				}
				
				
				case 1524426083:
				{
					if (field.equals("m_original")) 
					{
						__temp_executeDef54365 = false;
						return this.m_original;
					}
					
					break;
				}
				
				
				case -1705094408:
				{
					if (field.equals("m_length")) 
					{
						__temp_executeDef54365 = false;
						return this.m_length;
					}
					
					break;
				}
				
				
				case -1618510683:
				{
					if (field.equals("m_offset")) 
					{
						__temp_executeDef54365 = false;
						return this.m_offset;
					}
					
					break;
				}
				
				
				case 974314479:
				{
					if (field.equals("get_length")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_length"))) );
					}
					
					break;
				}
				
				
				case 281453070:
				{
					if (field.equals("byteLength")) 
					{
						__temp_executeDef54365 = false;
						if (handleProperties) 
						{
							return this.get_byteLength();
						}
						 else 
						{
							return this.byteLength;
						}
						
					}
					
					break;
				}
				
				
				case -1106363674:
				{
					if (field.equals("length")) 
					{
						__temp_executeDef54365 = false;
						if (handleProperties) 
						{
							return this.get_length();
						}
						 else 
						{
							return this.length;
						}
						
					}
					
					break;
				}
				
				
				case -938371433:
				{
					if (field.equals("get_byteLength")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_byteLength"))) );
					}
					
					break;
				}
				
				
				case -829503743:
				{
					if (field.equals("get_bytesPerElement")) 
					{
						__temp_executeDef54365 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get_bytesPerElement"))) );
					}
					
					break;
				}
				
				
				case 1062817258:
				{
					if (field.equals("bytesPerElement")) 
					{
						__temp_executeDef54365 = false;
						if (handleProperties) 
						{
							return this.get_bytesPerElement();
						}
						 else 
						{
							return this.bytesPerElement;
						}
						
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54365) 
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
			boolean __temp_executeDef54366 = true;
			switch (field.hashCode())
			{
				case -1705094408:
				{
					if (field.equals("m_length")) 
					{
						__temp_executeDef54366 = false;
						return ((double) (this.m_length) );
					}
					
					break;
				}
				
				
				case -1618510683:
				{
					if (field.equals("m_offset")) 
					{
						__temp_executeDef54366 = false;
						return ((double) (this.m_offset) );
					}
					
					break;
				}
				
				
				case -1106363674:
				{
					if (field.equals("length")) 
					{
						__temp_executeDef54366 = false;
						if (handleProperties) 
						{
							return ((double) (this.get_length()) );
						}
						 else 
						{
							return ((double) (this.length) );
						}
						
					}
					
					break;
				}
				
				
				case 281453070:
				{
					if (field.equals("byteLength")) 
					{
						__temp_executeDef54366 = false;
						if (handleProperties) 
						{
							return ((double) (this.get_byteLength()) );
						}
						 else 
						{
							return ((double) (this.byteLength) );
						}
						
					}
					
					break;
				}
				
				
				case 1062817258:
				{
					if (field.equals("bytesPerElement")) 
					{
						__temp_executeDef54366 = false;
						if (handleProperties) 
						{
							return ((double) (this.get_bytesPerElement()) );
						}
						 else 
						{
							return ((double) (this.bytesPerElement) );
						}
						
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54366) 
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
			boolean __temp_executeDef54367 = true;
			switch (field.hashCode())
			{
				case 305726883:
				{
					if (field.equals("ResetSlice")) 
					{
						__temp_executeDef54367 = false;
						this.ResetSlice();
					}
					
					break;
				}
				
				
				case 702559561:
				{
					if (field.equals("get_buffer")) 
					{
						__temp_executeDef54367 = false;
						return this.get_buffer();
					}
					
					break;
				}
				
				
				case 119371723:
				{
					if (field.equals("SetViewSlice")) 
					{
						__temp_executeDef54367 = false;
						this.SetViewSlice(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case -938371433:
				{
					if (field.equals("get_byteLength")) 
					{
						__temp_executeDef54367 = false;
						return this.get_byteLength();
					}
					
					break;
				}
				
				
				case -645846934:
				{
					if (field.equals("SetByte")) 
					{
						__temp_executeDef54367 = false;
						this.SetByte(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case -829503743:
				{
					if (field.equals("get_bytesPerElement")) 
					{
						__temp_executeDef54367 = false;
						return this.get_bytesPerElement();
					}
					
					break;
				}
				
				
				case 1589010782:
				{
					if (field.equals("GetByte")) 
					{
						__temp_executeDef54367 = false;
						return this.GetByte(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case 974314479:
				{
					if (field.equals("get_length")) 
					{
						__temp_executeDef54367 = false;
						return this.get_length();
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef54367) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("m_length");
		baseArr.push("length");
		baseArr.push("bytesPerElement");
		baseArr.push("byteLength");
		baseArr.push("m_offset");
		baseArr.push("m_original");
		baseArr.push("m_buffer");
		baseArr.push("buffer");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}

