package com.example.vanahel.tasksmanagerapplication;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.vanahel.tasksmanagerapplication.adapter.TasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.constants.TabConstants;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.event.listener.NewTaskButtonListener;
import com.example.vanahel.tasksmanagerapplication.event.listener.TaskListItemClickListener;
import com.example.vanahel.tasksmanagerapplication.loader.callback.AsyncTaskLoaderCallbacks;
import com.example.vanahel.tasksmanagerapplication.loader.callback.FavoriteAsyncTasksLoaderCallbacks;

import static com.example.vanahel.tasksmanagerapplication.constants.LoaderConstants.ALL_TASKS_LOADER_ID;
import static com.example.vanahel.tasksmanagerapplication.constants.LoaderConstants.FAVORITE_TASKS_LOADER_ID;

public class MainActivity extends TabActivity
 implements NavigationView.OnNavigationItemSelectedListener {

    private ListView tasksList;
    private ListView favoriteTasksList;
    private TasksArrayAdapter allTasksArrayAdapter;
    private TasksArrayAdapter favoriteTasksArrayAdapter;
    private AsyncTaskLoaderCallbacks asyncTaskLoaderCallbacks;
    private FavoriteAsyncTasksLoaderCallbacks favoriteAsyncTasksLoaderCallbacks;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FloatingActionButton createNewTaskButton = (FloatingActionButton) findViewById(R.id.new_task_button);
        tasksList = (ListView) findViewById(R.id.simple_task_list_view);
        favoriteTasksList = (ListView) findViewById(R.id.favorite_task_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TaskDAO taskDAO = DAOManager.getInstance().getTaskDAO();

        allTasksArrayAdapter = new TasksArrayAdapter(this);
        favoriteTasksArrayAdapter = new TasksArrayAdapter(this);

        asyncTaskLoaderCallbacks = new AsyncTaskLoaderCallbacks(this, taskDAO, allTasksArrayAdapter);
        favoriteAsyncTasksLoaderCallbacks = new FavoriteAsyncTasksLoaderCallbacks(MainActivity.this,
                taskDAO, favoriteTasksArrayAdapter);

        TabHost tabHost = getTabHost();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        this.setNewTab(this, tabHost, TabConstants.ALL_TAB, R.string.textTabTitle1, android.R.drawable.star_on, R.id.tab1);
        this.setNewTab(this, tabHost, TabConstants.FAVORITE_TAB, R.string.textTabTitle2,
                android.R.drawable.star_on, R.id.tab2);

        NewTaskButtonListener newTaskButtonListener =
                new NewTaskButtonListener(this, tabHost);
        createNewTaskButton.setOnClickListener(newTaskButtonListener);

        TaskListItemClickListener  taskListItemClickListener = new TaskListItemClickListener(this);
        tasksList.setOnItemClickListener(taskListItemClickListener);
        favoriteTasksList.setOnItemClickListener(taskListItemClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getLoaderManager().restartLoader(ALL_TASKS_LOADER_ID, null,
                asyncTaskLoaderCallbacks).forceLoad();
        getLoaderManager().restartLoader(FAVORITE_TASKS_LOADER_ID, null,
                favoriteAsyncTasksLoaderCallbacks).forceLoad();

        tasksList.setAdapter(allTasksArrayAdapter);
        favoriteTasksList.setAdapter(favoriteTasksArrayAdapter);

    }

    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, int contentID ){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        String titleString = getString(title);
        tabSpec.setIndicator(titleString, context.getResources().getDrawable(android.R.drawable.star_on));
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }

        @NonNull
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.nav_task) {
                 Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 0);
            } else if (id == R.id.nav_settings) {
                 Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 0);
         }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

        }

}
