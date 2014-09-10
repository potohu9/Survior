package bullet;

import openGLES20.Common;
import function.ColorData;
import function.Graphics.BlendMode;
import function.Vector2D;
import function.Vector2DDomain;
import particle.GeneratMode;
import particle.ParticleFunction;
import particle.ParticleFunctionFactory;
import particle.ParticleSystem;

public class BulletFunctionFactory {
	
	/*--------------------------------�e�̊O���ݒ�n�֐�--------------------------------*/
	public static class SetParticle{
		/*�߂�l�F���^�̃p�[�e�B�N���e�����֐�*/
		/*���^�̃p�[�e�B�N���e�����֐��̐���*/
		public static BulletFunction makeFireParticle(){
			return new AbstractBulletFunction(){
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
		}
		
		/*�����F��������p�[�e�B�N���e�������ɕK�v�Ȓl*/
		/*�߂�l;��������p�[�e�B�N���e�������֐�*/
		/*��������p�[�e�B�N���e�������֐��̐���*/
		public static BulletFunction makeParticleInitialaize(final String key,final ParticleFunction particleFunction){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.initialize(key,particleFunction);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�߂�l;��������p�[�e�B�N���e���f�t�H���g�ɐݒ肷��֐�*/
		/*��������p�[�e�B�N���e���f�t�H���g�ɐݒ肷��֐��̐���*/
		public static BulletFunction makeSetParticleDefault(){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setDefault();
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N����������*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N�����������ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N�����������ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleGeneratMode(final GeneratMode generatMode){
			return new AbstractBulletFunction(){

				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setGeneratMode(generatMode);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N�������C���^�[�o������*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N�������C���^�[�o�����Ԑݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N�������C���^�[�o�����Ԑݒ�֐��̐���*/
		public static BulletFunction makeSetParticleGeneratInterval(final int base,final int margin){
			return new AbstractBulletFunction(){

				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setGeneratInterval(base,margin);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N�����ˊp�x*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N�����ˊp�x�ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N�����ˊp�x�ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleGeneratAngle(final int base,final int margin){
			return new AbstractBulletFunction(){

				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setGeneratAngle(base,margin);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N�������T�C�Y*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N�������T�C�Y�ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N�������T�C�Y�ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleScale(final float base,final float margin){
			return new AbstractBulletFunction(){

				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setParticleScale(base,margin);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N�������܂ł̋���*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N�������܂ł̋����ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N�������܂ł̋����ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleDisappearanceDistance(final float base,final float margin){
			return new AbstractBulletFunction(){

				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setDisappearanceDistance(base,margin);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N���̏������x*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N���̏������x�ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N���̏������x�ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleInitialVecocity(final float base,final float margin){
			return new AbstractBulletFunction(){

				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setInitialVecocity(base,margin);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̐������W*/
		/*�߂�l;��������p�[�e�B�N���e�̐������W�ݒ�֐�*/
		/*��������p�[�e�B�N���e�̐������W�ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleGeneratPoint(final Vector2D margin){
			return new AbstractBulletFunction(){

				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setGeneratPoint(new Vector2D(),margin);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N��������*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N���������ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N���������ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleGeneratNum(final int base,final int margin){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setGeneratNum(base,margin);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N�����̋����֐�*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N�������ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N�������ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleFunction(final ParticleFunction particleFunction){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setParticleFunction(particleFunction);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N����������*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N�����������ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N�����������ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleBlend(final BlendMode blendMode){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setBlendMode(blendMode);
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
		/*�����F��������p�[�e�B�N���e�̃p�[�e�B�N���F*/
		/*�߂�l;��������p�[�e�B�N���e�̃p�[�e�B�N���F�ݒ�֐�*/
		/*��������p�[�e�B�N���e�̃p�[�e�B�N���F�ݒ�֐��̐���*/
		public static BulletFunction makeSetParticleColor(final ColorData baseColorData,final ColorData marginColorData){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {
					renderParticle.setA(baseColorData.getA(),marginColorData.getA());
					renderParticle.setR(baseColorData.getR(),marginColorData.getR());
					renderParticle.setG(baseColorData.getG(),marginColorData.getG());
					renderParticle.setB(baseColorData.getB(),marginColorData.getB());
				}

				@Override
				public void changeState(Bullet bullet) {
					
				}
			};
		}
		
	}
	
	/*--------------------------------�e�̋����ݒ�n�֐�--------------------------------*/
	public static class BulletBehavior{
		/*�����F��Z����w�萔�l*/
		/*�߂�l�F�e�̑��x��Z�֐�*/
		/*�e�̑��x��Z�֐��쐬*/
		public static BulletFunction makeVelocityMultiplication(final Vector2DDomain vector2DDomain,final float value){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {

				}

				@Override
				public void changeState(Bullet bullet) {
					Vector2D.Scale(bullet.getVec(),
							bullet.getVec(),value);
				}
			};
		}
		
		/*�����F���Z����w�萔�l*/
		/*�߂�l�F�e�̑��x���Z�֐�*/
		/*�e�̑��x���Z�֐��쐬*/
		public static BulletFunction makeVelocityAddition(final Vector2DDomain vector2DDomain,final Vector2D value){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {

				}

				@Override
				public void changeState(Bullet bullet) {
					Vector2D.Add(bullet.getVec(),
							bullet.getVec(),value);
				}
			};
		}
		
		/*�����F���Z����傫��*/
		/*�߂�l�F�e�̑傫�����Z�֐�*/
		/*�e�̑傫�����Z�֐��쐬*/
		public static BulletFunction makeScaleAddition(final float value){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {

				}

				@Override
				public void changeState(Bullet bullet) {
					bullet.getRenderParticle().setParticleScale(bullet.getRenderParticle().getParticleBaseScale() + value,
						bullet.getRenderParticle().getParticleScaleMargin());
				}
			};
		}
		
		/*�����F���Z����w�萔�l*/
		/*�߂�l�F�e�̏�������܂ł̎��ԉ��Z�֐�*/
		/*�e�̏�������܂ł̎��ԉ��Z�֐��쐬*/
		public static BulletFunction makeLongevityCountAddition(final int value){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {

				}

				@Override
				public void changeState(Bullet bullet) {
					int newLongevityCount = bullet.getLongevityCount()+value;
					bullet.setLongevityCount(newLongevityCount);
				}
			};
		}
		
		/*�߂�l�F�e����ʊO�ɏo�����������֐�*/
		/*�e����ʊO�ɏo�����������֐��쐬*/
		public static BulletFunction makeNotInScreenIsNotExistence(){
			return new AbstractBulletFunction(){
				@Override
				public void initBulletParticle(ParticleSystem renderParticle) {

				}

				@Override
				public void changeState(Bullet bullet) {
					if(	bullet.getPos().x < 0 || bullet.getPos().x > Common.getScreenProportionW() ||
						bullet.getPos().y < 0 || bullet.getPos().y > Common.getScreenProportionH()){
						bullet.setIsExistence(false);
					}
				}
			};
		}
	}
}
