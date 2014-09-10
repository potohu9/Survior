package bullet;

import particle.ParticleFunctionFactory;
import particle.ParticleSystem;
import function.Graphics.BlendMode;
import function.Utile;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObjectFunction;
import gameObject.GameObjectFunctionFactory;

public class BulletGenerator {
	private Vector2DDomain vector2DDomain;
	private final int bulletLimit;			// 弾の最大数
	private int nowUseBuffer;
	private Bullet[] bullet;				// 弾
	private boolean isDischarge;			// 発射中か
	private Vector2D dischargePoint;		// 発射点
	private Vector2D aimingPoint;			// 標準点
	private int dischargeInterval;			// インターバル
	private int dischargeCount;				// 発射カウント
	private float bulletInitVelocity;
	private int bulletLongevity;
	private BulletFunction bulletFunction;
	private GameObjectFunction gameObjectFunction;
	
	/*各セッター*/
	public void setBullet(Bullet[] bullet) {
		this.bullet = bullet;
	}
	public void setIsDischarge(boolean isDischarge) {
		this.isDischarge = isDischarge;
	}
	public void setDischargePoint(Vector2D dischargePoint) {
		this.dischargePoint = dischargePoint;
	}
	public void setAimingPoint(Vector2D aimingPoint) {
		this.aimingPoint = aimingPoint;
	}
	public void setDischargeInterval(int dischargeInterval) {
		this.dischargeInterval = dischargeInterval;
	}
	public void setDischargeCount(int dischargeCount){
		this.dischargeCount = dischargeCount;
	}
	public void setBulletFunction(BulletFunction bulletFunction){
		for(int i=0;i<bulletLimit;i++){
			bullet[i].setBulletFunction(bulletFunction);
		}
		this.bulletFunction = bulletFunction;
	}
	public void setBulletInitVelocity(float bulletInitVelocity) {
		this.bulletInitVelocity = bulletInitVelocity;
	}
	public void setGameObjectFunction(GameObjectFunction gameObjectFunction) {
		for(int i=0;i<bulletLimit;i++){
			bullet[i].setFunction(gameObjectFunction);
		}
		this.gameObjectFunction = gameObjectFunction;
	}
	
	/*各ゲッター*/
	public int getBulletLimit()							{return bulletLimit;}
	public Bullet[] getBullet()							{return bullet;}
	public Bullet getBullet(int index)					{return bullet[index];}
	public boolean getIsDischarge()						{return isDischarge;}
	public Vector2D getDischargePoint()					{return dischargePoint;}
	public Vector2D getAimingPoint()					{return aimingPoint;}
	public int getDischargeInterval()					{return dischargeInterval;}
	public int getDischargeCount()						{return dischargeCount;}
	public BulletFunction getBulletFunction()			{return bulletFunction;}
	public float getBulletInitVelocity()				{return bulletInitVelocity;}
	public GameObjectFunction getGameObjectFunction()	{return gameObjectFunction;}
	
	
	public BulletGenerator(){
		this.vector2DDomain = new Vector2DDomain();
		bulletFunction = new AbstractBulletFunction(){
			@Override
			public void initBulletParticle(ParticleSystem renderParticle) {
				renderParticle.initialize("Light",
				ParticleFunctionFactory.makeAlphaMultiplication(0.9f).compose(
				ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.1f)));
				renderParticle.setDefault();
				renderParticle.setGeneratNum(8,3);
				renderParticle.setGeneratAngle(0,180);
				renderParticle.setInitialVecocity(4,2);
				renderParticle.setDisappearanceDistance(30,20);
				renderParticle.setParticleScale(30,20);
				renderParticle.setBlendMode(BlendMode.ADDITION);
				renderParticle.setA(0.3f,0.1f);
				renderParticle.setR(1.0f,0.4f);
				renderParticle.setG(0.6f,0.3f);
				renderParticle.setB(0.3f,0.0f);
			}

			@Override
			public void changeState(Bullet bullet) {
				
			}
		};
		gameObjectFunction = GameObjectFunctionFactory.makeNotProcessing();
		bulletLongevity = 200;
		bulletLimit = 30;
		nowUseBuffer = 0;
		bullet = new Bullet[bulletLimit];
		for(int i=0;i<bulletLimit;i++){
			bullet[i] = new Bullet(vector2DDomain,gameObjectFunction,bulletFunction,bulletLongevity);
		}
		isDischarge = false;
		dischargePoint = new Vector2D();
		aimingPoint = new Vector2D();
		dischargeInterval = 0;
		dischargeCount = 0;
		bulletInitVelocity = 15.0f;
	}
	
	public BulletGenerator(int bulletLimit,int bulletLongevity,BulletFunction bulletFunction){
		this.bulletFunction = bulletFunction;
		this.bulletLimit = bulletLimit;
		this.vector2DDomain = new Vector2DDomain();
		nowUseBuffer = 0;
		bullet = new Bullet[bulletLimit];
		this.bulletLongevity = bulletLongevity;
		gameObjectFunction = GameObjectFunctionFactory.makeNotProcessing();
		for(int i=0;i<bulletLimit;i++){
			bullet[i] = new Bullet(vector2DDomain,gameObjectFunction,bulletFunction,bulletLongevity);
		}
		isDischarge = false;
		dischargePoint = new Vector2D();
		aimingPoint = new Vector2D();
		dischargeInterval = 0;
		dischargeCount = 0;
		bulletInitVelocity = 15.0f;
	}
	
	public BulletGenerator(int bulletLimit,int bulletLongevity,GameObjectFunction gameObjectFunction,BulletFunction bulletFunction){
		this.bulletFunction = bulletFunction;
		this.bulletLimit = bulletLimit;
		this.vector2DDomain = new Vector2DDomain();
		nowUseBuffer = 0;
		bullet = new Bullet[bulletLimit];
		this.bulletLongevity = bulletLongevity;
		this.gameObjectFunction = gameObjectFunction;
		for(int i=0;i<bulletLimit;i++){
			bullet[i] = new Bullet(vector2DDomain,gameObjectFunction,bulletFunction,bulletLongevity);
		}
		isDischarge = false;
		dischargePoint = new Vector2D();
		aimingPoint = new Vector2D();
		dischargeInterval = 0;
		dischargeCount = 0;
		bulletInitVelocity = 15.0f;
	}
	
	private void bulletDischarge(){
		if(dischargeCount < 0){	// カウントが0になったら
			if(isDischarge){	// 発射中なら
				nowUseBuffer = Utile.UseBuffer(nowUseBuffer,bulletLimit);
				bullet[nowUseBuffer].initialize(dischargePoint,aimingPoint,bulletInitVelocity);
				dischargeCount = dischargeInterval;
			}
		}
		else{
			dischargeCount--;
		}
	}
	
	public void update(){
		for(int i=0;i<bulletLimit;i++){
			bullet[i].update();
		}
		bulletDischarge();
	}
}
