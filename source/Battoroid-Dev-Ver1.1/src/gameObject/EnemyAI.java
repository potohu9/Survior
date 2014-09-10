package gameObject;

import line.CollisionLine;
import object2D.Object2D;
import openGLES20.Common;
import visionField.VisionFieldFunctionFactory;
import function.Vector2D;
import function.Vector2DDomain;

public class EnemyAI {
	public static GameObjectFunction AIPattern1(final Vector2DDomain vector2DDomain,	// 計算領域
												float bulletAvoidVisionField,			// 避ける弾を発見する視界の大きさ
												float bulletAvoidPrecision,				// 弾を避ける精度
												float playerApproachPrecision,			// プレイヤーに近づく精度
												float friendAvoidVisionField,			// 仲間を発見する視界の大きさ
												float friendAvoidPrecision,				// 仲間と離れる精度
												final Enemy enemy,						// 自分自身を持つ領域
												final Enemy[] enemyList,				// 仲間のリスト
												final Player player,					// プレイヤーデータ
												final CollisionLine slideLine,			// 壁刷りする線のデータ
												final CollisionLine refrectLine){		// 反射する線のデータ
		return GameObjectFunctionFactory.makeMoveObject(vector2DDomain).compose(
				GameObjectFunctionFactory.makeInversionDesignationRange(new Vector2D(Common.getScreenProportionW()/8*3,0),new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH())).compose(
				GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(bulletAvoidVisionField),
						bulletAvoidVisionField,bulletAvoidPrecision,enemy.getMoveSpeed(),player.getBulletGenerator(0).getBullet()).compose(
				GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(bulletAvoidVisionField),
						bulletAvoidVisionField,bulletAvoidPrecision,enemy.getMoveSpeed(),player.getBulletGenerator(1).getBullet()).compose(			
				GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(bulletAvoidVisionField),
						bulletAvoidVisionField,bulletAvoidPrecision,enemy.getMoveSpeed(),player.getBulletGenerator(2).getBullet()).compose(
				GameObjectFunctionFactory.makeSpeedLimitApproachMultipleNearPoint(vector2DDomain,
						playerApproachPrecision,enemy.getMoveSpeed(),player).compose(
				GameObjectFunctionFactory.makeSpeedLimitAvoidMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(friendAvoidVisionField),
						friendAvoidVisionField,friendAvoidPrecision,enemy.getMoveSpeed(),enemyList).compose(
				new AbstractGameObjectFunction(){
					@Override
					public void changeState(Object2D object) {
						if(enemy.getIsExistence()){
							refrectLine.collision(enemy,0.95f);
							if(Vector2D.Length(vector2DDomain,
									enemy.getVec()) > enemy.getRad()){
								Vector2D.Scale(enemy.getVec(),
										Vector2D.Normalize(vector2DDomain,
										enemy.getVec()),enemy.getRad());
							}
							if(player.getIsExistence()){
								for(int i=0;i<enemy.getBulletTypeNum();i++){
									enemy.getBulletGenerator(i).setAimingPoint(player.getPos());
									enemy.getBulletGenerator(i).setIsDischarge(true);
								}
							}
						}
					}
				}
		)))))));
	}
	
	public static GameObjectFunction AIPattern2(final Vector2DDomain vector2DDomain,	// 計算領域
												float bulletApproachVisionField,		// 避ける弾を発見する視界の大きさ
												float bulletApproachPrecision,			// 弾を避ける精度
												float playerApproachPrecision,			// プレイヤーに近づく精度
												float friendAvoidVisionField,			// 仲間を発見する視界の大きさ
												float friendAvoidPrecision,				// 仲間と離れる精度
												final Enemy enemy,						// 自分自身を持つ領域
												final Enemy[] enemyList,				// 仲間のリスト
												final Player player,					// プレイヤーデータ
												final CollisionLine slideLine,			// 壁刷りする線のデータ
												final CollisionLine refrectLine){		// 反射する線のデータ
		return 	GameObjectFunctionFactory.makeMoveObject(vector2DDomain).compose(
				GameObjectFunctionFactory.makeInversionDesignationRange(new Vector2D(Common.getScreenProportionW()/8*3,0),new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH())).compose(
				GameObjectFunctionFactory.makeSpeedFixationApproachMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(bulletApproachVisionField),
						bulletApproachVisionField,bulletApproachPrecision,enemy.getMoveSpeed(),player.getBulletGenerator(0).getBullet()).compose(
				GameObjectFunctionFactory.makeSpeedFixationApproachMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(bulletApproachVisionField),
						bulletApproachVisionField,bulletApproachPrecision,enemy.getMoveSpeed(),player.getBulletGenerator(1).getBullet()).compose(			
				GameObjectFunctionFactory.makeSpeedFixationApproachMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(bulletApproachVisionField),
						bulletApproachVisionField,bulletApproachPrecision,enemy.getMoveSpeed(),player.getBulletGenerator(2).getBullet()).compose(
				GameObjectFunctionFactory.makeSpeedLimitApproachMultipleNearPoint(vector2DDomain,
						playerApproachPrecision,enemy.getMoveSpeed(),player).compose(
				GameObjectFunctionFactory.makeSpeedLimitAvoidMultipleNearPoint(vector2DDomain,
						VisionFieldFunctionFactory.makeCircleVisionField(friendAvoidVisionField),
						friendAvoidVisionField,friendAvoidPrecision,enemy.getMoveSpeed(),enemyList).compose(
				new AbstractGameObjectFunction(){
					@Override
					public void changeState(Object2D object) {
						if(enemy.getIsExistence()){
							refrectLine.collision(enemy,0.95f);
							if(Vector2D.Length(vector2DDomain,
									enemy.getVec()) > enemy.getRad()){
								Vector2D.Scale(enemy.getVec(),
										Vector2D.Normalize(vector2DDomain,
												enemy.getVec()),enemy.getRad());
							}
							if(player.getIsExistence()){
								for(int i=0;i<enemy.getBulletTypeNum();i++){
									enemy.getBulletGenerator(i).setAimingPoint(player.getPos());
									enemy.getBulletGenerator(i).setIsDischarge(true);
								}
							}
						}	
					}
				}
		)))))));
	}
}
