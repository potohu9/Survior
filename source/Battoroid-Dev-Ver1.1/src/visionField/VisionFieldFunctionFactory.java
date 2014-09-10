package visionField;

import object2D.Object2D;
import collision.Collision;

public class VisionFieldFunctionFactory {
	
	/*–ß‚è’l:ˆ—–³‚µŠÖ”*/
	/*ˆ—–³‚µŠÖ”ì¬*/
	public static VisionFieldFunction makeNotProcessing(){
		return new AbstractVisionFieldFunction(){
			@Override
			public boolean isInVisionField(Object2D obj1,Object2D obj2){
				return true;
			}
		};
	}
	
	/*ˆø”:‹ŠE‚Ì’·‚³*/
	/*–ß‚è’lF‰~Œ`‚Ì‹ŠEì¬ŠÖ”*/
	/*‰~Œ`‚Ì‹ŠEì¬ŠÖ”ì¬*/
	public static VisionFieldFunction makeCircleVisionField(final float visionFieldLength){
		return new AbstractVisionFieldFunction(){
			@Override
			public boolean isInVisionField(Object2D obj1,Object2D obj2){
				if(Collision.circleToCircle(obj1.getPos().x,obj1.getPos().y,1,obj2.getPos().x,obj2.getPos().y,visionFieldLength)){
					return true;
				}
				return false;
			}
		};
	}
}
