package com.weaverprojects.mycalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one_btn = (Button) findViewById(R.id.one_btn);
        Button two_btn = (Button) findViewById(R.id.two_btn);
        Button three_btn = (Button) findViewById(R.id.three_btn);
        Button four_btn = (Button) findViewById(R.id.four_btn);
        Button five_btn = (Button) findViewById(R.id.five_btn);
        Button six_btn = (Button) findViewById(R.id.six_btn);
        Button seven_btn = (Button) findViewById(R.id.seven_btn);
        Button eight_btn = (Button) findViewById(R.id.eight_btn);
        Button nine_btn = (Button) findViewById(R.id.nine_btn);

        Button plus_btn = (Button) findViewById(R.id.plus_btn);
        Button equal_btn = (Button) findViewById(R.id.equal_btn);

        final EditText result_label = (EditText) findViewById(R.id.result_label);

        int value1 = -1;
        int value2 = -1;

        equal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //double centimeters = Double.valueOf(centimetersItem.getText().toString());
                //double inches = centimeters * 0.393700787;
                //inchesItem.setText(String.valueOf(inches));
                if(value1 != -1){
                    if(value2 != -1){
                        int result = value1 + value2;
                        result_label.setText(String.valueOf(result));
                    }
                }
            }
        });
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //double centimeters = Double.valueOf(centimetersItem.getText().toString());
                //double inches = centimeters * 0.393700787;
                //inchesItem.setText(String.valueOf(inches));

            }
        });
        one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(value1 == -1){
                     //value1 = 1;
                 }else{
                     //value2 = 1;
                 }
            }
        });
        two_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 2;
                }else{
                    //value2 = 2;
                }
            }
        });
        three_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 3;
                }else{
                    //value2 = 3;
                }
            }
        });
        four_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 4;
                }else{
                    //value2 = 4;
                }
            }
        });
        five_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 5;
                }else{
                    //value2 = 5;
                }
            }
        });
        six_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 6;
                }else{
                    //value2 = 6;
                }
            }
        });
        seven_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 7;
                }else{
                    //value2 = 7;
                }
            }
        });
        eight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 8;
                }else{
                    //value2 = 8;
                }
            }
        });
        nine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == -1){
                    //value1 = 9;
                }else{
                    //value2 = 9;
                }
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
}
