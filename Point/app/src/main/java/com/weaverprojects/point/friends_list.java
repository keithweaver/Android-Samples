package com.weaverprojects.point;

import android.app.Activity;
import android.os.Bundle;

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
    }
}
