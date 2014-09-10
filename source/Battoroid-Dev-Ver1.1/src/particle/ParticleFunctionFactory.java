package particle;

import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObjectFunction;

public class ParticleFunctionFactory {
	
	/*戻り値：処理無し関数*/
	/*処理無し関数作成*/
	public static ParticleFunction makeNotProcessing(){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				
			}
		};
	}
	
	/*引数：乗算する指定数値*/
	/*戻り値：消失までの移動距離乗算関数*/
	/*消失までの移動距離乗算関数関数作成*/
	public static ParticleFunction makeDisappearanceDistanceMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newdisappearanceDistance = particle.getDisappearanceDistance()*value;
				particle.setDisappearanceDistance(newdisappearanceDistance);
			}
		};
	}
	
	/*引数：加算する指定数値*/
	/*戻り値：消失までの移動距離加算関数*/
	/*消失までの移動距離加算関数関数作成*/
	public static ParticleFunction makeDisappearanceDistanceAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newdisappearanceDistance = particle.getDisappearanceDistance()+value;
				particle.setDisappearanceDistance(newdisappearanceDistance);
			}
		};
	}
	
	/*引数：乗算する指定数値*/
	/*戻り値：R値乗算関数*/
	/*R値乗算関数関数作成*/
	public static ParticleFunction makeRedMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newRed = particle.getColorData().getR()*value;
				particle.getColorData().setR(newRed);
			}
		};
	}
	
	/*引数：加算する指定数値*/
	/*戻り値：R値加算関数*/
	/*R値加算関数作成*/
	public static ParticleFunction makeRedAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newRed = particle.getColorData().getR()+value;
				particle.getColorData().setR(newRed);
			}
		};
	}
	
	/*引数：乗算する指定数値*/
	/*戻り値：G値乗算関数*/
	/*G値乗算関数作成*/
	public static ParticleFunction makeGreenMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newGreen = particle.getColorData().getG()*value;
				particle.getColorData().setG(newGreen);
			}
		};
	}
	
	/*引数：加算する指定数値*/
	/*戻り値：G値加算関数*/
	/*G値加算関数作成*/
	public static ParticleFunction makeGreenAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newGreen = particle.getColorData().getG()+value;
				particle.getColorData().setG(newGreen);
			}
		};
	}
	
	/*引数：乗算する指定数値*/
	/*戻り値：B値乗算関数*/
	/*B値乗算関数作成*/
	public static ParticleFunction makeBlueMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newBlue = particle.getColorData().getB()*value;
				particle.getColorData().setB(newBlue);
			}
		};
	}
	
	/*引数：加算する指定数値*/
	/*戻り値：B値加算関数*/
	/*B値加算関数作成*/
	public static ParticleFunction makeBlueAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newBlue = particle.getColorData().getB()+value;
				particle.getColorData().setB(newBlue);
			}
		};
	}
	
	/*引数：乗算する指定数値*/
	/*戻り値：アルファ値乗算関数*/
	/*アルファ値乗算関数作成*/
	public static ParticleFunction makeAlphaMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newAlpha = particle.getColorData().getA()*value;
				particle.getColorData().setA(newAlpha);
			}
		};
	}
	
	/*引数：加算する指定数値*/
	/*戻り値：アルファ値加算関数*/
	/*アルファ値加算関数作成*/
	public static ParticleFunction makeAlphaAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newAlpha = particle.getColorData().getA()+value;
				particle.getColorData().setA(newAlpha);
			}
		};
	}
	
	/*引数：乗算する指定数値*/
	/*戻り値：大きさ乗算関数*/
	/*大きさ乗算関数作成*/
	public static ParticleFunction makeScaleMultiplication(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newScale = particle.getScale()*value;
				particle.setScale(newScale);
			}
		};
	}
	
	/*引数：加算する指定数値*/
	/*戻り値：大きさ加算関数*/
	/*大きさ加算関数作成*/
	public static ParticleFunction makeSceleAddition(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				float newScale = particle.getScale()+value;
				particle.setScale(newScale);
			}
		};
	}
	
	/*引数：乗算する指定数値*/
	/*戻り値：速度ベクトル乗算関数*/
	/*速度ベクトル乗算関数作成*/
	public static ParticleFunction makeVectorMultiplication(final Vector2DDomain vector2DDomain,final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				Vector2D.Scale(particle.getVec(),particle.getVec(),value);
			}
		};
	}
	
	/*引数：加算する指定数値*/
	/*戻り値：速度ベクトル加算関数*/
	/*速度ベクトル加算関数作成*/
	public static ParticleFunction makeVectorAddition(final Vector2DDomain vector2DDomain,final Vector2D value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				Vector2D.Add(particle.getVec(),particle.getVec(),value);
			}
		};
	}
	
	/*引数：指定する消失アルファ値*/
	/*戻り値：指定アルファ値以下消失関数*/
	/*指定アルファ値以下消失関数作成*/
	public static ParticleFunction makeDesignationAlphaIsNotExistence(final float value){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				if(particle.getColorData().getA()<value){particle.setIsExistence(false);}
			}
		};
	}
	
	/*引数：GameObjectにかける関数*/
	/*戻り値：GameObjectにかける関数実行関数*/
	/*GameObjectにかける関数実行関数作成*/
	public static ParticleFunction makePlayObject2DFunction(final GameObjectFunction function){
		return new AbstractParticleFunction(){
			@Override
			public void changeState(Particle particle) {
				function.changeState(particle);
			}
		};
	}
}
