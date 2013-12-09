package com.udb.modulo2.clase28;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class TouchDrawer extends View {
	
	String txt="Evento";
	float x=50;
	float y=50;
	Paint paint = new Paint();
	Path path = new Path();
	

	public TouchDrawer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		paint.setColor(Color.BLACK);
		paint.setTextSize(35);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
		//canvas.drawColor(Color.LTGRAY);
		
		//canvas.drawCircle(x, y, 20, paint);
		canvas.drawText("x= "+x, 100, 50, paint);
		canvas.drawText("y= "+y, 100, 90, paint);
		canvas.drawText(txt, 100, 130, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		x=event.getX();
		y=event.getY();
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			txt="Action Down";
			path.moveTo(x, y);
		}
		if(event.getAction()==MotionEvent.ACTION_UP){
			txt="Action Up";
			path.lineTo(x, y);
		}
		if(event.getAction()==MotionEvent.ACTION_MOVE){
			txt="Action Move";
		}
		invalidate();
		return true;
	}
}
