package gameObject;

import function.Vector2D;

public abstract class AbstractOptionFunction implements OptionFunction{
	@Override
	public abstract  void initialize();
	@Override
	public abstract void update(gameObject.Options options,Vector2D aimingPoint);					// オブジェクトにかける処理
	
	@Override
    public OptionFunction compose(final OptionFunction f) {		// 関数合成用関数
        return new AbstractOptionFunction() {
			@Override
			public void initialize() {
				f.initialize();
				AbstractOptionFunction.this.initialize();
			}
			@Override
			public void update(gameObject.Options options,Vector2D aimingPoint) {
				f.update(options,aimingPoint);
				AbstractOptionFunction.this.update(options,aimingPoint);
			}
        };
    }
}
