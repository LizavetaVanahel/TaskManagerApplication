package com.example.vanahel.tasksmanagerapplication.dao;

public class DAOManager {

    private static final DAOManager DAOManagerInstance = new DAOManager();
    private TaskDAO taskDAO;

    private DAOManager() {
    }

    public static DAOManager getInstance() {
        return DAOManager.DAOManagerInstance;
    }

    public TaskDAO getTaskDAO() {
        if (null == this.taskDAO) {
            throw new NullPointerException("DAOManager: taskDAO object wasn't initialized");
        }
        return this.taskDAO;
    }

    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }
}
