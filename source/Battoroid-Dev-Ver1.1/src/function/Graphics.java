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
		ALPHA,				// アルファ
		ADDITION,			// 加算
		SUBTRACTION,		// 減算
		MULTIPLICATION,		// 乗算
		INVERSION,			// 反転
		SCREEN,				// スクリーン
		EX_OR,				//　排他的論理和
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
	
	/*引数:合成方式*/
	/*合成方式のセット*/
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
	
	/*引数:バイト型配列*/
	/*戻り値:バイトバッファ*/
	/*バイトバッファ変換*/
	private static ByteBuffer makeByteBuffer(byte[] array){
		ByteBuffer bb = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		bb.put(array).position(0);
		return bb;
	}

	/*戻り値:UVバッファ*/
	/*デフォルトUVバッファ生成*/
	public static FloatBuffer makeDefaultUVBuffer(){
		float[] uvs = {
			0.0f,	0.0f,
			0.0f,	1.0f,
			1.0f,	0.0f,
			1.0f,	1.0f,
		};
		return Utile.makeFloatBuffer(uvs);
	}
	
	/*引数:表示位置の中心点、中心点からの長さ、角度*/
	/*戻り値:生成されたフロートバッファ*/
	/*フロートバッファ生成*/
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
	
	/*引数:各頂点座標、中心点からの長さ、角度*/
	/*戻り値:生成されたフロートバッファ*/
	/*フロートバッファ生成*/
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
	
	/*引数:各頂点、角度、表示するテクスチャのID、合成方式*/
	/*テクスチャの表示*/
	public static void drawTexture(Vector2D topLeft,Vector2D topRight,Vector2D underLeft,Vector2D underRight,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// 頂点バッファ
		FloatBuffer uvBuffer;		// UVバッファ
		ByteBuffer indexBuffer;
		
		// テクスチャの有効化
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// インデックスバッファの指定
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(topLeft,topRight,underLeft,underRight,angle);
		
		Graphics.setBlend(blendMode);
		
		//　テクスチャの指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UVバッファの指定
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,1.0f,1.0f,1.0f,1.0f);
		
		// 四角形の描画
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*引数:各頂点、中心点からの長さ、角度、表示するテクスチャのID、合成方式*/
	/*テクスチャの表示*/
	public static void drawTexture(Vector2D topLeft,Vector2D topRight,Vector2D underLeft,Vector2D underRight,ColorData color,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// 頂点バッファ
		FloatBuffer uvBuffer;		// UVバッファ
		ByteBuffer indexBuffer;
		
		// テクスチャの有効化
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// インデックスバッファの指定
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(topLeft,topRight,underLeft,underRight,angle);
		
		Graphics.setBlend(blendMode);
		
		//　テクスチャの指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UVバッファの指定
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,color.getR(),color.getG(),color.getB(),color.getA());
		
		// 四角形の描画
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*引数:表示位置の中心点、中心点からの長さ、角度、表示するテクスチャのID、合成方式*/
	/*テクスチャの表示*/
	public static void drawTexture(Vector2D drawPoint,float scale,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// 頂点バッファ
		FloatBuffer uvBuffer;		// UVバッファ
		ByteBuffer indexBuffer;
		
		// テクスチャの有効化
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// インデックスバッファの指定
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(drawPoint,scale*2,angle);
		
		Graphics.setBlend(blendMode);
		
		//　テクスチャの指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UVバッファの指定
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,1.0f,1.0f,1.0f,1.0f);
		
		// 四角形の描画
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*引数:表示位置の中心点、中心点からの長さ、角度、表示するテクスチャのID、合成方式*/
	/*テクスチャの表示*/
	public static void drawTexture(Vector2D drawPoint,ColorData color,float scale,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// 頂点バッファ
		FloatBuffer uvBuffer;		// UVバッファ
		ByteBuffer indexBuffer;
		
		// テクスチャの有効化
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = makeDefaultUVBuffer();
				
		// インデックスバッファの指定
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(drawPoint,scale*2,angle);
		
		Graphics.setBlend(blendMode);
		
		//　テクスチャの指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UVバッファの指定
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,color.getR(),color.getG(),color.getB(),color.getA());
		
		// 四角形の描画
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*引数:表示位置の中心点、中心点からの長さ、UV、角度、表示するテクスチャのID、合成方式*/
	/*テクスチャの表示*/
	public static void drawTexture(Vector2D drawPoint,ColorData color,float scale,float[] uv,int angle,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// 頂点バッファ
		FloatBuffer uvBuffer;		// UVバッファ
		ByteBuffer indexBuffer;
		
		// テクスチャの有効化
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = Utile.makeFloatBuffer(uv);
				
		// インデックスバッファの指定
		byte[] indexs= {0,1,2, 1,2,3};
		indexBuffer = makeByteBuffer(indexs);
		
		vertexBuffer = makeVertexBuffer(drawPoint,scale*2,angle);
		
		Graphics.setBlend(blendMode);
		
		//　テクスチャの指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UVバッファの指定
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);
		
		GLES20.glUniform4f(GLES._2DHandle.color,color.getR(),color.getG(),color.getB(),color.getA());
		
		// 四角形の描画
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*引数:頂点データ、UVデータ、インデックスデータ、表示するテクスチャのID、合成方式*/
	/*テクスチャの表示*/
	public static void drawTexture(float[] vertex,float[] uv,byte[] index,ColorData colorData,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		
		FloatBuffer vertexBuffer;	// 頂点バッファ
		FloatBuffer uvBuffer;		// UVバッファ
		ByteBuffer indexBuffer;		// インデックスバッファ

		// テクスチャの有効化
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);

		uvBuffer = Utile.makeFloatBuffer(uv);
		
		indexBuffer = makeByteBuffer(index);
		
		vertexBuffer = Utile.makeFloatBuffer(vertex);
		
		Graphics.setBlend(blendMode);
		
		//　テクスチャの指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._2DHandle.texture,0);
		
		// UVバッファの指定
		GLES20.glVertexAttribPointer(GLES._2DHandle.UV,2,GLES20.GL_FLOAT,false,0,uvBuffer);

		GLES20.glUniform4f(GLES._2DHandle.color,colorData.getR(),colorData.getG(),colorData.getB(),colorData.getA());
		
		// 四角形の描画
		GLES20.glVertexAttribPointer(GLES._2DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		
		GLES20.glDrawElements(GL10.GL_TRIANGLES,index.length,GLES20.GL_UNSIGNED_BYTE,indexBuffer);
	}
	
	/*引数:表示するテクスチャのID、合成方式*/
	/*画面全体に表示*/
	public static void drawTexture(int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		Graphics.drawTexture(vertex,uv,index,colorData,textureID,blendMode);
	}
	
	/*引数:表示するテクスチャのID、色、合成方式*/
	/*画面全体に表示*/
	public static void drawTexture(ColorData colorData,int textureID,BlendMode blendMode){
		GLES.changeUseShader(ShaderMode._2D);
		Graphics.drawTexture(vertex,uv,index,colorData,textureID,blendMode);
	}
	
	public static void drawNumbers(Vector2D drawPos,float scale,int dispNum,BlendMode blendMode){
		int figure = String.valueOf(dispNum).length();	// 桁数を算出
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
		int figure = String.valueOf(dispNum).length();	// 桁数を算出
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
