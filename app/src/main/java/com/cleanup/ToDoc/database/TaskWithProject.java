package com.cleanup.ToDoc.database;

import androidx.room.Embedded;

import com.cleanup.ToDoc.model.Project;
import com.cleanup.ToDoc.model.Task;

public class TaskWithProject {
    @Embedded
    Task task;
    @Embedded(prefix = Project.PREFIX)
    Project project;
}
