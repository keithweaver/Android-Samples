package com.weaverprojects.test5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-05-24.
 */
public class FriendRequestAdapter extends ArrayAdapter<User> {
    Context mContext;
    int layoutResourceId;
    ArrayList<User> data = new ArrayList<User>();
    int size = 0;


    public FriendRequestAdapter(Context context, int layoutResourceId, ArrayList<User> data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        this.data = data;
        size++;
    }
    public View getView(final int position,  View convertView, ViewGroup parent){
        View row = convertView;
        UserHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new UserHolder();
            holder.textName = (TextView) row.findViewById(R.id.text1);
            holder.textUser = (TextView) row.findViewById(R.id.text2);
            holder.btn1 = (ImageView) row.findViewById(R.id.btn1);
            holder.btn2 = (ImageView) row.findViewById(R.id.btn2);

            row.setTag(holder);
        }

        User user = data.get(position);

        holder.textName.setText(user.getName());
        holder.textUser.setText(user.getUser());



        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptFriendRequest(position);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denyFriendRequest(position);
            }
        });





        return row;
    }

    static class UserHolder{
        TextView textName;
        TextView textUser;
        ImageView btn1;
        ImageView btn2;
    }

    protected void display(String s){
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
    }
    protected void acceptFriendRequest(int position){
        String temp = MainActivity.friendsMap.get(position);
        display("Accept " + temp + " - " + String.valueOf(position));
    }
    protected void denyFriendRequest(int position){
        String temp = MainActivity.friendsMap.get(position);
        display("Deny " + temp + " - " + String.valueOf(position));
    }


}
