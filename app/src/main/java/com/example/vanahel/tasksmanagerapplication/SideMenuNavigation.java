package com.example.vanahel.tasksmanagerapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class SideMenuNavigation implements NavigationView.OnNavigationItemSelectedListener {

    private Activity activity;
    private DrawerLayout drawer;

    public SideMenuNavigation (Activity activity, DrawerLayout drawer){
        this.activity = activity;
        this.drawer = drawer;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_task) {
            Intent intent = new Intent( activity, MainActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            activity.startActivityForResult( intent, 0 );
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent( activity, SettingsActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            activity.startActivityForResult( intent, 0 );
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
