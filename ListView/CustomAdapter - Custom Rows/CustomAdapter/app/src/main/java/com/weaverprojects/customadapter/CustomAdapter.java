package com.weaverprojects.customadapter;

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
 * Created by Keith on 2015-09-02.
 */
public class CustomAdapter extends ArrayAdapter<SingleRow> {
    Context context;
    int layoutResourceId;
    ArrayList<SingleRow> data = new ArrayList<SingleRow>();

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<SingleRow> data) {
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
            holder.topLabel = (TextView) row.findViewById(R.id.topLabel);
            holder.subLabel = (TextView) row.findViewById(R.id.subLabel);

            holder.btn1 = (Button) row.findViewById(R.id.btn1);
            holder.btn2 = (Button) row.findViewById(R.id.btn2);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        SingleRow singleRow = data.get(position);
        holder.topLabel.setText(singleRow.getMainText());
        holder.subLabel.setText(singleRow.getSubText());

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("BTN1 WAS CLICKED:", "" + position);
                //Toast.makeText(context, "Btn1 Clicked", Toast.LENGTH_SHORT).show();

            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("BTN2 WAS CLICKED",""+position);
                //Toast.makeText(context, "Btn2 Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return row;
    }
    static class UserHolder{
        TextView topLabel;
        TextView subLabel;
        Button btn1;
        Button btn2;
    }
}
