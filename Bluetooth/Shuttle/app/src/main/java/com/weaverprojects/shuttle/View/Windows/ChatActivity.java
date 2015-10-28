package com.weaverprojects.shuttle.View.Windows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.weaverprojects.shuttle.Controller.C;
import com.weaverprojects.shuttle.Controller.local.OnGoingChatsDB;
import com.weaverprojects.shuttle.Controller.local.SavedFriendsDataDB;
import com.weaverprojects.shuttle.Model.ConvoListItem;
import com.weaverprojects.shuttle.Model.Message;
import com.weaverprojects.shuttle.R;
import com.weaverprojects.shuttle.View.CustomAdapters.ConvoListAdapter;
import com.weaverprojects.shuttle.View.CustomAdapters.MessageAdapter;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-10-25.
 */
public class ChatActivity extends Activity {
    public static final String TAG = "Shuttle_";

    ListView mainMessageListView;
    MessageAdapter mMessageAdapter;
    String user = "user1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        declareUI();
        getExtras();

        ArrayList<Message> messagesList = new ArrayList<>();

        //Testing values
        messagesList.add(new Message(1, "Hey", "user2", "", false));
        messagesList.add(new Message(2, "Hey friend", "user1", "", true));
        messagesList.add(new Message(3, "Whats up", "user1", "", true));
        messagesList.add(new Message(4, "Nothing", "user2", "", false));
        messagesList.add(new Message(5, "You?", "user2", "", true));
        messagesList.add(new Message(6, "Just building an Android app", "user1", "", true));

        messagesList.add(new Message(7, "Lots of text and more text and this is to test that the layout works with long strings. but we will see.", "user2", "", true));
        messagesList.add(new Message(8, "Just building an Android app", "user1", "", false));
        messagesList.add(new Message(9, "You?", "user2", "", true));
        messagesList.add(new Message(10, "Just building an Android app", "user1", "", false));
        messagesList.add(new Message(11, "You?", "user2", "", true));
        messagesList.add(new Message(12, "Just building an Android app", "user1", "", true));



        mainMessageListView = (ListView) findViewById(R.id.mainMessageListView);
        mMessageAdapter = new MessageAdapter(this, R.layout.single_message_row, messagesList);
        mainMessageListView.setAdapter(mMessageAdapter);

        scrollMyListViewToBottom();
    }
    private void scrollMyListViewToBottom() {
        mainMessageListView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                mainMessageListView.setSelection(mMessageAdapter.getCount() - 1);
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
    public void declareUI(){

    }
    public void getExtras(){

    }
}
