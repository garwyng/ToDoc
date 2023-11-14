package com.cleanup.ToDoc.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.ToDoc.model.Project;
import com.cleanup.ToDoc.model.Task;

import java.util.List;
@Dao
public interface TaskDao {
    @Query("SELECT * FROM task_table")
    List<Task> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);
    @Query("SELECT task_table.*," +
            Project.TABLE_NAME +"." + Project.ID_COLUMN_NAME +" AS " + Project.PREFIX + Project.ID_COLUMN_NAME + "," +
            Project.TABLE_NAME +"." + Project.NAME_COLUMN_NAME + " AS " + Project.PREFIX + Project.NAME_COLUMN_NAME + ", " +
            Project.TABLE_NAME +"." + Project.COLOR_COLUMN_NAME + " AS " + Project.PREFIX + Project.COLOR_COLUMN_NAME + " " +
            "FROM task_table " +
            "JOIN " + Project.TABLE_NAME + " ON task_table.projectId = " + Project.TABLE_NAME + "." + Project.ID_COLUMN_NAME + ";"
    )
    List<TaskWithProject> getAllTasksWithProjects();
    @Query("SELECT * From task_table WHERE id= :taskId")
    LiveData<Task> getTaskById(Long taskId);
    @Update
    void updateTask(Task task);
}
