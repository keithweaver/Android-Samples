package com.weaverprojects.time;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String label = "";
        Calendar rightNow = Calendar.getInstance();

        //label += rightNow.DAY_OF_YEAR;
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String yesterdayDate = "Mar 22, 2015 7:11:49 PM";

        TextView main_label = (TextView) findViewById(R.id.main_label);
        main_label.setText(currentDateTimeString);
    }
    public boolean runAnotherCheck(String time1, String time2){
        String[] items1 = time1.split("\\s+");
        String[] items2 = time2.split("\\s+");
        //Mar
        //22,
        //2015
        //time
        //PM
        if(items1[0] != null && items1[1] != null && items1[2] != null && items1[3] != null && items1[4] != null
                && items2[0] != null && items2[1] != null && items2[2] != null && items2[3] != null && items2[4] != null) {
            if (items1[0].equals(items2[0])) {
                //SAME MONTH
                if(items1[1].equals(items2[1])){
                    //same day
                    if(items1[4].equals(items2[4])){
                        //same time of day
                        //PM VS AM
                        try {
                            String[] timeStr1 = items1[3].split(":");
                            String[] timeStr2 = items2[3].split(":");
                            int hour1 = Integer.valueOf(timeStr1[0]);
                            int hour2 = Integer.valueOf(timeStr2[0]);
                            int min1 = Integer.valueOf(timeStr1[1]);
                            int min2 = Integer.valueOf(timeStr2[1]);
                            if (hour1 == hour2) {
                                if(min1 == min2){
                                    return true;
                                }
                            }
                        }catch(Exception e){
                            Log.e("ERROR:", e.toString());
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
    public int getHourStr(String s){

        return 0;
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
