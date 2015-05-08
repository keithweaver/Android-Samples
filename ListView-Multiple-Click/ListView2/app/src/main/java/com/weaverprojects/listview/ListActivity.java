package com.weaverprojects.listview;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Weaver on 15-05-07.
 */
public class ListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //if (BuildConfig.DEBUG)
        //    ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if (BuildConfig.DEBUG)
        //    ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if (BuildConfig.DEBUG)
        //    ViewServer.get(this).setFocusedWindow(this);
    }
}
