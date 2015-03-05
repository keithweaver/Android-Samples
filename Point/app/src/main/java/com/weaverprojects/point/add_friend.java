package com.weaverprojects.point;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-03-05.
 */
public class add_friend extends ListActivity{
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> a_list = new ArrayList<String>();
    ArrayList<String> b_list = new ArrayList<String>();
    ArrayList<String> c_list = new ArrayList<String>();
    ArrayList<String> d_list = new ArrayList<String>();
    ArrayList<String> e_list = new ArrayList<String>();
    ArrayList<String> f_list = new ArrayList<String>();
    ArrayList<String> g_list = new ArrayList<String>();
    ArrayList<String> h_list = new ArrayList<String>();
    ArrayList<String> i_list = new ArrayList<String>();
    ArrayList<String> j_list = new ArrayList<String>();
    ArrayList<String> k_list = new ArrayList<String>();
    ArrayList<String> l_list = new ArrayList<String>();
    ArrayList<String> m_list = new ArrayList<String>();
    ArrayList<String> n_list = new ArrayList<String>();
    ArrayList<String> o_list = new ArrayList<String>();
    ArrayList<String> p_list = new ArrayList<String>();
    ArrayList<String> q_list = new ArrayList<String>();
    ArrayList<String> r_list = new ArrayList<String>();
    ArrayList<String> s_list = new ArrayList<String>();
    ArrayList<String> t_list = new ArrayList<String>();
    ArrayList<String> u_list = new ArrayList<String>();
    ArrayList<String> v_list = new ArrayList<String>();
    ArrayList<String> w_list = new ArrayList<String>();
    ArrayList<String> x_list = new ArrayList<String>();
    ArrayList<String> y_list = new ArrayList<String>();
    ArrayList<String> z_list = new ArrayList<String>();

    //ListView listview;
    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> a_adapter;
    ArrayAdapter<String> b_adapter;
    ArrayAdapter<String> c_adapter;
    ArrayAdapter<String> d_adapter;
    ArrayAdapter<String> e_adapter;
    ArrayAdapter<String> f_adapter;
    ArrayAdapter<String> g_adapter;
    ArrayAdapter<String> h_adapter;
    ArrayAdapter<String> i_adapter;
    ArrayAdapter<String> j_adapter;
    ArrayAdapter<String> k_adapter;
    ArrayAdapter<String> l_adapter;
    ArrayAdapter<String> m_adapter;
    ArrayAdapter<String> n_adapter;
    ArrayAdapter<String> o_adapter;
    ArrayAdapter<String> p_adapter;
    ArrayAdapter<String> q_adapter;
    ArrayAdapter<String> r_adapter;
    ArrayAdapter<String> s_adapter;
    ArrayAdapter<String> t_adapter;
    ArrayAdapter<String> u_adapter;
    ArrayAdapter<String> v_adapter;
    ArrayAdapter<String> w_adapter;
    ArrayAdapter<String> x_adapter;
    ArrayAdapter<String> y_adapter;
    ArrayAdapter<String> z_adapter;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.add_friend);
        }catch(Exception e){
            Log.v("Page Error:", "Doesn't load");
        }
        //listview = (ListView) findViewById(R.id.listView);

        a_list.add("alittle");
        b_list.add("bee");
        if(a_list.size() > 0) {

            ArrayAdapter<String> a_adapter = new ArrayAdapter<String>(this, R.layout.add_friend_row, R.id.text1, a_list);

            ListView a_list_view = (ListView) findViewById(R.id.a_listview);
            a_list_view.setAdapter(a_adapter);
        }
        if(b_list.size() > 0) {

            ArrayAdapter<String> b_adapter = new ArrayAdapter<String>(this, R.layout.add_friend_row, R.id.text1, b_list);

            ListView b_list_view = (ListView) findViewById(R.id.b_listview);
            b_list_view.setAdapter(b_adapter);
        }
        if(c_list.size() > 0) {

            ArrayAdapter<String> c_adapter = new ArrayAdapter<String>(this, R.layout.add_friend_row, R.id.text1, c_list);

            ListView c_list_view = (ListView) findViewById(R.id.c_listview);
            c_list_view.setAdapter(c_adapter);
        }
        if(d_list.size() > 0) {

            ArrayAdapter<String> d_adapter = new ArrayAdapter<String>(this, R.layout.add_friend_row, R.id.text1, d_list);

            ListView d_list_view = (ListView) findViewById(R.id.d_listview);
            d_list_view.setAdapter(d_adapter);
        }

    }

}
