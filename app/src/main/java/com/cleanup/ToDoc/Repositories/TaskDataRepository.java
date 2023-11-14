package com.cleanup.ToDoc.Repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.ToDoc.database.TaskDao;
import com.cleanup.ToDoc.model.Task;

import java.util.List;

public class TaskDataRepository {
    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<Task> getTask(Long TaskId){return  this.taskDao.getTaskById(TaskId);}

    public void addTask(Task task) {
        this.taskDao.insertTask(task);
    }
    public List<Task> getTasks(){return this.taskDao.getAll();}
    public void deleteTask(Task task) {
        this.taskDao.deleteTask(task);
    }

    public void updateTask(Task task) { this.taskDao.updateTask(task);}
}
