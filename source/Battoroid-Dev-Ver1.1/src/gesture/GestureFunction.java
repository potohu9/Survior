package gesture;

import android.view.MotionEvent;

public interface GestureFunction {
	/*index �^�b�`���ꂽ����*/
	public void gesture(int index,MotionEvent event);			// �W�F�X�`���[����
	public void touchCancel(int index,MotionEvent event);		// �^�b�`�L�����Z�����ꂽ���̏���
}
