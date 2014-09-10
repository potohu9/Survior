package gameObject;

import bullet.BulletFactory;
import bullet.BulletGenerator;
import function.Vector2D;
import function.Vector2DDomain;
import function.Graphics.BlendMode;
import particle.ParticleFunctionFactory;
import particle.ParticleSystem;

public class Options extends GameObject{
	private Vector2DDomain vector2DDomain;
	private final int optionNum;
	private ParticleSystem[] options;
	private BulletGenerator[] bulletGenerator;
	private Vector2D aimingPoint;

	private OptionFunction optionFunction;
	
	Options(){
		super();
		vector2DDomain = new Vector2DDomain();
		optionNum = 5;
		options = new ParticleSystem[optionNum];
		for(int i=0;i<optionNum;i++){	
			options[i] = new ParticleSystem();
			options[i].initialize("Light",
				ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0)).compose(
				ParticleFunctionFactory.makeAlphaAddition(-0.1f).compose(
				ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.0f))));
			options[i].setDefault();
			options[i].setBlendMode(BlendMode.SUBTRACTION);
			options[i].setGeneratNum(1,0);
			options[i].setAngleMargin(30);
			options[i].setGeneratAngle(0,360);
			options[i].setInitialVecocity(3,1);
			options[i].setDisappearanceDistance(150,10);
			options[i].setParticleScale(80,20);
			options[i].setA(1.0f,0.0f);
			options[i].setR(0.2f,0.2f);
			options[i].setG(0.4f,0.2f);
			options[i].setB(0.8f,0.3f);
		}
		initBullet();
		
		for(int i=0;i<optionNum;i++){
			bulletGenerator[i].setDischargeInterval(5);
		}

		setOptionFunction(OptionType.Type1(optionNum,5,0,300,5));
	}
	
	Options(int optionNum){
		super();
		vector2DDomain = new Vector2DDomain();
		this.optionNum = optionNum;
		options = new ParticleSystem[optionNum];
		for(int i=0;i<optionNum;i++){	
			options[i] = new ParticleSystem();
			options[i].initialize("Light",
				ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0)).compose(
				ParticleFunctionFactory.makeAlphaAddition(-0.1f).compose(
				ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.0f))));
			options[i].setDefault();
			options[i].setBlendMode(BlendMode.SUBTRACTION);
			options[i].setGeneratNum(1,0);
			options[i].setAngleMargin(30);
			options[i].setGeneratAngle(0,360);
			options[i].setInitialVecocity(3,1);
			options[i].setDisappearanceDistance(150,10);
			options[i].setParticleScale(80,20);
			options[i].setA(1.0f,0.0f);
			options[i].setR(0.2f,0.2f);
			options[i].setG(0.4f,0.2f);
			options[i].setB(0.8f,0.3f);
		}
		initBullet();
		setOptionFunction(OptionType.Type1(optionNum,5,0,300,5));
	}

	public void setAimingPoint(Vector2D aimingPoint){
		this.aimingPoint = aimingPoint;
	}
	
	public void setOptionFunction(OptionFunction optionFunction){
		this.optionFunction = optionFunction;
		optionFunction.initialize();
	}
	
	public BulletGenerator[] getBulletGenerator()			{return bulletGenerator;}
	public BulletGenerator getBulletGenerator(int index)	{return bulletGenerator[index];}
	public ParticleSystem getOptions(int index)				{return options[index];}
	
	public void update(){
		optionFunction.update(this,aimingPoint);
	}
	
	private void initBullet(){
		bulletGenerator = new BulletGenerator[optionNum];
		for(int i=0;i<optionNum;i++){
			bulletGenerator[i] = BulletFactory.makeEnemyPenetrationBullet(vector2DDomain);
			bulletGenerator[i].setDischargeInterval(5);
		}
	}
}