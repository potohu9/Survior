package object2D;

import function.Vector2D;

public class Object2D {
	protected Vector2D pos;
	protected Vector2D vec;
	protected float rad;
	protected int angle;
	protected boolean isExistence;			// 存在しているか
	
	public Object2D(){
		pos = new Vector2D();
		vec = new Vector2D();
		rad = 0;
		angle = 0;
	}
	
	public void setPos(Vector2D pos) {
		this.pos = pos;
	}
	public void setVec(Vector2D vec) {
		this.vec = vec;
	}
	public void setRad(float rad) {
		this.rad = rad;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public void setIsExistence(boolean isExistence) {
		this.isExistence = isExistence;
	}

	public Vector2D getPos()		{return pos;}
	public Vector2D getVec()		{return vec;}
	public float getRad()			{return rad;}
	public int getAngle()			{return angle;}
	public boolean getIsExistence()	{return isExistence;}
	
	// オブジェクトの全滅判定
	public static boolean isAnnihilation(Object2D... object){
		for(int i=0;i<object.length;i++){
			if(object[i].getIsExistence())	return false;
		}
		return true;
	}
	
	// オブジェクトの非全滅判定
	public static boolean isNotAnnihilation(Object2D... object){
		for(int i=0;i<object.length;i++){
			if(object[i].getIsExistence())	return true;
		}
		return false;
	}
}
