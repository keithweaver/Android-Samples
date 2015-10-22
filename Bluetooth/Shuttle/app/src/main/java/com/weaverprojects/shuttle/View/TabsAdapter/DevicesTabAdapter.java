package com.weaverprojects.shuttle.View.TabsAdapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weaverprojects.shuttle.View.Windows.Fragments.FoundDevicesFragment;
import com.weaverprojects.shuttle.View.Windows.Fragments.PairedDevicesFragment;

/**
 * Created by Keith on 2015-10-21.
 */
public class DevicesTabAdapter extends FragmentPagerAdapter {

    public DevicesTabAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new PairedDevicesFragment();
            case 1:
                return new FoundDevicesFragment();
        }

        return null;
    }

    @Override
    public int getCount()
    {
        return 2;
    }
}
