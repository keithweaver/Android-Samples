package com.weaverprojects.listview;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-05-08.
 */
public class CustomAdapter extends ArrayAdapter<User> {
    Context context;
    int layoutResourceId;
    ArrayList<User> data = new ArrayList<User>();


    public CustomAdapter(Context context, int layoutResourceId, ArrayList<User> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new UserHolder();
            holder.textName = (TextView) row.findViewById(R.id.text1);
            holder.textUser = (TextView) row.findViewById(R.id.text2);

            holder.btn1 = (Button) row.findViewById(R.id.btn1);
            holder.btn2 = (Button) row.findViewById(R.id.btn2);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        User user = data.get(position);
        holder.textName.setText(user.getName());
        holder.textUser.setText(user.getUser());

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("BTN1 WAS CLICKED:", ""+position);
                //Toast.makeText(context, "Btn1 Clicked", Toast.LENGTH_SHORT).show();

            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("BTN2 WAS CLICKED",""+position);
                //Toast.makeText(context, "Btn2 Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return row;
    }
    static class UserHolder{
        TextView textName;
        TextView textUser;
        Button btn1;
        Button btn2;
    }
}
