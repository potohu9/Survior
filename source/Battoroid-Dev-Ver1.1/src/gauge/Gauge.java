package gauge;

import function.ColorData;
import function.Graphics;
import function.TextureManager;
import function.Utile;
import function.Vector2D;
import function.Graphics.BlendMode;
import function.Vector2DDomain;

public class Gauge {
	private float maxValue;
	private float minValue;
	private float nowValue;
	
	private final float gaugeLength;
	private float nowGaugeLength;
	private float addGaugeSpeed;
	private float targetGaugeLength;
	
	private Vector2D gaugeAreaStartPos;
	private Vector2D gaugeAreaEndPos;
	
	private ColorData gaugeColor;
	private float[] baceVertex;
	private float[] vertex;
	private float[] baceUV;
	private float[] uv;
	private byte[] index;
	
	public Gauge(Vector2DDomain vector2DDomain,
			float maxValue,float minValue,float nowValue,
			Vector2D gaugeAreaStartPos,Vector2D gaugeAreaEndPos,
			ColorData gaugeColor){
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.nowValue = nowValue;
		this.gaugeAreaStartPos = gaugeAreaStartPos;
		this.gaugeAreaEndPos = gaugeAreaEndPos;
		this.gaugeColor = gaugeColor;
		gaugeLength = gaugeAreaEndPos.x - gaugeAreaStartPos.x;
		nowGaugeLength = gaugeLength;
		addGaugeSpeed = 0;
		Vector2D startPos = Utile.translatScreenPoint(gaugeAreaStartPos);
		Vector2D endPos = Utile.translatScreenPoint(gaugeAreaEndPos);
		baceVertex = new float[]{
			startPos.x,startPos.y,0,
			startPos.x,endPos.y,0,
			endPos.x,startPos.y,0,
			endPos.x,endPos.y,0,
		};
		baceUV = new float[]{
			0,0,
			0,1,
			1,0,
			1,1,
		};
		setVertex();
		setUV(1);
		initIndex();
	}
	
	public void setNowValue(float nowValue) {	
		if(nowValue < minValue){
			nowValue = minValue;
		}
		if(nowValue > maxValue){
			nowValue = maxValue;
		}
		this.nowValue = nowValue;
		float ratio = nowValue / (maxValue - minValue);
		targetGaugeLength = gaugeLength * ratio;
		addGaugeSpeed = (targetGaugeLength - nowGaugeLength) / 10;
	}
	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}
	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public float getNowValue(){return nowValue;}
	public float getMinValue(){return minValue;}
	public float getMaxValue(){return maxValue;}
	
	public void update(){
		if(addGaugeSpeed != 0){
			nowGaugeLength += addGaugeSpeed;
			float ratio = nowGaugeLength / gaugeLength;
			setUV(ratio);
			setVertex();
			if(addGaugeSpeed < 0){
				if(nowGaugeLength <= targetGaugeLength){
					addGaugeSpeed = 0;
				}
			}
			else{
				if(nowGaugeLength >= targetGaugeLength){
					addGaugeSpeed = 0;
				}
			}
		}
	}

	public void render(){
		Graphics.drawTexture(baceVertex,baceUV,index,gaugeColor,TextureManager.getTexture("BaceGauge"),BlendMode.ALPHA);
		Graphics.drawTexture(vertex,uv,index,gaugeColor,TextureManager.getTexture("Gauge"),BlendMode.ALPHA);
	}
	
	private void setVertex(){
		Vector2D gaugeAreaStartPos = Utile.translatScreenPoint(this.gaugeAreaStartPos);
		Vector2D gaugeAreaEndPos = Utile.translatScreenPoint(this.gaugeAreaEndPos);
		Vector2D gaugeDispPos = this.gaugeAreaStartPos.getClone();
		gaugeDispPos.x += nowGaugeLength;
		gaugeDispPos = Utile.translatScreenPoint(gaugeDispPos);
		vertex = new float[]{
			gaugeAreaStartPos.x,gaugeAreaStartPos.y,0,
			gaugeAreaStartPos.x,gaugeAreaEndPos.y,0,
			gaugeDispPos.x,gaugeAreaStartPos.y,0,
			gaugeDispPos.x,gaugeAreaEndPos.y,0,
		};
	}
	
	private void setUV(float ratio){
		uv = new float[]{
			0,0,
			0,1,
			1*ratio,0,
			1*ratio,1,
		};
	}
	
	private void initIndex(){
		index = new byte[]{
			0,1,2,
			1,2,3,
		};
	}
}
