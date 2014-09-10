package gameObject;

import object2D.Object2D;

public interface GameObjectFunction {
	void changeState(Object2D object);
	GameObjectFunction compose(GameObjectFunction f);
}
