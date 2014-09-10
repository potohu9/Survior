package openGLES20;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import sound.Sound;
import com.drjiro.game.battoroid.s122240.game.*;

public class GLActivity extends Activity{
	private GLView glView;		// このアクティビティで使用するビュー
	
	// アクティビティ生成時に呼ばれる
	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		glView = new GLView(this);
		
		Common.setContext(this);
		Sound.setContext(this);
		
		setContentView(glView);
	}
	
	// アクティビティレジューム時に呼ばれる
	@Override
	public void onResume(){
		super.onResume();
	}
	
	// アクティビティポーズ時に呼ばれる
	@Override
	public void onPause(){
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
     
    @Override
    protected void onStart(){
        super.onStart();
    }
    
    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }
     
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
