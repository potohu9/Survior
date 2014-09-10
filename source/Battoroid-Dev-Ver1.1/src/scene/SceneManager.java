package scene;

import openGLES20.Common;
import particle.ParticleManager;
import sound.BGMManager;
import sound.SEManager;
import sound.Sound;
import android.opengl.GLES20;
import android.view.MotionEvent;
import function.TouchManager;

public class SceneManager{
	private static Scene currentScene;				// 現在のシーン
	private static Scene nextScene;					// 次のシーン
	
	private static boolean isSleepUpdate;
	
	public static boolean getIsSleepUpdate()			{return isSleepUpdate;}
	
	static{
		currentScene = null;
		nextScene = null;
		isSleepUpdate = false;
	}

	public static void setIsSleepUpdate(boolean isSleepUpdate) {
		SceneManager.isSleepUpdate = isSleepUpdate;
	}
	
	public SceneManager(){

	}
	
	/*シーンマネージャーの初期化*/
	public static void initialize(){
		currentScene = new LogoScene();								// 開始するシーンを変更したい場合はここを変更
		currentScene.intialize();
		SEManager.setContext(Common.getContext());
		SEManager.LodeSE();
		Sound.setContext(Common.getContext());
		BGMManager.LodeBGM();
	}	
	
	/*更新処理*/
	public static void update(){
		// 次のシーンがあったら
		if(nextScene != null){
			ParticleManager.reset();
			nextScene.intialize();									// 次シーンを初期化
			currentScene.finalize();								// 現在シーンの終了処理
			currentScene = nextScene;								// シーンの置き換え
			nextScene = null;		
			// 次シーンをnullへ
		}
		if(!isSleepUpdate){
			try{
				Thread.sleep(Common.getMaxFPS());
			}
			catch (InterruptedException e){

			}
			MotionEvent event = TouchManager.getEvent();
			if(currentScene != null){
				if(event != null){
					currentScene.touchEvent(event);
				}
				currentScene.update();
			}
		}
	}
	
	/*描画処理*/
	public static void render(){
		if(currentScene != null){
			// 画面のクリア
			GLES20.glClearColor(0.5f,0.5f,0.5f,1.0f);
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
			currentScene.render();
			if(Fade.getIsPlayFade())	Fade.render();
		}
	}
	
	/*シーンの変更*/
	/*引数:変更したいシーンの指定*/
	public static void changeScene(Scene scene){
		if(nextScene == null){
			nextScene = scene;
		}
	}
}
