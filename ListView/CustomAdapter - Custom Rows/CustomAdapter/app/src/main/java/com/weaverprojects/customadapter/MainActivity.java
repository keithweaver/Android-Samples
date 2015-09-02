package com.weaverprojects.customadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SingleRow row1 = new SingleRow("John Smith 1", "jsmith");
        SingleRow row2 = new SingleRow("John Smith 2", "jsmith2");
        SingleRow row3 = new SingleRow("John Smith 3", "jsmith3");
        SingleRow row4 = new SingleRow("John Smith 4", "jsmith4");
        SingleRow row5 = new SingleRow("John Smith 5", "jsmith5");

        ArrayList<SingleRow> listOfUsers = new ArrayList<>();
        listOfUsers.add(row1);
        listOfUsers.add(row2);
        listOfUsers.add(row3);
        listOfUsers.add(row4);
        listOfUsers.add(row5);

        mCustomAdapter = new CustomAdapter(this, R.layout.single_row, listOfUsers);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mCustomAdapter);
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
