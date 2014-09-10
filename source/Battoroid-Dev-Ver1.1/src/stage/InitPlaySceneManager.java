package stage;

import object2D.Object2D;
import openGLES20.Common;
import visionField.VisionFieldFunctionFactory;
import function.TextureManager;
import function.Vector2D;
import gameObject.AbstractGameObjectFunction;
import gameObject.Enemy;
import gameObject.GameObjectFunctionFactory;
import line.CollisionLine;
import line.CollisionLine.CollisionMode;
import line.Line;

public class InitPlaySceneManager {
	private static InitPlaySceneFunction initPlaySceneFunction;
	
	static{
		// デフォルト設定
		initPlaySceneFunction = new AbstractInitPlaySceneFunction(){			
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[2];
				enemy[0] = new Enemy(TextureManager.getTexture("Doroid"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
				enemy[0].setRad(40);
				enemy[0].setVec(new Vector2D(3,5));
				enemy[0].setMoveSpeed(Vector2D.Length(vector2DDomain,enemy[0].getVec()));
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
				enemy[0].getCollisionLine().addLine(line);
				enemy[0].setHitPoint(15);
				enemy[0].setFunction(GameObjectFunctionFactory.makeMoveObject(vector2DDomain).compose(
					GameObjectFunctionFactory.makeInversionDesignationRange(new Vector2D(Common.getScreenProportionW()/8*2,0),new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH())).compose(
					GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
							VisionFieldFunctionFactory.makeCircleVisionField(800),
							600f,6f,enemy[0].getMoveSpeed(),player.getBulletGenerator(0).getBullet()).compose(
					GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
							VisionFieldFunctionFactory.makeCircleVisionField(800),
							600f,6f,enemy[0].getMoveSpeed(),player.getBulletGenerator(1).getBullet()).compose(			
					GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
							VisionFieldFunctionFactory.makeCircleVisionField(800),
							600f,6f,enemy[0].getMoveSpeed(),player.getBulletGenerator(2).getBullet()).compose(
					GameObjectFunctionFactory.makeSpeedLimitApproachMultipleNearPoint(vector2DDomain,
							2.8f,enemy[0].getMoveSpeed(),player).compose(
					new AbstractGameObjectFunction(){
						@Override
						public void changeState(Object2D object) {
							if(enemy[0].getIsExistence()){
								refrectLine.collision(enemy[0],1.0f);
								if(Vector2D.Length(vector2DDomain,enemy[0].getVec()) > enemy[0].getRad()){
									enemy[0].setVec(Vector2D.Scale(vector2DDomain,
											Vector2D.Normalize(vector2DDomain,
											enemy[0].getVec()),enemy[0].getRad()));
								}
								if(player.getIsExistence()){
									for(int i=0;i<enemy[0].getBulletTypeNum();i++){
										enemy[0].getBulletGenerator(i).setAimingPoint(player.getPos());
										enemy[0].getBulletGenerator(i).setIsDischarge(true);
									}
								}
							}
						}
					}
				)))))));
				for(int i=0;i<enemy[0].getBulletTypeNum();i++){
					enemy[0].getBulletGenerator(i).setDischargeInterval(50);
				}
				
				enemy[1] = new Enemy(TextureManager.getTexture("Doroid"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
				enemy[1].setRad(60);
				enemy[1].setVec(new Vector2D(3,5));
				enemy[1].setMoveSpeed(Vector2D.Length(vector2DDomain,enemy[1].getVec()));
				enemy[1].setPos(new Vector2D(Common.getScreenProportionW()/4,Common.getScreenProportionH()/2-500));
				enemy[1].getCollisionLine().addLine(line);
				enemy[1].setHitPoint(20);
				enemy[1].setFunction(GameObjectFunctionFactory.makeMoveObject(vector2DDomain).compose(
					GameObjectFunctionFactory.makeInversionDesignationRange(new Vector2D(Common.getScreenProportionW()/8*2,0),new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH())).compose(
					GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
							VisionFieldFunctionFactory.makeCircleVisionField(800),
							600f,6f,enemy[1].getMoveSpeed(),player.getBulletGenerator(0).getBullet()).compose(
					GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
							VisionFieldFunctionFactory.makeCircleVisionField(800),
							600f,6f,enemy[1].getMoveSpeed(),player.getBulletGenerator(1).getBullet()).compose(			
					GameObjectFunctionFactory.makeSpeedFixationAvoidMultipleNearPoint(vector2DDomain,
							VisionFieldFunctionFactory.makeCircleVisionField(800),
							600f,6f,enemy[1].getMoveSpeed(),player.getBulletGenerator(2).getBullet()).compose(
					GameObjectFunctionFactory.makeSpeedLimitApproachMultipleNearPoint(vector2DDomain,
							2.4f,enemy[1].getMoveSpeed(),player).compose(
					GameObjectFunctionFactory.makeSpeedLimitAvoidMultipleNearPoint(vector2DDomain,
							VisionFieldFunctionFactory.makeCircleVisionField(400),
							400f,4.4f,enemy[1].getMoveSpeed(),enemy).compose(
					new AbstractGameObjectFunction(){
						@Override
						public void changeState(Object2D object) {
							if(enemy[1].getIsExistence()){
								refrectLine.collision(enemy[1],1.0f);
								if(Vector2D.Length(vector2DDomain,enemy[1].getVec()) > enemy[1].getRad()){
									enemy[1].setVec(Vector2D.Scale(vector2DDomain,
											Vector2D.Normalize(vector2DDomain,
											enemy[1].getVec()),enemy[1].getRad()));
								}
								if(player.getIsExistence()){
									for(int i=0;i<enemy[1].getBulletTypeNum();i++){
										enemy[1].getBulletGenerator(i).setAimingPoint(player.getPos());
										enemy[1].getBulletGenerator(i).setIsDischarge(true);
									}
									
								}
							}
						}
					}
				))))))));
				for(int i=0;i<enemy[1].getBulletTypeNum();i++){
					enemy[1].getBulletGenerator(i).setDischargeInterval(50);
				}
				return enemy;
			}

			@Override
			public Line[] initLine() {
				line = new Line[4];
				line[0] = underLine;
				line[1] = leftLine;
				line[2] = topLine;
				line[3] = rightLine;
				return line;
			}
		};
	}
	
	public static void setInitPlaySceneFunction(InitPlaySceneFunction initPlaySceneFunction) {
		InitPlaySceneManager.initPlaySceneFunction = initPlaySceneFunction;
	}

	public static InitPlaySceneFunction getInitPlaySceneFunction()	{return initPlaySceneFunction;}
}
