package com.example.vanahel.tasksmanagerapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vanahel.tasksmanagerapplication.tab.AllTabFragmentProvider;
import com.example.vanahel.tasksmanagerapplication.tab.FavoriteTabFragmentProvider;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 2;
    private Fragment fragment = null;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = AllTabFragmentProvider.getAllTabFragment();
                break;
            case 1:
                fragment = FavoriteTabFragmentProvider.getFavoriteTabFragment();
                break;
        }

        return fragment;
    }


    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    public Fragment getCurrentFragment() {
        return fragment;
    }
}
