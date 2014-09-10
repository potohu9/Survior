package function;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import openGLES20.GLES;
import openGLES20.GLES.ShaderMode;

import android.opengl.GLES20;

public class Graphics {
	public enum BlendMode{
		ALPHA,				// �A���t�@
		ADDITION,			// ���Z
		SUBTRACTION,		// ���Z
		MULTIPLICATION,		// ��Z
		INVERSION,			// ���]
		SCREEN,				// �X�N���[��
		EX_OR,				//�@�r���I�_���a
	};
	
	private static class NumbersUV {
		private static float[][] uvs;
		
		static{
			uvs = new float[10][8];
			for(int i=0;i<10;i++){
				uvs[i] = new float[]{
					(i%4)*0.25f,((float)Math.floor(i/4))*0.25f,
					(i%4)*0.25f,((float)Math.floor(i/4)+1)*0.25f,
					(i%4+1)*0.25f,((float)Math.floor(i/4))*0.25f,
					(i%4+1)*0.25f,((float)Math.floor(i/4)+1)*0.25f,
				};
			}
		}
		public static float[] getUV(int num)		{return uvs[num];}
	}
	static float[] vertex;
	static float[] uv;
	static byte[] index;
	static ColorData colorData;
	
	static{
		vertex = new float[]{
				-1, 1,0,
				 1, 1,0,
				-1,-1,0,
				 1,-1,0,
			};
			
		uv = new float[]{
			0,0,
			1,0,
			0,1,
			1,1,
		};
		
		index = new byte[]{
			0,1,2,
			1,2,3,
		};
		
		colorData = new ColorData();
		colorData.setARGB(1,1,1,1);
	}
	
	/*����:��������*/
	/*���������̃Z�b�g*/
	public static void setBlend(BlendMode blend){
		GLES20.glBlendEquation(GLES20.GL_FUNC_ADD);
		GLES20.glEnable(GLES20.GL_BLEND);
		switch(blend){
		case ADDITION:
			GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA,GLES20.GL_ONE);
			break;
		case ALPHA:
			GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA,GLES20.GL_ONE_MINUS_SRC_ALPHA);
			break;
		case EX_OR:
			GLES20.glBlendFunc(GLES20.GL_ONE_MINUS_DST_COLOR,GLES20.GL_ONE_MINUS_SRC_COLOR);
			break;
		case INVERSION:
			GLES20.glBlendFunc(GLES20.GL_ONE_MINUS_DST_COLOR,GLES20.GL_ZERO);
			break;
		case MULTIPLICATION:
			GLES20.glBlendFunc(GLES20.GL_ZERO,GLES20.GL_SRC_COLOR);
			break;
		case SCREEN:
			GLES20.glBlendFunc(GLES20.GL_ONE_MINUS_DST_COLOR,GLES20.GL_ONE);
			break;
		case SUBTRACTION:
			GLES20.glBlendEquation(GLES20.GL_FUNC_REVERSE_SUBTRACT);
			GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA,GLES20.GL_ONE);
			break;
		}
	}
	
	/*����:�o�C�g�^�z��*/
	/*�߂�l:�o�C�g�o�b�t�@*/
	/*�o�C�g�o�b�t�@�ϊ�*/
	private static ByteBuffer makeByteBuffer(byte[] array){
		ByteBuffer bb = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		bb.put(array).position(0);
		return bb;
	}

	/*�߂�l:UV�o�b�t�@*/
	/*�f�t�H���gUV�o�b�t�@����*/
	public static FloatBuffer makeDefaultUVBuffer(){
		float[] uvs = {
			0.0f,	0.0f,
			0.0f,	1.0f,
			1.0f,	0.0f,
			1.0f,	1.0f,
		};
		return Utile.makeFloatBuffer(uvs);
	}
	
	/*����:�\���ʒu�̒��S�_�A���S�_����̒����A�p�x*/
	/*�߂�l:�������ꂽ�t���[�g�o�b�t�@*/
	/*�t���[�g�o�b�t�@����*/
	private static FloatBuffer makeVertexBuffer(Vector2D center,float length,int angle){
		Vector2D topLeft = Utile.rotatePoint(135+angle,length,center);
		Vector2D topRight = Utile.rotatePoint(45+angle,length,center);
		Vector2D underLeft = Utile.rotatePoint(225+angle,length,center);
		Vector2D underRight = Utile.rotatePoint(315+angle,length,center);
		
		topLeft = Utile.translatScreenPoint(topLeft);
		topRight = Utile.translatScreenPoint(topRight);
		underLeft = Utile.translatScreenPoint(underLeft);
		underRight = Utile.translatScreenPoint(underRight);
		
		float[] vertex = {
			topLeft.x,topLeft.y,0.0f,
			topRight.x,topRight.y,0.0f,
			underLeft.x,underLeft.y,0.0f,
			underRight.x,underRight.y,0.0f,
		};

		return Utile.makeFloatBuffer(vertex);
	}
	
	/*����:�e���_���W�A���S�_����̒����A�p�x*/
	/*�߂�l:�������ꂽ�t���[�g�o�b�t�@*/
	/*�t���[�g�o�b�t�@����*/
	private static FloatBuffer makeVertexBuffer(Vector2D topLeft,Vector2D topRight,Vector2D underLeft,Vector2D underRight,int angle){
		topLeft = Utile.translatScreenPoint(topLeft);
		topRight = Utile.translatScreenPoint(topRight);
		underLeft = Utile.translatScreenPoint(underLeft);
		underRight = Utile.translatScreenPoint(underRight);
		
		float[] vertex = {
			topLeft.x,topLeft.y,0.0f,
			topRight.x,topRight.y,0.0f,
			underLeft.x,underLeft.y,0.0f,
			underRight.x,underRight.y,0.0f,
		};

		return Utile.makeFloatBuffer(vertex);
	}
	
	/*����:�e���_�A�p�x�A�\������e�N�X�`����ID�A��������*/
	/*�e�N�X�`���̕\��*/
	public static void drawTexture(Vector2D topLeft,Vector2D topRight,Vector2D underLeft,Vector2D underRight,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// ���_�o�b�t�@
		FloatBuffer uvBuffer;		// UV�o�b�t�@
		ByteBuffer indexBuffer;
		
		// �e�N�X�`���̗L����
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// �C���f�b�N�X�o�b�t�@�̎w��
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(topLeft,topRight,underLeft,underRight,angle);
		
		Graphics.setBlend(blendMode);
		
		//�@�e�N�X�`���̎w��
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UV�o�b�t�@�̎w��
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,1.0f,1.0f,1.0f,1.0f);
		
		// �l�p�`�̕`��
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*����:�e���_�A���S�_����̒����A�p�x�A�\������e�N�X�`����ID�A��������*/
	/*�e�N�X�`���̕\��*/
	public static void drawTexture(Vector2D topLeft,Vector2D topRight,Vector2D underLeft,Vector2D underRight,ColorData color,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// ���_�o�b�t�@
		FloatBuffer uvBuffer;		// UV�o�b�t�@
		ByteBuffer indexBuffer;
		
		// �e�N�X�`���̗L����
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// �C���f�b�N�X�o�b�t�@�̎w��
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(topLeft,topRight,underLeft,underRight,angle);
		
		Graphics.setBlend(blendMode);
		
		//�@�e�N�X�`���̎w��
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UV�o�b�t�@�̎w��
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,color.getR(),color.getG(),color.getB(),color.getA());
		
		// �l�p�`�̕`��
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*����:�\���ʒu�̒��S�_�A���S�_����̒����A�p�x�A�\������e�N�X�`����ID�A��������*/
	/*�e�N�X�`���̕\��*/
	public static void drawTexture(Vector2D drawPoint,float scale,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// ���_�o�b�t�@
		FloatBuffer uvBuffer;		// UV�o�b�t�@
		ByteBuffer indexBuffer;
		
		// �e�N�X�`���̗L����
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// �C���f�b�N�X�o�b�t�@�̎w��
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(drawPoint,scale*2,angle);
		
		Graphics.setBlend(blendMode);
		
		//�@�e�N�X�`���̎w��
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UV�o�b�t�@�̎w��
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,1.0f,1.0f,1.0f,1.0f);
		
		// �l�p�`�̕`��
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*����:�\���ʒu�̒��S�_�A���S�_����̒����A�p�x�A�\������e�N�X�`����ID�A��������*/
	/*�e�N�X�`���̕\��*/
	public static void drawTexture(Vector2D drawPoint,ColorData color,float scale,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// ���_�o�b�t�@
		FloatBuffer uvBuffer;		// UV�o�b�t�@
		ByteBuffer indexBuffer;
		
		// �e�N�X�`���̗L����
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// �C���f�b�N�X�o�b�t�@�̎w��
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(drawPoint,scale*2,angle);
		
		Graphics.setBlend(blendMode);
		
		//�@�e�N�X�`���̎w��
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UV�o�b�t�@�̎w��
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,color.getR(),color.getG(),color.getB(),color.getA());
		
		// �l�p�`�̕`��
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*����:�\���ʒu�̒��S�_�A���S�_����̒����AUV�A�p�x�A�\������e�N�X�`����ID�A��������*/
	/*�e�N�X�`���̕\��*/
	public static void drawTexture(Vector2D drawPoint,ColorData color,float scale,float[] uv,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// ���_�o�b�t�@
		FloatBuffer uvBuffer;		// UV�o�b�t�@
		ByteBuffer indexBuffer;
		
		// �e�N�X�`���̗L����
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = Utile.makeFloatBuffer(uv);
				
		// �C���f�b�N�X�o�b�t�@�̎w��
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(drawPoint,scale*2,angle);
		
		Graphics.setBlend(blendMode);
		
		//�@�e�N�X�`���̎w��
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UV�o�b�t�@�̎w��
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,color.getR(),color.getG(),color.getB(),color.getA());
		
		// �l�p�`�̕`��
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*����:���_�f�[�^�AUV�f�[�^�A�C���f�b�N�X�f�[�^�A�\������e�N�X�`����ID�A��������*/
	/*�e�N�X�`���̕\��*/
	public static void drawTexture(float[] vertex,float[] uv,byte[] index,ColorData colorData,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// ���_�o�b�t�@
		FloatBuffer uvBuffer;		// UV�o�b�t�@
		ByteBuffer indexBuffer;		// �C���f�b�N�X�o�b�t�@

		// �e�N�X�`���̗L����
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = Utile.makeFloatBuffer(uv);
		
		indexBuffer = makeByteBuffer(index);
		
		vertexBuffer = Utile.makeFloatBuffer(vertex);
		
		Graphics.setBlend(blendMode);
		
		//�@�e�N�X�`���̎w��
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UV�o�b�t�@�̎w��
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);

		GLES20.glUniform4f(GLES._2DHandle.color,colorData.getR(),colorData.getG(),colorData.getB(),colorData.getA());
		
		// �l�p�`�̕`��
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,index.length,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*����:�\������e�N�X�`����ID�A��������*/
	/*��ʑS�̂ɕ\��*/
	public static void drawTexture(int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		Graphics.drawTexture(vertex,uv,index,colorData,textureID,blendMode);
	}
	
	/*����:�\������e�N�X�`����ID�A�F�A��������*/
	/*��ʑS�̂ɕ\��*/
	public static void drawTexture(ColorData colorData,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		Graphics.drawTexture(vertex,uv,index,colorData,textureID,blendMode);
	}
	
	public static void drawNumbers(Vector2D drawPos,float scale,int dispNum,BlendMode blendMode){
		int figure = String.valueOf(dispNum).length();	// �������Z�o
		for(int j=0;j<figure;j++){
			int nowFigureNum = dispNum % 10;
			dispNum = dispNum / 10;
			Vector2D drawPoint = drawPos.getClone();
			drawPoint.x -= j * scale / 2;
			drawPoint.x += (figure - j - 1) * scale / 2;
			Graphics.drawTexture(drawPoint,colorData,scale,NumbersUV.getUV(nowFigureNum),0,TextureManager.getTexture("Numbers"),blendMode);
		}
	}
	
	public static void drawNumbers(Vector2D drawPos,ColorData color,float scale,int dispNum,BlendMode blendMode){
		int figure = String.valueOf(dispNum).length();	// �������Z�o
		for(int j=0;j<figure;j++){
			int nowFigureNum = dispNum % 10;
			dispNum = dispNum / 10;
			Vector2D drawPoint = drawPos.getClone();
			drawPoint.x -= j * scale / 2;
			drawPoint.x += (figure - j - 1) * scale / 2;
			Graphics.drawTexture(drawPoint,color,scale,NumbersUV.getUV(nowFigureNum),0,TextureManager.getTexture("Numbers"),blendMode);
		}
	}
}
