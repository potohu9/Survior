package particle;

import java.util.ArrayList;

public class ParticleManager {
	private static ArrayList<ParticleSystem> particleSystems;
	private static ParticleSystem[] particleSystemData;
	static {
		particleSystems = new ArrayList<ParticleSystem>();
	}
	
	public static void render(){
		int arraySize = particleSystems.size();
		for(int i=0;i<arraySize;i++){
			particleSystemData[i].render();
		}
	}
	
	public static void AddParticle(ParticleSystem particleSystem){
		particleSystems.add(particleSystem);
		int arraySize = particleSystems.size();
		particleSystemData = new ParticleSystem[arraySize];
		for(int i=0;i<arraySize;i++){
			particleSystemData[i] = particleSystems.get(i);
		}
	}
	
	public static void reset(){
		particleSystems.clear();
	}
}
