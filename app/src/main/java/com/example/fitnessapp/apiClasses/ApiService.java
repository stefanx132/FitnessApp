package com.example.fitnessapp.apiClasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // Endpoint to search for foods and get basic info
    @GET("recipes/complexSearch")
    Call<FoodSearchResponse> searchFoods(
            @Query("query") String query,
            @Query("number") int number,
            @Query("apiKey") String apiKey
    );

    // Endpoint to get nutrients for a specific food item by ID
    @GET("recipes/{id}/nutritionWidget.json")
    Call<NutrientsResponse> getFoodNutrients(
            @Path("id") int foodId,
            @Query("apiKey") String apiKey
    );
}
