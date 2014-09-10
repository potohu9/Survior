package selectCursor;

import openGLES20.Common;
import sound.SEManager;
import function.AbstractRunnable;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObject;

public class SelectCursor {
	private Vector2DDomain vector2DDomain;
	
	private int selectNum;
	private int nowCursor;
	private GameObject[] selectObject;
	private GameObject cursorObject;
	private float cursorVel;
	private int inputCount;
	private static final int inputTime = 5;
	
	private boolean isPlayThread;
	
	private Runnable selectThread;
	private Thread thread;
	
	public void setNowCursor(int nowCursor){
		if(inputCount <= 0){
			if(nowCursor != this.nowCursor){
				SEManager.Play("Select");
			}
			if(	nowCursor < selectNum &&
				nowCursor >= 0){
					this.nowCursor = nowCursor;
					inputCount = inputTime;
			}
		}
	}

	public int getNowCursor()						{return nowCursor;}
	public GameObject[] getSelectObject()			{return selectObject;}
	public GameObject getSelectObject(int index)	{return selectObject[index];}
	
	public SelectCursor(){
		selectNum = 0;
		nowCursor = 0;
		selectObject =  new GameObject[selectNum];
		cursorObject = new GameObject();
		cursorVel = 1;
		inputCount = 0;
		vector2DDomain = new Vector2DDomain();
		initThread();
	}
	
	public SelectCursor(int nowCursor,float cursorVel,GameObject cursorObject,GameObject... selectObject){
		selectNum = selectObject.length;
		this.nowCursor = nowCursor;
		this.cursorObject = cursorObject;
		this.selectObject = selectObject;
		cursorObject.setPos(selectObject[nowCursor].getPos());
		this.cursorVel = cursorVel;
		inputCount = 0;
		vector2DDomain = new Vector2DDomain();
		initThread();
	}
	
	private void update(){
		if(inputCount > 0){
			inputCount--;
		}
		
		for(int i=0;i<selectObject.length;i++){
			selectObject[i].update();
		}
		cursorObject.update();
		cursorObject.setPos(
					Vector2D.Add(vector2DDomain,
							cursorObject.getPos(),
					Vector2D.Scale(vector2DDomain,
							cursorVel,
					Vector2D.Normalize(vector2DDomain,
					Vector2D.Sub(vector2DDomain,
							selectObject[nowCursor].getPos(),cursorObject.getPos())))));
	}
	
	public void render(){
		for(int i=0;i<selectObject.length;i++){
			selectObject[i].render();
		}
	}
	
	private void initThread(){
		isPlayThread = true;
		selectThread = new AbstractRunnable(){
			@Override
			public void run() {
				while(isPlayThread){
					try{
						Thread.sleep(Common.getMaxFPS());
					}
					catch (InterruptedException e){
						
					}
					update();
				}
			}
		};
		thread = new Thread(selectThread);
		thread.start();
	}
	
	public void finalize(){
		isPlayThread = false;
		thread = null;
		selectThread = null;
	}
}