package com.weaverprojects.tabnavigation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Keith on 2015-10-02.
 */
public class Tab1Fragment  extends Fragment implements View.OnClickListener {

    protected Activity mActivity;
    private ImageView mCamera;
    private TextView intro;

    @Override
    public void onAttach(Activity act)
    {
        super.onAttach(act);
        mActivity = act;
    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);



        return view;
    }

    @Override
    public void onClick(View v)
    {
        //do whatever you want here
    }
}