package scene;

import android.view.MotionEvent;

public interface Scene {
	public void intialize();
	public void update();
	public void render();
	public void finalize();
	public void touchEvent(MotionEvent event);
}
