package com.example.vanahel.tasksmanagerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.vanahel.tasksmanagerapplication.contracts.SettingsActivityContract;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.presenter.SettingsActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends PreferenceActivity implements NavigationView.OnNavigationItemSelectedListener,
        SettingsActivityContract.View {

    private TaskDAO taskDAO;
    private SettingsActivityPresenter presenter;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        addPreferencesFromResource(R.xml.preferences);
        ButterKnife.bind(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        ListPreference listPreference = (ListPreference) findPreference("listPref");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        presenter = new SettingsActivityPresenter(this);
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object object) {
                onTaskSettingsChange(object, taskDAO);
                return true;
               }
        });
    }

    @NonNull
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_task) {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, 0);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, 0);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onTaskSettingsChange(Object object, TaskDAO taskDAO) {
        presenter.onTaskSettingsChanged(object, taskDAO);
    }
}
