package com.weaverprojects.shuttle.View.Windows;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.weaverprojects.shuttle.R;
import com.weaverprojects.shuttle.View.CustomAdapters.ConvoListAdapter;
import com.weaverprojects.shuttle.View.TabsAdapter.DevicesTabAdapter;

/**
 * Created by Keith on 2015-10-21.
 */
public class DevicesActivity extends FragmentActivity implements ActionBar.TabListener{
    public static final String TAG = "Shuttle_";

    private ViewPager viewPager;
    private ActionBar actionBar;
    private DevicesTabAdapter mDevicesTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab()
                .setCustomView(R.layout.tab_paired)
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab()
                .setCustomView(R.layout.tab_found_device)
                .setTabListener(this));

        mDevicesTabAdapter = new DevicesTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mDevicesTabAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paired, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
