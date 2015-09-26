package com.weaverprojects.parseexampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SELECT
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TABLE_NAME");
        query.whereEqualTo("COLUMN_NAME", "VALUE_SEARCHING_FOR");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + parseObjects.size() + " scores");//NUMBER OF ROWS
                    for(int i = 0;i < parseObjects.size();i++){
                        Log.d("Item:",parseObjects.get(i).get("ANOTHER_COLUMN_NAME").toString());
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("TABLE_NAME");
        query.whereEqualTo("COLUMN_NAME","COLUMN_VALUE_BEING_LOOKED_FOR");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    // iterate over all messages and delete them
                    for(ParseObject row : parseObjects)
                    {
                        row.deleteEventually();
                    }
                } else {
                    Log.d("Something went wrong", e.getMessage());
                }
            }
        });

        try {
            ParseObject userInfo = new ParseObject("TABLE_NAME");
            userInfo.put("COLUMN_NAME_ONE", "COLUMN_VALUE");
            userInfo.put("COLUMN_NAME_TWO", "COLUMN_VALUE_TWO");
            userInfo.saveInBackground();
        }catch(Exception e){
            Log.e("ERROR","UNABLE TO ADD");
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
