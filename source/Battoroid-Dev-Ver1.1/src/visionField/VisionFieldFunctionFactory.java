package visionField;

import object2D.Object2D;
import collision.Collision;

public class VisionFieldFunctionFactory {
	
	/*�߂�l:���������֐�*/
	/*���������֐��쐬*/
	public static VisionFieldFunction makeNotProcessing(){
		return new AbstractVisionFieldFunction(){
			@Override
			public boolean isInVisionField(Object2D obj1,Object2D obj2){
				return true;
			}
		};
	}
	
	/*����:���E�̒���*/
	/*�߂�l�F�~�`�̎��E�쐬�֐�*/
	/*�~�`�̎��E�쐬�֐��쐬*/
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
