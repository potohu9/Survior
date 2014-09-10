package sound;

import java.util.HashMap;
import java.util.Map;

import com.drjiro.game.battoroid.s122240.game.*;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SEManager {
	private static SoundPool sp;
	private static Map<String,Integer> map;
	private static Context context;
	
	public static void setContext(Context context){
		SEManager.context = context;
	}

	static {
		sp = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
		map = new HashMap<String,Integer>();
	}
	
	public static void LodeSE(){
		Load(R.raw.ban,"Ban");
		Load(R.raw.select,"Select");
		Load(R.raw.decision,"Decision");
		Load(R.raw.cancel,"Cancel");
	}

	public static void Load(int resId,String key) {
		map.put(key,sp.load(context,resId,1));
	}

	public static void Play(String key) {
		sp.play(map.get(key),1.0f,1.0f,0,0,1.0f);
	}

	public static void Play(String key,float leftVolume,float rightVolume,int priority, int loop, float rate) {
		sp.play(map.get(key),leftVolume,rightVolume,priority,loop,rate);
	}

	public static void Pause(String key) {
		sp.pause(map.get(key));
	}

	public static void Stop(String key) {
		sp.stop(map.get(key));
	}

	public static void Release() {
		sp.release();
	}
}
