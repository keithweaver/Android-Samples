package com.weaverprojects.point;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Weaver on 15-03-05.
 */
public class launch extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //here check if the gps is on.
        setContentView(R.layout.gps_check);


        /*THIS SHOULD REMOVE BECASE THEY SHOULDNT BE ABLE TO SKIP THE GPS SCREEN*/
        Button continue_btn = (Button) findViewById(R.id.continue_btn);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.start"));
            }
        });
        /*END OF CODE THAT GETS  REMOVE */
    }

}
