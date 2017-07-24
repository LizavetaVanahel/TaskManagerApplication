package com.example.vanahel.tasksmanagerapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vanahel.tasksmanagerapplication.tab.AllTabFragment;
import com.example.vanahel.tasksmanagerapplication.tab.FavoriteTabFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment fragment = null;
    private static final int TAB_COUNT = 2;

    public ViewPagerAdapter ( FragmentManager fragmentManager ) {
        super( fragmentManager );
    }

    @Override
    public Fragment getItem( int position ) {
        switch ( position ) {
            case 0:
                fragment = AllTabFragment.getAllTabFragment();
                break;
            case 1:
                fragment = FavoriteTabFragment.getAllTabFragment();
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
