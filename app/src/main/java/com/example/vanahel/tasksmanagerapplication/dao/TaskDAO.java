package com.example.vanahel.tasksmanagerapplication.dao;

import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;
import java.util.List;

public interface TaskDAO {

     void save(String title, String description, Boolean isFavorite);

     List<Task> getTasks ();

     List<Task> getFavoriteTasks ();

     void delete (Task task) throws IOException;

     void updateTask (Task newTask,Task oldTask) throws IOException;

}
