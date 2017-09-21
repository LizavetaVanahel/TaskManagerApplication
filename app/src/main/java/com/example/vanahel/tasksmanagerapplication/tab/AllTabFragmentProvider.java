package com.example.vanahel.tasksmanagerapplication.tab;

public class AllTabFragmentProvider {

    private static AllTabFragment allTabFragment;

    private AllTabFragmentProvider() {
    }

    public static AllTabFragment getAllTabFragment() {
        if (allTabFragment == null) {
            allTabFragment = new AllTabFragment();
        }
        return allTabFragment;
    }
}
