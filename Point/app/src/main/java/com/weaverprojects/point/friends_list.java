package com.weaverprojects.point;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Weaver on 15-03-05.
 */
public class friends_list extends Activity{
    public void onCreate(Bundle savedInstanceState){
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ACnXHKlS8m40Xppjus7W21FJes5Fjw7cKdxA2sBX", "XxPYpJBO1XC3yqMs0l1FAUvXpM3wYqN8E7Gjf2Pq");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list);




        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();


        /* --------------------- */
        /* --- Button Clicks --- */

        //OPENS ADD/DELETE FRIEND PAGE
        ImageView add_friend_btn = (ImageView) findViewById(R.id.add_friend_btn);
        add_friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.add_friend"));
            }
        });

    }
}
