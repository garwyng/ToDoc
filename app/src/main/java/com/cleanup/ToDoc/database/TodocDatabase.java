package com.cleanup.ToDoc.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.ToDoc.R;
import com.cleanup.ToDoc.model.Project;
import com.cleanup.ToDoc.model.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = { Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {
    private static volatile TodocDatabase INSTANCE;
    private static final String DATABASE_NAME = "TODOC_database";
    private static Context contextAPP;

    public abstract ProjectDao daoProject();

    public abstract TaskDao daoTask();

    public static synchronized TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            contextAPP= context.getApplicationContext();
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, DATABASE_NAME)
                    .addCallback(prepopulateDatabase())
                    .build();
            Log.d("instance", "getInstance:1 " + INSTANCE);
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback prepopulateDatabase() {

        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db){
                super.onCreate(db);
                Log.d("instance", "getInstance:2 " + INSTANCE);
                Gson gson = new Gson();
                InputStream inputStream = contextAPP.getResources().openRawResource(R.raw.projects);
                InputStreamReader reader = new InputStreamReader(inputStream);

                try {
                    Log.d("instance", "getInstance:3 " + INSTANCE);
                    List<Project> projects = Arrays.asList(gson.fromJson(reader, Project[].class));
                        Executors.newSingleThreadExecutor().execute(() -> INSTANCE.daoProject().insertAll(projects));
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };

    }
}
