package openGLES20;

public class Shader{
	
	// 3D�p�V�F�[�_
	public class _3DShader{
		// ���_�V�F�[�_�̃R�[�h
		public final static String VERTEX_CODE =
			"attribute vec4 a_Position;" +
			"attribute vec4 a_VertexColor;" +
			"uniform   mat4 u_ViewMatrix;" +
			"uniform   mat4 u_ProjectionMatrix;" +
			"uniform   vec3 u_AmbientLight;"+
			"attribute vec4 a_Normal;" +
			"uniform   vec3 u_DiffeuseLightColor;"+
			"uniform   vec3 u_DiffeuseLightDirection;"+
			"varying   vec4 v_Color;" +
			"uniform   mat4 u_ModelMatrix;"+
			"uniform   mat4 u_NormalModelMatrix;"+
			"attribute vec2 a_UV;"+
	        "varying vec2 v_UV;"+
			"void main() {" +
			"	v_UV = a_UV;"+
			"	gl_Position = u_ProjectionMatrix * u_ViewMatrix * u_ModelMatrix * a_Position;" +
			"	vec3 ambient = u_AmbientLight * a_VertexColor.rgb;"+
			"	vec3 normal = normalize(vec3(u_NormalModelMatrix * a_Normal));"+
			"	float nDotL = max(dot(normalize(u_DiffeuseLightDirection),normal),0.0);"+
			"	vec3 diffeuse = u_DiffeuseLightColor * a_VertexColor.rgb * nDotL;"+
			"	vec3 color = ambient + diffeuse;"+
			"	v_Color = vec4(color,a_VertexColor.a);" +
			"}";

		
		// �t���O�����g�V�F�[�_�̃R�[�h
		public final static String FRAGMENT_CODE =
			"precision mediump float;" +
			"varying vec2 v_UV;"+
			"uniform sampler2D u_Texture;"+
			"varying vec4 v_Color;" +
			"void main() {" +
			"	gl_FragColor = texture2D(u_Texture,v_UV) * v_Color;"+
			"}";
	}
	
	// 2D�p�V�F�[�_
	public class _2DShader{
		// ���_�V�F�[�_�̃R�[�h
		public final static String VERTEX_CODE =
			"attribute vec4 a_Position;"+
	        "attribute vec2 a_UV;"+
	        "varying vec2 v_UV;"+
	        "void main(){"+
	        "	gl_Position = a_Position;"+
	        "	v_UV = a_UV;"+
	        "}";
		
		// �t���O�����g�V�F�[�_�̃R�[�h
		public final static String FRAGMENT_CODE =
			"precision mediump float;"+
	        "varying vec2 v_UV;"+
	        "uniform sampler2D u_Texture;"+
	        "uniform vec4 u_Color;"+
	        "void main(){"+
	        "	gl_FragColor = texture2D(u_Texture,v_UV) * u_Color;"+
	        "}";
	}
	
	// �_�`��p�V�F�[�_
	public class _0DShader{
		// ���_�V�F�[�_�̃R�[�h
		public final static String VERTEX_CODE =
			"attribute vec4 a_Position;"+
	        "attribute float a_PointSize;"+
	        "attribute vec4 a_Color;"+
	        "varying vec4 v_Color;"+ 
	        "void main(){"+
	        "	gl_Position = a_Position;"+
	        "	gl_PointSize = a_PointSize;"+
	        "	v_Color = a_Color;"+
	        "}";
		// �t���O�����g�V�F�[�_�̃R�[�h
		public final static String FRAGMENT_CODE =
			"precision mediump float;"+
	        "uniform sampler2D u_Texture;"+
	        "varying vec4 v_Color;"+ 
	        "void main(){"+     	
	        "	gl_FragColor = texture2D(u_Texture,gl_PointCoord) * v_Color;"+
	        "}";
	}
}
