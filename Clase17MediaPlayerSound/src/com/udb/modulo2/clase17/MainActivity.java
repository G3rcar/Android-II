package com.udb.modulo2.clase17;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	MediaPlayer mplayer;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void onPlay(View view){
    	if(mplayer!=null){
    		mplayer.release();
    	}
    	mplayer = MediaPlayer.create(this, R.raw.mysong2);
    	mplayer.seekTo(0);
    	mplayer.setLooping(true);
    	mplayer.setScreenOnWhilePlaying(true);
    	mplayer.start();
    }
    
    public void onStop(View view){
    	if(mplayer!=null){
    		mplayer.stop();
    	}
    }


}
