package com.example.fitnessapp.apiClasses;

public class Nutrients {
    private double calories;
    private double carbs;
    private double fats;
    private double proteins;
    private double servingSize;

    public Nutrients(double calories, double carbs, double fats, double proteins, double servingSize) {
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.servingSize = servingSize;
    }

    public Nutrients(double calories, double carbs, double fats, double proteins) {
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getServingSize() {
        return servingSize;
    }

    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }
}
