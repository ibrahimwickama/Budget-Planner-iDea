package com.wickerlabs.exp_tr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wickerlabs.exp_tr.DisplaysCharts.Disp1;
import com.wickerlabs.exp_tr.DisplaysCharts.Disp2;

/**
 * Created by anupamchugh on 26/12/15.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
            // it switches the Chart displays on viewPager
        switch(pos) {
            case 0: return Disp1.newInstance("Disp1, Instance 1");
            case 1: return Disp2.newInstance("Disp2, Instance 1");
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


}
