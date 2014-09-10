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
	/*�e�ϐ���Base �} Margin�@�͈̔͂Ńp�[�e�B�N���𐶐�����ׂ̂���*/
	/*������*/
	private int generatBaseNum;
	private int generatNumMargin;
	
	/*�C���^�[�o��*/
	private int generatBaseInterval;
	private int generatIntervalMargin;
	private int generatCount;							// �����J�E���g

	/*�����p�x*/
	private int generatBaseAngle;
	private int generatAngleMargin;

	/*�e�p�̊p�x�덷*/
	private int angleMargin;

	/*�����x*/
	private float initialBaseVelocity;
	private float initialVelocityMargin;

	/*�p�[�e�B�N�����ł܂ł̈ړ�����*/
	private float disappearanceBaseDistance;
	private float disappearanceDistanceMargin;

	/*�p�[�e�B�N���̑傫��*/
	private float particleBaseScale;
	private float particleScaleMargin;

	/*�������W*/
	private Vector2D generatBasePoint;
	private Vector2D generatPointMargin;

	/*��*/
	private float baceR;
	private float RMargin;

	/*��*/
	private float baceG;
	private float GMargin;

	/*��*/
	private float baceB;
	private float BMargin;

	/*�A���t�@*/
	private float baceA;
	private float AMargin;

	private ParticleFunction particleFunction;			// �p�[�e�B�N���̃X�e�[�^�X�ύX�֐�

	private boolean isDischarge;						// �p�[�e�B�N�����������ǂ���
	
	private GeneratMode generatMode;					// ��������
	private BlendMode blendMode;						// ��������
	
	private ColorData colorData;						// �F�f�[�^
	
	private int generatLimit;							// �������E��
	private Particle[] particle;						// �p�[�e�B�N��
	private int nowUseBuffer;							// ���ݎg�p���̃o�b�t�@�ʒu

	private String textureKey;							// �e�N�X�`���̃L�[
	
	private boolean isDraw;
	private Vector2DDomain vector2DDomain;
	/*�e�Z�b�^�[*/
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
	
	/*�e�Q�b�^�[*/
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
	
	/*������*/
	public void initialize(String textureKey,ParticleFunction particleFunction){
		generatLimit = 100;
		nowUseBuffer = 0;
		particle = new Particle[generatLimit];
		this.textureKey = textureKey;
		this.particleFunction = particleFunction;
	}
	
	/*�f�t�H���g�ݒ�̃Z�b�g*/
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

	/*�X�V����*/
	public void update(){
		ParticleUpdate();
		ParticleGenerat();
	}
	
	/*�`�揈��*/
	public void render(){
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);					// �e�N�X�`���̗L����
		int texture = TextureManager.getTexture(textureKey);			
		drawParticles(particle,texture);
	}
	
	/*���̐��l�ƃ����_�������痐���l�����߂�*/
	private float calculatMargin(Random random,float base,float margin){
		float inLimitNum = base + (random.nextFloat()-0.5f) * margin;
		return inLimitNum;
	}
	
	/*���̐��l�ƃ����_�������痐���l�����߂�*/
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
	
	/*�p�[�e�B�N���̈�ĕ`��*/
	public void drawParticles(Particle[] particles,int textureID){
		if(!isDraw)			return;
		
		// �u�����h�̎w��
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
	
	/*�p�[�e�B�N���X�V����*/
	private void ParticleUpdate(){
		for(int i=0;i<generatLimit;i++){
			if(particle[i].getIsExistence()){
				particleFunction.changeState(particle[i]);
				particle[i].update();	//�p�[�e�B�N���̍X�V
			}
		}
	}
	
	/*�p�[�e�B�N����������*/
	public void ParticleGenerat(){
		if(generatCount <= 0){	// �J�E���g��0�ɂȂ�����
			Random random = new Random();
			/*�p�[�e�B�N������*/
			if(isDischarge){	// ���˂��Ă���Ȃ琶��
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
			generatCount = calculatMargin(random,generatBaseInterval,generatIntervalMargin);	//���̃J�E���g�𐶐�
		}
		
		// �J�E���g�_�E��
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
				// �����x����{�l���痐���͈͓̔��œK�p�����l
				float initialVelocity = calculatMargin(random,initialBaseVelocity,initialVelocityMargin);
				// ���ł܂ł̋�������{�l���痐���͈͓̔��œK�p�����l
				float disappearanceDistance = calculatMargin(random,disappearanceBaseDistance,disappearanceDistanceMargin);
				
				colorData = new ColorData();
				// RGBA�l�S�ĂɊ�{�l���痐���l�͈͓̔��œK�p�����l��F�f�[�^�ɃZ�b�g����
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
				
				// �p�[�e�B�N������ԖڕW�n�_
				Vector2D targetPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
				
				// �p�[�e�B�N�������ʒu
				Vector2D generatPoint = Utile.rotatePoint(calculatMargin(random,angle,angleMargin),disappearanceDistance,targetPoint);
				
				// ������
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,initialVelocity,calculatMargin(random,particleBaseScale,particleScaleMargin));
			}
			angle += angleAdditionNum;	// �p���ɉ������p�x�ǉ�
		}
	}
	
	private void ModeEmission(Random random,int generatNum){
		int angleAdditionNum = 360/generatNum;
		int angle = calculatMargin(random,generatBaseAngle,generatAngleMargin);
		for(int i=0;i<generatNum;i++){
			if(nowUseBuffer+1 < generatLimit){
				nowUseBuffer++;	// �g�p�o�b�t�@�̈ړ�
			}
			else{
				nowUseBuffer = 0;	// �g�p�o�b�t�@�̃��Z�b�g
			}
			if(!particle[nowUseBuffer].getIsExistence()){
				// �����x����{�l���痐���͈͓̔��œK�p�����l
				float initialVelocity = calculatMargin(random,initialBaseVelocity,initialVelocityMargin);
				// ���ł܂ł̋�������{�l���痐���͈͓̔��œK�p�����l
				float disappearanceDistance = calculatMargin(random,disappearanceBaseDistance,disappearanceDistanceMargin);
				
				colorData = new ColorData();
				// RGBA�l�S�ĂɊ�{�l���痐���l�͈͓̔��œK�p�����l��F�f�[�^�ɃZ�b�g����
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
				
				// �p�[�e�B�N�������ʒu
				Vector2D generatPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
				
				// �p�[�e�B�N������ԖڕW�n�_
				Vector2D targetPoint = Utile.rotatePoint(calculatMargin(random,angle,angleMargin),disappearanceDistance,generatPoint);
				
				// ������
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,initialVelocity,calculatMargin(random,particleBaseScale,particleScaleMargin));
				
			}
			angle += angleAdditionNum;	// �p���ɉ������p�x�ǉ�
		}
	}
		
	private void ModeSameAngle(Random random,int generatNum){
		int angle = calculatMargin(random,generatBaseAngle,generatAngleMargin);
		for(int i=0;i<generatNum;i++){
			if(nowUseBuffer+1 < generatLimit){
				nowUseBuffer++;	// �g�p�o�b�t�@�̈ړ�
			}
			else{
				nowUseBuffer = 0;	// �g�p�o�b�t�@�̃��Z�b�g
			}
			if(!particle[nowUseBuffer].getIsExistence()){
				// �����x����{�l���痐���͈͓̔��œK�p�����l
				float initialVelocity = calculatMargin(random,initialBaseVelocity,initialVelocityMargin);
				// ���ł܂ł̋�������{�l���痐���͈͓̔��œK�p�����l
				float disappearanceDistance = calculatMargin(random,disappearanceBaseDistance,disappearanceDistanceMargin);
					
				colorData = new ColorData();
				// RGBA�l�S�ĂɊ�{�l���痐���l�͈͓̔��œK�p�����l��F�f�[�^�ɃZ�b�g����
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
					
				// �p�[�e�B�N�������ʒu
				Vector2D generatPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
					
				// �p�[�e�B�N������ԖڕW�n�_
				Vector2D targetPoint = Utile.rotatePoint(calculatMargin(random,angle,angleMargin),disappearanceDistance,generatPoint);
					
				// ������
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,initialVelocity,calculatMargin(random,particleBaseScale,particleScaleMargin));
			}
		}
	}
	
	public void ModePlace(Random random,int generatNum){
		for(int i=0;i<generatNum;i++){
			nowUseBuffer = Utile.UseBuffer(nowUseBuffer,generatLimit);
			if(!particle[nowUseBuffer].getIsExistence()){
				colorData = new ColorData();
				// RGBA�l�S�ĂɊ�{�l���痐���l�͈͓̔��œK�p�����l��F�f�[�^�ɃZ�b�g����
				colorData.setRGBA(calculatMargin(random,baceR,RMargin),calculatMargin(random,baceG,GMargin),calculatMargin(random,baceB,BMargin),calculatMargin(random,baceA,AMargin));
				
				// �p�[�e�B�N������ԖڕW�n�_
				Vector2D targetPoint = new Vector2D();
				
				// �p�[�e�B�N�������ʒu
				Vector2D generatPoint = new Vector2D(calculatMargin(random,generatBasePoint.x,generatPointMargin.x),calculatMargin(random,generatBasePoint.y,generatPointMargin.y));
				
				// ������
				particle[nowUseBuffer].initialize(generatPoint,targetPoint,colorData,0,calculatMargin(random,particleBaseScale,particleScaleMargin));
			}
		}
	}
}
