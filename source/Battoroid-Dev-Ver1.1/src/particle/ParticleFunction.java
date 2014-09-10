package particle;

public interface ParticleFunction {
	public void changeState(Particle particle);							// パーティクルにかける処理
	public ParticleFunction compose(ParticleFunction particleFunction);	// 関数合成用関数
}