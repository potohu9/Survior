package openGLES20;

import android.app.Activity;
import android.content.Context;

public class Common {
	private static Activity activity;		// �A�N�e�B�r�e�B
	private static int screenW;				//�@�X�N���[���̉���
	private static int screenH;				//�@�X�N���[���̏c��
	private static int screenProportionW;	// ���̌Œ��ʔ�
	private static int screenProportionH;	// �c�̌Œ��ʔ�
	private static Context context;			// �R���e�L�X�g
	private static int maxFPS;				// �ő�FPS
	
	static{
		screenProportionW = 1280;
		screenProportionH = 800;
		maxFPS = 20;
	}

	/*�e�Z�b�^�[*/
	public static void setActivity(Activity activity) {
		Common.activity = activity;
	}
	public static void setScreenW(int screenW) {
		Common.screenW = screenW;
	}
	public static void setScreenH(int screenH) {
		Common.screenH = screenH;
	}
	public static void setContext(Context context) {
		Common.context = context;
	}
	public static void setScreenProportionW(int screenProportionW) {
		Common.screenProportionW = screenProportionW;
	}
	public static void setScreenProportionH(int screenProportionH) {
		Common.screenProportionH = screenProportionH;
	}
	public static void setMaxFPS(int maxFPS) {
		Common.maxFPS = maxFPS;
	}	

	/*�e�Q�b�^�[*/
	public static Activity getActivity()		{return activity;}
	public static int getScreenW()				{return screenW;}
	public static int getScreenH()				{return screenH;}
	public static Context getContext()			{return context;}
	public static int getScreenProportionW()	{return screenProportionW;}
	public static int getScreenProportionH()	{return screenProportionH;}
	public static int getMaxFPS()				{return maxFPS;}
}
