package com.weaverprojects.point;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-03-05.
 */
public class add_friend extends ListActivity{
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend);

        adapter = new ArrayAdapter<String>(this, R.layout.add_friend_row, list);
        list.add("Keith Weaver");
        adapter.notifyDataSetChanged();
        setListAdapter(adapter);
        /*
        final ListView friendList = (ListView) findViewById(R.id.listView);
        String[] values = new String[]{"Keith Weaver","John Smith"};


        final ArrayList<String> myFriends = new ArrayList<String>();
        for(int i = 0;i<values.length;i++){
            myFriends.add(values[i]);

        }
           */
        /*
        final StableArrayAdapter adapter = new StableArrayAdapter(this, R.layout.add_friend_row, myFriends);
        friendList.setAdapter(adapter);


        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                myFriends.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }

        });
        */
    }
    /*
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
    */
}
