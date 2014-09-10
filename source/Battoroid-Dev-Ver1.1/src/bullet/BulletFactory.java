package bullet;

import line.CollisionLine;
import openGLES20.Common;
import particle.GeneratMode;
import particle.ParticleFunctionFactory;
import particle.ParticleSystem;
import particle.TriggerEffect;
import function.ColorData;
import function.Vector2D;
import function.Vector2DDomain;
import function.Graphics.BlendMode;

public class BulletFactory {
	private static Vector2DDomain vector2DDomain;
	private static TriggerEffect hitEffect;
	
	private static final int EFFECT_ANGLE;
	
	static {
		EFFECT_ANGLE = 270;
		vector2DDomain = new Vector2DDomain();
	}
	
	public static void initHitEffect(){
		// 壁ヒット時エフェクト
		ParticleSystem particleSystem = new ParticleSystem();
		particleSystem.initialize("Light",
			ParticleFunctionFactory.makeAlphaAddition(-0.008f).compose(
			ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.00f).compose(
			ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0.0f)))));
		
		particleSystem.setDefault();
		
		particleSystem.setAngleMargin(20);
		particleSystem.setBlendMode(BlendMode.ADDITION);
		particleSystem.setDisappearanceDistance(400,40);
		particleSystem.setGeneratAngle(0,5);
		particleSystem.setGeneratInterval(0,0);
		particleSystem.setGeneratMode(GeneratMode.SAME_ANGLE);
		particleSystem.setGeneratNum(12,4);
		particleSystem.setGeneratPoint(new Vector2D(),new Vector2D(10,10));
		particleSystem.setInitialVecocity(6,2);
		particleSystem.setIsDischarge(false);
		particleSystem.setParticleScale(50,20);
		particleSystem.setA(0.2f,0.1f);
		particleSystem.setR(0.0f,0.5f);
		particleSystem.setG(1.0f,0.0f);
		particleSystem.setB(1.0f,0.0f);
		
		hitEffect = new TriggerEffect(8,2,particleSystem);
	}
	
	public static void finalizeEffect(){
		hitEffect.finalize();
	}
	
	/*敵用*/
	public static BulletGenerator makeEnemyPenetrationBullet(final Vector2DDomain vector2DDomain){
		
		ColorData bulletColor = new ColorData();
		bulletColor.setARGB(0.2f,0.5f,1,1);	// パーティクルの基本色
		ColorData bulletColorMargin = new ColorData();
		bulletColorMargin.setARGB(0,0,0,0);	// パーティクルの色誤差
		BulletGenerator bulletGenerator = new BulletGenerator(
					10,					// 弾の最大数
					250,				// 弾の寿命
					/*パーティクルの設定*/
					// 合成方式
					BulletFunctionFactory.SetParticle.makeSetParticleBlend(BlendMode.SUBTRACTION).compose(
					// 生成方式
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratMode(GeneratMode.PLACE).compose(
					// 消失までの距離
					BulletFunctionFactory.SetParticle.makeSetParticleDisappearanceDistance(10,0).compose(
					// 生成数
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratNum(3,0).compose(
					// 生成位置誤差
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratPoint(new Vector2D(0,0)).compose(
					// 速度
					BulletFunctionFactory.SetParticle.makeSetParticleInitialVecocity(0,0)).compose(
					// 角度
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratAngle(0,0).compose(
					// 色
					BulletFunctionFactory.SetParticle.makeSetParticleColor(bulletColor,bulletColorMargin).compose(
					// インターバル
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratInterval(0,0).compose(
					// 大きさ	
					BulletFunctionFactory.SetParticle.makeSetParticleScale(50.0f,0).compose(
					// 他をデフォルトで設定
					BulletFunctionFactory.SetParticle.makeSetParticleDefault().compose(
					// テクスチャなどの初期化
					BulletFunctionFactory.SetParticle.makeParticleInitialaize("Light",
							// パーティクルにかける処理
							ParticleFunctionFactory.makeAlphaAddition(-0.05f).compose(
							ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0)).compose(
							ParticleFunctionFactory.makeDisappearanceDistanceAddition(-1))
							)).compose(
									
					/*弾にかける処理*/
					BulletFunctionFactory.BulletBehavior.makeVelocityMultiplication(vector2DDomain,1.02f))))))))))));
		
		bulletGenerator.setAimingPoint(new Vector2D(0,Common.getScreenProportionH()));
		bulletGenerator.setDischargeInterval(40);
		bulletGenerator.setBulletInitVelocity(3);
		bulletGenerator.setIsDischarge(true);
		return bulletGenerator;
	}
	
	public static BulletGenerator makeEnemyRefrectBullet(final Vector2DDomain vector2DDomain,final CollisionLine collisionLine){
		ColorData bulletColor = new ColorData();
		bulletColor.setARGB(0.2f,1,1,0.5f);	// パーティクルの基本色
		ColorData bulletColorMargin = new ColorData();
		bulletColorMargin.setARGB(0,0,0,0);	// パーティクルの色誤差
		BulletGenerator bulletGenerator = new BulletGenerator(
					30,					// 弾の最大数
					250,				// 弾の寿命
					/*パーティクルの設定*/
					// 合成方式
					BulletFunctionFactory.SetParticle.makeSetParticleBlend(BlendMode.SUBTRACTION).compose(
					// 生成方式
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratMode(GeneratMode.PLACE).compose(
					// 消失までの距離
					BulletFunctionFactory.SetParticle.makeSetParticleDisappearanceDistance(10,0).compose(
					// 生成数
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratNum(3,0).compose(
					// 生成位置誤差
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratPoint(new Vector2D(0,0)).compose(
					// 速度
					BulletFunctionFactory.SetParticle.makeSetParticleInitialVecocity(0,0)).compose(
					// 角度
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratAngle(0,0).compose(
					// 色
					BulletFunctionFactory.SetParticle.makeSetParticleColor(bulletColor,bulletColorMargin).compose(
					// インターバル
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratInterval(0,0).compose(
					// 大きさ	
					BulletFunctionFactory.SetParticle.makeSetParticleScale(70.0f,0).compose(
					// 他をデフォルトで設定
					BulletFunctionFactory.SetParticle.makeSetParticleDefault().compose(
					// テクスチャなどの初期化
					BulletFunctionFactory.SetParticle.makeParticleInitialaize("Light",
							// パーティクルにかける処理
							ParticleFunctionFactory.makeAlphaAddition(-0.05f).compose(
							ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0)).compose(
							ParticleFunctionFactory.makeDisappearanceDistanceAddition(-1))
							)).compose(
									
					/*弾にかける処理*/
					BulletFunctionFactory.BulletBehavior.makeVelocityMultiplication(vector2DDomain,1.0f).compose(
					new AbstractBulletFunction(){
						@Override
						public void initBulletParticle(
							ParticleSystem renderParticle) {
						}

						@Override
						public void changeState(Bullet bullet) {
							if(collisionLine.collision(bullet,1.0f)){
								hitEffect.getNextUseHitEffect().setBlendMode(BlendMode.SUBTRACTION);
								hitEffect.getNextUseHitEffect().setA(0.2f,0.1f);
								hitEffect.getNextUseHitEffect().setR(1.0f,0.0f);
								hitEffect.getNextUseHitEffect().setG(1.0f,0.0f);
								hitEffect.getNextUseHitEffect().setB(0.5f,0.0f);
								// 壁ヒット時エフェクトの発射方向設定
								hitEffect.getNextUseHitEffect().setGeneratAngle(
									(int)Vector2D.Angle(vector2DDomain,bullet.getVec()) + EFFECT_ANGLE,
									hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
								// エフェクトの発生
								hitEffect.EffectTriggerOn(bullet.getPos().getClone());
							}
						}
					}))))))))))));
		
		bulletGenerator.setAimingPoint(new Vector2D(0,Common.getScreenProportionH()));
		bulletGenerator.setDischargeInterval(10);
		bulletGenerator.setBulletInitVelocity(15);
		bulletGenerator.setIsDischarge(true);
		
		return bulletGenerator;
	}
	
	/*プレイヤー用*/
	public static BulletGenerator makePlayerRefrectBullet(final Vector2DDomain vector2DDomain,final CollisionLine collisionLine){
		ColorData bulletColor = new ColorData();
		bulletColor.setARGB(0.2f,0.5f,1,1);	// パーティクルの基本色
		ColorData bulletColorMargin = new ColorData();
		bulletColorMargin.setARGB(0,0,0,0);	// パーティクルの色誤差
		BulletGenerator bulletGenerator = new BulletGenerator(
					10,					// 生成インターバル
					250,				// 弾の寿命
					/*パーティクルの設定*/
					// 合成方式
					BulletFunctionFactory.SetParticle.makeSetParticleBlend(BlendMode.ADDITION).compose(
					// 生成方式
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratMode(GeneratMode.PLACE).compose(
					// 消失までの距離
					BulletFunctionFactory.SetParticle.makeSetParticleDisappearanceDistance(10,0).compose(
					// 生成数
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratNum(3,0).compose(
					// 生成位置誤差
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratPoint(new Vector2D(0,0)).compose(
					// 速度
					BulletFunctionFactory.SetParticle.makeSetParticleInitialVecocity(0,0)).compose(
					// 角度
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratAngle(0,0).compose(
					// 色
					BulletFunctionFactory.SetParticle.makeSetParticleColor(bulletColor,bulletColorMargin).compose(
					// 他をデフォルトで設定
					BulletFunctionFactory.SetParticle.makeSetParticleDefault().compose(
					// テクスチャなどの初期化
					BulletFunctionFactory.SetParticle.makeParticleInitialaize("Light",// パーティクルにかける処理
							ParticleFunctionFactory.makeAlphaAddition(-0.05f).compose(
							ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0)).compose(
							ParticleFunctionFactory.makeDisappearanceDistanceAddition(-1)
							))).compose(
									
					/*弾にかける処理*/
					BulletFunctionFactory.BulletBehavior.makeVelocityMultiplication(vector2DDomain,1.0f).compose(
					new AbstractBulletFunction(){
						@Override
						public void initBulletParticle(
							ParticleSystem renderParticle) {
						}

						@Override
						public void changeState(Bullet bullet) {
							if(collisionLine.collision(bullet,1.0f)){
								hitEffect.getNextUseHitEffect().setBlendMode(BlendMode.ADDITION);
								hitEffect.getNextUseHitEffect().setA(0.2f,0.1f);
								hitEffect.getNextUseHitEffect().setR(0.5f,0.0f);
								hitEffect.getNextUseHitEffect().setG(1.0f,0.0f);
								hitEffect.getNextUseHitEffect().setB(1.0f,0.0f);
								// 壁ヒット時エフェクトの発射方向設定
								hitEffect.getNextUseHitEffect().setGeneratAngle(
									(int)Vector2D.Angle(vector2DDomain,bullet.getVec()) + EFFECT_ANGLE,
									hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
								// エフェクトの発生
								hitEffect.EffectTriggerOn(bullet.getPos().getClone());
							}
						}
					}))))))))));
		
		bulletGenerator.setAimingPoint(new Vector2D(0,Common.getScreenH()));
		bulletGenerator.setDischargeInterval(10);
		bulletGenerator.setBulletInitVelocity(15);
		bulletGenerator.setIsDischarge(true);
		return bulletGenerator;
	}
	
	public static BulletGenerator makePlayerGravityBullet(final Vector2DDomain vector2DDomain,final CollisionLine collisionLine){
		ColorData bulletColor = new ColorData();
		bulletColor.setARGB(0.2f,1.0f,0.75f,0.75f);	// パーティクルの基本色
		ColorData bulletColorMargin = new ColorData();
		bulletColorMargin.setARGB(0,0,0,0);	// パーティクルの色誤差
		BulletGenerator bulletGenerator = new BulletGenerator(
					20,					// 弾の最大数
					250,				// 弾の寿命
					/*パーティクルの設定*/
					// 合成方式
					BulletFunctionFactory.SetParticle.makeSetParticleBlend(BlendMode.ADDITION).compose(
					// 生成方式
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratMode(GeneratMode.PLACE).compose(
					// 消失までの距離
					BulletFunctionFactory.SetParticle.makeSetParticleDisappearanceDistance(10,0).compose(
					// 生成数
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratNum(3,0).compose(
					// 生成位置誤差
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratPoint(new Vector2D(0,0)).compose(
					// 速度
					BulletFunctionFactory.SetParticle.makeSetParticleInitialVecocity(0,0)).compose(
					// 角度
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratAngle(0,0).compose(
					// 色
					BulletFunctionFactory.SetParticle.makeSetParticleColor(bulletColor,bulletColorMargin).compose(
					// 大きさ	
					BulletFunctionFactory.SetParticle.makeSetParticleScale(120.0f,0).compose(
					// インターバル
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratInterval(0,0).compose(
					// 他をデフォルトで設定
					BulletFunctionFactory.SetParticle.makeSetParticleDefault().compose(
					// テクスチャなどの初期化
					BulletFunctionFactory.SetParticle.makeParticleInitialaize("Light",
							// パーティクルにかける処理
							ParticleFunctionFactory.makeAlphaAddition(-0.05f).compose(
							ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0)).compose(
							ParticleFunctionFactory.makeDisappearanceDistanceAddition(-1)
							))).compose(
									
					/*弾にかける処理*/
					BulletFunctionFactory.BulletBehavior.makeVelocityMultiplication(vector2DDomain,1.0f).compose(
					BulletFunctionFactory.BulletBehavior.makeVelocityAddition(vector2DDomain,new Vector2D(0,0.25f)).compose(
					new AbstractBulletFunction(){
						@Override
						public void initBulletParticle(
							ParticleSystem renderParticle) {
						}

						@Override
						public void changeState(Bullet bullet) {
							if(collisionLine.collision(bullet,1.2f)){
								hitEffect.getNextUseHitEffect().setBlendMode(BlendMode.ADDITION);
								hitEffect.getNextUseHitEffect().setA(0.2f,0.1f);
								hitEffect.getNextUseHitEffect().setR(1.0f,0.0f);
								hitEffect.getNextUseHitEffect().setG(0.75f,0.0f);
								hitEffect.getNextUseHitEffect().setB(0.75f,0.0f);
								// 壁ヒット時エフェクトの発射方向設定
								hitEffect.getNextUseHitEffect().setGeneratAngle(
									(int)Vector2D.Angle(vector2DDomain,bullet.getVec()) + EFFECT_ANGLE,
									hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
								// エフェクトの発生
								hitEffect.EffectTriggerOn(bullet.getPos().getClone());
							}
						}
					})))))))))))));
		
		bulletGenerator.setAimingPoint(new Vector2D(0,Common.getScreenProportionH()));
		bulletGenerator.setDischargeInterval(15);
		bulletGenerator.setBulletInitVelocity(10);
		bulletGenerator.setIsDischarge(true);
		return bulletGenerator;
	}
	
	public static BulletGenerator makePlayerHomingBullet(final Vector2DDomain vector2DDomain,final CollisionLine collisionLine){
		ColorData bulletColor = new ColorData();
		bulletColor.setARGB(0.2f,0.7f,0.3f,1);	// パーティクルの基本色
		ColorData bulletColorMargin = new ColorData();
		bulletColorMargin.setARGB(0,0,0,0);	// パーティクルの色誤差
		BulletGenerator bulletGenerator = new BulletGenerator(
					10,					// 弾の最大数
					250,				// 弾の寿命
					/*パーティクルの設定*/
					// 合成方式
					BulletFunctionFactory.SetParticle.makeSetParticleBlend(BlendMode.ADDITION).compose(
					// 生成方式
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratMode(GeneratMode.PLACE).compose(
					// 消失までの距離
					BulletFunctionFactory.SetParticle.makeSetParticleDisappearanceDistance(10,0).compose(
					// 生成数
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratNum(3,0).compose(
					// 生成位置誤差
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratPoint(new Vector2D(0,0)).compose(
					// 速度
					BulletFunctionFactory.SetParticle.makeSetParticleInitialVecocity(0,0)).compose(
					// 角度
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratAngle(0,0).compose(
					// 色
					BulletFunctionFactory.SetParticle.makeSetParticleColor(bulletColor,bulletColorMargin).compose(
					// インターバル
					BulletFunctionFactory.SetParticle.makeSetParticleGeneratInterval(0,0).compose(
					// 大きさ	
					BulletFunctionFactory.SetParticle.makeSetParticleScale(70.0f,0).compose(
					// 他をデフォルトで設定
					BulletFunctionFactory.SetParticle.makeSetParticleDefault().compose(
					// テクスチャなどの初期化
					BulletFunctionFactory.SetParticle.makeParticleInitialaize("Light",
							// パーティクルにかける処理
							ParticleFunctionFactory.makeAlphaAddition(-0.05f).compose(
							ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0)).compose(
							ParticleFunctionFactory.makeDisappearanceDistanceAddition(-1))
							)).compose(
									
					/*弾にかける処理*/
					BulletFunctionFactory.BulletBehavior.makeVelocityMultiplication(vector2DDomain,1.02f).compose(
					new AbstractBulletFunction(){
						@Override
						public void initBulletParticle(
							ParticleSystem renderParticle) {
						}

						@Override
						public void changeState(Bullet bullet) {
							if(collisionLine.collision(bullet,1.0f)){
								hitEffect.getNextUseHitEffect().setBlendMode(BlendMode.ADDITION);
								hitEffect.getNextUseHitEffect().setA(0.2f,0.1f);
								hitEffect.getNextUseHitEffect().setR(0.7f,0.0f);
								hitEffect.getNextUseHitEffect().setG(0.3f,0.0f);
								hitEffect.getNextUseHitEffect().setB(1.0f,0.0f);
								Vector2D.Scale(bullet.getVec(),
										3,Vector2D.Normalize(vector2DDomain,
										bullet.getVec()));
								// 壁ヒット時エフェクトの発射方向設定
								hitEffect.getNextUseHitEffect().setGeneratAngle(
									(int)Vector2D.Angle(vector2DDomain,bullet.getVec()) + EFFECT_ANGLE,
									hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
								// エフェクトの発生
								hitEffect.EffectTriggerOn(bullet.getPos().getClone());
							}
						}
					}))))))))))));
		
		bulletGenerator.setAimingPoint(new Vector2D(0,Common.getScreenProportionH()));
		bulletGenerator.setDischargeInterval(20);
		bulletGenerator.setBulletInitVelocity(3.0f);
		bulletGenerator.setIsDischarge(true);
		return bulletGenerator;
	}
}
