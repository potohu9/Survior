package particle;

public interface ParticleFunction {
	public void changeState(Particle particle);							// �p�[�e�B�N���ɂ����鏈��
	public ParticleFunction compose(ParticleFunction particleFunction);	// �֐������p�֐�
}