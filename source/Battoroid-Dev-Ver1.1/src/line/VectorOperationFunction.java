package line;

import function.Vector2D;
import function.Vector2DDomain;

public interface VectorOperationFunction {
	VectorOperationFunction compose(VectorOperationFunction f,float properSupplies);	//関数合成用関数
    Vector2D VectorOperation(Vector2DDomain vector2DDomain,Vector2D spd,Vector2D nor,float coefficient);
}