package com.weaverprojects.shuttle.View.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weaverprojects.shuttle.Model.Message;
import com.weaverprojects.shuttle.R;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-10-25.
 */
public class MessageAdapter extends ArrayAdapter<Message> {
    Context context;
    int layoutResourceId;
    ArrayList<Message> data = new ArrayList<>();

    public MessageAdapter(Context context, int layoutResourceId, ArrayList<Message> data){
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    public View getView(final int position, View row, ViewGroup parent){
        MessageHolder mHolder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            mHolder = new MessageHolder();
            mHolder.messageMasterWrapper = (LinearLayout) row.findViewById(R.id.messageMasterWrapper);
            mHolder.messageWrapper = (LinearLayout) row.findViewById(R.id.messageWrapper);
            mHolder.messageText = (TextView) row.findViewById(R.id.messageText);

            row.setTag(mHolder);
        }else{
            mHolder = (MessageHolder) row.getTag();
        }

        Message singleMessage = data.get(position);

        if(singleMessage.isOwner()) {
            mHolder.messageMasterWrapper.setGravity(Gravity.RIGHT);
            mHolder.messageWrapper.setBackgroundColor(Color.parseColor("#FFD5EEFF"));
        }else{
            mHolder.messageMasterWrapper.setGravity(Gravity.LEFT);
            mHolder.messageWrapper.setBackgroundColor(Color.parseColor("#FFBAFFA8"));
        }
        mHolder.messageText.setText(singleMessage.getMessage());

        return row;
    }
    static class MessageHolder{
        LinearLayout messageMasterWrapper;
        LinearLayout messageWrapper;
        TextView messageText;
    }
}