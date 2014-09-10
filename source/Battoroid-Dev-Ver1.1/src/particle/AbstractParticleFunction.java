package particle;

public abstract class AbstractParticleFunction implements ParticleFunction{
	@Override
	public abstract void changeState(Particle particle);			// パーティクルにかける処理
	
	@Override
    public ParticleFunction compose(final ParticleFunction f) {		// 関数合成用関数
        return new AbstractParticleFunction() {
			@Override
			public void changeState(Particle particle) {
				f.changeState(particle);
				AbstractParticleFunction.this.changeState(particle);
			}
        };
    }
}