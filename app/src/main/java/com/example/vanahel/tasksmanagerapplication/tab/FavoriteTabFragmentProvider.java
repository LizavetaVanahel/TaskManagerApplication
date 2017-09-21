package com.example.vanahel.tasksmanagerapplication.tab;

public class FavoriteTabFragmentProvider {

    private static FavoriteTabFragment favoriteTabFragment;

    private FavoriteTabFragmentProvider() {
    }

    public static FavoriteTabFragment getFavoriteTabFragment() {
        if (favoriteTabFragment == null) {
            favoriteTabFragment = new FavoriteTabFragment();
        }
        return favoriteTabFragment;
    }
}
