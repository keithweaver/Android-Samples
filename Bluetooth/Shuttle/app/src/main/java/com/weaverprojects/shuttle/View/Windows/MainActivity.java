package com.weaverprojects.shuttle.View.Windows;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.weaverprojects.shuttle.Controller.C;
import com.weaverprojects.shuttle.Controller.local.OnGoingChatsDB;
import com.weaverprojects.shuttle.Controller.local.SavedFriendsDataDB;
import com.weaverprojects.shuttle.Model.ConvoListItem;
import com.weaverprojects.shuttle.R;
import com.weaverprojects.shuttle.View.CustomAdapters.ConvoListAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public static final String TAG = "Shuttle_";

    OnGoingChatsDB mOnGoingChatsDB;
    ArrayList<ConvoListItem> mConvoList;
    ConvoListAdapter mConvoListAdapter;
    SavedFriendsDataDB mSavedFriendsDataDB;

    ListView listView;
    ImageView addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        declareUI();

        mOnGoingChatsDB = new OnGoingChatsDB(this, C.local.DB_NAME, null, 1);
        mSavedFriendsDataDB = new SavedFriendsDataDB(this, C.local.DB_NAME, null, 1);
        mConvoList = mOnGoingChatsDB.getAllConvos();
        Log.v(TAG, "Convos:" + String.valueOf(mConvoList.size()));
        mConvoListAdapter = new ConvoListAdapter(this, R.layout.single_past_user, mConvoList);
        listView.setAdapter(mConvoListAdapter);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDeviceActivity = new Intent(v.getContext(), DevicesActivity.class);
                startActivity(openDeviceActivity);
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
            String imgLink = "https://pbs.twimg.com/profile_images/534057279796948993/dA7aBJGm.jpeg";
            mOnGoingChatsDB.insertRow("kweaver","last message goes here",imgLink,"device001");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void declareUI(){
        listView = (ListView) findViewById(R.id.listView);
        addBtn = (ImageView) findViewById(R.id.addBtn);
    }
}
