package sound;

import java.util.HashMap;
import java.util.Map;

import com.drjiro.game.battoroid.s122240.game.R;

public class BGMManager {
	private static Map<String,Sound> map;
	
	static {
		map = new HashMap<String,Sound>();
	}
	
	public static void LodeBGM(){
		map.put("Act",new Sound(R.raw.act));
		map.put("Mero1",new Sound(R.raw.mero1));
		map.put("BGM1",new Sound(R.raw.bgm1));
		map.put("BGM2",new Sound(R.raw.bgm2));
	}
	
	public static void soundStart(String key){
		Sound sound = map.get(key);
		sound.setLooping(true);
		sound.start();
	}
	
	public static void soundStop(String key){
		Sound sound = map.get(key);
		sound.stop();
	}
}
