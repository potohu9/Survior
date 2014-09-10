package symphony;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import function.AbstractRunnable;
import function.Utile;

public class Symphony {
	private Map<Integer,Phrase> map;
	private int[] index;
	private int nowIndex;
	private String key;
	private Thread thread;
	private boolean isPlayThread = false;
	
	public void setIndex(int[] index){
		this.index = index;
	}
	public void setKey(String key){
		this.key = key;
	}
	
	@SuppressLint("UseSparseArrays")
	public Symphony() {
		nowIndex = 0;
		map = new HashMap<Integer,Phrase>();
		index = new int[0];
		key = "";
	}
	public void add(Phrase phrase){
		int index = map.size();
		map.put(index,phrase);
	}
	public void start(){
		Phrase phrase = map.get(index[0]);
		phrase.play(key);
		isPlayThread = true;
		Runnable run = new AbstractRunnable(){
			@Override
			public void run() {
				while(isPlayThread){
					update();
				}
			}
		};
		thread = new Thread(run);
		thread.start();
	}
	private void play(){
		Phrase phrase = map.get(index[nowIndex]);
		phrase.play(key);
	}
	public void update(){
		Phrase phrase = map.get(index[nowIndex]);
		if(phrase.isEndPhrase()){
			nowIndex = Utile.UseBuffer(nowIndex,index.length);
			play();
		}
	}
	public void stopLoop(){
		isPlayThread = false;
	}
	public void stopSound(){
		for(int i=0;i<map.size();i++){
			Phrase phrase = map.get(i);
			phrase.stop();
		}
	}
}
