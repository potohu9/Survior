package selectCursor;

import particle.ParticleFunctionFactory;
import particle.ParticleSystem;
import function.Utile;
import function.Vector2D;
import gameObject.GameObject;

public class Cursor extends GameObject{
	private int[] angle;
	private ParticleSystem[] particleSystem;
	private float rotVel;
	private int particleNum;
	
	public void setRotVel(float rotVel){
		this.rotVel = rotVel;
	}
	
	public float getRotVel()	{return rotVel;}
	
	public Cursor(){
		super();
		particleNum = 3;
		particleSystem = new ParticleSystem[particleNum];
		angle = new int[particleNum];
		int angleAdditionNum = 360/particleNum;
		int angle = 0;
		rotVel = 1;
		for(int i=0;i<particleNum;i++){
			particleSystem[i] = new ParticleSystem();
			particleSystem[i].initialize("Light",
					ParticleFunctionFactory.makeNotProcessing());
			particleSystem[i].setDefault();
			this.angle[i] = angle;
			angle += angleAdditionNum;
		}
	}
	
	public Cursor(int particleNum,ParticleSystem[] particleSystem){
		super();
		this.particleNum = particleNum;
		this.particleSystem = new ParticleSystem[particleNum];
		angle = new int[particleNum];
		int angleAdditionNum = 360/particleNum;
		int angle = 0;
		rotVel = 1;
		this.particleSystem = particleSystem;
		for(int i=0;i<particleNum;i++){
			this.angle[i] = angle;
			angle += angleAdditionNum;
		}
	}
	
	@Override
	public void update(){
		super.update();
		for(int i=0;i<particleNum;i++){
			Vector2D pos = Utile.rotatePoint(angle[i],rad,this.pos);
			particleSystem[i].setGeneratPoint(pos,particleSystem[i].getGeneratPointMargin());
			particleSystem[i].update();
			angle[i] += rotVel;
		}
	}
}
