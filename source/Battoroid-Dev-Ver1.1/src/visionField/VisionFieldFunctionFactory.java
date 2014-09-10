package visionField;

import object2D.Object2D;
import collision.Collision;

public class VisionFieldFunctionFactory {
	
	/*戻り値:処理無し関数*/
	/*処理無し関数作成*/
	public static VisionFieldFunction makeNotProcessing(){
		return new AbstractVisionFieldFunction(){
			@Override
			public boolean isInVisionField(Object2D obj1,Object2D obj2){
				return true;
			}
		};
	}
	
	/*引数:視界の長さ*/
	/*戻り値：円形の視界作成関数*/
	/*円形の視界作成関数作成*/
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
