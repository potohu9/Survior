package openGLES20;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import scene.SceneManager;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class GLRenderer implements GLSurfaceView.Renderer{
	boolean isPlayInit;
	
	public void setActivity(Activity activity){
		Common.setActivity(activity);
	}
	
	public void initialize(){
		SceneManager.initialize();
		isPlayInit = true;
	}
	
	// サーフェイス生成時に呼ばれる
	@Override
	public void onSurfaceCreated(GL10 gl,EGLConfig config) {
		// プログラムの生成
		GLES.makeProgram();
		isPlayInit = false;
	}
	
	// 画面サイズ変更時の呼ばれる
	@Override
	public void onSurfaceChanged(GL10 gl,int width,int height) {
		// 画面の表示領域の指定
		GLES20.glViewport(0,0,width,height);
		Common.setScreenW(width);
		Common.setScreenH(height);
		if(width > height){
			Common.setScreenProportionW(Math.max(Common.getScreenProportionW(),Common.getScreenProportionH()));
			Common.setScreenProportionH(Math.min(Common.getScreenProportionW(),Common.getScreenProportionH()));
		}
		else{
			Common.setScreenProportionW(Math.min(Common.getScreenProportionW(),Common.getScreenProportionH()));
			Common.setScreenProportionH(Math.max(Common.getScreenProportionW(),Common.getScreenProportionH()));
		}
	}

	// 毎フレーム描画時に呼ばれる
	@Override
	public void onDrawFrame(GL10 gl){
		if(!isPlayInit){
			initialize();
		}
		SceneManager.update();
		SceneManager.render();
	}
}
