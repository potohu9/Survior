package gesture;

import android.view.MotionEvent;
import function.TouchManager;
import function.Vector2D;

public class GestureArea {
	private int touchNum;						// �^�b�`����Ă��鐔
	private int touchLimitNum;					// �^�b�`������
	
	private Vector2D areaStart;					// �^�b�`�̈�̊J�n�_
	private Vector2D areaEnd;					// �^�b�`�̈�̏I���_
	
	private GestureFunction gestureFunction;	// �^�b�`���ꂽ���̓���
	
	/*�e�Z�b�^�[*/
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
	
	/*�e�Q�b�^�[*/
	public int getTouchNum(){return touchNum;}
	public Vector2D getAreaStart(){return areaStart;}
	public Vector2D getAreaEnd(){return areaEnd;}
	public GestureFunction getGestureFunction(){return gestureFunction;}
	
	/*�f�t�H���g�R���X�g���N�^*/
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
	
	/*����:�^�b�`�̈�̊J�n�_�A�I���_�A�^�b�`���ꂽ���̓���*/
	/*�R���X�g���N�^*/
	public GestureArea(Vector2D areaStart,Vector2D areaEnd,int touchLimitNum,GestureFunction gestureFunction){
		this.areaStart = areaStart;
		this.areaEnd = areaEnd;
		this.touchLimitNum = touchLimitNum;
		this.gestureFunction = gestureFunction;
	}
	
	/*�^�b�`���ꂽ���̓���*/
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
