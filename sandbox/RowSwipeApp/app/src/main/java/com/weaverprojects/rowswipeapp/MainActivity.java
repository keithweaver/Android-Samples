package com.weaverprojects.rowswipeapp;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class MainActivity extends Activity {
    int width;
    int height;

    LinearLayout wrapper;
    LinearLayout col1;
    LinearLayout col2;
    LinearLayout col3;
    HorizontalScrollView hsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wrapper = (LinearLayout) findViewById(R.id.wrapper);
        col1 = (LinearLayout) findViewById(R.id.col1);
        col2 = (LinearLayout) findViewById(R.id.col2);
        col3 = (LinearLayout) findViewById(R.id.col3);
        hsv = (HorizontalScrollView) findViewById(R.id.ScrollView);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) wrapper.getLayoutParams();
        params.width = width * 3;
        params.height = 200;
        wrapper.setLayoutParams(params);

        hsv.scrollTo(width, 0);

//        setWidthOfDevice(col1);
//        setWidthOfDevice(col2);
//        setWidthOfDevice(col3);
//        setWidthOfDeviceTimesThree(wrapper);

//        LinearLayout heightDisplay = (LinearLayout) findViewById(R.id.heightDisplay);
//
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) heightDisplay.getLayoutParams();
//        params.width = width;
//        params.height = 200;
//        heightDisplay.setLayoutParams(params);
    }
    protected void setWidthOfDevice(LinearLayout l){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) l.getLayoutParams();
        params.width = width;
        params.height = 200;
        l.setLayoutParams(params);
    }
    protected void setWidthOfDeviceTimesThree(LinearLayout l){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) l.getLayoutParams();
        params.width = width * 3;
        params.height = 200;
        l.setLayoutParams(params);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
