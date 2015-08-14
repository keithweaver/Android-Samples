package com.weaverprojects.local1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    String TAG = "InLOCAL1_APP_";
    db mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = new db(this);

        //SQLiteDatabse mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
        //SQLiteDatabase mda = openOrCreateDatabase("name", MODE_PRIVATE, null);
        final TextView loadLabel = (TextView) findViewById(R.id.loadLabel);

        final TextView numberLabel = (TextView) findViewById(R.id.numberLabel);
        final EditText textField = (EditText) findViewById(R.id.editText);
        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textField.getText().toString();
                mDb.insert("test", s);
                numberLabel.setText("Number of items:" + String.valueOf(mDb.numberOfRows()));
                textField.setText("");
            }
        });
        Button updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textField.getText().toString();
                try{
                    int position = Integer.parseInt(s);
                    try {
                        mDb.updateContact(position, "NEW VALUE", "COL@" + position);
                    }catch(Exception e){
                        loadLabel.setText("Error: updating contact");
                    }
                }catch (Exception e){
                    loadLabel.setText("Error: converting value to integer");
                }
                textField.setText("");
            }
        });
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textField.getText().toString();
                try{
                    int position = Integer.parseInt(s);
                    mDb.deleteContact(position);
                    loadLabel.setText("Deleted");
                }catch(Exception e){
                    loadLabel.setText("Error1: converting to integer\n" + s);
                    Log.e(TAG, "_S:" + s);
                    Log.e(TAG, "_STACK:" + e.toString());
                }
                textField.setText("");
            }
        });


        Button loadBtn = (Button) findViewById(R.id.loadBtn);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> l = mDb.getAllCotacts();
                for(String item : l){
                    loadLabel.setText(loadLabel.getText() + ", " + item);
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
