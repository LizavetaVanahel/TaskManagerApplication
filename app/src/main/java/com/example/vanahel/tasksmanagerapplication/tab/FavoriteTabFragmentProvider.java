package com.example.vanahel.tasksmanagerapplication.tab;

/**
 * Created by dvkoleda on 24.07.17.
 */

public class FavoriteTabFragmentProvider {

    private static FavoriteTabFragment favoriteTabFragment;

    private FavoriteTabFragmentProvider(){
    }

    public static FavoriteTabFragment getFavoriteTabFragment(){
        if (favoriteTabFragment == null){
            favoriteTabFragment = new FavoriteTabFragment();
        }
        return favoriteTabFragment;
    }
}
