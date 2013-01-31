package com.anyreader.readerview;


import com.anyreader.engine.DrawPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ReaderView extends View{
	
	private ViewMode viewMode = ViewMode.SCROLL;
	private int textSize = 20;
	private Color textColor;
	private Color backGroundColor;
	private String text;
	private int widthSize;
	private int heightSize;
	private int spaceBetweenLines;
	
	public ReaderView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		Log.v("GraphView","constructor");
		// TODO Auto-generated constructor stub
	}
	public ReaderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public enum ViewMode{
		PAGES,
		SCROLL
	}
	
	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		Log.v("GraphView","onDraw");
		DrawPage dp = new DrawPage();
		dp.setText(text);
		dp.setTextSize(textSize);
		dp.setWidthSize(widthSize);
		dp.setHeightSize(heightSize);
		dp.setSpaceBetweenLines(spaceBetweenLines);
		dp.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

	    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

	    //Measure Width
	    if (widthMode == MeasureSpec.EXACTLY) {
	        //Must be this size
	    	this.widthSize = widthSize;
	    } else if (widthMode == MeasureSpec.AT_MOST) {
	        //Can't be bigger than...
	    	this.widthSize = Math.min(this.widthSize, widthSize);
	    } else {
	        //Be whatever you want
	    }

	    //Measure Height
	    if (heightMode == MeasureSpec.EXACTLY) {
	        //Must be this size
	    	this.heightSize = heightSize;
	    } else if (heightMode == MeasureSpec.AT_MOST) {
	        //Can't be bigger than...
	    	this.heightSize = Math.min(this.heightSize, heightSize);
	    } else {
	        //Be whatever you want
	        this.heightSize = calHeightSize();
	    }

	    //MUST CALL THIS
	    setMeasuredDimension(this.widthSize, this.heightSize);
	}
	
	public int calHeightSize() {
        int front = 0;
        int back = 0;
        int lineNum = 1;
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setTextSize(textSize);
		spaceBetweenLines = textSize/4;
		paint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.ITALIC));     
		
        while(back < text.length()) {
        	if(text.charAt(back) == ' ' && front == back) {
        		front = ++back;
        	}
        	else if(text.startsWith("&%", back)) {
        		lineNum += 7;
        	}
        	
        	float length = 0;
    		length = (float)paint.measureText(text, front, back);
    		if(text.charAt(back) == '\n') {
    			lineNum++;
    			front = back;
    		}
    		else if(length - widthSize > 0)
    		{
    			lineNum++;
    			front = back;
    		}
    		back++;
        }
        return (textSize + spaceBetweenLines) * ++lineNum + textSize;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public int getWidthSize() {
		return widthSize;
	}
	public void setWidthSize(int widthSize) {
		this.widthSize = widthSize;
	}
	public int getHeightSize() {
		return heightSize;
	}
	public void setHeightSize(int heightSize) {
		this.heightSize = heightSize;
	}
}
