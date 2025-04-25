package com.example.task5_1c.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.task5_1c.models.VideoEntity;

@Database(entities = {VideoEntity.class}, version = 1)
public abstract class VideoDatabase extends RoomDatabase {

    private static VideoDatabase instance;

    public abstract VideoDao videoDao();

    public static synchronized VideoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            VideoDatabase.class, "video_db")
                    .allowMainThreadQueries() // only for testing/demo
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
