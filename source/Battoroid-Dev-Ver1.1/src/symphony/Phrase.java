package symphony;

import java.util.HashMap;
import java.util.Map;

import sound.Sound;

public class Phrase {
	private Map<String,Sound> map;
	private boolean isStart;
	private static String nowKey;
	public Phrase() {
		map = new HashMap<String,Sound>();
		isStart = false;
		nowKey = "";
	}
	public void add(String key,Sound sound){
		sound.setLooping(false);
		sound.prepare();
		map.put(key,sound);
	}
	public void play(String key){
		Sound sound = map.get(key);
		sound.start();
		isStart = true;
		nowKey = key;
	}
	public boolean isEndPhrase(){
		if(!isStart){
			return true;
		}
		Sound sound = map.get(nowKey);
		if(!sound.isPlay()){
			isStart = false;
			return true;
		}
		return false;
	}
	public void stop(){
		for(Sound sound : map.values()){
		    sound.stop();
		}
	}
}
