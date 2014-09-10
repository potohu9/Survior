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
	
	/*����:float�z��*/
	/*�߂�l:float�z���FloatBuffer�^�ɕϊ������l*/
	/*float�z���FloatBuffer�ɕϊ�*/
	public static FloatBuffer makeFloatBuffer(float[] array){
		FloatBuffer fb = ByteBuffer.allocateDirect(array.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		fb.put(array).position(0);
		return fb;
	}
	
	/*����:�ʒu�x�N�g��*/
	/*�߂�l:�ʒu�x�N�g�����r���[���W�ɕϊ�*/
	/*�r���[���W�ɕύX*/
	public static Vector2D translatScreenPoint(Vector2D point){
		Vector2D screenPoint = new Vector2D();
		screenPoint.x = (point.x/Common.getScreenProportionW())*2.0f-1.0f;
		screenPoint.y = ((point.y/Common.getScreenProportionH())*2.0f-1.0f)*-1;
		return screenPoint;
	}
	
	/*����:�p�x�A�����A�ʒu�x�N�g��*/
	/*�߂�l:�ʒu�x�N�g�����璷�����̈ʒu�ɂ���ʒu�x�N�g��B���A�ʒu�x�N�g���𒆐S�Ƃ��Ċp�x���_��]��������̈ʒu�x�N�g��B*/
	/*�_��]*/
	public static Vector2D rotatePoint(double angle,float length,Vector2D point){
		Vector2D rotatedPoint = new Vector2D();
		Vector2D rotaryFront = new Vector2D(0,length);
		double radian = angle / 180 * Math.PI;
		rotatedPoint.x = (float)(rotaryFront.x*Math.cos(radian)-rotaryFront.y*Math.sin(radian)+point.x);
		rotatedPoint.y = (float)(rotaryFront.x*Math.sin(radian)+rotaryFront.y*Math.cos(radian)+point.y);
		
		return rotatedPoint;
	}
	
	/*����:�t�@�C����*/
	/*�߂�l:�w��̃t�@�C�����̕�����*/
	/*�t�@�C���ǂݍ���*/
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
            // �G���[�̏ꍇ��null��Ԃ��̂ł����ł͉������Ȃ�  
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

	/*����:float�z��1�Afloat�z��2*/
	/*�߂�l:float�z��2��float�z��1�̌��ɂ������float�z��*/
	/*�z�񍇐�*/
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
			nowBuffer++;		// �g�p�o�b�t�@�̈ړ�
		}
		else{
			nowBuffer = 0;		// �g�p�o�b�t�@�̃��Z�b�g
		}
		return nowBuffer;
	}
}
