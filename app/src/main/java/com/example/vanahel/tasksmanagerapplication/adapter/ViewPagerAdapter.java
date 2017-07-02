package com.example.vanahel.tasksmanagerapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vanahel.tasksmanagerapplication.tab.AllTabFragment;
import com.example.vanahel.tasksmanagerapplication.tab.FavoriteTabFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 2;

    public ViewPagerAdapter (FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllTabFragment allTabFragment = AllTabFragment.getAllTabFragment();
                return allTabFragment;
            case 1:
                FavoriteTabFragment favoriteTabFragment = FavoriteTabFragment.getAllTabFragment();
                return favoriteTabFragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
