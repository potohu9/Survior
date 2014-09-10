package scene;

import particle.ParticleManager;
import function.ColorData;
import function.ColorData.ColorList;
import function.Graphics;
import function.TouchManager;
import function.Graphics.BlendMode;
import function.TextureManager;
import android.view.MotionEvent;

public class LogoScene implements Scene{
	boolean isFadeOuted = false;
	public LogoScene() {
		
	}

	@Override
	public void intialize() {
		if(!Fade.getIsPlayFade()){
			Fade.FadeInStart(90,ColorData.getColor(ColorList.WHITE),false);
		}
	}

	@Override
	public void update() {
		if(!Fade.getIsPlayFade()){
			if(isFadeOuted){
				SceneManager.changeScene(SceneList.TITLE);
			}
			Fade.FadeOutStart(90,ColorData.getColor(ColorList.WHITE),false);
			isFadeOuted = true;
		}
	}

	@Override
	public void render() {
		Graphics.drawTexture(TextureManager.getTexture("Logo"),BlendMode.ALPHA);
		ParticleManager.render();
	}

	@Override
	public void finalize() {
		isFadeOuted = false;
	}

	@Override
	public void touchEvent(MotionEvent event) {
		switch(event.getAction()){		
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// âÊñ Ç™É^ÉbÉ`Ç≥ÇÍÇΩÇ∆Ç´ÇÃìÆçÏ
			TouchManager.actionReset();
			break;
		}
	}
}
