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
	private static Scene currentScene;				// ���݂̃V�[��
	private static Scene nextScene;					// ���̃V�[��
	
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
	
	/*�V�[���}�l�[�W���[�̏�����*/
	public static void initialize(){
		currentScene = new LogoScene();								// �J�n����V�[����ύX�������ꍇ�͂�����ύX
		currentScene.intialize();
		SEManager.setContext(Common.getContext());
		SEManager.LodeSE();
		Sound.setContext(Common.getContext());
		BGMManager.LodeBGM();
	}	
	
	/*�X�V����*/
	public static void update(){
		// ���̃V�[������������
		if(nextScene != null){
			ParticleManager.reset();
			nextScene.intialize();									// ���V�[����������
			currentScene.finalize();								// ���݃V�[���̏I������
			currentScene = nextScene;								// �V�[���̒u������
			nextScene = null;		
			// ���V�[����null��
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
	
	/*�`�揈��*/
	public static void render(){
		if(currentScene != null){
			// ��ʂ̃N���A
			GLES20.glClearColor(0.5f,0.5f,0.5f,1.0f);
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
			currentScene.render();
			if(Fade.getIsPlayFade())	Fade.render();
		}
	}
	
	/*�V�[���̕ύX*/
	/*����:�ύX�������V�[���̎w��*/
	public static void changeScene(Scene scene){
		if(nextScene == null){
			nextScene = scene;
		}
	}
}
