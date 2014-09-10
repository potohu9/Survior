package bullet;

import object2D.Object2D;
import particle.ParticleSystem;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObject;
import gameObject.GameObjectFunction;

public class Bullet extends GameObject{
	private Vector2DDomain vector2DDomain;
	private int longevity;					// ��{����
	private int longevityCount;				// �����J�E���g
	private Vector2D generatPointMargin;	// �p�[�e�B�N���������̐����ʒu�덷
	private ParticleSystem renderParticle;	// �\���p�[�e�B�N��
	private BulletFunction bulletFunction;	// �e�̓���
	
	Bullet(Vector2DDomain vector2DDomain,GameObjectFunction gameObjectFunction,BulletFunction bulletFunction,int longevity) {
		this.vector2DDomain = vector2DDomain;
		pos = new Vector2D();
		vec = new Vector2D();
		isExistence = false;
		this.longevity = longevity;
		longevityCount = 0;
		renderParticle = new ParticleSystem();
		this.bulletFunction = bulletFunction;
		this.function = gameObjectFunction;
		bulletFunction.initBulletParticle(renderParticle);
		generatPointMargin = renderParticle.getGeneratPointMargin();
		rad = renderParticle.getParticleBaseScale()*0.5f;
		renderParticle.setIsDraw(false);
	}
	
	/*�e�Z�b�^�[*/
	public void setLongevity(int longevity) {
		this.longevity = longevity;
	}
	public void setLongevityCount(int longevityCount) {
		this.longevityCount = longevityCount;
	}
	public void setBulletFunction(BulletFunction bulletFunction){
		this.bulletFunction = bulletFunction;
	}
	
	/*�e�Q�b�^�[*/
	public int getLongevity()					{return longevity;}
	public int getLongevityCount()				{return longevityCount;}
	public ParticleSystem getRenderParticle()	{return renderParticle;}
	
	public void initialize(Vector2D dischargePoint,Vector2D aimingPoint,float velocity){
		pos = dischargePoint;
		Vector2D.Scale(vec,
				Vector2D.Normalize(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				aimingPoint,dischargePoint)),velocity);
		isExistence = true;
		longevityCount = longevity;
		renderParticle.setIsDraw(true);
	}
	
	public void update(){
		super.update();
		if(Vector2D.Length(vector2DDomain,vec) >= rad){
			Vector2D.Scale(vec,rad,
				Vector2D.Normalize(vec,vec));
		}
		bulletFunction.changeState(this);
		if(longevityCount < 0){
			isExistence = false;
		}
		if(isExistence){
			Vector2D.Add(pos,pos,vec);
			longevityCount--;
		}
		else if(!Object2D.isNotAnnihilation(renderParticle.getParticle())){
			renderParticle.setIsDraw(false);
		}
		renderParticle.setGeneratPoint(pos,generatPointMargin);
		renderParticle.setIsDischarge(isExistence);
		rad = renderParticle.getParticleBaseScale()*0.5f;
		renderParticle.update();
	}
}
