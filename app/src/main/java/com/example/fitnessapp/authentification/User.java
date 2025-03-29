package com.example.fitnessapp.authentification;

public class User {
    private String userMail;
    private String firstName;
    private String lastName;
    private int age;
    private double currentWeight;
    private double goalWeight;
    private double height;
    private String gender;
    private String activityLevel;
    private String goal;
    private double progressGoal;
    private double CaloriesGoal;
    private double ProteinsGoal;
    private double CarbsGoal;
    private double FatsGoal;

    public User() {
    }
    public User(String userMail, String firstName, String lastName, int age,
                double currentWeight, double goalWeight, double height, String gender,
                String activityLevel, String goal, double progressGoal, double caloriesGoal,
                double proteinsGoal, double carbsGoal, double fatsGoal) {
        this.userMail = userMail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.currentWeight = currentWeight;
        this.goalWeight = goalWeight;
        this.height = height;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.goal = goal;
        this.progressGoal = progressGoal;
        CaloriesGoal = caloriesGoal;
        ProteinsGoal = proteinsGoal;
        CarbsGoal = carbsGoal;
        FatsGoal = fatsGoal;
    }
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getProgressGoal() {
        return progressGoal;
    }

    public void setProgressGoal(double progressGoal) {
        this.progressGoal = progressGoal;
    }

    public double getCaloriesGoal() {
        return CaloriesGoal;
    }

    public void setCaloriesGoal(double caloriesGoal) {
        CaloriesGoal = caloriesGoal;
    }

    public double getProteinsGoal() {
        return ProteinsGoal;
    }

    public void setProteinsGoal(double proteinsGoal) {
        ProteinsGoal = proteinsGoal;
    }

    public double getCarbsGoal() {
        return CarbsGoal;
    }

    public void setCarbsGoal(double carbsGoal) {
        CarbsGoal = carbsGoal;
    }

    public double getFatsGoal() {
        return FatsGoal;
    }

    public void setFatsGoal(double fatsGoal) {
        FatsGoal = fatsGoal;
    }
}
