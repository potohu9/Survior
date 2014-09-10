package particle;

import openGLES20.Common;
import function.ColorData;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObject;

public class Particle extends GameObject{
	private Vector2DDomain vector2DDomain;
	private float disappearanceDistance;	//消滅までの距離
	private ColorData colorData;			//色データ
	private float scale;					//大きさ
	private ParticleSystem system;
	
	public Particle(Vector2DDomain vector2DDomain){
		this.vector2DDomain = vector2DDomain;
	}
	
	/*各セッター*/
	public void setDisappearanceDistance(float disappearanceDistance) {
		this.disappearanceDistance = disappearanceDistance;
	}
	public void setColorData(ColorData colorData) {
		this.colorData = colorData;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public void setSystem(ParticleSystem system) {
		this.system = system;
	}
	
	/*各ゲッター*/
	public float getDisappearanceDistance()	{return disappearanceDistance;}
	public ColorData getColorData()			{return colorData;}
	public float getScale()					{return scale;}
	public ParticleSystem getSystem()		{return system;}
	
	/*引数：発生座標、目的座標、色データ(RGBA)、速度、消失距離*/
	/*初期化処理*/
	public void initialize(Vector2D generatPoint,Vector2D targetPoint,ColorData colorData,float initialVelocity,float scale){
		this.colorData = colorData;
		this.scale = scale/Math.max(Common.getScreenProportionW(),Common.getScreenProportionH())*Math.max(Common.getScreenW(),Common.getScreenH());
		rad = this.scale * 0.5f;
		pos = generatPoint;
		disappearanceDistance = (float)Vector2D.Length(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				generatPoint,targetPoint));
		Vector2D directionVector;
		directionVector = Vector2D.Normalize(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				targetPoint,generatPoint));	//方向ベクトルを求め
		Vector2D.Scale(vec,directionVector,initialVelocity);				//初速度で掛け算し、移動ベクトルを求める
		isExistence = true;
	}
	
	/*更新処理*/
	public void update(){
		super.update();
		rad = this.scale * 0.5f;
		if(getDisappearanceDistance() <= 0){
			isExistence = false;
		}
		disappearanceDistance = (float)(getDisappearanceDistance() - Vector2D.Length(vector2DDomain,vec));
		Vector2D.Add(pos,pos,vec);	//現在の表示位置と移動ベクトルを足し、新しい表示位置を算出
	}
	public void finalize(){
		colorData = null;
	}
}