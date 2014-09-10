package openGLES20;

import android.app.Activity;
import android.content.Context;

public class Common {
	private static Activity activity;		// アクティビティ
	private static int screenW;				//　スクリーンの横幅
	private static int screenH;				//　スクリーンの縦幅
	private static int screenProportionW;	// 横の固定画面比
	private static int screenProportionH;	// 縦の固定画面比
	private static Context context;			// コンテキスト
	private static int maxFPS;				// 最大FPS
	
	static{
		screenProportionW = 1280;
		screenProportionH = 800;
		maxFPS = 20;
	}

	/*各セッター*/
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

	/*各ゲッター*/
	public static Activity getActivity()		{return activity;}
	public static int getScreenW()				{return screenW;}
	public static int getScreenH()				{return screenH;}
	public static Context getContext()			{return context;}
	public static int getScreenProportionW()	{return screenProportionW;}
	public static int getScreenProportionH()	{return screenProportionH;}
	public static int getMaxFPS()				{return maxFPS;}
}
