package line;

import object2D.Object2D;
import function.Vector2D;
import function.Vector2DDomain;

public class CollisionPostprocessing{
	// ê¸Ç…è’ìÀÇµÇΩèÍçá
	public static void segment(Vector2DDomain vector2DDomain,Object2D object,Line line,float coefficient,VectorOperationFunction f){
		Vector2D s = Vector2D.Normalize(vector2DDomain,
				Vector2D.Sub(vector2DDomain,line.endPos,line.startPos));		
		Vector2D n = Vector2D.Perp(vector2DDomain,
				s);
		object.setVec(f.VectorOperation(vector2DDomain,object.getVec(),n,coefficient).getClone());
	}
	// ì_Ç…è’ìÀÇµÇΩèÍçá
	public static void point(Vector2DDomain vector2DDomain,Object2D object,Vector2D hitPosition,float coefficient,VectorOperationFunction f){
		Vector2D s = Vector2D.Sub(vector2DDomain,object.getPos(),hitPosition);
		object.setVec(f.VectorOperation(vector2DDomain,object.getVec(),s,coefficient).getClone());
	}
}
