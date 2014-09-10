package stage;

import line.CollisionLine;
import line.Line;
import gameObject.Enemy;
import gameObject.Player;

public interface InitPlaySceneFunction {
	public Player initPlayer();
	public Enemy[] initEnemy();
	public Line[] initLine();
	public CollisionLine initSlideLine();
	public CollisionLine initRefrectLine();
}
