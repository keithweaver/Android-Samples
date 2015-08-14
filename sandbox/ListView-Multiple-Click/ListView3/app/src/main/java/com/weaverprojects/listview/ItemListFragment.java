package com.weaverprojects.listview;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-05-07.
 */
public class ItemListFragment extends ListFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ArrayList<Item> values = new ArrayList<Item>();

        String s = "Keith Weaver";
        Item item = new Item(s);
        values.add(item);
        setListAdapter(new ItemAdapter(getActivity(), values));

        return v;
    }
}
