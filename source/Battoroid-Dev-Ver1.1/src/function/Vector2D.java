package function;

public class Vector2D {
	public float x;
	public float y;
	
	public Vector2D(){
		x = 0.0f;
		y = 0.0f;
	}
	public Vector2D(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2D getClone(){
		Vector2D clone = new Vector2D();
		clone.x = this.x;
		clone.y = this.y;
		return clone;
	}
	
	/*引数:速度ベクトル、法線ベクトル、適用値*/
	/*戻り値：反射後の速度ベクトル*/
	/*反射*/
	public static Vector2D Reflect(Vector2DDomain vector2DDomain,Vector2D spd,Vector2D nor,float coefficient){
		float t = vector2DDomain.getNextFloatBuffer();
		t = -(nor.x * spd.x + nor.y * spd.y) / (nor.x * nor.x + nor.y * nor.y);
	    Vector2D refrectVector = vector2DDomain.getNextVectorBuffer();
	    refrectVector.x = spd.x + t * nor.x * 2.0f * coefficient;
	    refrectVector.y = spd.y + t * nor.y * 2.0f * coefficient;
	    return refrectVector;
	}
	
	/*引数:速度ベクトル、法線ベクトル、適用値*/
	/*戻り値：反射後の速度ベクトル*/
	/*反射*/
	public static Vector2D Reflect(Vector2D result,Vector2D spd,Vector2D nor,float coefficient){
		float t = -(nor.x * spd.x + nor.y * spd.y) / (nor.x * nor.x + nor.y * nor.y);
	    Vector2D refrectVector = result;
	    refrectVector.x = spd.x + t * nor.x * 2.0f * coefficient;
	    refrectVector.y = spd.y + t * nor.y * 2.0f * coefficient;
	    return refrectVector;
	}
	
	/*引数:速度ベクトル、法線ベクトル、適用値*/
	/*戻り値：壁ずり後の速度ベクトル*/
	/*壁ずり*/
	public static Vector2D Slide(Vector2D result,Vector2D spd,Vector2D nor,float coefficient){
		float t = -(nor.x * spd.x + nor.y * spd.y) / (nor.x * nor.x + nor.y * nor.y);
		Vector2D slideVector = result;
	    slideVector.x = spd.x + t * nor.x * coefficient;
	    slideVector.y = spd.y + t * nor.y * coefficient;
	    return slideVector;
	}
	
	/*引数:速度ベクトル、法線ベクトル、適用値*/
	/*戻り値：壁ずり後の速度ベクトル*/
	/*壁ずり*/
	public static Vector2D Slide(Vector2DDomain vector2DDomain,Vector2D spd,Vector2D nor,float coefficient){
		float t = vector2DDomain.getNextFloatBuffer();
		t = -(nor.x * spd.x + nor.y * spd.y) / (nor.x * nor.x + nor.y * nor.y);
		Vector2D slideVector = vector2DDomain.getNextVectorBuffer();
	    slideVector.x = spd.x + t * nor.x * coefficient;
	    slideVector.y = spd.y + t * nor.y * coefficient;
	    return slideVector;
	}
	
	/*引数:ベクトル1、ベクトル2*/
	/*戻り値：ベクトル1とベクトル2の内績*/
	/*内績*/
	public static float DotProduct(Vector2DDomain vector2DDomain,Vector2D vec1,Vector2D vec2){
		float dotProduct = vector2DDomain.getNextFloatBuffer();
		dotProduct = (vec1.x * vec2.x) + (vec1.y * vec2.y);
		return dotProduct;
	}
	
	/*引数:ベクトル*/
	/*戻り値：ベクトルを正規化した値*/
	/*法線*/
	public static Vector2D Normalize (Vector2DDomain vector2DDomain,Vector2D vec){
		float length = vector2DDomain.getNextFloatBuffer();
		length = (float)Math.sqrt((vec.x * vec.x) + (vec.y * vec.y));
		if(length > 0)	length = 1 / length;
		Vector2D normalizeVector = vector2DDomain.getNextVectorBuffer();
		normalizeVector.x = vec.x * length;
		normalizeVector.y = vec.y * length;
		return normalizeVector;
	}
	
	/*引数:ベクトル*/
	/*戻り値：ベクトルを正規化した値*/
	/*法線*/
	public static Vector2D Normalize (Vector2D result,Vector2D vec){
		float length = (float)Math.sqrt((vec.x * vec.x) + (vec.y * vec.y));
		if(length > 0)	length = 1 / length;
		Vector2D normalizeVector = result;
		normalizeVector.x = vec.x * length;
		normalizeVector.y = vec.y * length;
		return normalizeVector;
	}
	
	/*引数:ベクトル*/
	/*戻り値：ベクトルに対して垂直な同じ長さのベクトル*/
	/*垂直*/
	public static Vector2D Perp(Vector2DDomain vector2DDomain,Vector2D vec){
		Vector2D perpendicularVector = vector2DDomain.getNextVectorBuffer();
		perpendicularVector.x = -vec.y;
		perpendicularVector.y = vec.x;
		return perpendicularVector;
	}
	
	/*引数:ベクトル*/
	/*戻り値：ベクトルに対して垂直な同じ長さのベクトル*/
	/*垂直*/
	public static Vector2D Perp(Vector2D result,Vector2D vec){
		Vector2D perpendicularVector = result;
		perpendicularVector.x = -vec.y;
		perpendicularVector.y = vec.x;
		return perpendicularVector;
	}
	
	/*引数:ベクトル*/
	/*戻り値：ベクトルの長さ*/
	/*長さ*/
	public static float Length(Vector2DDomain vector2DDomain,Vector2D vec){
		return (float)Math.sqrt((vec.x * vec.x) + (vec.y * vec.y));
	}
	
	/*引数:ベクトル、乗算したい値*/
	/*戻り値：ベクトルの長さを乗算したベクトル*/
	/*スカラーで乗算*/
	public static Vector2D Scale(Vector2DDomain vector2DDomain,Vector2D vec,float scale){
		Vector2D ScalingVector = vector2DDomain.getNextVectorBuffer();
		ScalingVector.x = vec.x * scale;
		ScalingVector.y = vec.y * scale;
		return ScalingVector;
	}
	
	/*引数:ベクトル、乗算したい値*/
	/*戻り値：ベクトルの長さを乗算したベクトル*/
	/*スカラーで乗算*/
	public static Vector2D Scale(Vector2D result,Vector2D vec,float scale){
		Vector2D ScalingVector = result;
		ScalingVector.x = vec.x * scale;
		ScalingVector.y = vec.y * scale;
		return ScalingVector;
	}
	
	/*引数:乗算したい値、ベクトル*/
	/*戻り値：ベクトルの長さを乗算したベクトル*/
	/*スカラーで乗算*/
	public static Vector2D Scale(Vector2DDomain vector2DDomain,float scale,Vector2D vec){
		Vector2D ScalingVector = vector2DDomain.getNextVectorBuffer();
		ScalingVector.x = vec.x * scale;
		ScalingVector.y = vec.y * scale;
		return ScalingVector;
	}
	
	/*引数:乗算したい値、ベクトル*/
	/*戻り値：ベクトルの長さを乗算したベクトル*/
	/*スカラーで乗算*/
	public static Vector2D Scale(Vector2D result,float scale,Vector2D vec){
		Vector2D ScalingVector = result;
		ScalingVector.x = vec.x * scale;
		ScalingVector.y = vec.y * scale;
		return ScalingVector;
	}
	
	/*引数:ベクトル1、ベクトル2*/
	/*戻り値：ベクトル1とベクトル2を減算したベクトル*/
	/*減算*/
	public static Vector2D Sub(Vector2DDomain vector2DDomain,Vector2D vec1,Vector2D vec2){
		Vector2D SubVector = vector2DDomain.getNextVectorBuffer();
		SubVector.x = (float) (vec1.x - vec2.x);
		SubVector.y = (float) (vec1.y - vec2.y);
		return SubVector;
	}
	
	/*引数:ベクトル1、ベクトル2*/
	/*戻り値：ベクトル1とベクトル2を減算したベクトル*/
	/*減算*/
	public static Vector2D Sub(Vector2D result,Vector2D vec1,Vector2D vec2){
		Vector2D SubVector = result;
		SubVector.x = (float) (vec1.x - vec2.x);
		SubVector.y = (float) (vec1.y - vec2.y);
		return SubVector;
	}
	
	/*引数:ベクトル1、ベクトル2*/
	/*戻り値：ベクトル1とベクトル2を加算したベクトル*/
	/*加算*/
	public static Vector2D Add(Vector2DDomain vector2DDomain,Vector2D vec1,Vector2D vec2){
		Vector2D addVector = vector2DDomain.getNextVectorBuffer();
		addVector.x = (float) (vec1.x + vec2.x);
		addVector.y = (float) (vec1.y + vec2.y);
		return addVector;
	}
	
	/*引数:ベクトル1、ベクトル2*/
	/*戻り値：ベクトル1とベクトル2を加算したベクトル*/
	/*加算*/
	public static Vector2D Add(Vector2D result,Vector2D vec1,Vector2D vec2){
		Vector2D addVector = result;
		addVector.x = (float) (vec1.x + vec2.x);
		addVector.y = (float) (vec1.y + vec2.y);
		return addVector;
	}
	
	/*引数:ベクトル*/
	/*戻り値：ベクトルの角度θ*/
	/*角度*/
	public static float Angle(Vector2DDomain vector2DDomain,Vector2D vec) {
		float sita = vector2DDomain.getNextFloatBuffer();
		sita = (float)Math.acos(vec.x / Math.sqrt(vec.x * vec.x + vec.y * vec.y));
		sita = (float)((sita / Math.PI) * 180.0);
		if (vec.y < 0)	  sita = 360 - sita;
		return sita;
	}
	
	/*引数:ベクトル1、ベクトル2*/
	/*戻り値：ベクトル1とベクトル2が成す角度θ*/
	/*2ベクトル間角度*/
	public static float AngleOf2Vector(Vector2DDomain vector2DDomain,Vector2D vec1,Vector2D vec2){
		//　※ベクトルの長さが0だと答えが出ませんので注意してください。

		float length1 = Vector2D.Length(vector2DDomain,vec1);
		float length2 = Vector2D.Length(vector2DDomain,vec2);

		float cos_sita = Vector2D.DotProduct(vector2DDomain,vec1,vec2) / (length1 * length2);

		float sita = (float)Math.acos(cos_sita);	

		sita = (float) (sita * 180.0 / Math.PI);
		return sita;
	}
	
	/*引数:位置ベクトル1、位置ベクトル1からの移動ベクトル、位置ベクトル2、位置ベクトル2からの移動ベクトル*/
	/*戻り値：位置ベクトルと移動ベクトルが成す2つの線の交点座標*/
	/*交点座標*/
	public static Vector2D CrossPoint(Vector2DDomain vector2DDomain,Vector2D l1start,Vector2D l1end, Vector2D l2start,Vector2D l2end){
		Vector2D vec1 = Vector2D.Sub(vector2DDomain,l1end,l1start);
		Vector2D vec2 = Vector2D.Sub(vector2DDomain,l2end,l2start);

		float det = vec2.x * vec1.y - vec1.x * vec2.y;
		
        if(det == 0){
        	Vector2D result = vector2DDomain.getNextVectorBuffer();
        	result.x = 0;
        	result.y = 0;
            return result;
        }
        
        Vector2D d = vector2DDomain.getNextVectorBuffer();
        d.x = l2start.x - l1start.x;
        d.y = l2start.y - l1start.y;
        
        float t = (vec2.x * d.y - vec2.y * d.x) / det;
        
        Vector2D result = vector2DDomain.getNextVectorBuffer();
        result.x = l1start.x+vec1.x*t;
        result.y = l1start.y+vec1.y*t;
        return result;
	}
}
