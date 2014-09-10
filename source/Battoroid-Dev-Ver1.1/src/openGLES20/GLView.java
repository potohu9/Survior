package openGLES20;

import function.TouchManager;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GLView extends GLSurfaceView {
	GLRenderer GLRenderer;		// このビューで使用するレンダラー
	
	public GLView(Context context) {
        super(context);
        // レンダラーの作成と設定
        GLRenderer = new GLRenderer();
        GLRenderer.setActivity((Activity)context);
        this.setEGLContextClientVersion(2);
        setRenderer(GLRenderer);
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		TouchManager.setEvent(event);	// タッチイベントを送る
		return false;
	}
}
