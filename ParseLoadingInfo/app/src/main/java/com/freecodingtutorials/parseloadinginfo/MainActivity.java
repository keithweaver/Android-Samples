package com.freecodingtutorials.parseloadinginfo;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    public Application mApplication;

    ArrayList<String> friend_list = new ArrayList<String>();
    ArrayAdapter<String> a_adapter;
    ListView friend_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = new Application();

        try {
            setContentView(R.layout.activity_main);
        }catch(Exception e){
            Log.e("ERROR","LAYOUT");
            Log.e("ERROR",e.toString());
        }
        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "ACnXHKlS8m40Xppjus7W21FJes5Fjw7cKdxA2sBX", "XxPYpJBO1XC3yqMs0l1FAUvXpM3wYqN8E7Gjf2Pq");
        }catch(Exception e){
            Log.e("ERROR 001:", e.toString());
            Log.e("ERROR 001:","UNABLE TO CONNECT");
        }

        /* --- EDITED CODE ----- */


        ParseQuery<ParseObject> query = ParseQuery.getQuery("friends");
        query.whereEqualTo("owner", "keweav");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + parseObjects.size() + " scores");
                    for(int i = 0;i < parseObjects.size();i++){
                        Log.d("Item:",parseObjects.get(i).get("friend_username").toString());
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        /*
        try {
            ParseObject userInfo = new ParseObject("friends");
            userInfo.put("owner", "keweav");
            userInfo.put("friend_username", "Weaver2");
            userInfo.saveInBackground();
        }catch(Exception e){
            Log.e("ERROR","UNABLE TO ADD");
        }
        */
        /* --- TILL HERE -- */
        //friend_list.add("alittle");
        //friend_list.add("keweav");
        //friend_list.add("another");


        try {
            ArrayAdapter<String> a_adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.text1, friend_list);
            friend_listview = (ListView) findViewById(R.id.listView);
            friend_listview.setAdapter(a_adapter);
            friend_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemValue = (String) friend_listview.getItemAtPosition(position);
                    //TextView friends_title_label = (TextView) findViewById(R.id.friends_title_label);
                    //friends_title_label.setText(itemValue);
                    Log.v("Content: ", itemValue);
                    startActivity(new Intent("android.intent.action.point"));
                }
            });
        }catch (Exception e){
            Log.e("Error","Adding to listview");
        }

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
