package line;

import function.Vector2D;
import function.Vector2DDomain;

public abstract class AbstractVectorOperationFunction implements VectorOperationFunction {
    @Override
    public VectorOperationFunction compose(final VectorOperationFunction f,final float properSupplies) {		//ä÷êîçáê¨ópä÷êî
        return new AbstractVectorOperationFunction() {
			@Override
			public Vector2D VectorOperation(Vector2DDomain vector2DDomain,Vector2D spd,Vector2D nor,float coefficient) {
				return AbstractVectorOperationFunction.this.VectorOperation(vector2DDomain,f.VectorOperation(vector2DDomain,spd,nor,coefficient * properSupplies),nor,coefficient);
			}
        };
    }
    @Override
    public abstract  Vector2D VectorOperation(Vector2DDomain vector2DDomain,Vector2D spd,Vector2D nor,float coefficient);
}
