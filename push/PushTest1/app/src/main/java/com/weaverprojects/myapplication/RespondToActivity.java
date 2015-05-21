package com.weaverprojects.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Weaver on 15-05-20.
 */
public class RespondToActivity extends Activity {
    private int i = 0;


    public RespondToActivity(){
        //super("RespondToActivity");
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respond);


    }


}
