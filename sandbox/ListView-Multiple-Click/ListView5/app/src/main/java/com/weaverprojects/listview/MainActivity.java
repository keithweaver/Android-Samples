package com.weaverprojects.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    ListView listview;
    CustomAdapter mCustomAdapter;
    ArrayList<User> list = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        list.add(new User("Keith Weaver","keweav"));
        list.add(new User("Scott Weaver","sct"));
        list.add(new User("Dana Weaver","dd"));

        mCustomAdapter = new CustomAdapter(this, R.layout.row_template, list);
        listview = (ListView) findViewById(R.id.listview);
        listview.setItemsCanFocus(false);
        listview.setAdapter(mCustomAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("List view was clicked","**");
                Log.i("POSITION:",""+position);
                Toast.makeText(MainActivity.this, ("List View Clicked:"+position), Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
