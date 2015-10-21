package com.weaverprojects.datausage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public String TAG = "WEAVER_MAIN_HOME_";
    //private static int UID = 0;
    TextView latest_rx = null;
    TextView latest_tx = null;
    TextView previous_rx = null;
    TextView previous_tx = null;
    TextView delta_rx = null;
    TextView delta_tx = null;

    private long mStartRX = 0;

    private long mStartTX = 0;


    public static int successfulTasks = 0;

    TextView tasksLabel;

    int testingTotalAsyncsFired = 0;
    int testingTotalSnaps = 0;

    public static boolean error = false;

    String supertotalMB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latest_rx = (TextView) findViewById(R.id.latest_rx);
        latest_tx = (TextView) findViewById(R.id.latest_tx);
        previous_rx = (TextView) findViewById(R.id.previous_rx);
        previous_tx = (TextView) findViewById(R.id.previous_tx);
        delta_rx = (TextView) findViewById(R.id.delta_rx);
        delta_tx = (TextView) findViewById(R.id.delta_tx);

        tasksLabel = (TextView) findViewById(R.id.tasksLabel);


        mStartRX = TrafficStats.getTotalRxBytes();

        mStartTX = TrafficStats.getTotalTxBytes();
        Log.v(TAG, "STARTING TOTAL RX:[" + mStartRX + "]");
        Log.v(TAG, "STARTING TOTAL TX:[" + mStartTX + "]");


        Button updateTasksLabelBtn = (Button) findViewById(R.id.updateTasksLabelBtn);
        updateTasksLabelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksLabel.setText("Tasks:" + String.valueOf(successfulTasks));
            }
        });

        Button fireHundredBtn = (Button) findViewById(R.id.fireHundredBtn);
        fireHundredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(MainActivity.this, "Firing 100", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < 10; i++) {
                        new ServerTaskServerActivity(v.getContext()).execute("kweaver0");
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to send 100", Toast.LENGTH_SHORT).show();
                }
            }
        });//fireSingleBtn
        Button fireSingleBtn = (Button) findViewById(R.id.fireSingleBtn);
        fireSingleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(MainActivity.this, "Firing one", Toast.LENGTH_SHORT).show();

                    new ServerTaskServerActivity(v.getContext()).execute("kweaver0");

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to send single", Toast.LENGTH_SHORT).show();
                }
            }
        });//fireSingleBtn

        //takeSnapshot(null);
        Button takeSnapShotBtn = (Button) findViewById(R.id.takeSnapShotBtn);
        takeSnapShotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Taking snapshot", Toast.LENGTH_SHORT).show();
                //takeSnapshot(null);
                snap();
            }
        });


        /****
         * RFOR TESTI
         */
        Timer mFireAsyncTimer = new Timer();
        mFireAsyncTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Fire async 1", Toast.LENGTH_SHORT).show();
                            //for (int i = 0; i < 10; i++) {
                            new ServerTaskServerActivity(MainActivity.this).execute("kweaver0");
                            //}
                            testingTotalAsyncsFired++;
                            Log.v(TAG, "Async Number:" + testingTotalAsyncsFired);
                        }
                    });
                } catch (Exception e) {

                }

            }
        }, 0, 120000);
        Timer m10FireAsyncTimer = new Timer();
        m10FireAsyncTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(error == false) {
                                Toast.makeText(MainActivity.this, "Fire async 10", Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < 10; i++) {
                                    new ServerTaskServerActivity(MainActivity.this).execute("kweaver0");
                                    testingTotalAsyncsFired++;
                                }

                                Log.v(TAG, "Async Number:" + testingTotalAsyncsFired);
                            }
                        }
                    });
                } catch (Exception e) {

                }

            }
        }, 0, 10000);

        Timer mSnapshotTimer = new Timer();
        mSnapshotTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(MainActivity.this, "Snap()", Toast.LENGTH_SHORT).show();
                            snap();

                            testingTotalSnaps++;
                            Log.v(TAG, "Snaps Fired:" + testingTotalSnaps);
                        }
                    });
                } catch (Exception e) {

                }

            }
        }, 0, 60000);

    }
    protected int getUID(String packageName){
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            //Log.v(TAG, "Package Name:[" + packageInfo.packageName);
            if(packageInfo.packageName.equals(packageName)){
                Log.v(TAG, "Found");
                return packageInfo.uid;
            }
        }
        return -1;
    }

    protected void snap() {
        int uid = getUID("com.weaverprojects.datausage");
        Log.v(TAG,"UID:" + uid);
        long rxBytes = TrafficStats.getTotalRxBytes()- mStartRX;
        long txBytes = TrafficStats.getTotalTxBytes()- mStartTX;
        Log.v(TAG,"RX TOTAL:[" + rxBytes + "]");
        Log.v(TAG,"TX TOTAL:[" + txBytes + "]");
        Log.v(TAG,"RX IN MEGA[" + ((rxBytes/1024)/1024) + "]");
        Log.v(TAG,"TX IN MEGA[" + ((txBytes/1024)/1024) + "]");
        latest_rx.setText("Re:[" + Long.toString(rxBytes) + "]");
        latest_tx.setText("Se:[" + Long.toString(txBytes) + "]");
        long packageRxBytes = TrafficStats.getUidRxBytes(uid);
        long packageTxBytes = TrafficStats.getUidTxBytes(uid);
        Log.v(TAG,"RX PACK:[" + packageRxBytes + "]");
        Log.v(TAG,"TX PACK:[" + packageTxBytes + "]");
        previous_rx.setText("Re P:[" + Long.toString(packageRxBytes) + "]");
        previous_tx.setText("Sen P:[" + Long.toString(packageTxBytes) + "]");
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
