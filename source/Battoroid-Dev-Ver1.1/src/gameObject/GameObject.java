package gameObject;

import function.ColorData;
import function.Graphics;
import function.Graphics.BlendMode;
import object2D.Object2D;

public class GameObject extends Object2D{
	protected int texture;
	protected GameObjectFunction function;
	protected boolean isExistence;
	protected ColorData colorData;
	protected BlendMode blendMode;
	
	public void setTexture(int texture) {
		this.texture = texture;
	}
	public void setFunction(GameObjectFunction function) {
		this.function = function;
	}
	public void setIsExistence(boolean isExistence) {
		this.isExistence = isExistence;
	}
	public void setColorData(ColorData colorData) {
		this.colorData = colorData;
	}
	public void setBlendMode(BlendMode blendMode) {
		this.blendMode = blendMode;
	}
	
	public int getTexture()						{return texture;}
	public GameObjectFunction getFunction()		{return function;}
	public boolean getIsExistence()				{return isExistence;}
	public ColorData getColorData()				{return colorData;}
	public BlendMode getBlendMode()				{return blendMode;}
	
	public GameObject(){
		this.function = GameObjectFunctionFactory.makeNotProcessing();
		this.isExistence = true;
		colorData = new ColorData();
		colorData.setARGB(1,1,1,1);
		blendMode = BlendMode.ALPHA;
	}
	
	public GameObject(int texture,GameObjectFunction function){
		this.function = GameObjectFunctionFactory.makeNotProcessing();
		this.texture = texture;
		this.function = function;
		this.isExistence = true;
		colorData = new ColorData();
		colorData.setARGB(1,1,1,1);
		blendMode = BlendMode.ALPHA;
	}
	
	public void update(){
		function.changeState(this);
	}
	
	public void render(){
		if(isExistence){
			Graphics.drawTexture(pos,colorData,rad,angle,texture,blendMode);
		}
	}
}
