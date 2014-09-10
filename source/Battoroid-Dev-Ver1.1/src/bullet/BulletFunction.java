package bullet;

import particle.ParticleSystem;

public interface BulletFunction {
	public void initBulletParticle(ParticleSystem renderParticle);	// バレットのパーティクル初期化
	public void changeState(Bullet bullet);							// バレットにかける処理
	public BulletFunction compose(BulletFunction bulletFunction);	// 関数合成用関数
}