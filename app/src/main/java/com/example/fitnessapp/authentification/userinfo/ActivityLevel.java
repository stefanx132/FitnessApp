package com.example.fitnessapp.authentification.userinfo;

public class ActivityLevel {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ActivityLevel(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
