package gameObject;

import function.Vector2D;

public interface OptionFunction {
	public void initialize();
	public void update(gameObject.Options options,Vector2D aimingPoint);
	OptionFunction compose(OptionFunction f);
}
