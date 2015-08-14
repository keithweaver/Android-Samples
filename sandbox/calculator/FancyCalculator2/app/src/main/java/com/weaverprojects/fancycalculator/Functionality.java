package com.weaverprojects.fancycalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class Functionality extends ActionBarActivity {

    private  int value1 = 0;
    private  int value2 = 0;

    private int value1Count = 0;
    private int value2Count = 0;

    private boolean plusItem = false;
    private boolean subItem = false;
    private boolean multiplyItem = false;
    private boolean divItem = false;

    private boolean selected = false;

    private String equation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functionality);

        Button one_btn = (Button) findViewById(R.id.one_btn);
        Button two_btn = (Button) findViewById(R.id.two_btn);
        Button three_btn = (Button) findViewById(R.id.three_btn);
        Button four_btn = (Button) findViewById(R.id.four_btn);
        Button five_btn = (Button) findViewById(R.id.five_btn);
        Button six_btn = (Button) findViewById(R.id.six_btn);
        Button seven_btn = (Button) findViewById(R.id.seven_btn);
        Button eight_btn = (Button) findViewById(R.id.eight_btn);
        Button nine_btn = (Button) findViewById(R.id.nine_btn);
        Button zero_btn = (Button) findViewById(R.id.zero_btn);

        Button plus_btn = (Button) findViewById(R.id.plus_btn);
        Button sub_btn = (Button) findViewById(R.id.sub_btn);
        final Button multiply_btn = (Button) findViewById(R.id.multiply_btn);
        Button div_btn = (Button) findViewById(R.id.div_btn);
        Button equals_btn = (Button) findViewById(R.id.equal_btn);


        final TextView result_label = (TextView) findViewById(R.id.result_textview);

        equals_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int result = 0;
                if(plusItem == true){
                    result = value1 + value2;
                }else if(subItem == true){
                    result = value1 - value2;
                }else if(multiplyItem == true){
                     result = value1 * value2;
                }else if(divItem == true){
                     result = value1 / value2;
                }
                result_label.setText(String.valueOf(result));
                equation = "";

            }
        });
        plus_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plusItem = true;
                subItem = false;
                multiplyItem = false;
                divItem = false;
                selected = true;
                equation = value1 + "+";
                result_label.setText(String.valueOf(equation));
            }
        });
        sub_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plusItem = false;
                subItem = true;
                multiplyItem = false;
                divItem = false;
                selected = true;
                equation = value1 + "-";
                result_label.setText(String.valueOf(equation));
            }
        });

        multiply_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plusItem = false;
                subItem = false;
                multiplyItem = true;
                divItem = false;
                selected = true;
                equation = value1 + "x";
                result_label.setText(String.valueOf(equation));
            }
        });
        div_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plusItem = false;
                subItem = false;
                multiplyItem = false;
                divItem = true;
                selected = true;
                equation = value1 + "/";
                result_label.setText(String.valueOf(equation));
            }

        });



        one_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 1 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 1 * length;
                    value2Count++;
                }
                equation += "1";
                result_label.setText(String.valueOf(equation));
            }
        });
        two_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 2 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 2 * length;
                    value2Count++;
                }
                equation += "2";
                result_label.setText(String.valueOf(equation));
            }
        });
        three_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 3 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 3 * length;
                    value2Count++;
                }
                equation += "3";
                result_label.setText(String.valueOf(equation));
            }
        });
        four_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 4 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 4 * length;
                    value2Count++;
                }
                equation += "4";
                result_label.setText(String.valueOf(equation));
            }
        });
        five_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 5 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 5 * length;
                    value2Count++;
                }
                equation += "5";
                result_label.setText(String.valueOf(equation));
            }
        });
        six_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 6 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 6 * length;
                    value2Count++;
                }
                equation += "6";
                result_label.setText(String.valueOf(equation));
            }
        });
        seven_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 7 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 7  * length;
                    value2Count++;
                }
                equation += "7";
                result_label.setText(String.valueOf(equation));
            }
        });
        eight_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 8 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 8 * length;
                    value2Count++;
                }
                equation += "8";
                result_label.setText(String.valueOf(equation));
            }
        });
        nine_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 9 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 9 * length;
                    value2Count++;
                }
                equation += "9";
                result_label.setText(String.valueOf(equation));
            }
        });
        zero_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selected == false){
                    int length = 10 ^ value1Count;
                    value1 = 0 * length;
                    value1Count++;
                }else{
                    int length = 10 ^ value2Count;
                    value2 = 0 * length;
                    value2Count++;
                }
                equation += "0";
                result_label.setText(String.valueOf(equation));
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_functionality, menu);
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
