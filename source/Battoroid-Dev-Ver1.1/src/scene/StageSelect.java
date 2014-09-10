package scene;

import collision.Collision;
import openGLES20.Common;
import particle.ParticleFunctionFactory;
import particle.ParticleManager;
import particle.ParticleSystem;
import selectCursor.Cursor;
import selectCursor.SelectCursor;
import sound.BGMManager;
import sound.SEManager;
import stage.InitPlaySceneManager;
import stage.StageData;
import function.ColorData;
import function.Graphics;
import function.Graphics.BlendMode;
import function.TextureManager;
import function.TouchManager;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObject;
import gameObject.GameObjectFunctionFactory;
import gesture.ProportionMotionEvent;
import android.view.MotionEvent;

public class StageSelect implements Scene{
	boolean isFadeOuted = false;
	Vector2DDomain vector2DDomain;
	
	GameObject[] button;
	SelectCursor selectCursor;

	public StageSelect() {
		
	}

	@Override
	public void intialize() {
		vector2DDomain = new Vector2DDomain();
		
		BGMManager.soundStart("Mero1");
		
		ColorData fadeColor = new ColorData();
		fadeColor.setARGB(1,1,1,1);
		if(!Fade.getIsPlayFade()){
			Fade.FadeInStart(30,fadeColor,false);
		}
		
		button = new GameObject[StageData.getStageNum()];
		
		int buttonLineNum = (int)Math.ceil(button.length/4);
		int nowLine = 0;
		float buttonRad = Common.getScreenProportionH()/(buttonLineNum*10);
		
		for(int i=0;i<button.length;i++){
			button[i] = new GameObject();
			button[i].setIsExistence(true);
			button[i].setTexture(TextureManager.getTexture("Button"));
			button[i].setAngle(0);
			button[i].setFunction(GameObjectFunctionFactory.makeNotProcessing());
			button[i].setRad(buttonRad);
			if(i%4==0)nowLine++;
			button[i].setPos(new Vector2D(
					Common.getScreenProportionW()/5*(i%4+1),
					Common.getScreenProportionH()/(buttonLineNum+2)*nowLine + button[i].getRad()));
			ColorData buttonColor = new ColorData();
			buttonColor.setARGB(1,1,1,1);
			button[i].setColorData(buttonColor);
			button[i].setBlendMode(BlendMode.ALPHA);
		}

		int paticleNum = 5;
		
		ParticleSystem[] particleSystem = new ParticleSystem[paticleNum];
		for(int i=0;i<paticleNum;i++){
			particleSystem[i] = new ParticleSystem();
			particleSystem[i].initialize("Light",
				ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0)).compose(
				ParticleFunctionFactory.makeAlphaMultiplication(0.85f).compose(
				ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.05f).compose(
				ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,-1.8f))))));
		
			particleSystem[i].setDefault();
			particleSystem[i].setGeneratNum(4,2);
			particleSystem[i].setAngleMargin(30);
			particleSystem[i].setGeneratAngle(0,360);
			particleSystem[i].setGeneratPoint(new Vector2D(),new Vector2D(2,2));
			particleSystem[i].setInitialVecocity(4,1);
			particleSystem[i].setDisappearanceDistance(150,10);
			particleSystem[i].setParticleScale(70,20);
			particleSystem[i].setA(0.6f,0.2f);
			particleSystem[i].setR(0.8f,0.3f);
			particleSystem[i].setG(0.4f,0.2f);
			particleSystem[i].setB(0.2f,0.2f);
		}
		Cursor cursor = new Cursor(paticleNum,particleSystem);
		cursor.setRotVel(3);
		cursor.setRad(buttonRad*2.1f);
		
		selectCursor = new SelectCursor(0,20,cursor,button){
			@Override
			public void render(){
				super.render();
				GameObject dispObjct;
				for(int i=0;i<getSelectObject().length;i++){
					dispObjct = getSelectObject(i);
					int dispNum = i+1;
					float scale = dispObjct.getRad();
					Graphics.drawNumbers(dispObjct.getPos(),scale,dispNum,BlendMode.ALPHA);
				}
			}
		};
	}

	@Override
	public void update() {
		if(!Fade.getIsPlayFade()){
			if(isFadeOuted){
				SceneManager.changeScene(SceneList.PLAY);
				SEManager.Play("Decision");
			}
		}
	}

	@Override
	public void render() {
		Graphics.drawTexture(TextureManager.getTexture("StageSelect"),BlendMode.ALPHA);
		selectCursor.render();
		ParticleManager.render();
	}

	@Override
	public void finalize() {
		selectCursor.finalize();
		isFadeOuted = false;
		vector2DDomain = null;
		
		button = null;
		selectCursor = null;
		
		BGMManager.soundStop("Mero1");
	}

	@Override
	public void touchEvent(MotionEvent event) {
		switch(event.getAction()){		
		case MotionEvent.ACTION_DOWN:
			// ‰æ–Ê‚ªƒ^ƒbƒ`‚³‚ê‚½‚Æ‚«‚Ì“®ì
			for(int i=0;i<button.length;i++){
				if(Collision.circleToCircle(button[i].getPos(),button[i].getRad(),new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY()),10)){
					if(i==selectCursor.getNowCursor()){
						InitPlaySceneManager.setInitPlaySceneFunction(StageData.getStageData(i));
						ColorData fadeColor = new ColorData();
						fadeColor.setARGB(1,1,1,1);
						if(!Fade.getIsPlayFade()){
							Fade.FadeOutStart(90,fadeColor,false);
							isFadeOuted = true;
							SEManager.Play("Decision");
						}
					}
					else{
						selectCursor.setNowCursor(i);
					}
				}
			}
			
//			if(Collision.circleToCircle(new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH()),30,
//										new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY()),30)){
//				SceneManager.changeScene(SceneList.EDIT);
//			}
			
			TouchManager.actionReset();
			break;
		}
	}
}
