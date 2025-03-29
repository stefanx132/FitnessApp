package com.example.fitnessapp.apiClasses;

import com.google.gson.annotations.SerializedName;

public class NutrientsResponse {
    private String calories;
    private String carbs;
    private String fat;
    private String protein;

    @SerializedName("weightPerServing")
    private WeightPerServing weightPerServing; // Nested structure for serving size

    // Nested class to handle weightPerServing
    public static class WeightPerServing {
        private double amount;
        private String unit;

        public double getAmount() {
            return amount;
        }

        public String getUnit() {
            return unit;
        }
    }

    // Method to parse and return a Nutrients object
    public Nutrients toNutrients() {
        double parsedCalories = parseAmount(calories);
        double parsedCarbs = parseAmount(carbs);
        double parsedFat = parseAmount(fat);
        double parsedProtein = parseAmount(protein);
        double parsedServingSize = weightPerServing != null ? weightPerServing.getAmount() : 0.0;

        return new Nutrients(parsedCalories, parsedCarbs, parsedFat, parsedProtein, parsedServingSize);
    }

    // Helper method to parse amount, stripping units if present
    private double parseAmount(String value) {
        if (value != null) {
            try {
                // Remove any non-numeric characters, leaving only the number
                String amountStr = value.replaceAll("[^\\d.]", "");
                return Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0.0; // Default to 0.0 if parsing fails
    }
}
