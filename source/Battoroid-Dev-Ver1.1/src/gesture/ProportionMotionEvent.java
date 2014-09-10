package gesture;

import openGLES20.Common;
import android.view.MotionEvent;

public class ProportionMotionEvent {
	private static MotionEvent event;
	
	public static void setEvent(MotionEvent event) {
		ProportionMotionEvent.event = event;
	}
	
	public static MotionEvent getEvent()	{return event;}
	public static float getX()				{return event.getX()/Common.getScreenW()*Common.getScreenProportionW();};
	public static float getY()				{return event.getY()/Common.getScreenH()*Common.getScreenProportionH();};
	public static float getX(int index)		{return event.getX(index)/Common.getScreenW()*Common.getScreenProportionW();};
	public static float getY(int index)		{return event.getY(index)/Common.getScreenH()*Common.getScreenProportionH();};
}
