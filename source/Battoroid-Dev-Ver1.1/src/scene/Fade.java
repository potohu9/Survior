package scene;

import openGLES20.Common;
import function.AbstractRunnable;
import function.ColorData;
import function.Graphics;
import function.TextureManager;
import function.Graphics.BlendMode;

public class Fade {
	private static int fadeCount;
	private static float addAlpha;
	private static Runnable fadeThread;
	private static Thread thread;
	private static ColorData colorData;
	
	private static boolean isPlayFadeOut;
	private static boolean isPlayFadeIn;
	
	public static boolean getIsPlayFadeOut()		{return isPlayFadeOut;}
	public static boolean getIsPlayFadeIn()			{return isPlayFadeIn;}
	public static boolean getIsPlayFade()			{return (isPlayFadeOut || isPlayFadeIn);}
	
	static{
		fadeCount = 0;
		addAlpha = 0;
		colorData = new ColorData();
		colorData.setARGB(1,1,1,1);
		isPlayFadeOut = false;
		isPlayFadeIn = false;
	}
	
	public static void FadeInStart(final int fadeTime,ColorData colorData,boolean isSleepUpdate){
		fadeCount = fadeTime;
		addAlpha = -(1.0f/fadeTime);
		colorData.setA(1.0f);
		Fade.colorData = colorData;
		isPlayFadeIn = true;
		SceneManager.setIsSleepUpdate(isSleepUpdate);
		fadeThread = new AbstractRunnable(){
			@Override
			public void run() {
				while(isPlayFadeIn){
					try{
						Thread.sleep(Common.getMaxFPS());
					}
					catch (InterruptedException e){
						
					}
					Fade.colorData.setA(Fade.colorData.getA() + addAlpha);
					fadeCount--;
					if(fadeCount <= 0){
						SceneManager.setIsSleepUpdate(false);
						isPlayFadeIn = false;
						break;
					}
				}
			}		
		};
		thread = new Thread(fadeThread);
		thread.start();
	}
	
	public static void FadeOutStart(final int fadeTime,ColorData colorData,boolean isSleepUpdate){
		fadeCount = fadeTime;
		addAlpha = 1.0f/fadeTime;
		colorData.setA(0.0f);
		Fade.colorData = colorData;
		isPlayFadeOut = true;
		SceneManager.setIsSleepUpdate(isSleepUpdate);
		fadeThread = new AbstractRunnable(){
			@Override
			public void run() {
				while(isPlayFadeOut){
					try{
						Thread.sleep(Common.getMaxFPS());
					}
					catch (InterruptedException e){
						
					}
					Fade.colorData.setA(Fade.colorData.getA() + addAlpha);
					fadeCount--;
					if(fadeCount <= 0){
						SceneManager.setIsSleepUpdate(false);
						isPlayFadeOut = false;
						break;
					}
				}
			}		
		};
		thread = new Thread(fadeThread);
		thread.start();
	}
	
	public static void render(){
		Graphics.drawTexture(colorData,TextureManager.getTexture("Fade"),BlendMode.ALPHA);
	}
}
