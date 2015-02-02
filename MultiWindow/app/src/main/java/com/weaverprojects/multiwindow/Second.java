package com.weaverprojects.multiwindow;

/**
 * Created by Weaver on 15-02-02.
 */

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.app.Activity;


public class Second extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        //Intent intent = new Intent(this, DisplayMessageActivity.class);
    }


}
