package com.weaverprojects.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Weaver on 15-05-04.
 */
public class ItemAdapter extends ArrayAdapter<Item>

    {

    public ItemAdapter(Context c, List <Item> items) {
        super(c, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainActivity itemView = (MainActivity)convertView;
        if (null == itemView)
            itemView = ItemView.inflate(parent);
        itemView.setItem(getItem(position));
        return itemView;
    }
}
