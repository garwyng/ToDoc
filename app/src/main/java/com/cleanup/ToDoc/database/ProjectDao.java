package com.cleanup.ToDoc.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.ToDoc.model.Project;

import java.util.List;
@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Project project);
    @Query("SELECT * FROM projects_table")
    List<Project> getAllProjects();
    @Query("SELECT * FROM " + Project.TABLE_NAME + " WHERE " + Project.ID_COLUMN_NAME + "=:projectId")
    Project getProjectById(long projectId);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Project> projects);
}
