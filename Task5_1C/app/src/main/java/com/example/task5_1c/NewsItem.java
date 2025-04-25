package com.example.task5_1c.models;

public class NewsItem {
    private String title;
    private String description;
    private int imageResId;  // for local image resource

    public NewsItem(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
