package com.example.task5_1c;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "videos")
public class VideoEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String videoUrl;

    public VideoEntity(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
