package gesture;

import android.view.MotionEvent;

public interface GestureFunction {
	/*index タッチされた順番*/
	public void gesture(int index,MotionEvent event);			// ジェスチャー処理
	public void touchCancel(int index,MotionEvent event);		// タッチキャンセルされた時の処理
}
