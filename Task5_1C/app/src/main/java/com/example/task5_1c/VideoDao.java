package com.example.task5_1c.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.task5_1c.models.VideoEntity;

import java.util.List;

@Dao
public interface VideoDao {

    @Insert
    void insert(VideoEntity video);

    @Query("SELECT * FROM videos ORDER BY id DESC")
    List<VideoEntity> getAllVideos();
}
