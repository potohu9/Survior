package function;

import gesture.ProportionMotionEvent;
import android.view.MotionEvent;

public class TouchManager {
	private static MotionEvent event;	//�@���݂̃C�x���g
	
	/*�Z�b�^�[*/
	public static void setEvent(MotionEvent event) {
		TouchManager.event = event;
		ProportionMotionEvent.setEvent(event);
	}
	/*�Q�b�^�[*/
	public static MotionEvent getEvent()	{return event;}
	
	/*���݃A�N�V�����̃��Z�b�g*/
	public static void actionReset(){
		TouchManager.event.setAction(MotionEvent.ACTION_CANCEL);
	}
}
