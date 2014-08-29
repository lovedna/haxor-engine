package haxe.crypto;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class BaseCode extends haxe.lang.HxObject
{
	public    BaseCode(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    BaseCode(haxe.io.Bytes base)
	{
		haxe.crypto.BaseCode.__hx_ctor_haxe_crypto_BaseCode(this, base);
	}
	
	
	public static   void __hx_ctor_haxe_crypto_BaseCode(haxe.crypto.BaseCode __temp_me80545, haxe.io.Bytes base)
	{
		int len = base.length;
		int nbits = 1;
		while (( len > ( 1 << nbits ) ))
		{
			nbits++;
		}
		
		if (( ( nbits > 8 ) || ( len != ( 1 << nbits ) ) )) 
		{
			throw haxe.lang.HaxeException.wrap("BaseCode : base length must be a power of two.");
		}
		
		__temp_me80545.base = base;
		__temp_me80545.nbits = nbits;
	}
	
	
	public static   java.lang.String encode(java.lang.String s, java.lang.String base)
	{
		haxe.crypto.BaseCode b = new haxe.crypto.BaseCode(((haxe.io.Bytes) (haxe.io.Bytes.ofString(base)) ));
		return b.encodeString(s);
	}
	
	
	public static   java.lang.String decode(java.lang.String s, java.lang.String base)
	{
		haxe.crypto.BaseCode b = new haxe.crypto.BaseCode(((haxe.io.Bytes) (haxe.io.Bytes.ofString(base)) ));
		return b.decodeString(s);
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.crypto.BaseCode(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.crypto.BaseCode(((haxe.io.Bytes) (arr.__get(0)) ));
	}
	
	
	public  haxe.io.Bytes base;
	
	public  int nbits;
	
	public  haxe.root.Array<java.lang.Object> tbl;
	
	public   haxe.io.Bytes encodeBytes(haxe.io.Bytes b)
	{
		int nbits = this.nbits;
		haxe.io.Bytes base = this.base;
		int size = ( ( b.length * 8 ) / nbits );
		haxe.io.Bytes out = haxe.io.Bytes.alloc(( size + (( (( ( ( b.length * 8 ) % nbits ) == 0 )) ? (0) : (1) )) ));
		int buf = 0;
		int curbits = 0;
		int mask = ( (( 1 << nbits )) - 1 );
		int pin = 0;
		int pout = 0;
		while (( pout < size ))
		{
			while (( curbits < nbits ))
			{
				curbits += 8;
				buf <<= 8;
				{
					int pos = pin++;
					buf |= ( b.b[pos] & 255 );
				}
				
			}
			
			curbits -= nbits;
			{
				int pos1 = pout++;
				out.b[pos1] = ((byte) (( base.b[( ( buf >> curbits ) & mask )] & 255 )) );
			}
			
		}
		
		if (( curbits > 0 )) 
		{
			int pos2 = pout++;
			out.b[pos2] = ((byte) (( base.b[( ( buf << ( nbits - curbits ) ) & mask )] & 255 )) );
		}
		
		return out;
	}
	
	
	public   void initTable()
	{
		haxe.root.Array<java.lang.Object> tbl = new haxe.root.Array<java.lang.Object>();
		{
			int _g = 0;
			while (( _g < 256 ))
			{
				int i = _g++;
				tbl.__set(i, -1);
			}
			
		}
		
		{
			int _g1 = 0;
			int _g2 = this.base.length;
			while (( _g1 < _g2 ))
			{
				int i1 = _g1++;
				tbl.__set(( this.base.b[i1] & 255 ), i1);
			}
			
		}
		
		this.tbl = tbl;
	}
	
	
	public   haxe.io.Bytes decodeBytes(haxe.io.Bytes b)
	{
		int nbits = this.nbits;
		haxe.io.Bytes base = this.base;
		if (( this.tbl == null )) 
		{
			this.initTable();
		}
		
		haxe.root.Array<java.lang.Object> tbl = this.tbl;
		int size = ( ( b.length * nbits ) >> 3 );
		haxe.io.Bytes out = haxe.io.Bytes.alloc(size);
		int buf = 0;
		int curbits = 0;
		int pin = 0;
		int pout = 0;
		while (( pout < size ))
		{
			while (( curbits < 8 ))
			{
				curbits += nbits;
				buf <<= nbits;
				int __temp_stmt80962 = 0;
				{
					int pos = pin++;
					__temp_stmt80962 = ( b.b[pos] & 255 );
				}
				
				java.lang.Object __temp_stmt80961 = tbl.__get(__temp_stmt80962);
				int i = ((int) (haxe.lang.Runtime.toInt(__temp_stmt80961)) );
				if (( i == -1 )) 
				{
					throw haxe.lang.HaxeException.wrap("BaseCode : invalid encoded char");
				}
				
				buf |= i;
			}
			
			curbits -= 8;
			{
				int pos1 = pout++;
				out.b[pos1] = ((byte) (( ( buf >> curbits ) & 255 )) );
			}
			
		}
		
		return out;
	}
	
	
	public   java.lang.String encodeString(java.lang.String s)
	{
		return this.encodeBytes(haxe.io.Bytes.ofString(s)).toString();
	}
	
	
	public   java.lang.String decodeString(java.lang.String s)
	{
		return this.decodeBytes(haxe.io.Bytes.ofString(s)).toString();
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef80963 = true;
			switch (field.hashCode())
			{
				case 104611444:
				{
					if (field.equals("nbits")) 
					{
						__temp_executeDef80963 = false;
						this.nbits = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef80963) 
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
			boolean __temp_executeDef80964 = true;
			switch (field.hashCode())
			{
				case 114622:
				{
					if (field.equals("tbl")) 
					{
						__temp_executeDef80964 = false;
						this.tbl = ((haxe.root.Array<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3016401:
				{
					if (field.equals("base")) 
					{
						__temp_executeDef80964 = false;
						this.base = ((haxe.io.Bytes) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 104611444:
				{
					if (field.equals("nbits")) 
					{
						__temp_executeDef80964 = false;
						this.nbits = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef80964) 
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
			boolean __temp_executeDef80965 = true;
			switch (field.hashCode())
			{
				case -2001259617:
				{
					if (field.equals("decodeString")) 
					{
						__temp_executeDef80965 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("decodeString"))) );
					}
					
					break;
				}
				
				
				case 3016401:
				{
					if (field.equals("base")) 
					{
						__temp_executeDef80965 = false;
						return this.base;
					}
					
					break;
				}
				
				
				case -864869945:
				{
					if (field.equals("encodeString")) 
					{
						__temp_executeDef80965 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("encodeString"))) );
					}
					
					break;
				}
				
				
				case 104611444:
				{
					if (field.equals("nbits")) 
					{
						__temp_executeDef80965 = false;
						return this.nbits;
					}
					
					break;
				}
				
				
				case 1166820125:
				{
					if (field.equals("decodeBytes")) 
					{
						__temp_executeDef80965 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("decodeBytes"))) );
					}
					
					break;
				}
				
				
				case 114622:
				{
					if (field.equals("tbl")) 
					{
						__temp_executeDef80965 = false;
						return this.tbl;
					}
					
					break;
				}
				
				
				case -268398434:
				{
					if (field.equals("initTable")) 
					{
						__temp_executeDef80965 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("initTable"))) );
					}
					
					break;
				}
				
				
				case 1896214517:
				{
					if (field.equals("encodeBytes")) 
					{
						__temp_executeDef80965 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("encodeBytes"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef80965) 
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
			boolean __temp_executeDef80966 = true;
			switch (field.hashCode())
			{
				case 104611444:
				{
					if (field.equals("nbits")) 
					{
						__temp_executeDef80966 = false;
						return ((double) (this.nbits) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef80966) 
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
			boolean __temp_executeDef80967 = true;
			switch (field.hashCode())
			{
				case -2001259617:
				{
					if (field.equals("decodeString")) 
					{
						__temp_executeDef80967 = false;
						return this.decodeString(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case 1896214517:
				{
					if (field.equals("encodeBytes")) 
					{
						__temp_executeDef80967 = false;
						return this.encodeBytes(((haxe.io.Bytes) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -864869945:
				{
					if (field.equals("encodeString")) 
					{
						__temp_executeDef80967 = false;
						return this.encodeString(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case -268398434:
				{
					if (field.equals("initTable")) 
					{
						__temp_executeDef80967 = false;
						this.initTable();
					}
					
					break;
				}
				
				
				case 1166820125:
				{
					if (field.equals("decodeBytes")) 
					{
						__temp_executeDef80967 = false;
						return this.decodeBytes(((haxe.io.Bytes) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef80967) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("tbl");
		baseArr.push("nbits");
		baseArr.push("base");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}

