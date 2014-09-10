package scene;

import openGLES20.Common;
import particle.GeneratMode;
import particle.ParticleFunctionFactory;
import particle.ParticleManager;
import particle.ParticleSystem;
import sound.BGMManager;
import function.ColorData;
import function.Graphics;
import function.Vector2D;
import function.ColorData.ColorList;
import function.Graphics.BlendMode;
import function.TextureManager;
import function.TouchManager;
import function.Vector2DDomain;
import android.view.MotionEvent;

public class ResultScene implements Scene{
	boolean isFadeOuted = false;
	Vector2DDomain vector2DDomain;
	boolean isDisp;
	int dispCount;
	final int dispChangeTime = 20;
	ColorData colorData;
	String dispKey;
	
	ParticleSystem particle;
	
	public ResultScene() {
		
	}

	@Override
	public void intialize() {
		BGMManager.soundStart("BGM1");
		if(!Fade.getIsPlayFade()){
			Fade.FadeInStart(90,ColorData.getColor(ColorList.WHITE),false);
		}
		vector2DDomain = new Vector2DDomain();
		dispCount = dispChangeTime;
		isDisp = false;
		
		switch(ResultManager.getResultList()){
		case LOSE:
			dispKey = "ResultLose";
			colorData = ColorData.getColor(ColorList.BLACK);
			initParticle(BlendMode.SUBTRACTION,GeneratMode.EMISSION);
			break;
		case WIN:
			dispKey = "ResultWin";
			colorData = ColorData.getColor(ColorList.WHITE);
			initParticle(BlendMode.ADDITION,GeneratMode.CONVERGENCE);
			break;
		default:
			dispKey = "ResultLose";
			colorData = ColorData.getColor(ColorList.BLACK);
			initParticle(BlendMode.SUBTRACTION,GeneratMode.EMISSION);
			break;
		}
	}

	@Override
	public void update() {
		dispCount--;
		if(dispCount <= 0){
			if(isDisp){
				isDisp = false;
			}
			else{
				isDisp = true;
			}
			dispCount = dispChangeTime;
		}
		particle.update();
		if(!Fade.getIsPlayFade()){
			if(isFadeOuted){
				SceneManager.changeScene(SceneList.TITLE);
			}
		}
	}

	@Override
	public void render() {
		Graphics.drawTexture(TextureManager.getTexture(dispKey),BlendMode.ALPHA);
		if(isDisp){
			Graphics.drawTexture(colorData,TextureManager.getTexture("TouchDisp"),BlendMode.ALPHA);
		}
		ParticleManager.render();
	}

	@Override
	public void finalize() {
		isFadeOuted = false;
		vector2DDomain = null;
		isDisp = false;
		dispCount = dispChangeTime;
		colorData = null;
		dispKey = null;
		
		particle = null;
		BGMManager.soundStop("BGM1");
	}

	@Override
	public void touchEvent(MotionEvent event) {
		switch(event.getAction()){		
		case MotionEvent.ACTION_DOWN:
			// ‰æ–Ê‚ªƒ^ƒbƒ`‚³‚ê‚½‚Æ‚«‚Ì“®ì
			if(!Fade.getIsPlayFade()){
				Fade.FadeOutStart(90,ColorData.getColor(ColorList.WHITE),false);
				isFadeOuted = true;
			}
			TouchManager.actionReset();
			break;
		}
	}
	
	private void initParticle(BlendMode blendMode,GeneratMode generatMode){
		particle = new ParticleSystem();
		particle.initialize("Light",
			ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0)).compose(
			ParticleFunctionFactory.makeAlphaMultiplication(0.98f).compose(
			ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.01f))));
		particle.setDefault();
		particle.setBlendMode(blendMode);
		particle.setGeneratMode(generatMode);
		particle.setGeneratNum(2,1);
		particle.setGeneratPoint(
				new Vector2D(Common.getScreenProportionW()*0.5f,Common.getScreenProportionH()*0.5f),
				new Vector2D(30,30));
		particle.setAngleMargin(30);
		particle.setGeneratAngle(0,360);
		particle.setInitialVecocity(10,5);
		float length = Vector2D.Length(vector2DDomain,
				new Vector2D(Common.getScreenProportionW()*0.5f,Common.getScreenProportionH()*0.5f));
		particle.setDisappearanceDistance(length,10);
		particle.setParticleScale(120,90);
		particle.setA(0.2f,0.1f);
		particle.setR(0.1f,0.0f);
		particle.setG(0.6f,0.3f);
		particle.setB(0.8f,0.5f);
	}
}
