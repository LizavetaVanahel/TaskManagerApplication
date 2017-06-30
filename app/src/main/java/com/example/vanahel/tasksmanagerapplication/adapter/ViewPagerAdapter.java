package com.example.vanahel.tasksmanagerapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vanahel.tasksmanagerapplication.tab.AllTabFragment;
import com.example.vanahel.tasksmanagerapplication.tab.FavoriteTabFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public ViewPagerAdapter (FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllTabFragment allTabFragment = new AllTabFragment();
                return allTabFragment;
            case 1:
                FavoriteTabFragment favoriteTabFragment = new FavoriteTabFragment();
                return favoriteTabFragment;
            default:
                return null;
        }
    }



    @Override
    public int getCount() {
        return tabCount;
    }
}
