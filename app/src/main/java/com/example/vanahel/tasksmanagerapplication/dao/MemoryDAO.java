package com.example.vanahel.tasksmanagerapplication.dao;

import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        List <Task> favoriteTasksList = new ArrayList<>();
        for ( Task currentTask :tasksList ) {
            if (currentTask.getFavorite()){
                favoriteTasksList.add(currentTask);
            }
        }
        return favoriteTasksList;
    }

    @Override
    public void delete(Task task) throws IOException {
        for (Iterator<Task> iterator = tasksList.iterator(); iterator.hasNext(); ) {
            Task currentTask = iterator.next();
            if (currentTask.equals(task)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void updateTask(Task newTask, Task oldTask) throws IOException {
        for (Iterator<Task> iterator = tasksList.iterator(); iterator.hasNext(); ) {
            Task currentTask = iterator.next();
            if (currentTask.equals(oldTask)) {
                iterator.remove();
                tasksList.add(newTask);
            }
        }
    }
}
