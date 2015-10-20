package com.weaverprojects.pulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-10-20.
 */
public class ListViewActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    ArrayAdapter mAdapter;
    ArrayList<String> mList;

    ListView listView;

    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mList = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView);



        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mList);
        listView.setAdapter(mAdapter);

        for(int i = 0;i < 25;i++){
            String t = "Test"  + String.valueOf(i);
            mList.add(t);
            mAdapter.notifyDataSetChanged();
            Log.v("LISTVIEW_", String.valueOf(i));
        }

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }
    public boolean canChildScrollUp() {
        ListView mListView = (ListView) this.findViewById(android.R.id.list);
        return mListView.getFirstVisiblePosition() != 0;
    }
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 5000);
    }
}
