package com.weaverprojects.tabnavigation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Keith on 2015-10-02.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();
            case 2:
                return new Tab3Fragment();
        }

        return null;
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}
