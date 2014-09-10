package sound;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;

public class Sound{
	private MediaPlayer sound;
	private static Context context;
	private int soundID;
	
	public static void setContext(Context context){
		Sound.context = context;
	}
	
	public Sound(int soundID){
		sound = new MediaPlayer();
		sound = MediaPlayer.create(context,soundID);
		this.soundID = soundID;
	}
	
	public void setLooping(boolean looping){
		sound.setLooping(looping);
	}
	
	public void start(){
		if(!sound.isPlaying()){
			sound.start();
		}
	}
	
	public void stop(){
		sound.stop();
		sound = new MediaPlayer();
		sound = MediaPlayer.create(context,soundID);
	}

	public boolean isPlay(){
		return sound.isPlaying();
	}
	
	public boolean isLooping(){
		return sound.isLooping();
	}

	public void prepare() {
		try {
			sound.prepare();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressLint("NewApi")
	public void setNextSound(Sound sound){
		this.sound.setNextMediaPlayer(sound.getSound());
	}
	
	public MediaPlayer getSound(){
		return sound;
	}
}