package gesture;

import android.view.MotionEvent;

public abstract class AbstractGestureFunction implements GestureFunction{
	@Override
	public abstract void gesture(int index,MotionEvent event);			// ジェスチャー処理
	@Override
	public abstract void touchCancel(int index,MotionEvent event);		// タッチキャンセルされた時の処理
}