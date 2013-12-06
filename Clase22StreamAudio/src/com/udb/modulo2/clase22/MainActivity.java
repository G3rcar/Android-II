package com.udb.modulo2.clase22;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,OnPreparedListener,OnErrorListener,OnCompletionListener {
	
	MediaPlayer mediaplayer;
    ProgressDialog pd;
    
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
	
	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.i("StreamAudioDemo", "prepare finished");
		pd.setMessage("Playing.....");
		mp.start();
	}
	
	
	@Override
	  public void onClick(View v) {
	       try
	        {
	            pd = new ProgressDialog(this);
	            pd.setMessage("Buffering.....");
	            pd.show();
	            mediaplayer = new MediaPlayer();
	            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
	            mediaplayer.setOnPreparedListener(this);
	            mediaplayer.setOnErrorListener(this);
	            mediaplayer.setDataSource("http://dreammedia.ru/music/Bon%20Jovi/Crush/Bon%20Jovi%20-%2002%20-%20Say%20it%20isn't%20so.mp3");
	            mediaplayer.prepareAsync();
	            mediaplayer.setOnCompletionListener(this);
	        }
	        catch(Exception e)
	        {
	            Log.e("StreamAudioDemo", e.getMessage());
	        }
	    }
	 
	  	public void onStop(View view){
	  		if(mediaplayer!=null){
	  			mediaplayer.stop();
	  		}
	  	}
	  
	    @Override
	    public boolean onError(MediaPlayer mp, int what, int extra) {
	      pd.dismiss();
	      return false;
	    }
	 
	    @Override
	    public void onCompletion(MediaPlayer mp) {
	        pd.dismiss();
	        Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_LONG).show();     
	    }

}
