package com.example.fitnessapp.foodClasses;

import com.google.firebase.Timestamp;

public class foodRecipe {
    private int idFood;
    private String foodName;
    private String foodType;
    private double calories;
    private double quantity;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private String foodImage;
    private Timestamp date;
    private String docId;

    public foodRecipe() {
    }

    public foodRecipe(int idFood, String foodName, String foodType, double calories,
                      double quantity, double proteins, double fats, double carbohydrates,
                      Timestamp date,String foodImage) {
        this.idFood = idFood;
        this.foodName = foodName;
        this.foodType = foodType;
        this.calories = calories;
        this.quantity = quantity;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.date = date;
        this.foodImage = foodImage;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setDocumentId(String docId){
        this.docId = docId;
    }

    public String getDocId(){
        return docId;
    }
}
