package function;

import gesture.ProportionMotionEvent;
import android.view.MotionEvent;

public class TouchManager {
	private static MotionEvent event;	//　現在のイベント
	
	/*セッター*/
	public static void setEvent(MotionEvent event) {
		TouchManager.event = event;
		ProportionMotionEvent.setEvent(event);
	}
	/*ゲッター*/
	public static MotionEvent getEvent()	{return event;}
	
	/*現在アクションのリセット*/
	public static void actionReset(){
		TouchManager.event.setAction(MotionEvent.ACTION_CANCEL);
	}
}
