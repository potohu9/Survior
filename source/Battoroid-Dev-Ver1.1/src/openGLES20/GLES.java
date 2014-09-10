package openGLES20;

import android.opengl.GLES20;

public class GLES {
	public enum ShaderMode{
		_3D,
		_2D,
		_0D,
		INIT,
	};
	
	private static ShaderMode nowUseShader;
	
	// �V�X�e��
	private static int _3DShaderProgram;
	private static int _2DShaderProgram;
	private static int _0DShaderProgram;
	
	// 3D�n���h��
	public static class _3DHandle{
		public static int diffeuseLightColor;		// �g�U���ˌ��F
		public static int diffeuseLightDirection;	// �g�U���ˌ��̕���
		public static int ambientLight;				// �����F
	
		public static int position;					// ���_���W�n���h��
		public static int normal;					// �@���n���h��
		public static int vertexColor;				// ���_�F�n���h��
		public static int modelMatrix;				// ���f���}�g���b�N�X�n���h��
		public static int modelNormalMatrix;		// ���f���}�g���b�N�X�n���h���̋t�]�u�s��
	
		public static int viewMatrix;				// �r���[�s��n���h��
		public static int projectionMatrix;			// �ˉe�s��n���h��
		public static int UV;
		public static int texture;
	}
	
	
	// 2D�n���h��
	public static class _2DHandle{
		public static int position;					// �ʒu�n���h��    
		public static int UV;						// UV�n���h�� 
		public static int texture;					// �e�N�X�`���n���h��
		public static int color;					// �F�n���h��
	}
	
	
	// �_�`��n���h��
	public static class _0DHandle{
		public static int position;					// �ʒu�n���h��    
		public static int texture;					// �e�N�X�`���n���h��
		public static int size;						// �e�N�X�`���n���h��
		public static int color;					// �F�n���h��
	}
	
	private static void make3DShaderProgram(){
		int vertexShader   = loadShader(GLES20.GL_VERTEX_SHADER,Shader._3DShader.VERTEX_CODE);
		int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,Shader._3DShader.FRAGMENT_CODE);
		
		_3DShaderProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(_3DShaderProgram, vertexShader);
		GLES20.glAttachShader(_3DShaderProgram, fragmentShader);
		GLES20.glLinkProgram(_3DShaderProgram);

		_3DHandle.position         			= GLES20.glGetAttribLocation	(_3DShaderProgram,"a_Position");
		_3DHandle.vertexColor       		= GLES20.glGetAttribLocation	(_3DShaderProgram,"a_VertexColor");
		_3DHandle.viewMatrix        		= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_ViewMatrix");
		_3DHandle.projectionMatrix  		= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_ProjectionMatrix");
		_3DHandle.modelMatrix       		= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_ModelMatrix");
		_3DHandle.modelNormalMatrix 		= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_NormalModelMatrix");
		_3DHandle.ambientLight				= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_AmbientLight");
		_3DHandle.normal          			= GLES20.glGetAttribLocation	(_3DShaderProgram,"a_Normal");	
		_3DHandle.diffeuseLightColor		= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_DiffeuseLightColor");
		_3DHandle.diffeuseLightDirection	= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_DiffeuseLightDirection");
		_3DHandle.UV						= GLES20.glGetAttribLocation	(_3DShaderProgram,"a_UV");
		_3DHandle.texture					= GLES20.glGetUniformLocation	(_3DShaderProgram,"u_Textuer");
		
		GLES20.glEnableVertexAttribArray(_3DHandle.position);
     	GLES20.glEnableVertexAttribArray(_3DHandle.normal);
     	GLES20.glEnableVertexAttribArray(_3DHandle.vertexColor);
     	GLES20.glEnableVertexAttribArray(_3DHandle.UV);
	}
	
	private static void make2DShaderProgram(){
		int vertexShader   = loadShader(GLES20.GL_VERTEX_SHADER,Shader._2DShader.VERTEX_CODE);
		int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,Shader._2DShader.FRAGMENT_CODE);
		
		_2DShaderProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(_2DShaderProgram, vertexShader);
		GLES20.glAttachShader(_2DShaderProgram, fragmentShader);
		GLES20.glLinkProgram(_2DShaderProgram);
		
		_2DHandle.position = GLES20.glGetAttribLocation(_2DShaderProgram,"a_Position");
		_2DHandle.UV = GLES20.glGetAttribLocation(_2DShaderProgram,"a_UV");
		_2DHandle.texture = GLES20.glGetUniformLocation(_2DShaderProgram,"u_Textuer");
		_2DHandle.color = GLES20.glGetUniformLocation(_2DShaderProgram,"u_Color");

     	GLES20.glEnableVertexAttribArray(_2DHandle.position);
     	GLES20.glEnableVertexAttribArray(_2DHandle.UV);
	}
	
	private static void make0DShaderProgram(){
		int vertexShader   = loadShader(GLES20.GL_VERTEX_SHADER,Shader._0DShader.VERTEX_CODE);
		int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,Shader._0DShader.FRAGMENT_CODE);
		
		_0DShaderProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(_0DShaderProgram,vertexShader);
		GLES20.glAttachShader(_0DShaderProgram,fragmentShader);
		GLES20.glLinkProgram(_0DShaderProgram);
		
		_0DHandle.position = GLES20.glGetAttribLocation(_0DShaderProgram,"a_Position");
		_0DHandle.size = GLES20.glGetAttribLocation(_0DShaderProgram,"a_PointSize");
		_0DHandle.texture = GLES20.glGetUniformLocation(_0DShaderProgram,"u_Textuer");
		_0DHandle.color = GLES20.glGetAttribLocation(_0DShaderProgram,"a_Color");
		
     	GLES20.glEnableVertexAttribArray(_0DHandle.position);
    	GLES20.glEnableVertexAttribArray(_0DHandle.size);
     	GLES20.glEnableVertexAttribArray(_0DHandle.color);
	}
	
	public static void makeProgram() {
		nowUseShader = ShaderMode.INIT;
		make3DShaderProgram();
		make2DShaderProgram();
		make0DShaderProgram();
		changeUseShader(ShaderMode._3D);
	}
	
	public static void changeUseShader(ShaderMode shaderMode){
		if(nowUseShader == shaderMode){
			return;
		}
		switch(shaderMode){
		case _3D:
			GLES20.glUseProgram(_3DShaderProgram);
			break;
		case _2D:
			GLES20.glUseProgram(_2DShaderProgram);
			break;
		case _0D:
			GLES20.glUseProgram(_0DShaderProgram);
			break;
		case INIT:
		default:
			return;
		}
		nowUseShader = shaderMode;
	}

	private static int loadShader(int type, String shaderCode) {
		int shader = GLES20.glCreateShader(type);
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);

	    int[] compiled = new int[1];
	    GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
	    if (compiled[0] != GLES20.GL_TRUE) {
	    	String error = GLES20.glGetShaderInfoLog(shader);
	    	throw new RuntimeException("failed to compile shader: " + error);
	    }
		return shader;
	}
	
}
