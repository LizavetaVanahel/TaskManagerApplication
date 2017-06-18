package com.example.vanahel.tasksmanagerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.vanahel.tasksmanagerapplication.constants.SettingsConstants;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.event.listener.SettingsPreferenceChangeListener;
import com.example.vanahel.tasksmanagerapplication.event.listener.SettingsPreferenceClickListener;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends PreferenceActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TaskDAO taskDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        final List<CheckBoxPreference> checkBoxPreferenceList = new ArrayList<>();

        CheckBoxPreference memoryPref =
                (CheckBoxPreference) getPreferenceManager().findPreference(SettingsConstants.MEMORY);
        CheckBoxPreference sharedprefPref =
                (CheckBoxPreference) getPreferenceManager().findPreference(SettingsConstants.SHARED_PREFERENCES);
        CheckBoxPreference databasePref =
                (CheckBoxPreference) getPreferenceManager().findPreference(SettingsConstants.DATABASE);
        CheckBoxPreference externalstoragePref =
                (CheckBoxPreference) getPreferenceManager().findPreference(SettingsConstants.EXTERNAL_STORAGE);
        CheckBoxPreference internalstoragePref =
                (CheckBoxPreference) getPreferenceManager().findPreference(SettingsConstants.INTERNAL_STORAGE);

        checkBoxPreferenceList.add(memoryPref);
        checkBoxPreferenceList.add(sharedprefPref);
        checkBoxPreferenceList.add(databasePref);
        checkBoxPreferenceList.add(externalstoragePref);
        checkBoxPreferenceList.add(internalstoragePref);

         SettingsPreferenceClickListener settingsPreferenceClickListener =
                new SettingsPreferenceClickListener(checkBoxPreferenceList);

        SettingsPreferenceChangeListener settingsPreferenceChangeListener =
                new SettingsPreferenceChangeListener(this, taskDAO);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        memoryPref.setOnPreferenceChangeListener(settingsPreferenceChangeListener);
        sharedprefPref.setOnPreferenceChangeListener(settingsPreferenceChangeListener);
        databasePref.setOnPreferenceChangeListener(settingsPreferenceChangeListener);
        externalstoragePref.setOnPreferenceChangeListener(settingsPreferenceChangeListener);
        internalstoragePref.setOnPreferenceChangeListener(settingsPreferenceChangeListener);

        memoryPref.setOnPreferenceClickListener(settingsPreferenceClickListener);
        sharedprefPref.setOnPreferenceClickListener(settingsPreferenceClickListener);
        databasePref.setOnPreferenceClickListener(settingsPreferenceClickListener);
        externalstoragePref.setOnPreferenceClickListener(settingsPreferenceClickListener);
        internalstoragePref.setOnPreferenceClickListener(settingsPreferenceClickListener);
    }

    @NonNull
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_task) {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
