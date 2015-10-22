package com.weaverprojects.shuttle.View.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weaverprojects.shuttle.Controller.C;
import com.weaverprojects.shuttle.Controller.local.SavedFriendsDataDB;
import com.weaverprojects.shuttle.Model.ConvoListItem;
import com.weaverprojects.shuttle.R;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-10-21.
 */
public class ConvoListAdapter extends ArrayAdapter<ConvoListItem> {
    Context context;
    int layoutResourceId;
    ArrayList<ConvoListItem> data = new ArrayList<ConvoListItem>();

    SavedFriendsDataDB mSavedFriendsDataDB;

    public ConvoListAdapter(Context context, int layoutResourceId, ArrayList<ConvoListItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

        mSavedFriendsDataDB = new SavedFriendsDataDB(context, C.local.DB_NAME, null, 1);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new UserHolder();
            holder.nameTextView = (TextView) row.findViewById(R.id.nameTextView);
            holder.userTextView = (TextView) row.findViewById(R.id.userTextView);
            holder.chatTextView = (TextView) row.findViewById(R.id.chatTextView);
            holder.userImg = (ImageView) row.findViewById(R.id.userImg);
            holder.convoItemWrapper = (GridLayout) row.findViewById(R.id.convoItemWrapper);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        ConvoListItem singleRow = data.get(position);
        String fullName = mSavedFriendsDataDB.getName(singleRow.getUserName());
        if(fullName.equals("")) {
            holder.nameTextView.setText(singleRow.getUserName());
        }else{
            holder.nameTextView.setText(fullName);
        }
        holder.userTextView.setText(singleRow.getUserName());
        holder.chatTextView.setText(singleRow.getLastMessage());

        Picasso.with(context).load(singleRow.getImgLink()).into(holder.userImg);


        holder.convoItemWrapper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("BTN2 WAS CLICKED", "" + position);
                //Toast.makeText(context, "Btn2 Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return row;
    }
    static class UserHolder{
        TextView nameTextView;
        TextView userTextView;
        TextView chatTextView;
        ImageView userImg;
        GridLayout convoItemWrapper;
    }
}
