package particle;

import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import function.ColorData;
import function.Graphics;
import function.Graphics.BlendMode;
import function.TextureManager;
import function.Utile;
import function.Vector2D;
import function.Vector2DDomain;

import openGLES20.Common;
import openGLES20.GLES;
import openGLES20.GLES.ShaderMode;

import android.opengl.GLES20;

public class ParticleSystem {
	/*各変数はBase ± Margin　の範囲でパーティクルを生成する為のもの*/
	/*生成数*/
	private int generatBaseNum;
	private int generatNumMargin;
	
	/*インターバル*/
	private int generatBaseInterval;
	private int generatIntervalMargin;
	private int generatCount;							// 生成カウント

	/*生成角度*/
	private int generatBaseAngle;
	private int generatAngleMargin;

	/*各角の角度誤差*/
	private int angleMargin;

	/*初速度*/
	private float initialBaseVelocity;
	private float initialVelocityMargin;

	/*パーティクル消滅までの移動距離*/
	private float disappearanceBaseDistance;
	private float disappearanceDistanceMargin;

	/*パーティクルの大きさ*/
	private float particleBaseScale;
	private float particleScaleMargin;

	/*生成座標*/
	private Vector2D generatBasePoint;
	private Vector2D generatPointMargin;

	/*赤*/
	private float baceR;
	private float RMargin;

	/*緑*/
	private float baceG;
	private float GMargin;

	/*青*/
	private float baceB;
	private float BMargin;

	/*アルファ*/
	private float baceA;
	private float AMargin;

	private ParticleFunction particleFunction;			// パーティクルのステータス変更関数

	private boolean isDischarge;						// パーティクル発生中かどうか
	
	private GeneratMode generatMode;					// 生成方式
	private BlendMode blendMode;						// 合成方式
	
	private ColorData colorData;						// 色データ
	
	private int generatLimit;							// 生成限界数
	private Particle[] particle;						// パーティクル
	private int nowUseBuffer;							// 現在使用中のバッファ位置

	private String textureKey;							// テクスチャのキー
	
	private boolean isDraw;
	private Vector2DDomain vector2DDomain;
	/*各セッター*/
	public void setGeneratNum(int base,int margin){
		generatBaseNum = base;
		generatNumMargin = margin;
	}
	public void setGeneratInterval(int base,int margin){
		generatBaseInterval = base;
		generatIntervalMargin = margin;
	}
	public void setGeneratAngle(int base,int margin){
		generatBaseAngle = base;
		generatAngleMargin = margin;
	}
	public void setAngleMargin(int angleMargin){
		this.angleMargin = angleMargin;
	}
	public void setInitialVecocity(float base,float margin){
		initialBaseVelocity = base;
		initialVelocityMargin = margin;
	}
	public void setDisappearanceDistance(float base,float margin){
		disappearanceBaseDistance = base;
		disappearanceDistanceMargin = margin;
	}
	public void setParticleScale(float base,float margin){
		particleBaseScale = base;
		particleScaleMargin = margin;
	}
	public void setGeneratPoint(Vector2D base,Vector2D margin){
		generatBasePoint = base;
		generatPointMargin = margin;
	}
	public void setR(float base,float margin){
		baceR = base;
		RMargin = margin;
	}
	public void setG(float base,float margin){
		baceG = base;
		GMargin = margin;
	}
	public void setB(float base,float margin){
		baceB = base;
		BMargin = margin;
	}
	public void setA(float base,float margin){
		baceA = base;
		AMargin = margin;
	}
	public void setParticleFunction(ParticleFunction particleFunction){
		this.particleFunction = particleFunction;
	}
	public void setIsDischarge(boolean isDischarge){
		this.isDischarge = isDischarge;
	}
	public void setGeneratMode(GeneratMode generatMode){
		this.generatMode = generatMode;
	}
	public void setBlendMode(BlendMode blendMode){
		this.blendMode = blendMode;
	}
	public void setTextureKey(String textureKey){
		this.textureKey = textureKey;
	}
	public void setIsDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}
	public void setVector2DDomain(Vector2DDomain vector2DDomain) {
		this.vector2DDomain = vector2DDomain;
	}
	public void setSystem(){
		for(int i=0;i<generatLimit;i++){
			particle[i].setSystem(this);
		}
	}
	
	/*各ゲッター*/
	public int getGeneratBaseNum()						{return generatBaseNum;}
	public int getGeneratNumMargin()					{return generatNumMargin;}
	public int getGeneratBaseInterval()					{return generatBaseInterval;}
	public int getGeneratIntervalMargin()				{return generatIntervalMargin;}
	public int getGeneratBaseAngle()					{return generatBaseAngle;}
	public int getGeneratAngleMargin()					{return generatAngleMargin;}
	public int getAngleMargin()							{return angleMargin;}
	public float getInitialBaseVelocity()				{return initialBaseVelocity;}
	public float getInitialVelocityMargin()				{return initialVelocityMargin;}
	public float getDisappearanceBaseDistance()			{return disappearanceBaseDistance;}
	public float getDisappearanceDistanceMargin()		{return disappearanceDistanceMargin;}
	public float getParticleBaseScale()					{return particleBaseScale;}
	public float getParticleScaleMargin()				{return particleScaleMargin;}
	public Vector2D getGeneratBasePoint()				{return generatBasePoint;}
	public Vector2D getGeneratPointMargin()				{return generatPointMargin;}
	public float getBaceR()								{return baceR;}
	public float getRMargin()							{return RMargin;}
	public float getBaceG()								{return baceG;}
	public float getGMargin()							{return GMargin;}
	public float getBaceB()								{return baceB;}
	public float getBMargin()							{return BMargin;}
	public float getBaceA()								{return baceA;}
	public float getAMargin()							{return AMargin;}
	public ParticleFunction getParticleFunction()		{return particleFunction;}
	public boolean getIsDischarge()						{return isDischarge;}
	public GeneratMode getGeneratMode()					{return generatMode;}
	public BlendMode getBlendMode()						{return blendMode;}
	public boolean getIsDraw()							{return isDraw;}
	public Particle[] getParticle()						{return particle;}
	public Particle getParticle(int index)				{return particle[index];}
	
	public ParticleSystem(){
		ParticleManager.AddParticle(this);
		this.vector2DDomain = new Vector2DDomain();
	}
	
	public ParticleSystem getClone(){
		ParticleSystem result = new ParticleSystem();
		
		result.initialize(this.textureKey,this.getParticleFunction());
		
		result.setDefault();
		
		result.setAngleMargin(this.getAngleMargin());
		result.setBlendMode(this.getBlendMode());
		result.setDisappearanceDistance(this.getDisappearanceBaseDistance(),this.getDisappearanceDistanceMargin());
		result.setGeneratAngle(this.getGeneratBaseAngle(),this.getGeneratAngleMargin());
		result.setGeneratInterval(this.getGeneratBaseInterval(),this.getGeneratIntervalMargin());
		result.setGeneratMode(this.getGeneratMode());
		result.setGeneratNum(this.getGeneratBaseNum(),this.getGeneratNumMargin());
		result.setGeneratPoint(this.getGeneratBasePoint(),this.getGeneratPointMargin());
		result.setInitialVecocity(this.getInitialBaseVelocity(),this.getInitialVelocityMargin());
		result.setIsDischarge(this.getIsDischarge());
		result.setParticleScale(this.getParticleBaseScale(),this.getParticleScaleMargin());
		result.setA(this.getBaceA(),this.getAMargin());
		result.setR(this.getBaceR(),this.getRMargin());
		result.setG(this.getBaceG(),this.getGMargin());
		result.setB(this.getBaceB(),this.getBMargin());
		
		return result;
	}
	
	/*初期化*/
	public void initialize(String textureKey,ParticleFunction particleFunction){
		generatLimit = 100;
		nowUseBuffer = 0;
		particle = new Particle[generatLimit];
		this.textureKey = textureKey;
		this.particleFunction = particleFunction;
	}
	
	/*デフォルト設定のセット*/
	public void setDefault(){
		generatBaseNum = 5;
		generatNumMargin = 0;
		
		generatBaseInterval = 0;
		generatIntervalMargin = 0;
		generatCount = 0;
		
		generatBaseAngle = 0;
		generatAngleMargin = 0;
		
		initialBaseVelocity = 30;
		initialVelocityMargin = 0;
		
		disappearanceBaseDistance = Math.min(Common.getScreenProportionW()/2,Common.getScreenProportionH()/2)*0.9f;
		disappearanceDistanceMargin = 0;
		
		generatLimit = 100;
		particle = new Particle[generatLimit];
		nowUseBuffer = 0;
		
		isDischarge = true;
		
		generatBasePoint = new Vector2D(Common.getScreenProportionW()/2,Common.getScreenProportionH()/2);
		generatPointMargin = new Vector2D(0,0);
		
		particleBaseScale = 60;
		particleScaleMargin = 0;
		
		for(int i=0;i<generatLimit;i++){
			particle[i] = new Particle(vector2DDomain);
			particle[i].setIsExistence(false);
			particle[i].setSystem(this);
		}
		
		baceR = 1.0f;
		baceG = 1.0f;
		baceB = 1.0f;
		baceA = 1.0f;
		
		RMargin = 0.0f;
		GMargin = 0.0f;
		BMargin = 0.0f;
		AMargin = 0.0f;
		
		generatMode = GeneratMode.EMISSION;
		blendMode = Graphics.BlendMode.ADDITION;
		
		isDraw = true;
	}

	/*更新処理*/
	public void update(){
		ParticleUpdate();
		ParticleGenerat();
	}
	
	/*描画処理*/
	public void render(){
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);					// テクスチャの有効化
		int texture = TextureManager.getTexture(textureKey);			
		drawParticles(particle,texture);
	}
	
	/*元の数値とランダム幅から乱数値を求める*/
	private float calculatMargin(Random random,float base,float margin){
		float inLimitNum = base + (random.nextFloat()-0.5f) * margin;
		return inLimitNum;
	}
	
	/*元の数値とランダム幅から乱数値を求める*/
	private int calculatMargin(Random random,int base,int margin){
		int inLimitNum;
		if(margin != 0){
			inLimitNum = base + random.nextInt(margin*2)-margin;
		}
		else{
			inLimitNum = base;
		}
		return inLimitNum;
	}
	
	/*パーティクルの一斉描画*/
	public void drawParticles(Particle[] particles,int textureID){
		if(!isDraw)			return;
		
		// ブレンドの指定
		Graphics.setBlend(blendMode);
		
		GLES.changeUseShader(ShaderMode._0D);
		
		int length = 0;
		float[] scale = new float[0];
		float[] vertex = new float[0];
		float[] color = new float[0];
		
		for(int i=0;i<particles.length;i++){
			if(particles[i].getIsExistence()){
				scale = Utile.composeArray(scale,new float[]{particles[i].getScale()});
				Vector2D drawPos = Utile.translatScreenPoint(particles[i].getPos());
				vertex = Utile.composeArray(vertex,new float[]{drawPos.x,drawPos.y,0.0f});
				color = Utile.composeArray(color,new float[]{	particles[i].getColorData().getR(),
												   			particles[i].getColorData().getG(),					
												   			particles[i].getColorData().getB(),
												   			particles[i].getColorData().getA()});
				length++;
			}
		}
		
		FloatBuffer scaleBuffer = Utile.makeFloatBuffer(scale);
		FloatBuffer vertexBuffer = Utile.makeFloatBuffer(vertex);
		FloatBuffer colorBuffer = Utile.makeFloatBuffer(color);
		
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,textureID);
		GLES20.glUniform1i(GLES._0DHandle.texture,0);
		
		GLES20.glVertexAttribPointer(GLES._0DHandle.size,1,GLES20.GL_FLOAT,false,0,scaleBuffer);
		GLES20.glVertexAttribPointer(GLES._0DHandle.position,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
		GLES20.glVertexAttribPointer(GLES._0DHandle.color,4,GLES20.GL_FLOAT,false,0,colorBuffer);
		GLES20.glDrawArrays(GL10.GL_POINTS,0,length);
	}
	
	/*パーティクル更新処理*/
	private void ParticleUpdate(){
		for(int i=0;i<generatLimit;i++){
			if(particle[i].getIsExistence()){
				particleFunction.changeState(particle[i]);
				particle[i].update();	//パーティクルの更新
			}
		}
	}
	
	/*パーティクル生成処理*/
	public void ParticleGenerat(){
		if(generatCount <= 0){	// カウントが0になったら
			Random random = new Random();
			/*パーティクル生成*/
			if(isDischarge){	// 発射しているなら生成
				int generatNum = calculatMargin(random,generatBaseNum,generatNumMargin);
				switch(generatMode){
				case CONVERGENCE:
					ModeConvergence(random,generatNum);
					break;
				case EMISSION:
					ModeEmission(random,generatNum);
					break;
				case SAME_ANGLE:
					ModeSameAngle(random,generatNum);
					break;
				case PLACE:
					ModePlace(random,generatNum);
					break;
				}
			}
			generatCount = calculatMargin(random,generatBaseInterval,generatIntervalMargin);	//次のカウントを生成
		}
		
		// カウントダウン
		else{
			generatCount--;
		}
	}
	
	public void ModeConvergence(Random random,int generatNum){
		int angleAdditionNum = 360/generatNum;
		int angle = calculatMargin(random,generatBaseAngle,generatAngleMargin);
		for(int i=0;i<generatNum;i++){
			nowUseBuffer = Utile.UseBuffer(nowUseBuffer,generatLimit);
			if(!particle[nowUseBuffer].getIsExistence()){
				// 初速度を基本値から乱数の範囲内で適用した値
				float initialVelocity = calculatMargin(random,initialBaseVelocity,initialVelocityMargin);
				// 消滅までの距離を基本値から乱数の範囲内で適用した値
				float disappearanceDistance = calculatMargin(random,disappearanceBaseDistance,disappearanceDistanceMargin);
				
				colorData = new ColorData();
				// RGBA値全てに基本値から乱数値の範囲内で適用した値を色データにセットする
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
				
				// パーティクルが飛ぶ目標地点
				Vector2D targetPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
				
				// パーティクル生成位置
				Vector2D generatPoint = Utile.rotatePoint(calculatMargin(random,angle,angleMargin),disappearanceDistance,targetPoint);
				
				// 初期化
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,initialVelocity,calculatMargin(random,particleBaseScale,particleScaleMargin));
			}
			angle += angleAdditionNum;	// 角数に応じた角度追加
		}
	}
	
	private void ModeEmission(Random random,int generatNum){
		int angleAdditionNum = 360/generatNum;
		int angle = calculatMargin(random,generatBaseAngle,generatAngleMargin);
		for(int i=0;i<generatNum;i++){
			if(nowUseBuffer+1 < generatLimit){
				nowUseBuffer++;	// 使用バッファの移動
			}
			else{
				nowUseBuffer = 0;	// 使用バッファのリセット
			}
			if(!particle[nowUseBuffer].getIsExistence()){
				// 初速度を基本値から乱数の範囲内で適用した値
				float initialVelocity = calculatMargin(random,initialBaseVelocity,initialVelocityMargin);
				// 消滅までの距離を基本値から乱数の範囲内で適用した値
				float disappearanceDistance = calculatMargin(random,disappearanceBaseDistance,disappearanceDistanceMargin);
				
				colorData = new ColorData();
				// RGBA値全てに基本値から乱数値の範囲内で適用した値を色データにセットする
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
				
				// パーティクル生成位置
				Vector2D generatPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
				
				// パーティクルが飛ぶ目標地点
				Vector2D targetPoint = Utile.rotatePoint(calculatMargin(random,angle,angleMargin),disappearanceDistance,generatPoint);
				
				// 初期化
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,initialVelocity,calculatMargin(random,particleBaseScale,particleScaleMargin));
				
			}
			angle += angleAdditionNum;	// 角数に応じた角度追加
		}
	}
		
	private void ModeSameAngle(Random random,int generatNum){
		int angle = calculatMargin(random,generatBaseAngle,generatAngleMargin);
		for(int i=0;i<generatNum;i++){
			if(nowUseBuffer+1 < generatLimit){
				nowUseBuffer++;	// 使用バッファの移動
			}
			else{
				nowUseBuffer = 0;	// 使用バッファのリセット
			}
			if(!particle[nowUseBuffer].getIsExistence()){
				// 初速度を基本値から乱数の範囲内で適用した値
				float initialVelocity = calculatMargin(random,initialBaseVelocity,initialVelocityMargin);
				// 消滅までの距離を基本値から乱数の範囲内で適用した値
				float disappearanceDistance = calculatMargin(random,disappearanceBaseDistance,disappearanceDistanceMargin);
					
				colorData = new ColorData();
				// RGBA値全てに基本値から乱数値の範囲内で適用した値を色データにセットする
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
					
				// パーティクル生成位置
				Vector2D generatPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
					
				// パーティクルが飛ぶ目標地点
				Vector2D targetPoint = Utile.rotatePoint(calculatMargin(random,angle,angleMargin),disappearanceDistance,generatPoint);
					
				// 初期化
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,initialVelocity,calculatMargin(random,particleBaseScale,particleScaleMargin));
			}
		}
	}
	
	public void ModePlace(Random random,int generatNum){
		for(int i=0;i<generatNum;i++){
			nowUseBuffer = Utile.UseBuffer(nowUseBuffer,generatLimit);
			if(!particle[nowUseBuffer].getIsExistence()){
				colorData = new ColorData();
				// RGBA値全てに基本値から乱数値の範囲内で適用した値を色データにセットする
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
				
				// パーティクルが飛ぶ目標地点
				Vector2D targetPoint = new Vector2D();
				
				// パーティクル生成位置
				Vector2D generatPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
				
				// 初期化
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,0,calculatMargin(random,particleBaseScale,particleScaleMargin));
			}
		}
	}
}
