package stage;

import object2D.Object2D;
import openGLES20.Common;
import function.TextureManager;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.AbstractGameObjectFunction;
import gameObject.Enemy;
import gameObject.GameObjectFunctionFactory;
import gameObject.Player;
import line.CollisionLine;
import line.CollisionLine.CollisionMode;
import line.Line;

public abstract class AbstractInitPlaySceneFunction implements InitPlaySceneFunction {
	protected static Vector2DDomain vector2DDomain;
	
	public static Player player;
	public static Enemy[] enemy;
	public static Line[] line;
	public static CollisionLine slideLine;
	public static CollisionLine refrectLine;
	
	public static Line underLine;
	public static Line leftLine;
	public static Line topLine;
	public static Line rightLine;
	
	static{
		vector2DDomain = new Vector2DDomain();
		Vector2D topLeft = new Vector2D(0,0);
		Vector2D topRight = new Vector2D(Common.getScreenProportionW(),0);
		Vector2D underLeft = new Vector2D(0,Common.getScreenProportionH()-100);
		Vector2D underRight = new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH()-100);
		
		underLine = new Line(underLeft,underRight);
		leftLine = new Line(topLeft,underLeft);
		topLine = new Line(topRight,topLeft);
		rightLine = new Line(underRight,topRight);
	}
	
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
				))));
		player.setRad(40);
		player.setPos(new Vector2D(Common.getScreenProportionW()/8,Common.getScreenProportionH()/2));
		
		player.getCollisionLine().addLine(line);
		return player;
	}
	
	@Override
	public abstract Enemy[] initEnemy();
	@Override
	public abstract Line[] initLine();

	@Override
	public CollisionLine initSlideLine() {
		slideLine = new CollisionLine(CollisionMode.SLIDE);
		slideLine.addLine(line);
		return slideLine;
	}

	@Override
	public CollisionLine initRefrectLine() {
		refrectLine = new CollisionLine(CollisionMode.REFLECT);
		refrectLine.addLine(line);
		return refrectLine;
	}
}
