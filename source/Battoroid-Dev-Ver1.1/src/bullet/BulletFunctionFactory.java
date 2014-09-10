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
	
	/*--------------------------------弾の外見設定系関数--------------------------------*/
	public static class SetParticle{
		/*戻り値：炎型のパーティクル弾生成関数*/
		/*炎型のパーティクル弾生成関数の生成*/
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
		
		/*引数：生成するパーティクル弾初期化に必要な値*/
		/*戻り値;生成するパーティクル弾初期化関数*/
		/*生成するパーティクル弾初期化関数の生成*/
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
		
		/*戻り値;生成するパーティクル弾をデフォルトに設定する関数*/
		/*生成するパーティクル弾をデフォルトに設定する関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル生成方式*/
		/*戻り値;生成するパーティクル弾のパーティクル生成方式設定関数*/
		/*生成するパーティクル弾のパーティクル生成方式設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル生成インターバル期間*/
		/*戻り値;生成するパーティクル弾のパーティクル生成インターバル期間設定関数*/
		/*生成するパーティクル弾のパーティクル生成インターバル期間設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル発射角度*/
		/*戻り値;生成するパーティクル弾のパーティクル発射角度設定関数*/
		/*生成するパーティクル弾のパーティクル発射角度設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル生成サイズ*/
		/*戻り値;生成するパーティクル弾のパーティクル生成サイズ設定関数*/
		/*生成するパーティクル弾のパーティクル生成サイズ設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル消失までの距離*/
		/*戻り値;生成するパーティクル弾のパーティクル消失までの距離設定関数*/
		/*生成するパーティクル弾のパーティクル消失までの距離設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクルの初期速度*/
		/*戻り値;生成するパーティクル弾のパーティクルの初期速度設定関数*/
		/*生成するパーティクル弾のパーティクルの初期速度設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾の生成座標*/
		/*戻り値;生成するパーティクル弾の生成座標設定関数*/
		/*生成するパーティクル弾の生成座標設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル生成数*/
		/*戻り値;生成するパーティクル弾のパーティクル生成数設定関数*/
		/*生成するパーティクル弾のパーティクル生成数設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル毎の挙動関数*/
		/*戻り値;生成するパーティクル弾のパーティクル挙動設定関数*/
		/*生成するパーティクル弾のパーティクル挙動設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル合成方式*/
		/*戻り値;生成するパーティクル弾のパーティクル合成方式設定関数*/
		/*生成するパーティクル弾のパーティクル合成方式設定関数の生成*/
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
		
		/*引数：生成するパーティクル弾のパーティクル色*/
		/*戻り値;生成するパーティクル弾のパーティクル色設定関数*/
		/*生成するパーティクル弾のパーティクル色設定関数の生成*/
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
	
	/*--------------------------------弾の挙動設定系関数--------------------------------*/
	public static class BulletBehavior{
		/*引数：乗算する指定数値*/
		/*戻り値：弾の速度乗算関数*/
		/*弾の速度乗算関数作成*/
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
		
		/*引数：加算する指定数値*/
		/*戻り値：弾の速度加算関数*/
		/*弾の速度加算関数作成*/
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
		
		/*引数：加算する大きさ*/
		/*戻り値：弾の大きさ加算関数*/
		/*弾の大きさ加算関数作成*/
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
		
		/*引数：加算する指定数値*/
		/*戻り値：弾の消失するまでの時間加算関数*/
		/*弾の消失するまでの時間加算関数作成*/
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
		
		/*戻り値：弾が画面外に出たら消失する関数*/
		/*弾が画面外に出たら消失する関数作成*/
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
