package bullet;

import particle.ParticleSystem;

public abstract class AbstractBulletFunction implements BulletFunction{
	@Override
	public abstract void initBulletParticle(ParticleSystem renderParticle);
	@Override
	public abstract void changeState(Bullet bullet);
	@Override
	public BulletFunction compose(final BulletFunction f) {		// ä÷êîçáê¨ópä÷êî
		return new AbstractBulletFunction() {
			@Override
			public void initBulletParticle(ParticleSystem renderParticle) {
				f.initBulletParticle(renderParticle);
				AbstractBulletFunction.this.initBulletParticle(renderParticle);
			}

			@Override
			public void changeState(Bullet bullet) {
				f.changeState(bullet);
				AbstractBulletFunction.this.changeState(bullet);
			}
		};
	}
}
