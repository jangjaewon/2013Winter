package com.anyreader.readerview;

import com.anyread.R;
import com.anyreader.engine.FileInfo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class ReaderViewActivity extends Activity {

    @SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_reader_view);
        
        // set custom view
        ReaderView readerView = (ReaderView)findViewById(R.id.ReaderView);
        FileInfo fi = new FileInfo("/data/app/sample.txt");
        readerView.setText(fi.getText());
        
        // get display size
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        readerView.setWidthSize(display.getWidth());
        readerView.setHeightSize(display.getHeight());
        readerView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_reader_view, menu);
        return true;
    }
    
}
