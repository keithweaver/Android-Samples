package com.weaverprojects.listview4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-05-07.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.row_template, values);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        if(row == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            row = vi.inflate(R.layout.row_template, null);
        }
        String p = getItem(position);
        Log.e("::",p);
        Log.e(":::",""+position);
        if(p != null){
            Button btn1 = (Button) row.findViewById(R.id.btn1);
            Button btn2 = (Button) row.findViewById(R.id.btn2);

            Log.e("P:",p);
            if(btn1 != null){
                Log.e("---","BTN 1 CLICKED");

            }
            if(btn2 != null){
                Log.e("---","BTN 2 CLICKED");

            }
        }
        return row;
    }
}
