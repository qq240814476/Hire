package com.An.Other;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class RotatePic extends TextView {
	public RotatePic(Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 
	public RotatePic(Context context) { 
		super(context);
    } 
  
    @Override 
    public void onDraw(Canvas canvas) { 
    	///ÈÃ»­²¼Ðý×ª90¶È
    	canvas.rotate(-90); 
        canvas.translate(-getHeight(), 0); 
        super.onDraw(canvas); 
    } 
}
