package gameObject;

import object2D.Object2D;

public abstract class AbstractGameObjectFunction implements GameObjectFunction{
	@Override
	public abstract void changeState(Object2D object);					// オブジェクトにかける処理
	
	@Override
    public GameObjectFunction compose(final GameObjectFunction f) {		// 関数合成用関数
        return new AbstractGameObjectFunction() {
			@Override
			public void changeState(Object2D object) {
				f.changeState(object);
				AbstractGameObjectFunction.this.changeState(object);
			}
        };
    }
}
