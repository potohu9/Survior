package collision;

import function.Utile;
import function.Vector2D;
import function.Vector2DDomain;
import object2D.Object2D;
import line.Line;

public class Collision{
	private static Vector2DDomain vector2DDomain;
	
	static{
		vector2DDomain = new Vector2DDomain();
	}
	
	public static boolean circleToCircle
	(float obj1_pos_x,float obj1_pos_y,float obj1_radius,
	float obj2_pos_x,float obj2_pos_y,float obj2_radius){

		float dx	= obj2_pos_x - obj1_pos_x;
		float dy	= obj2_pos_y - obj1_pos_y;
		float r		= obj2_radius + obj1_radius;

		return ((dx * dx) + (dy * dy) < (r * r));
	}
	
	public static boolean circleToCircle(Object2D obj1,Object2D obj2){

		float dx	= obj2.getPos().x - obj1.getPos().x;
		float dy	= obj2.getPos().y - obj1.getPos().y;
		float r		= obj2.getRad()*0.5f + obj1.getRad()*0.5f;

		return ((dx * dx) + (dy * dy) < (r * r));
	}
	
	public static boolean circleToCircle(Vector2D pos1,float rad1,Vector2D pos2,float rad2){

		float dx	= pos2.x - pos1.x;
		float dy	= pos2.y - pos1.y;
		float r		= rad1 + rad2;

		return ((dx * dx) + (dy * dy) < (r * r));
	}
	
	public static boolean squareToSquare
	(float obj1_pos_x,float obj1_pos_y,float obj1_offset_x,float obj1_offset_y,
	float obj2_pos_x,float obj2_pos_y,float obj2_offset_x,float obj2_offset_y){
		return (obj2_pos_x + obj2_offset_x > obj1_pos_x - obj1_offset_x
				&&obj2_pos_x - obj2_offset_x < obj1_pos_x + obj1_offset_x
				&&obj2_pos_y + obj2_offset_y > obj1_pos_y - obj1_offset_y
				&&obj2_pos_y - obj2_offset_y < obj1_pos_y + obj1_offset_y);
	}
	
	// 円と線分のあたり判定
	public static boolean segmentToCircle(Object2D object,Line line){
		Vector2D normal = Vector2D.Normalize(vector2DDomain,
				Vector2D.Perp(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				line.endPos,line.startPos)));
		Vector2D invertScale = Vector2D.Scale(vector2DDomain,
				normal,object.getRad());
		return segmentToSegment(line.startPos,line.endPos,object.getPos(),Vector2D.Add(vector2DDomain,
				object.getPos(),invertScale));
	}
	
	// 線分と線分のあたり判定
	public static boolean segmentToSegment(Vector2D a1,Vector2D a2,Vector2D b1,Vector2D b2){
		// 外積:axb = ax*by - ay*bx
		// 外積と使用して交差判定を行なう
		float v1 = (a2.x - a1.x) * (b1.y - a1.y) - (a2.y - a1.y) * (b1.x - a1.x);
		float v2 = (a2.x - a1.x) * (b2.y - a1.y) - (a2.y - a1.y) * (b2.x - a1.x);
		float m1 = (b2.x - b1.x) * (a1.y - b1.y) - (b2.y - b1.y) * (a1.x - b1.x);
		float m2 = (b2.x - b1.x) * (a2.y - b1.y) - (b2.y - b1.y) * (a2.x - b1.x);
		// +-,-+だったら-値になるのでそれぞれを掛けて確認する
		return (v1 * v2 <= 0) && (m1 * m2 <= 0);
	}
	
	// 扇状の当たり判定
	public static boolean Flabellate(Vector2D point1,float rad1,Vector2D point2,float rad2,int angle1,int angle2){
		if(Collision.circleToCircle(point1.x,point1.y,rad1,point2.x,point2.y,rad2)){
			float baceAngle = (angle1 + angle2) / 2;
			float collisionAngle = Math.abs(angle1 - angle2) / 2;
			Vector2D baceVector = Utile.rotatePoint(baceAngle,rad1,new Vector2D(0,0));
			float angle = Vector2D.AngleOf2Vector(vector2DDomain,
					Vector2D.Sub(vector2DDomain,
					point2,point1),baceVector);
			if(angle < collisionAngle){
				return true;
			}
		}
		return false;
	}
}