package com.weaverprojects.colortoolbar;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    String TAG = "ColorToolbar_";
    ImageView refresh;
    TransitionDrawable td;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionbar = getActionBar();

//        actionbar.setTitle("");
//        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BF360C")));
//        actionbar.show();

        View mActionBarView = getLayoutInflater().inflate(R.layout.custom_menu, null);
        actionbar.setCustomView(mActionBarView);
        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);


        refresh = (ImageView) findViewById(R.id.action_refresh);
        //refresh.setImageDrawable(getResources().getDrawable(R.drawable.menu));

        td = new TransitionDrawable( new Drawable[] {
                getResources().getDrawable(R.drawable.menu),
                getResources().getDrawable(R.drawable.left)
        });
        refresh.setImageDrawable(td);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Refresh_Clicked 2");
                td.startTransition(1000);
                //refresh.setImageDrawable(getResources().getDrawable(R.drawable.left));
                td.reverseTransition(500);
            }
        });
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
//        if (id == R.id.action_settings) {
//            //Open settings
//            Log.v(TAG, "Opening settings...");
//            return true;
//        }else
        if(id == R.id.action_refresh){
            Log.v(TAG, "Opening refresh");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
