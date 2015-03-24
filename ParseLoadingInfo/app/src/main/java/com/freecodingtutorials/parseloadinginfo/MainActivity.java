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
import android.widget.Button;
import android.widget.ListView;

import com.parse.ParseAnalytics;
import com.parse.ParsePush;

import java.util.ArrayList;


public class MainActivity extends Activity {
    public Application mApplication;

    ArrayList<String> friend_list = new ArrayList<String>();
    ArrayAdapter<String> a_adapter;
    ListView friend_listview;

    String username = "keweav";
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
        /*
        try {
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "ACnXHKlS8m40Xppjus7W21FJes5Fjw7cKdxA2sBX", "XxPYpJBO1XC3yqMs0l1FAUvXpM3wYqN8E7Gjf2Pq");
        }catch(Exception e){
            Log.e("ERROR 001:", e.toString());
            Log.e("ERROR 001:","UNABLE TO CONNECT");
        }
        */
        //------- PARSE PUSH STUFF
        ParseAnalytics.trackAppOpened(getIntent());

        //ParsePush.subscribeInBackground("MyChannel");
        //String value = ParseInstallation.getCurrentInstallation().getString("gender");

        /*
        ParsePush push = new ParsePush();
        push.setChannel("Giants");
        push.setMessage("The Giants just scored! It's now 2-2 against the Mets.");
        push.sendInBackground();
        */
        /*
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("username", username);
        installation.saveInBackground();

        ParsePush parsePush = new ParsePush();
        ParseQuery pQuery = ParseInstallation.getQuery(); // <-- Installation query
        pQuery.whereEqualTo("username", username); // <-- you'll probably want to target someone that's not the current user, so modify accordingly
        parsePush.sendMessageInBackground("Only for special people", pQuery);
        */
        //JSONObject object = new JSONObject();
        /*
        IntentFilter intentFilter = new IntentFilter("MyAction");
        BroadcastReceiver pushReceiver;
        pushReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                String message = extras != null ? extras.getString("com.parse.Data") : "";

                try {
                    //jObject = new JSONObject(message);
                    Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

                    toast.show();
                } catch (Exception e) {
                    Log.e("ERROR:","Unabelt o receive");
                }
            }
        };
        registerReceiver(pushReceiver, intentFilter);
        */

        //try {

            //object.put("title", "MyTitle");
            /*
            ParsePush pushMessageClient1 = new ParsePush();
            pushMessageClient1.setMessage("TEXT");
            pushMessageClient1.setChannel("MyChannel");
            pushMessageClient1.sendInBackground(new SendCallback() {

                public void done(com.parse.ParseException e) {
                    if(e != null) {
                        Log.e("ERROR:","SOMETHING WENT WRONG WITH THE SEND PUSH");
                    }
                }
            });
            */
        //} catch (Exception e){
            //e.printStackTrace();
        //}

        Button send_btn = (Button) findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("SENT:","SENT NOTIF");
                try{
                    ParsePush push = new ParsePush();
                    String message = "New Message";
                    push.setChannel("ch1");
                    push.setMessage(message);
                    push.sendInBackground();
                }catch(Exception e){
                    Log.e("ERROR:","Unable to send push.");
                }
            }
        });
        Button receive_btn = (Button) findViewById(R.id.receive_btn);
        receive_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        /* --- EDITED CODE ----- */

        //ParseQuery<ParseObject> query = ParseQuery.getQuery("friends");
        //query.whereEqualTo("owner","keweav");


        //THIS IS SELECT
        /*
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
        */
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User_Login");
        query.whereEqualTo("username", "jguenth");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + parseObjects.size() + " scores");
                    for(int i = 0;i < parseObjects.size();i++){
                        Log.d("Item:",parseObjects.get(i).get("password").toString());
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        */

        //THIS IS THE INSERT
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
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("friends");
        query.whereEqualTo("objectId","Xkt5B3raHJ");
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
                    Log.d("Semothing went wrong. Show useful message based on ParseException data", e.getMessage());
                }
            }
        });
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
    public void onPushOpen(){

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
