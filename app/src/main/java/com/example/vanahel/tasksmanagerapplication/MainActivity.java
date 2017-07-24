package com.example.vanahel.tasksmanagerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.vanahel.tasksmanagerapplication.adapter.ViewPagerAdapter;
import com.example.vanahel.tasksmanagerapplication.contracts.MainActivityContract;
import com.example.vanahel.tasksmanagerapplication.presenter.MainActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
 implements TabLayout.OnTabSelectedListener,
        MainActivityContract.View{

    private MainActivityPresenter presenter;
    private ViewPagerAdapter viewPagerAdapter;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.new_task_button)
    FloatingActionButton createNewTaskButton;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate( final Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        tabLayout.addTab( tabLayout.newTab().setText("All") );
        tabLayout.addTab( tabLayout.newTab().setText("Favorite") );

        viewPagerAdapter =
                new ViewPagerAdapter( getSupportFragmentManager() );

        viewPager.setAdapter( viewPagerAdapter );

        tabLayout.setOnTabSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        SideMenuNavigation sideMenuNavigation = new SideMenuNavigation( this, drawer );

        navigationView.setNavigationItemSelectedListener( sideMenuNavigation );

        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ) );

        presenter = new MainActivityPresenter( this, viewPager );
        createNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onNewTaskButtonClicked();
            }
        });
    }

    @Override
    public void onTabSelected( TabLayout.Tab tab ) {
        viewPager.setCurrentItem( tab.getPosition() );
        viewPagerAdapter.getCurrentFragment().onResume();

    }

    @Override
    public void onTabUnselected( TabLayout.Tab tab ) {

    }

    @Override
    public void onTabReselected( TabLayout.Tab tab ) {

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );
        if ( resultCode == RESULT_OK ){
            viewPagerAdapter.getCurrentFragment().onResume();
        }
    }
}



