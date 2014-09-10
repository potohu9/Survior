package particle;

public abstract class AbstractParticleFunction implements ParticleFunction{
	@Override
	public abstract void changeState(Particle particle);			// �p�[�e�B�N���ɂ����鏈��
	
	@Override
    public ParticleFunction compose(final ParticleFunction f) {		// �֐������p�֐�
        return new AbstractParticleFunction() {
			@Override
			public void changeState(Particle particle) {
				f.changeState(particle);
				AbstractParticleFunction.this.changeState(particle);
			}
        };
    }
}