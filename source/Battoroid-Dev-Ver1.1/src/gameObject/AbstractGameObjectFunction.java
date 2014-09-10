package gameObject;

import object2D.Object2D;

public abstract class AbstractGameObjectFunction implements GameObjectFunction{
	@Override
	public abstract void changeState(Object2D object);					// �I�u�W�F�N�g�ɂ����鏈��
	
	@Override
    public GameObjectFunction compose(final GameObjectFunction f) {		// �֐������p�֐�
        return new AbstractGameObjectFunction() {
			@Override
			public void changeState(Object2D object) {
				f.changeState(object);
				AbstractGameObjectFunction.this.changeState(object);
			}
        };
    }
}
