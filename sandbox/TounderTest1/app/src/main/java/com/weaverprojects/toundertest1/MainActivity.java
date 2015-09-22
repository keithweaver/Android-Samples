package com.weaverprojects.toundertest1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tounder.tounder.Tounder;


public class MainActivity extends Activity {
    int sent = 0;
    TextView count;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.sendBtn);
        count = (TextView) findViewById(R.id.count);

        final Tounder mTounder = new Tounder(this, "testapp", "hMlgKiJ-UfqA2jdUe88NIE5dqTrDlRjWkVAxChElqABRrC~RWbeffERfX50166130FjFWOc");

        mTounder.registerThisDeviceForPushNotifications("kweaver");

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sent++;
                updateUI();
                mTounder.sendNotificationToServer("kweaver", "kweaver", "msg " + String.valueOf(sent));
                Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void updateUI(){
        count.setText("Sent:" + String.valueOf(sent));
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
