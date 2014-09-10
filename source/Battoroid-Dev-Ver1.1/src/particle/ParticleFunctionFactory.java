package particle;

import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObjectFunction;

public class ParticleFunctionFactory {
	
	/*�߂�l�F���������֐�*/
	/*���������֐��쐬*/
	public static ParticleFunction makeNotProcessing(){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				
			}
		};
	}
	
	/*�����F��Z����w�萔�l*/
	/*�߂�l�F�����܂ł̈ړ�������Z�֐�*/
	/*�����܂ł̈ړ�������Z�֐��֐��쐬*/
	public static ParticleFunction makeDisappearanceDistanceMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newdisappearanceDistance = particle.getDisappearanceDistance()*value;
				particle.setDisappearanceDistance(newdisappearanceDistance);
			}
		};
	}
	
	/*�����F���Z����w�萔�l*/
	/*�߂�l�F�����܂ł̈ړ��������Z�֐�*/
	/*�����܂ł̈ړ��������Z�֐��֐��쐬*/
	public static ParticleFunction makeDisappearanceDistanceAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newdisappearanceDistance = particle.getDisappearanceDistance()+value;
				particle.setDisappearanceDistance(newdisappearanceDistance);
			}
		};
	}
	
	/*�����F��Z����w�萔�l*/
	/*�߂�l�FR�l��Z�֐�*/
	/*R�l��Z�֐��֐��쐬*/
	public static ParticleFunction makeRedMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newRed = particle.getColorData().getR()*value;
				particle.getColorData().setR(newRed);
			}
		};
	}
	
	/*�����F���Z����w�萔�l*/
	/*�߂�l�FR�l���Z�֐�*/
	/*R�l���Z�֐��쐬*/
	public static ParticleFunction makeRedAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newRed = particle.getColorData().getR()+value;
				particle.getColorData().setR(newRed);
			}
		};
	}
	
	/*�����F��Z����w�萔�l*/
	/*�߂�l�FG�l��Z�֐�*/
	/*G�l��Z�֐��쐬*/
	public static ParticleFunction makeGreenMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newGreen = particle.getColorData().getG()*value;
				particle.getColorData().setG(newGreen);
			}
		};
	}
	
	/*�����F���Z����w�萔�l*/
	/*�߂�l�FG�l���Z�֐�*/
	/*G�l���Z�֐��쐬*/
	public static ParticleFunction makeGreenAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newGreen = particle.getColorData().getG()+value;
				particle.getColorData().setG(newGreen);
			}
		};
	}
	
	/*�����F��Z����w�萔�l*/
	/*�߂�l�FB�l��Z�֐�*/
	/*B�l��Z�֐��쐬*/
	public static ParticleFunction makeBlueMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newBlue = particle.getColorData().getB()*value;
				particle.getColorData().setB(newBlue);
			}
		};
	}
	
	/*�����F���Z����w�萔�l*/
	/*�߂�l�FB�l���Z�֐�*/
	/*B�l���Z�֐��쐬*/
	public static ParticleFunction makeBlueAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newBlue = particle.getColorData().getB()+value;
				particle.getColorData().setB(newBlue);
			}
		};
	}
	
	/*�����F��Z����w�萔�l*/
	/*�߂�l�F�A���t�@�l��Z�֐�*/
	/*�A���t�@�l��Z�֐��쐬*/
	public static ParticleFunction makeAlphaMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newAlpha = particle.getColorData().getA()*value;
				particle.getColorData().setA(newAlpha);
			}
		};
	}
	
	/*�����F���Z����w�萔�l*/
	/*�߂�l�F�A���t�@�l���Z�֐�*/
	/*�A���t�@�l���Z�֐��쐬*/
	public static ParticleFunction makeAlphaAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newAlpha = particle.getColorData().getA()+value;
				particle.getColorData().setA(newAlpha);
			}
		};
	}
	
	/*�����F��Z����w�萔�l*/
	/*�߂�l�F�傫����Z�֐�*/
	/*�傫����Z�֐��쐬*/
	public static ParticleFunction makeScaleMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newScale = particle.getScale()*value;
				particle.setScale(newScale);
			}
		};
	}
	
	/*�����F���Z����w�萔�l*/
	/*�߂�l�F�傫�����Z�֐�*/
	/*�傫�����Z�֐��쐬*/
	public static ParticleFunction makeSceleAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newScale = particle.getScale()+value;
				particle.setScale(newScale);
			}
		};
	}
	
	/*�����F��Z����w�萔�l*/
	/*�߂�l�F���x�x�N�g����Z�֐�*/
	/*���x�x�N�g����Z�֐��쐬*/
	public static ParticleFunction makeVectorMultiplication(final Vector2DDomain vector2DDomain,final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				Vector2D.Scale(particle.getVec(),particle.getVec(),value);
			}
		};
	}
	
	/*�����F���Z����w�萔�l*/
	/*�߂�l�F���x�x�N�g�����Z�֐�*/
	/*���x�x�N�g�����Z�֐��쐬*/
	public static ParticleFunction makeVectorAddition(final Vector2DDomain vector2DDomain,final Vector2D value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				Vector2D.Add(particle.getVec(),particle.getVec(),value);
			}
		};
	}
	
	/*�����F�w�肷������A���t�@�l*/
	/*�߂�l�F�w��A���t�@�l�ȉ������֐�*/
	/*�w��A���t�@�l�ȉ������֐��쐬*/
	public static ParticleFunction makeDesignationAlphaIsNotExistence(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				if(particle.getColorData().getA()<value){particle.setIsExistence(false);}
			}
		};
	}
	
	/*�����FGameObject�ɂ�����֐�*/
	/*�߂�l�FGameObject�ɂ�����֐����s�֐�*/
	/*GameObject�ɂ�����֐����s�֐��쐬*/
	public static ParticleFunction makePlayObject2DFunction(final GameObjectFunction function){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				function.changeState(particle);
			}
		};
	}
}
