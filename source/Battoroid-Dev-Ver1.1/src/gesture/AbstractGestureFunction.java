package gesture;

import android.view.MotionEvent;

public abstract class AbstractGestureFunction implements GestureFunction{
	@Override
	public abstract void gesture(int index,MotionEvent event);			// �W�F�X�`���[����
	@Override
	public abstract void touchCancel(int index,MotionEvent event);		// �^�b�`�L�����Z�����ꂽ���̏���
}