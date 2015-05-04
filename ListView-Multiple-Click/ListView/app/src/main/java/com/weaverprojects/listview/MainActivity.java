package com.weaverprojects.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);
        String[] values = new String[]{"Keith Weaver","Scott Weaver","Dana Weaver"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listView.getItemAtPosition(position);
                Log.v("Id:",String.valueOf(id));
                Log.v("Item Value:", itemValue);
                Log.v("Position:", String.valueOf(position));

                TextView positionLabel = (TextView)findViewById(R.id.positionLabel);
                positionLabel.setText(String.valueOf(position));


                /*
                if(view.getId() == R.id.btn1){
                    Log.v("","CLICK BUTTON 1");
                    //Log.v("","");
                }else if(view.getId() == R.id.btn2){
                    Log.v("","CLICK BUTTON 2");
                }
                */

            }

        });


    }
    //public View getView ( int position, View convertView, ViewGroup parent ) {
            // Inflate the custom view convertView = mInflater . inflate ( R . layout . custom_list_item, null ) ; final Day currDay = ( Day ) mItems . get ( position ) ; // Set the progress bar ( ( ProgressBar ) convertView . findViewById ( R . id . progressBar ) ) . setProgress ( ( int ) currDay . getProgress ( ) ) // Set the Date text	( ( TextView ) convertView . findViewById ( R . id . dateCell ) ) . setText ( currDay . getDateAsString ( ) ) ;
    //        return convertView ;
    //}



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
