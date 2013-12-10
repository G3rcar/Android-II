package com.udb.modulo2.clase29;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	boolean conti = true;
	float speed=0.5f;
	int dt=10;
	int time=0;
	Thread thread=null;
	DynamicView dynamicview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dynamicview = new DynamicView(this);
        setContentView(dynamicview);
        thread = new Thread(dynamicview);
        thread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
    public void onPause(){
    	super.onPause();
    	conti=false;
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	conti=true;
    	if(thread==null){
    		thread = new Thread(dynamicview);
    		thread.start();
    	}
    }
    
    
    
    
    public class DynamicView extends View implements Runnable{

    	int x,y,ymax;
    	Paint background = new Paint();
    	Paint paint = new Paint();
    	Paint particle = new Paint();
    	
    	public DynamicView(Context context) {
    		super(context);
    		background.setColor(Color.LTGRAY);
    		particle.setColor(Color.RED);
    		paint.setColor(Color.BLACK);
    		paint.setTextSize(30);
    	}

    	@Override
    	public void run() {
    		while(conti){
    			time=time+dt;
    			y = y + (int)(speed+dt);
    			if(y>ymax){
    				speed =- speed;
    				y=0;
    			}
    			if(y<0){
    				speed =- speed;
    			}
    			postInvalidate();
    			try{
    				Thread.sleep(dt);
    			}catch(InterruptedException e){
    				Log.e("DRAW", "Interrumped "+e.getMessage());
    			}
    		}
    	}//run

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawPaint(background);
			canvas.drawCircle(x, y, 30, particle);
			canvas.drawText("y="+y, 50, 50, paint);
			canvas.drawText("t="+time, 50, 90, paint);
		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			x=w/2;
			y=0;
			ymax=h;
			Log.d("DRAW", "x="+x+" ymax="+ymax);
		}    	
    }//end DynamicView

}
