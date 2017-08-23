package com.example.vanahel.tasksmanagerapplication.dao;

import android.util.Log;

import com.example.vanahel.tasksmanagerapplication.task.Task;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InternetDAO implements TaskDAO {

    private static final String READ_FAILED_TAG = "Database read failed";
    private static final String FIREBASE_DATABASE_URL =
            "https://tasksmanagerapplication.firebaseio.com/";
    private Firebase ref = new Firebase( FIREBASE_DATABASE_URL );
    private List <Task> tasksList;
    private Task task;
    private List <Task> currentTasksList;

    public InternetDAO (){
        tasksList = new ArrayList<>();
        currentTasksList = new ArrayList<>();
        ref.child("tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                currentTasksList.clear();
                for ( DataSnapshot postSnapshot : dataSnapshot.getChildren() ) {
                    task = postSnapshot.getValue( Task.class );
                    currentTasksList.add(task);
                }
                tasksList.clear();
                tasksList.addAll(currentTasksList);
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {
                Log.d( READ_FAILED_TAG, "The read failed: " + firebaseError.getMessage() );
            }
        });
    }

    @Override
    public void save(Task task) {

        ref.child("tasks").child(task.getId()).setValue(task);
    }

    @Override
    public List<Task> getTasks() {
        return tasksList;
    }

    @Override
    public List<Task> getFavoriteTasks() {
        final List<Task> favoriteTasksList = new ArrayList<>();
        for ( Task currentTask : tasksList ) {
            if (currentTask.getFavorite()){
                favoriteTasksList.add(currentTask);
            }
        }
        return favoriteTasksList;
    }

    @Override
    public void delete(Task task) throws IOException {
        ref.child("tasks").child(task.getId()).removeValue();
    }

    @Override
    public void updateTask( Task newTask, Task oldTask ) throws IOException {

        ref.child("tasks").child(oldTask.getId()).setValue(newTask);
    }

}
