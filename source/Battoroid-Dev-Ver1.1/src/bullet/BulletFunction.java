package bullet;

import particle.ParticleSystem;

public interface BulletFunction {
	public void initBulletParticle(ParticleSystem renderParticle);	// �o���b�g�̃p�[�e�B�N��������
	public void changeState(Bullet bullet);							// �o���b�g�ɂ����鏈��
	public BulletFunction compose(BulletFunction bulletFunction);	// �֐������p�֐�
}