package com.example.vanahel.tasksmanagerapplication.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SharedPreferencesDAO implements TaskDAO {

    private final static String SHARED_PREFERENCES_STORAGE = "Task";
    private final static String SHARED_PREFERENCES_VARIABLE = "taskList";
    private final static String COMMA = ",";
    private static final String TAG = "SharedPreferencesDAO";
    private Context context;


    public SharedPreferencesDAO(Context context) {
        this.context = context;
    }

    @Override
    public void save(Task task) {

        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String tasksString = sharedPreferences.getString(SHARED_PREFERENCES_VARIABLE, "");
        Integer favorite = task.getFavoriteAsInt();
        String tasksCSV = task.getTitle() + COMMA + task.getDescription() + COMMA + favorite
                + COMMA + task.getId() + System.lineSeparator();
        String result = tasksString + tasksCSV;

        edit.putString(SHARED_PREFERENCES_VARIABLE, result).apply();
    }

    @Override
    public List<Task> getTasks() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_STORAGE, Context.MODE_PRIVATE);
        String tasksString = sharedPreferences.getString(SHARED_PREFERENCES_VARIABLE, "");
        List<Task> tasksList = new LinkedList<>();
        try {
            String[] tasksArr = tasksString.split(System.lineSeparator());
            ArrayList<String> arrayToList = new ArrayList<>(Arrays.asList(tasksArr));

            for (String taskStr : arrayToList) {
                String[] taskValues = taskStr.split(COMMA);
                if (taskValues.length == 0) {
                    continue;
                }
                tasksList.add(new Task(taskValues[0], taskValues[1], taskValues[2].equals("1"),
                        taskValues[3]));
            }
        } catch (Exception e) {
            Log.e(TAG, "Shared preferences is empty");
        }
        return tasksList;
    }

    @Override
    public List<Task> getFavoriteTasks() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_STORAGE, Context.MODE_PRIVATE);
        String tasksString = sharedPreferences.getString(SHARED_PREFERENCES_VARIABLE, "");
        List<Task> favoriteTasksList = new LinkedList<>();
        try {
            String[] tasksArr = tasksString.split(System.lineSeparator());
            ArrayList<String> arrayToList = new ArrayList<>(Arrays.asList(tasksArr));

            for (String taskStr : arrayToList) {
                String[] taskValues = taskStr.split(COMMA);
                if (taskValues.length == 0) {
                    continue;
                }
                if (taskValues[2].equals("1")) {
                    favoriteTasksList.add(new Task(taskValues[0], taskValues[1],
                            taskValues[2].equals("1"), taskValues[3]));
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Shared preferences is empty");
        }

        return favoriteTasksList;
    }

    @Override
    public void delete(Task task) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_STORAGE, Context.MODE_PRIVATE);
        String tasksString = sharedPreferences.getString(SHARED_PREFERENCES_VARIABLE, "");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String newCreatedStr = "";
        String[] tasksArr = tasksString.split(System.lineSeparator());

        for (String taskStr : tasksArr) {
            String[] values = taskStr.split(COMMA);
            if (values.length < 3) {
                continue;
            }
            String title = values[0];
            String description = values[1];
            Boolean isFavorite = values[2].equals("1");
            String id = values[3];

            Task currentTask = new Task(title, description, isFavorite, id);

            if (!currentTask.equals(task)) {
                newCreatedStr += currentTask;
            }
        }
        edit.putString(SHARED_PREFERENCES_VARIABLE, newCreatedStr).apply();
    }

    @Override
    public void updateTask(Task newTask, Task oldTask) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_STORAGE, Context.MODE_PRIVATE);
        String tasksString = sharedPreferences.getString(SHARED_PREFERENCES_VARIABLE, "");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String newCreatedStr = "";
        String[] tasksArr = tasksString.split(System.lineSeparator());
        for (String taskStr : tasksArr) {
            String[] values = taskStr.split(COMMA);
            if (values.length < 3) {
                continue;
            }
            String title = values[0];
            String description = values[1];
            Boolean isFavorite = values[2].equals("1");
            String id = values[3];
            Task currentTask = new Task(title, description, isFavorite, id);
            if (currentTask.getId().equals(oldTask.getId())) {
                newCreatedStr += newTask;
            } else {
                newCreatedStr += currentTask;
            }
        }
        edit.putString(SHARED_PREFERENCES_VARIABLE, newCreatedStr).apply();
    }


}
