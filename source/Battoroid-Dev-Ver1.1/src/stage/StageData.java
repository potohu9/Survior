package stage;

import line.CollisionLine;
import line.Line;
import line.CollisionLine.CollisionMode;
import object2D.Object2D;
import openGLES20.Common;
import function.Graphics;
import function.Graphics.BlendMode;
import function.TextureManager;
import function.Vector2D;
import gameObject.AbstractGameObjectFunction;
import gameObject.Enemy;
import gameObject.EnemyList;
import gameObject.GameObjectFunctionFactory;
import gameObject.Player;
import gameObject.EnemyList.EnemyName;

public class StageData {
	private static int stageNum = 11;
	
	public static int getStageNum()					{return stageNum;}
	
	public static InitPlaySceneFunction getStageData(int stageNumber){
		switch(stageNumber){
		case 0:
			return Stage1();
		case 1:
			return Stage2();
		case 2:
			return Stage3();
		case 3:
			return Stage4();
		case 4:
			return Stage5();
		case 5:
			return Stage6();
		case 6:
			return Stage7();
		case 7:
			return Stage8();
		case 8:
			return Stage9();
		case 9:
			return Stage10();
		case 10:
			return Stage11();
		default:
			return Stage1();	
		}
	}
	
	private static InitPlaySceneFunction Stage1(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Player initPlayer() {
				player = new Player(TextureManager.getTexture("Player"),
						new CollisionLine(CollisionMode.REFLECT),
						15,
						20,
						new AbstractGameObjectFunction(){
							@Override
							public void changeState(Object2D object) {
								if(player.getIsExistence()){
									player.setIsGrounding(slideLine.collision(player,0.95f));
									if(Vector2D.Length(vector2DDomain,player.getVec()) > player.getRad()){
										player.setVec(Vector2D.Scale(vector2DDomain,
												Vector2D.Normalize(vector2DDomain,
												player.getVec()),player.getRad()).getClone());
									}
								}
							}
						}.compose(
						GameObjectFunctionFactory.makeCanMovementDesignationRange(new Vector2D(0,0),new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH())).compose(
						GameObjectFunctionFactory.makeSetGravity(vector2DDomain,new Vector2D(0.0f,1.5f)).compose(
						GameObjectFunctionFactory.makeMoveObject(vector2DDomain)
						)))){
					@Override
					public void render(){
						Graphics.drawTexture(TextureManager.getTexture("Guide"),BlendMode.ALPHA);
						super.render();
					}
				};
				player.setRad(40);
				player.setPos(new Vector2D(Common.getScreenProportionW()/8,Common.getScreenProportionH()/2));
				
				player.getCollisionLine().addLine(line);
				return player;
			}
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[1];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.ZAKO);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
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
	
	private static InitPlaySceneFunction Stage2(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[2];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.ZAKO);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
				enemy[1] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.ZAKO);
				enemy[1].setPos(new Vector2D(Common.getScreenProportionW()/4,Common.getScreenProportionH()/2-200));
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
	
	private static InitPlaySceneFunction Stage3(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[3];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.TANK);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
				enemy[1] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.TANK);
				enemy[1].setPos(new Vector2D(Common.getScreenProportionW()/4,Common.getScreenProportionH()/2-100));
				enemy[2] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.TANK);
				enemy[2].setPos(new Vector2D(Common.getScreenProportionW()/3,Common.getScreenProportionH()/2-300));
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
	
	private static InitPlaySceneFunction Stage4(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[4];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.DAMAGE_DEALER);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
				enemy[1] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.TANK);
				enemy[1].setPos(new Vector2D(Common.getScreenProportionW()/4,Common.getScreenProportionH()/2-200));
				enemy[2] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.TANK);
				enemy[2].setPos(new Vector2D(Common.getScreenProportionW()/3,Common.getScreenProportionH()/2-100));	
				enemy[3] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.TANK);
				enemy[3].setPos(new Vector2D(Common.getScreenProportionW()/3,Common.getScreenProportionH()/2+300));
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
	
	private static InitPlaySceneFunction Stage5(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[1];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.DAMAGE_DEALER);
				enemy[0].setHitPoint(30);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
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
	
	private static InitPlaySceneFunction Stage6(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[2];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.DAMAGE_DEALER);
				enemy[0].setHitPoint(20);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
				enemy[1] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.DAMAGE_DEALER);
				enemy[1].setHitPoint(20);
				enemy[1].setPos(new Vector2D(Common.getScreenProportionW()/4,Common.getScreenProportionH()/2-200));
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
	
	private static InitPlaySceneFunction Stage7(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[2];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.DAMAGE_DEALER);
				enemy[0].setHitPoint(20);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
				enemy[1] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.DAMAGE_DEALER);
				enemy[1].setHitPoint(20);
				enemy[1].setPos(new Vector2D(Common.getScreenProportionW()/4,Common.getScreenProportionH()/2-200));
				return enemy;
			}

			@Override
			public Line[] initLine() {
				line = new Line[7];
				line[0] = underLine;
				line[1] = leftLine;
				line[2] = topLine;
				line[3] = rightLine;
				line[4] = new Line(new Vector2D(700,600),new Vector2D(500,300));
				line[5] = new Line(new Vector2D(900,500),new Vector2D(700,600));
				line[6] = new Line(new Vector2D(500,300),new Vector2D(900,500));
				return line;
			}
		};
	}
	
	private static InitPlaySceneFunction Stage8(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[1];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.SP_ENEMY1);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
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
	
	private static InitPlaySceneFunction Stage9(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[1];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.SP_ENEMY2);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
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
	
	private static InitPlaySceneFunction Stage10(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[1];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.SP_ENEMY3);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
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
	
	private static InitPlaySceneFunction Stage11(){
		return new AbstractInitPlaySceneFunction(){
			@Override
			public Enemy[] initEnemy() {
				enemy = new Enemy[2];
				enemy[0] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.SP_ENEMY3);
				enemy[0].setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
				enemy[0].setHitPoint(10);
				enemy[1] = EnemyList.getEnemy(vector2DDomain,line,enemy,player,slideLine,refrectLine,EnemyName.SP_ENEMY2);
				enemy[1].setPos(new Vector2D(Common.getScreenProportionW()/4*3,Common.getScreenProportionH()/2-200));
				enemy[1].setHitPoint(10);
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
}
