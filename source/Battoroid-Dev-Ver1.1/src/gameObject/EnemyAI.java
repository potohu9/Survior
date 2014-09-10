package gameObject;

import line.CollisionLine;
import object2D.Object2D;
import openGLES20.Common;
import visionField.VisionFieldFunctionFactory;
import function.Vector2D;
import function.Vector2DDomain;

public class EnemyAI {
	public static GameObjectFunction AIPattern1(final Vector2DDomain vector2DDomain,	// �v�Z�̈�
												float bulletAvoidVisionField,			// ������e�𔭌����鎋�E�̑傫��
												float bulletAvoidPrecision,				// �e������鐸�x
												float playerApproachPrecision,			// �v���C���[�ɋ߂Â����x
												float friendAvoidVisionField,			// ���Ԃ𔭌����鎋�E�̑傫��
												float friendAvoidPrecision,				// ���ԂƗ���鐸�x
												final Enemy enemy,						// �������g�����̈�
												final Enemy[] enemyList,				// ���Ԃ̃��X�g
												final Player player,					// �v���C���[�f�[�^
												final CollisionLine slideLine,			// �Ǎ��肷����̃f�[�^
												final CollisionLine refrectLine){		// ���˂�����̃f�[�^
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
	
	public static GameObjectFunction AIPattern2(final Vector2DDomain vector2DDomain,	// �v�Z�̈�
												float bulletApproachVisionField,		// ������e�𔭌����鎋�E�̑傫��
												float bulletApproachPrecision,			// �e������鐸�x
												float playerApproachPrecision,			// �v���C���[�ɋ߂Â����x
												float friendAvoidVisionField,			// ���Ԃ𔭌����鎋�E�̑傫��
												float friendAvoidPrecision,				// ���ԂƗ���鐸�x
												final Enemy enemy,						// �������g�����̈�
												final Enemy[] enemyList,				// ���Ԃ̃��X�g
												final Player player,					// �v���C���[�f�[�^
												final CollisionLine slideLine,			// �Ǎ��肷����̃f�[�^
												final CollisionLine refrectLine){		// ���˂�����̃f�[�^
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
