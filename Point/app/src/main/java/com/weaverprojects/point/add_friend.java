package com.weaverprojects.point;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-03-05.
 */
public class add_friend extends ListActivity{
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();
    //ListView listview;
    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.add_friend);
        }catch(Exception e){
            Log.v("Page Error:", "Doesn't load");
        }
        //listview = (ListView) findViewById(R.id.listView);

        list.add("keweav");

        //adapter = new ArrayAdapter<String>(this, R.layout.add_friend_row, R.id.text1, list);
        //adapter.notifyDataSetChanged();
        //listview.setListAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.add_friend_row, R.id.text1, list);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

    }

}
