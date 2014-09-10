package particle;

import openGLES20.Common;
import function.AbstractRunnable;
import function.Utile;
import function.Vector2D;
import function.Graphics.BlendMode;
import function.Vector2DDomain;

public class TriggerEffect {
	private Vector2DDomain vector2DDomain;
	private final int effectBufferNum;
	private ParticleSystem[] hitEffect;
	private int nowUseParticleBuffer;
	
	private int[] particleCount;
	private final int particleTime;
	
	private Runnable effectThread;
	private Thread thread;
	
	private boolean isPlayThread;
	
	/*各セッター*/
	public void setParticleCount(int[] particleCount) {
		this.particleCount = particleCount;
	}
	public void setHitEffect(ParticleSystem[] hitEffect) {
		this.hitEffect = hitEffect;
	}
	public void setHitEffect(ParticleSystem hitEffect) {
		for(int i=0;i<this.hitEffect.length;i++){
			this.hitEffect[i] = hitEffect.getClone();
		}
	}
	
	/*各ゲッター*/
	public int getEffectBufferNum()				{return effectBufferNum;}
	public ParticleSystem[] getHitEffect()		{return hitEffect;}
	public ParticleSystem getNowUseHitEffect()	{return hitEffect[nowUseParticleBuffer];}
	public ParticleSystem getNextUseHitEffect()	{return hitEffect[Utile.UseBuffer(nowUseParticleBuffer,effectBufferNum)];}
	public int[] getParticleCount()				{return particleCount;}	
	public int getParticleTime()				{return particleTime;}
	
	public TriggerEffect(){
		vector2DDomain = new Vector2DDomain();
		nowUseParticleBuffer = 0;
		this.effectBufferNum = 5;
		this.particleTime = 3;
		
		hitEffect = new ParticleSystem[effectBufferNum];
		particleCount = new int[effectBufferNum];
		for(int i=0;i<effectBufferNum;i++){
			hitEffect[i] = new ParticleSystem();
			particleCount[i] = particleTime;
			
			hitEffect[i].initialize("Star",ParticleFunctionFactory.makeNotProcessing());
			
			hitEffect[i].setDefault();
			
			hitEffect[i].setAngleMargin(20);
			hitEffect[i].setBlendMode(BlendMode.ADDITION);
			hitEffect[i].setDisappearanceDistance(80,20);
			hitEffect[i].setGeneratAngle(0,360);
			hitEffect[i].setGeneratInterval(0,0);
			hitEffect[i].setGeneratMode(GeneratMode.EMISSION);
			hitEffect[i].setGeneratNum(8,4);
			hitEffect[i].setGeneratPoint(new Vector2D(),new Vector2D(10,10));
			hitEffect[i].setInitialVecocity(3,2);
			hitEffect[i].setIsDischarge(false);
			hitEffect[i].setParticleScale(30,20);
			hitEffect[i].setA(1.0f,0.5f);
			hitEffect[i].setR(0.4f,0.6f);
			hitEffect[i].setG(0.4f,0.6f);
			hitEffect[i].setB(0.0f,1.0f);
		}
		initThread();
	}
	
	public TriggerEffect(int effectBufferNum,int particleTime,ParticleSystem hitEffect){
		vector2DDomain = new Vector2DDomain();
		nowUseParticleBuffer = 0;
		this.effectBufferNum = effectBufferNum;
		this.particleTime = particleTime;
		
		this.hitEffect = new ParticleSystem[effectBufferNum];
		particleCount = new int[effectBufferNum];
		for(int i=0;i<effectBufferNum;i++){
			this.hitEffect[i] = hitEffect.getClone();
			this.hitEffect[i].setVector2DDomain(vector2DDomain);
		}
		initThread();
	}
	
	public void update(){
		for(int i=0;i<effectBufferNum;i++){
			hitEffect[i].update();
			if(hitEffect[i].getIsDischarge()){
				particleCount[i]--;
				if(particleCount[i]<=0){
					hitEffect[i].setIsDischarge(false);
				}
			}
		}
	}
	
	public void EffectTriggerOn(Vector2D triggerPos){
		nowUseParticleBuffer = Utile.UseBuffer(nowUseParticleBuffer,effectBufferNum);
		particleCount[nowUseParticleBuffer] = particleTime;
		hitEffect[nowUseParticleBuffer].setIsDischarge(true);
		hitEffect[nowUseParticleBuffer].setGeneratPoint(triggerPos,hitEffect[nowUseParticleBuffer].getGeneratPointMargin());
	}
	
	public void initThread(){
		isPlayThread = true;
		effectThread = new AbstractRunnable(){
			@Override
			public void run() {
				while(isPlayThread){
					try{
						Thread.sleep(Common.getMaxFPS());
					}
					catch (InterruptedException e){
						
					}
					update();
				}
			}
		};
		thread = new Thread(effectThread);
		thread.start();
	}
	
	public void finalize(){
		isPlayThread = false;
		thread = null;
		effectThread = null;
	}
}
