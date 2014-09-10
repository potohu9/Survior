package scene;

import particle.ParticleManager;
import function.ColorData;
import function.Graphics;
import function.Graphics.BlendMode;
import function.TextureManager;
import function.TouchManager;
import android.view.MotionEvent;

public class TitleScene implements Scene{
	boolean isFadeOuted = false;
	boolean isDisp;
	int dispCount;
	final int dispChangeTime = 20;
	ColorData colorData;
	int logoChangeCount = 1000;
	
	public TitleScene() {
		
	}

	@Override
	public void intialize() {
		dispCount = dispChangeTime;
		isDisp = false;
		colorData = new ColorData();
		colorData.setARGB(1,1,1,1);
		//BGMManager.soundStart("Act");
		ColorData fadeColor = new ColorData();
		fadeColor.setARGB(1,1,1,1);
		if(!Fade.getIsPlayFade()){
			Fade.FadeInStart(90,fadeColor,false);
		}
	}

	@Override
	public void update() {
		dispCount--;
		logoChangeCount--;
		if(dispCount <= 0){
			if(isDisp){
				isDisp = false;
			}
			else{
				isDisp = true;
			}
			dispCount = dispChangeTime;
		}
		if(logoChangeCount <= 0){
			ColorData fadeColor = new ColorData();
			fadeColor.setARGB(1,1,1,1);
			if(!Fade.getIsPlayFade()){
				if(isFadeOuted){
					SceneManager.changeScene(SceneList.LOGO);
				}
				Fade.FadeOutStart(90,fadeColor,false);
				isFadeOuted = true;
			}
		}
		if(!Fade.getIsPlayFade()){
			if(isFadeOuted){
				SceneManager.changeScene(SceneList.STAGE_SELECT);
			}
		}
	}

	@Override
	public void render() {
		Graphics.drawTexture(TextureManager.getTexture("Title"),BlendMode.ALPHA);
		if(isDisp){
			Graphics.drawTexture(colorData,TextureManager.getTexture("TouchDisp"),BlendMode.ALPHA);
		}
		ParticleManager.render();
	}

	@Override
	public void finalize() {
		//BGMManager.soundStop("Act");
		isFadeOuted = false;
		isDisp = false;
		dispCount = dispChangeTime;
		colorData = null;
		logoChangeCount = 1000;
	}

	@Override
	public void touchEvent(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			// ‰æ–Ê‚ªƒ^ƒbƒ`‚³‚ê‚½‚Æ‚«‚Ì“®ì
			ColorData fadeColor = new ColorData();
			fadeColor.setARGB(1,1,1,1);
			if(!Fade.getIsPlayFade()){
				Fade.FadeOutStart(90,fadeColor,false);
				isFadeOuted = true;
			}
			TouchManager.actionReset();
			break;
		}
	}
}
