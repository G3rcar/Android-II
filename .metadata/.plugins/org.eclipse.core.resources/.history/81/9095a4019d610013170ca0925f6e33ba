package com.udb.modulo2.clase26;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;

public class CustomDrawableView {
	
	private ShapeDrawable mDrawable;

    public CustomDrawableView(Context context) {
    	super(context);

    	int x = 10;
    	int y = 10;
    	int width = 300;
    	int height = 50;

    	
    	
    	mDrawable = new ShapeDrawable(new OvalShape());
    	mDrawable.getPaint().setColor(Color.BLUE);
    	mDrawable.setBounds(x, y, x + width, y + height);
    	
    	
    }

    protected void onDraw(Canvas canvas) {
    	mDrawable.draw(canvas);

		
    }
}
