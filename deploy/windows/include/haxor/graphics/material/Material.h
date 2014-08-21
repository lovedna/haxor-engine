#ifndef INCLUDED_haxor_graphics_material_Material
#define INCLUDED_haxor_graphics_material_Material

#ifndef HXCPP_H
#include <hxcpp.h>
#endif

#include <haxor/core/Resource.h>
HX_DECLARE_CLASS2(haxor,core,IDisposable)
HX_DECLARE_CLASS2(haxor,core,Resource)
HX_DECLARE_CLASS3(haxor,graphics,material,Material)
HX_DECLARE_CLASS3(haxor,graphics,material,MaterialUniform)
HX_DECLARE_CLASS3(haxor,graphics,material,Shader)
HX_DECLARE_CLASS3(haxor,graphics,texture,Texture)
namespace haxor{
namespace graphics{
namespace material{


class HXCPP_CLASS_ATTRIBUTES  Material_obj : public ::haxor::core::Resource_obj{
	public:
		typedef ::haxor::core::Resource_obj super;
		typedef Material_obj OBJ_;
		Material_obj();
		Void __construct(::String p_name);

	public:
		inline void *operator new( size_t inSize, bool inContainer=true)
			{ return hx::Object::operator new(inSize,inContainer); }
		static hx::ObjectPtr< Material_obj > __new(::String p_name);
		static Dynamic __CreateEmpty();
		static Dynamic __Create(hx::DynamicArray inArgs);
		//~Material_obj();

		HX_DO_RTTI;
		static void __boot();
		static void __register();
		void __Mark(HX_MARK_PARAMS);
		void __Visit(HX_VISIT_PARAMS);
		::String __ToString() const { return HX_CSTRING("Material"); }

		bool ztest;
		int zfunc;
		bool zwrite;
		bool blend;
		int queue;
		int blendSrc;
		int blendDst;
		bool invert;
		int cull;
		bool lighting;
		bool grab;
		virtual ::haxor::graphics::material::Shader get_shader( );
		Dynamic get_shader_dyn();

		virtual ::haxor::graphics::material::Shader set_shader( ::haxor::graphics::material::Shader v);
		Dynamic set_shader_dyn();

		::haxor::graphics::material::Shader m_shader;
		Array< ::Dynamic > m_uniforms;
		virtual Void SetBlending( int p_src,int p_dst);
		Dynamic SetBlending_dyn();

		virtual Void SetTexture( ::String p_name,::haxor::graphics::texture::Texture p_texture);
		Dynamic SetTexture_dyn();

		virtual Void SetFloat( ::String p_name,Float p_v);
		Dynamic SetFloat_dyn();

		virtual Void SetFloat4( ::String p_name,Float p_x,Float p_y,Float p_z,Float p_w);
		Dynamic SetFloat4_dyn();

		virtual Void SetFloat3( ::String p_name,Float p_x,Float p_y,Float p_z);
		Dynamic SetFloat3_dyn();

		virtual Void SetFloat2( ::String p_name,Float p_x,Float p_y);
		Dynamic SetFloat2_dyn();

		virtual Void SetFloatArray( ::String p_name,Array< Float > p_list);
		Dynamic SetFloatArray_dyn();

		virtual Void SetFloat2Array( ::String p_name,Array< Float > p_list);
		Dynamic SetFloat2Array_dyn();

		virtual Void SetFloat3Array( ::String p_name,Array< Float > p_list);
		Dynamic SetFloat3Array_dyn();

		virtual Void SetFloat4Array( ::String p_name,Array< Float > p_list);
		Dynamic SetFloat4Array_dyn();

		virtual Void SetInt( ::String p_name,int p_v);
		Dynamic SetInt_dyn();

		virtual Void SetInt4( ::String p_name,int p_x,int p_y,int p_z,int p_w);
		Dynamic SetInt4_dyn();

		virtual Void SetInt3( ::String p_name,int p_x,int p_y,int p_z);
		Dynamic SetInt3_dyn();

		virtual Void SetInt2( ::String p_name,int p_x,int p_y);
		Dynamic SetInt2_dyn();

		virtual Void SetIntArray( ::String p_name,Array< int > p_list);
		Dynamic SetIntArray_dyn();

		virtual Void SetInt2Array( ::String p_name,Array< int > p_list);
		Dynamic SetInt2Array_dyn();

		virtual Void SetInt3Array( ::String p_name,Array< int > p_list);
		Dynamic SetInt3Array_dyn();

		virtual Void SetInt4Array( ::String p_name,Array< int > p_list);
		Dynamic SetInt4Array_dyn();

		virtual Void RemoveUniform( ::String p_name);
		Dynamic RemoveUniform_dyn();

		virtual ::haxor::graphics::material::MaterialUniform FetchUniform( ::String p_name,bool p_is_float,int p_length,int p_offset,bool p_create);
		Dynamic FetchUniform_dyn();

		virtual Void OnDestroy( );

};

} // end namespace haxor
} // end namespace graphics
} // end namespace material

#endif /* INCLUDED_haxor_graphics_material_Material */ 