package com.novalfakhri.muvi.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.novalfakhri.muvi.PopularFragment;
import com.novalfakhri.muvi.TopRatedFragment;
import com.novalfakhri.muvi.UpComingFragment;


public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new PopularFragment();
            case 1 :
                return new UpComingFragment();
            case 2 :
                return new TopRatedFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Popular";
            case 1:
                return "Upcoming";
            case 2:
                return "Top Rated";
        }
        return null;
    }
}
