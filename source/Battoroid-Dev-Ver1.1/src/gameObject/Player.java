package gameObject;

import line.CollisionLine;
import openGLES20.Common;
import particle.ParticleFunctionFactory;
import particle.ParticleSystem;
import selectCursor.Cursor;
import selectCursor.SelectCursor;
import bullet.BulletFactory;
import bullet.BulletGenerator;
import function.ColorData;
import function.Graphics;
import function.TextureManager;
import function.Utile;
import function.Vector2D;
import function.Vector2DDomain;
import function.Graphics.BlendMode;
import gauge.Gauge;

public class Player extends GameObject {
	private Vector2DDomain vector2DDomain;
	private boolean isGrounding;
	private boolean isPlayJump;
	private BulletGenerator[] bulletGenerator;
	private final int bulletTypeNum = 3;
	private GameObject[] select;
	private SelectCursor selectCursor;
	private CollisionLine collisionLine;
	private int hitPoint;
	private int maxHitPoint;
	private int jumpCount;
	private final int jumpInterval;
	private Enemy[] myEnemy;
	private Gauge playerHPGauge;
	private float[] UIVertex;
	private float[] UIUV;
	private byte[] UIIndex;
	private ColorData UIColor;		// UI‚ÌF
	
	public Player(int texture,CollisionLine collisionLine,int maxHitPoint,int jumpInterval,GameObjectFunction function) {
		super(texture,function);
		vector2DDomain = new Vector2DDomain();
		isGrounding = false;
		isPlayJump = false;
		this.jumpInterval = jumpInterval;
		this.collisionLine = collisionLine;
		this.maxHitPoint = maxHitPoint;
		myEnemy = new Enemy[0];
		initBullet();
		initCursor();
		initUI();
		Vector2D gaugeAreaStartPos = new Vector2D(100,Common.getScreenProportionH()-100);
		Vector2D gaugeAreaEndPos = new Vector2D(880,Common.getScreenProportionH());
		ColorData gaugeColor = new ColorData();
		gaugeColor.setARGB(1,1,1,1);
		playerHPGauge = new Gauge(	vector2DDomain,
									maxHitPoint,0,maxHitPoint,
									gaugeAreaStartPos,gaugeAreaEndPos,
									gaugeColor);
		hitPoint = maxHitPoint;
	}
	
	public void setIsGrounding(boolean isGrounding) {
		this.isGrounding = isGrounding;
	}
	public void setIsPlayJump(boolean isPlayJump) {
		this.isPlayJump = isPlayJump;
	}
	public void setCollisionLine(CollisionLine collisionLine) {
		this.collisionLine = collisionLine;
	}
	public void setHitPoint(int hitPoint) {
		this.hitPoint = hitPoint;
		playerHPGauge.setNowValue(hitPoint);
	}
	public void setHomingObject(Enemy... myEnemy) {
		this.myEnemy = myEnemy;
		setHomingBulletFunction();
	}
	
	public CollisionLine getCollisionLine()					{return collisionLine;}
	public boolean getIsGrounding()							{return isGrounding;}
	public boolean getIsPlayJump()							{return isPlayJump;}
	public BulletGenerator getBulletGenerator(int index)	{return bulletGenerator[index];}
	public BulletGenerator[] getBulletGenerator()			{return bulletGenerator;}
	public SelectCursor getSelectCursor()					{return selectCursor;}
	public int getBulletTypeNum()							{return bulletTypeNum;}
	public int getHitPoint()								{return hitPoint;}
	public int getJumpInterval()							{return jumpInterval;}
	public Enemy[] getMyEnemy()						{return myEnemy;}
	
	@Override
	public void update(){
		super.update();
		if(hitPoint > maxHitPoint){
			hitPoint = maxHitPoint;
		}
		if(hitPoint <= 0){
			isExistence = false;
		}
		if(isGrounding){
			jumpCount = 0;
		}
		if(isExistence&&isPlayJump){
			if(jumpCount <= 0){
				Vector2D.Add(pos,pos,new Vector2D(0,-25));
				vec = new Vector2D(vec.x,-25);
				jumpCount = jumpInterval;
			}
			isPlayJump = false;
		}
		
		for(int i=0;i<bulletTypeNum;i++){
			bulletGenerator[i].setDischargePoint(pos);
			bulletGenerator[i].update();
			bulletGenerator[i].setIsDischarge(false);
		}
		if(jumpCount>0){
			jumpCount--;
		}
		playerHPGauge.update();
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	public void UIRender(){
		playerHPGauge.render();
		Graphics.drawTexture(UIVertex,UIUV,UIIndex,UIColor,TextureManager.getTexture("UI"),BlendMode.ALPHA);
		Graphics.drawTexture(TextureManager.getTexture("Frame"),BlendMode.ALPHA);
		selectCursor.render();
	}
	
	private void initBullet(){
		bulletGenerator = new BulletGenerator[bulletTypeNum];
		
		bulletGenerator[0] = BulletFactory.makePlayerRefrectBullet(vector2DDomain,collisionLine);
		bulletGenerator[1] = BulletFactory.makePlayerGravityBullet(vector2DDomain,collisionLine);
		bulletGenerator[2] = BulletFactory.makePlayerHomingBullet(vector2DDomain,collisionLine);
	}
	
	private void initCursor(){
		select = new GameObject[bulletTypeNum];
		String[] keys = {"Bullet1","Bullet2","Bullet3"};
		for(int i=0;i<bulletTypeNum;i++){
			select[i] = new GameObject(TextureManager.getTexture(keys[i]),
					GameObjectFunctionFactory.makeNotProcessing());
			select[i].setRad(35);
			select[i].setPos(new Vector2D(Common.getScreenProportionW()-(select[i].getRad()*(bulletTypeNum-i)*3),Common.getScreenProportionH()-50));
		}
		
		int paticleNum = 3;
		
		ParticleSystem[] particleSystem = new ParticleSystem[paticleNum];
		for(int i=0;i<paticleNum;i++){
			particleSystem[i] = new ParticleSystem();
			particleSystem[i].initialize("Light",
				ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0)).compose(
				ParticleFunctionFactory.makeAlphaMultiplication(0.8f).compose(
				ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.2f))));
		
			particleSystem[i].setDefault();
			particleSystem[i].setGeneratNum(1,0);
			particleSystem[i].setAngleMargin(30);
			particleSystem[i].setGeneratAngle(0,360);
			particleSystem[i].setInitialVecocity(3,1);
			particleSystem[i].setDisappearanceDistance(150,10);
			particleSystem[i].setParticleScale(40,10);
			particleSystem[i].setA(1.0f,0.0f);
			particleSystem[i].setR(0.8f,0.3f);
			particleSystem[i].setG(0.4f,0.2f);
			particleSystem[i].setB(0.2f,0.2f);
		}
		Cursor cursor = new Cursor(paticleNum,particleSystem);
		cursor.setRotVel(4);
		cursor.setRad(40);
		
		selectCursor = new SelectCursor(Math.round(bulletTypeNum/2),10,cursor,select);
	}
	
	private void setHomingBulletFunction(){
		bulletGenerator[2].setGameObjectFunction(GameObjectFunctionFactory.makeSpeedFixationApproachMultipleNearPoint(vector2DDomain,0.5f,myEnemy));
	}
	
	public void finalize(){
		selectCursor.finalize();
	}
	
	private void initUI(){
		Vector2D topLeft = new Vector2D(0,Common.getScreenProportionH()-100);
		Vector2D underRight = new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH());
		
		topLeft = Utile.translatScreenPoint(topLeft);
		underRight = Utile.translatScreenPoint(underRight);
		
		UIVertex = new float[]{
			topLeft.x,topLeft.y,0,
			topLeft.x,underRight.y,0,
			underRight.x,topLeft.y,0,
			underRight.x,underRight.y,0
		};
		UIUV = new float[]{
			0,0,
			0,1,
			1,0,
			1,1,
		};
		UIIndex = new byte[]{
			0,1,2,
			1,2,3,
		};
		UIColor = new ColorData();
		UIColor.setARGB(1,1,1,1);
	}
}
