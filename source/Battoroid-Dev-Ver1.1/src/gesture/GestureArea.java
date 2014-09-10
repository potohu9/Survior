package gesture;

import android.view.MotionEvent;
import function.TouchManager;
import function.Vector2D;

public class GestureArea {
	private int touchNum;						// タッチされている数
	private int touchLimitNum;					// タッチ制限数
	
	private Vector2D areaStart;					// タッチ領域の開始点
	private Vector2D areaEnd;					// タッチ領域の終了点
	
	private GestureFunction gestureFunction;	// タッチされた時の動作
	
	/*各セッター*/
	public void setTouchNum(int touchNum) {
		this.touchNum = touchNum;
	}
	public void setTouchLimitNum(int touchLimitNum) {
		this.touchLimitNum = touchLimitNum;
	}
	public void setAreaStart(Vector2D areaStart) {
		this.areaStart = areaStart;
	}
	public void setAreaEnd(Vector2D areaEnd) {
		this.areaEnd = areaEnd;
	}
	public void setGestureFunction(GestureFunction gestureFunction) {
		this.gestureFunction = gestureFunction;
	}
	
	/*各ゲッター*/
	public int getTouchNum(){return touchNum;}
	public Vector2D getAreaStart(){return areaStart;}
	public Vector2D getAreaEnd(){return areaEnd;}
	public GestureFunction getGestureFunction(){return gestureFunction;}
	
	/*デフォルトコンストラクタ*/
	public GestureArea(){
		areaStart = new Vector2D();
		areaEnd = new Vector2D();
		touchLimitNum = 5;
		this.gestureFunction = new AbstractGestureFunction(){
			@Override
			public void gesture(int index, MotionEvent event) {
				
			}
			@Override
			public void touchCancel(int index,MotionEvent event) {
				
			}
		};
	}
	
	/*引数:タッチ領域の開始点、終了点、タッチされた時の動作*/
	/*コンストラクタ*/
	public GestureArea(Vector2D areaStart,Vector2D areaEnd,int touchLimitNum,GestureFunction gestureFunction){
		this.areaStart = areaStart;
		this.areaEnd = areaEnd;
		this.touchLimitNum = touchLimitNum;
		this.gestureFunction = gestureFunction;
	}
	
	/*タッチされた時の動作*/
	public void touchEvent(){
		MotionEvent event = TouchManager.getEvent();
		touchNum = event.getPointerCount();
		boolean[] isTouch = new boolean[5];
		boolean[] isThisArea = new boolean[5];
		for(int i=0;i<5;i++){
			isTouch[i] = false;
			isThisArea[i] = false;
		}
		int touchCount = touchLimitNum;
		for(int i=0;i<touchNum;i++){
			if(	event.getX(i) < areaStart.x ||
				event.getY(i) < areaStart.y ||
				event.getX(i) > areaEnd.x ||
				event.getY(i) > areaEnd.y ){
				continue;
			}
			isThisArea[i] = true;
			switch(event.getAction() & MotionEvent.ACTION_MASK){
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				if(touchCount > 0){
					isTouch[i] = true;
					touchCount--;
				}
				break;
			default:
				
			}
		}
		for(int i=0;i<5;i++){
			if(isThisArea[i]){
				if(isTouch[i]){
					gestureFunction.gesture(i,event);
				}
				else{
					gestureFunction.touchCancel(i,event);
				}
			}
		}
	}
}
