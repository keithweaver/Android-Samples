package com.weaverprojects.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParsePush;


public class MainActivity extends Activity {

    public String currentUser = "Usr2";
    public String sendUser = "Usr1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*
        ParsePush.subscribeInBackground(currentUser, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        */
        final EditText sendMessageTextbox = (EditText) findViewById(R.id.messageText);

        /*
        final EditText userTextbox = (EditText) findViewById(R.id.userText);
        final EditText channelTextbox = (EditText) findViewById(R.id.channelTextbox);

        final TextView userLabel = (TextView) findViewById(R.id.userLabel);

        Button updateUserBtn = (Button) findViewById(R.id.updateBtn);
        */
        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        /*
        Button subscribeBtn = (Button) findViewById(R.id.subscribeBtn);

        updateUserBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendUser = userTextbox.getText().toString();
                userLabel.setText("Send User: " + currentUser);
            }
        });
        */

        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String message = sendMessageTextbox.getText().toString();

                Log.v("Message:", message);

                ParsePush push = new ParsePush();
                push.setChannel("usr1");
                push.setMessage(message);
                push.sendInBackground();

                Log.v("","Should be sent.");
                sendMessageTextbox.setText("");
            }
        });

        /*
        subscribeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendUser = channelTextbox.getText().toString();
            }
        });
        */
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
