package function;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;

import openGLES20.Common;

public class Utile {
	
	/*引数:float配列*/
	/*戻り値:float配列をFloatBuffer型に変換した値*/
	/*float配列をFloatBufferに変換*/
	public static FloatBuffer makeFloatBuffer(float[] array){
		FloatBuffer fb = ByteBuffer.allocateDirect(array.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		fb.put(array).position(0);
		return fb;
	}
	
	/*引数:位置ベクトル*/
	/*戻り値:位置ベクトルをビュー座標に変換*/
	/*ビュー座標に変更*/
	public static Vector2D translatScreenPoint(Vector2D point){
		Vector2D screenPoint = new Vector2D();
		screenPoint.x = (point.x/Common.getScreenProportionW())*2.0f-1.0f;
		screenPoint.y = ((point.y/Common.getScreenProportionH())*2.0f-1.0f)*-1;
		return screenPoint;
	}
	
	/*引数:角度、長さ、位置ベクトル*/
	/*戻り値:位置ベクトルから長さ分の位置にある位置ベクトルBを、位置ベクトルを中心として角度分点回転させた後の位置ベクトルB*/
	/*点回転*/
	public static Vector2D rotatePoint(double angle,float length,Vector2D point){
		Vector2D rotatedPoint = new Vector2D();
		Vector2D rotaryFront = new Vector2D(0,length);
		double radian = angle / 180 * Math.PI;
		rotatedPoint.x = (float)(rotaryFront.x*Math.cos(radian)-rotaryFront.y*Math.sin(radian)+point.x);
		rotatedPoint.y = (float)(rotaryFront.x*Math.sin(radian)+rotaryFront.y*Math.cos(radian)+point.y);
		
		return rotatedPoint;
	}
	
	/*引数:ファイル名*/
	/*戻り値:指定のファイル内の文字列*/
	/*ファイル読み込み*/
	public static String readFile(String fileName) {  
        String str = null;  
        byte[] data = readBinaryFile(Common.getContext(),fileName);  
        if (data != null) {  
            str = new String(data);  
        } 
        return str;
    }
	
	private static byte[] readBinaryFile(Context context,String fileName){
        if(!(new File(fileName).exists())){  
            return null;
        }  
  
        int size;  
        byte[] data = new byte[1024];  
        InputStream in = null;  
        ByteArrayOutputStream out = null;  
  
        try {  
            in = context.openFileInput(fileName);  
            out = new ByteArrayOutputStream();  
            while((size = in.read(data)) != -1){  
                out.write(data, 0, size);  
            }  
            return out.toByteArray();  
        }
        catch(Exception e) {  
            // エラーの場合もnullを返すのでここでは何もしない  
        }
        finally{  
            try{  
                if(in != null)in.close();  
                if(out != null)out.close();  
            }
            catch(Exception e){  
            }  
        }  
  
        return null;  
    }

	/*引数:float配列1、float配列2*/
	/*戻り値:float配列2をfloat配列1の後ろにつけた後のfloat配列*/
	/*配列合成*/
	public static float[] composeArray(float[] array1,float[] array2){
		int arrayLength = array1.length + array2.length;
		float[] array = new float[arrayLength];
		for(int i=0;i<arrayLength;i++){
			if(i < array1.length){
				array[i] = array1[i];
			}
			else{
				array[i] = array2[i - array1.length];
			}
		}
		return array;
	}
	
	public static int UseBuffer(int nowBuffer,int bufferNum){
		if(nowBuffer+1 < bufferNum){
			nowBuffer++;		// 使用バッファの移動
		}
		else{
			nowBuffer = 0;		// 使用バッファのリセット
		}
		return nowBuffer;
	}
}
