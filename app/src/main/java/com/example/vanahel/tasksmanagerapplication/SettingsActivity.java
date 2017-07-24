package com.example.vanahel.tasksmanagerapplication;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.example.vanahel.tasksmanagerapplication.contracts.SettingsActivityContract;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.presenter.SettingsActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends PreferenceActivity implements
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

        SideMenuNavigation sideMenuNavigation = new SideMenuNavigation( this, drawer );

        navigationView.setNavigationItemSelectedListener( sideMenuNavigation );

        presenter = new SettingsActivityPresenter(this);
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object object) {
                presenter.onTaskSettingsChanged( object, taskDAO );
                return true;
               }
        });
    }
}
