package com.anyreader.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class DrawPage {
	public DrawPage() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.ITALIC));
	}
	
	public void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        int front = 0;
        int back = 0;
        int lineNum = 1;
        while(back < text.length()) {
        	if(text.charAt(back) == ' ') {
        		back++;
        	}
        	else if(text.startsWith("&%", back)) {
        		canvas.drawText(text.substring(front, back-1), 5, (textSize + spaceBetweenLines) * lineNum++, paint);
        		
        		front = back += 2;
        		while(text.charAt(back+1) != '\n' && text.charAt(back+1) != '\0')
        			back++;
        		String img = text.substring(front, back+1);
        		BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(img, options);
                canvas.drawBitmap(bitmap, (widthSize - bitmap.getWidth()) / 2, (textSize + spaceBetweenLines) * lineNum++, null);
                lineNum += 6;
                front = ++back;
        	}
        	front = back;
        	back = divideLine(front, back);
        	if(text.charAt(back) == '\n')
        		canvas.drawText(text.substring(front, back), 5, (textSize + spaceBetweenLines) * lineNum++, paint);
        	else
        		canvas.drawText(text.substring(front, back+1), 5, (textSize + spaceBetweenLines) * lineNum++, paint);
        	back++;
        }
        
        
	}
	
	public int divideLine(int front, int back) {
		if(back < 0 || back > text.length())
			return -1;
		float length = 0;
		while(back < text.length()) {
			length = (float)paint.measureText(text, front, back);
			if(text.charAt(back) == '\n')
				return back;
			else if(length - widthSize + 25 > 0)
				return back-1;
			back++;
		}
		return back - 1;
	}
	
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setWidthSize(int widthSize) {
		this.widthSize = widthSize;
	}
	
	public void setHeightSize(int heightSize) {
		this.heightSize = heightSize;
	}
	
	public int getHeightSize() {
		return heightSize;
	}
	
	public void setSpaceBetweenLines(int spaceBetweenLines) {
		this.spaceBetweenLines = spaceBetweenLines;
	}
	
	private Paint paint;
	private int textSize;
	private String text = "";
	private int widthSize;
	private int heightSize;
	private int spaceBetweenLines;
}
