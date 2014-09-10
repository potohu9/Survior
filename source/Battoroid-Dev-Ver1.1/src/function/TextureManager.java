package function;

import java.util.HashMap;

import openGLES20.Common;
import com.drjiro.game.battoroid.s122240.game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class TextureManager {
	private static HashMap<String,Integer> map;					// キーと共にテクスチャIDを保存
	
	static{
		map = new HashMap<String,Integer>();
		TextureManager.setTexture("Numbers",R.drawable.numbers);
		TextureManager.setTexture("Write",R.drawable.write);
		TextureManager.setTexture("Deleat",R.drawable.deleat);
		TextureManager.setTexture("End",R.drawable.end);
		TextureManager.setTexture("BackGround",R.drawable.background);
		TextureManager.setTexture("Ball",R.drawable.ball);
		TextureManager.setTexture("BaceGauge",R.drawable.bacegauge);
		TextureManager.setTexture("Button",R.drawable.button);
		TextureManager.setTexture("Bullet1",R.drawable.bullet1);
		TextureManager.setTexture("Bullet2",R.drawable.bullet2);
		TextureManager.setTexture("Bullet3",R.drawable.bullet3);
		TextureManager.setTexture("Cyber",R.drawable.cyber);
		TextureManager.setTexture("Doroid",R.drawable.ic_launcher);
		TextureManager.setTexture("Enemy",R.drawable.enemy);
		TextureManager.setTexture("Fade",R.drawable.fade);
		TextureManager.setTexture("Fire",R.drawable.fire);
		TextureManager.setTexture("Frash",R.drawable.frash);
		TextureManager.setTexture("Frame",R.drawable.frame);
		TextureManager.setTexture("Gauge",R.drawable.gauge);
		TextureManager.setTexture("Guide",R.drawable.guide);
		TextureManager.setTexture("Light",R.drawable.light);
		TextureManager.setTexture("Line",R.drawable.line2);
		TextureManager.setTexture("Load",R.drawable.load);
		TextureManager.setTexture("Loading",R.drawable.loading);
		TextureManager.setTexture("Logo",R.drawable.logo);
		TextureManager.setTexture("Player",R.drawable.player);
		TextureManager.setTexture("Plaid",R.drawable.plaid);
		TextureManager.setTexture("ResultLose",R.drawable.resultlose);
		TextureManager.setTexture("ResultWin",R.drawable.resultwin);
		TextureManager.setTexture("Star",R.drawable.star);
		TextureManager.setTexture("StageSelect",R.drawable.stageselect);
		TextureManager.setTexture("Title",R.drawable.title);
		TextureManager.setTexture("TouchDisp",R.drawable.touchdisp);
		TextureManager.setTexture("UI",R.drawable.ui);
	}
	
	/*引数:テクスチャに設定するキー、テクスチャのID*/
	/*指定されたキーでテクスチャをセット*/
	public static void setTexture(String key,int textureID){
		Bitmap bitmap = BitmapFactory.decodeResource(Common.getActivity().getResources(),textureID);
		map.put(key,makeTexture(bitmap));
	}
	
	/*引数:キー*/
	/*戻り値:テクスチャ*/
	/*キーから対応するテクスチャを取得*/
	public static int getTexture(String key){
		return map.get(key);
	}
	
	/*引数:ビットマップ*/
	/*戻り値:テクスチャ*/
	/*テクスチャの生成*/
	private static int makeTexture(Bitmap bitmap){
		// テクスチャのメモリ確保
		int[] textureIDs = new int[1];
		GLES20.glGenTextures(1,textureIDs,0);
		
		//　テクスチャへのビットマップ指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureIDs[0]);
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D,0,bitmap,0);
		
		//　テクスチャフィルタの設定
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_NEAREST);
		return textureIDs[0];
	}
}
