package openGLES20;

import function.TouchManager;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GLView extends GLSurfaceView {
	GLRenderer GLRenderer;		// ���̃r���[�Ŏg�p���郌���_���[
	
	public GLView(Context context) {
        super(context);
        // �����_���[�̍쐬�Ɛݒ�
        GLRenderer = new GLRenderer();
        GLRenderer.setActivity((Activity)context);
        this.setEGLContextClientVersion(2);
        setRenderer(GLRenderer);
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		TouchManager.setEvent(event);	// �^�b�`�C�x���g�𑗂�
		return false;
	}
}
