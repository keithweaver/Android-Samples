package com.weaverprojects.multiplewindowswithpassinginfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Keith on 2015-09-02.
 */
public class SecondActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView infoLabel = (TextView) findViewById(R.id.infoLabel);

        Intent intent = getIntent();

        String info = intent.getStringExtra("INFO");
        infoLabel.setText("Info:" + info);
    }
}
