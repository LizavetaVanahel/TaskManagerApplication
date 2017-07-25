package com.example.vanahel.tasksmanagerapplication.tab;

/**
 * Created by dvkoleda on 24.07.17.
 */

public class AllTabFragmentProvider {

    private static AllTabFragment allTabFragment;

    private AllTabFragmentProvider(){
    }

    public static AllTabFragment getAllTabFragment(){
        if (allTabFragment == null){
            allTabFragment = new AllTabFragment();
        }
        return allTabFragment;
    }
}
