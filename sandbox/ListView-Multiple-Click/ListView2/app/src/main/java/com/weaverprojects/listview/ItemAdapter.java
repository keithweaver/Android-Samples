package com.weaverprojects.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Weaver on 15-05-05.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context c, List<Item> items) {
        super(c, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ItemView itemView = (ItemView)convertView;
        //if (null == itemView)
            //itemView = ItemView.inflate(parent);


        View row = convertView;
        if(row == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            row = vi.inflate(R.layout.item_view_children, null);
        }

        Item p = getItem(position);
        if(p != null){




        }


        //itemView.setItem(getItem(position));
        //return itemView;
        return row;
    }

}
