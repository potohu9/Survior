package gameObject;

import line.CollisionLine;
import bullet.BulletFactory;
import bullet.BulletGenerator;
import function.Vector2D;
import function.Vector2DDomain;

public class Enemy extends GameObject {
	protected Vector2DDomain vector2DDomain;
	protected boolean isGrounding;
	protected boolean isPlayJump;
	protected final int bulletTypeNum = 1;
	protected BulletGenerator[] bulletGenerator;
	protected CollisionLine collisionLine;
	protected int hitPoint;
	protected float moveSpeed;
	protected Player myEnemy;
	
	public Enemy(int texture,CollisionLine collisionLine,GameObjectFunction function) {
		super(texture,function);
		vector2DDomain = new Vector2DDomain();
		isGrounding = false;
		isPlayJump = false;
		moveSpeed = 3;
		this.collisionLine = collisionLine;
		initBullet();
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
	}
	public void setMoveSpeed(float f) {
		this.moveSpeed = f;
	}
	public void setMyEnemy(Player myEnemy){
		this.myEnemy = myEnemy;
	}
	
	public CollisionLine getCollisionLine()					{return collisionLine;}
	public boolean getIsGrounding()							{return isGrounding;}
	public boolean getIsPlayJump()							{return isPlayJump;}
	public BulletGenerator[] getBulletGenerator()			{return bulletGenerator;}
	public BulletGenerator getBulletGenerator(int index)	{return bulletGenerator[index];}
	public int getHitPoint()								{return hitPoint;}
	public float getMoveSpeed()								{return moveSpeed;}
	public int getBulletTypeNum()							{return bulletTypeNum;}

	@Override
	public void update(){
		super.update();
		if(hitPoint <= 0){
			isExistence = false;
		}
		if(isPlayJump&&isExistence){
			if(isGrounding){
				Vector2D.Add(pos,pos,new Vector2D(0,-25));
				vec = new Vector2D(vec.x,-25);
			}
			isPlayJump = false;
		}
		for(int i=0;i<bulletTypeNum;i++){
			bulletGenerator[i].setDischargePoint(pos);
			bulletGenerator[i].update();
			bulletGenerator[i].setIsDischarge(false);
		}
	}
	
	@Override
	public void render(){
		super.render();
	}
	
	protected void initBullet(){
		bulletGenerator = new BulletGenerator[bulletTypeNum];
		bulletGenerator[0] = BulletFactory.makeEnemyPenetrationBullet(vector2DDomain);
	}
}
