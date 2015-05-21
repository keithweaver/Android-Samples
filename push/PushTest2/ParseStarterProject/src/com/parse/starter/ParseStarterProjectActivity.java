package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseAnalytics;
import com.parse.ParsePush;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());

        Button sendBtn = (Button) findViewById(R.id.button);
        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ParsePush push = new ParsePush();
                push.setChannel("usr1");
                push.setMessage("Test 1");
                push.sendInBackground();
            }
        });
	}
}
