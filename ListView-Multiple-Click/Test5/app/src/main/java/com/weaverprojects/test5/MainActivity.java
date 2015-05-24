package com.weaverprojects.test5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    public FriendRequestAdapter mFriendRequestAdapter;

    ListView listview;

    ArrayList<User> list = new ArrayList<User>();

    public static HashMap<Integer, String> friendsMap;
    int friendsListSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        listview = (ListView) findViewById(R.id.listView);
        mFriendRequestAdapter = new FriendRequestAdapter(this, R.layout.friend_row, list);
        listview.setItemsCanFocus(false);
        listview.setAdapter(mFriendRequestAdapter);

        friendsMap = new HashMap<Integer, String>();
        friendsListSize = 0;

        addFriend("First Last1", "User 1");
        addFriend("First Last2", "User 2");
        addFriend("First Last3", "User 3");
        addFriend("First Last4", "User 4");
        addFriend("First Last5", "User 5");


        mFriendRequestAdapter.notifyDataSetChanged();
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


    protected void addFriend(String name, String user){

        friendsMap.put(friendsListSize, user);
        mFriendRequestAdapter.add(new User(name, user));
        friendsListSize++;
    }
}
