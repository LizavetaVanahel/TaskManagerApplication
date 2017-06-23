package com.example.vanahel.tasksmanagerapplication.dao;

import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryDAO  implements TaskDAO {

    private List<Task> tasksList = new ArrayList<>();

    @Override
    public void save(Task task) {
        tasksList.add(new Task (task.getTitle(), task.getDescription(), task.getFavorite(), task.getId()));
    }

    @Override
    public List<Task> getTasks() {
        return tasksList;
    }

    @Override
    public List<Task> getFavoriteTasks() {
        List <Task> favoriteTasksList = new ArrayList<>();
        for ( Task currentTask : tasksList ) {
            if ( currentTask.getFavorite() ){
                favoriteTasksList.add( currentTask );
            }
        }
        return favoriteTasksList;
    }

    @Override
    public void delete(Task task) throws IOException {
        for (Iterator<Task> iterator = tasksList.iterator(); iterator.hasNext(); ) {
            Task currentTask = iterator.next();
            if (currentTask.getId().equals(task.getId())) {
                iterator.remove();
            }
        }
    }

    @Override
    public void updateTask(Task newTask, Task oldTask) throws IOException {
        for (Iterator<Task> iterator = tasksList.iterator(); iterator.hasNext(); ) {
            Task currentTask = iterator.next();
            if (currentTask.getId().equals(oldTask.getId())) {
                iterator.remove();
                tasksList.add(newTask);
            }
        }
    }
}
