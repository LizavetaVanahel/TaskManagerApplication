package com.example.vanahel.tasksmanagerapplication.dao;

import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvkoleda on 05.06.17.
 */

public class MemoryDAO  implements TaskDAO {

    private List<Task> tasksList = new ArrayList<>();


    @Override
    public void save(String title, String description, Boolean isFavorite) {

        tasksList.add(new Task (title, description, isFavorite));
    }

    @Override
    public List<Task> getTasks() {
        return tasksList;
    }

    @Override
    public List<Task> getFavoriteTasks() {
        return null;
    }

    @Override
    public void delete(Task task) throws IOException {

    }

    @Override
    public void updateTask(Task newTask, Task oldTask) throws IOException {

    }
}
