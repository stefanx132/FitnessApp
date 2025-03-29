package com.example.fitnessapp.apiClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.R;
import com.example.fitnessapp.foodClasses.foodRecipe;

import java.util.List;
import java.util.Objects;

public class AlimentsAdapter extends RecyclerView.Adapter<AlimentsAdapter.ViewHolder> {
    private List<?> foodFetched;
    private List<SearchFoodRecipe> foods;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Object item);
    }
    public AlimentsAdapter(List<?> foodFetched,OnItemClickListener listener){
        this.foodFetched = foodFetched;
        this.listener = listener;
    }
    public void clearList(){
        if(foods != null){
            foods.clear();
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object item = foodFetched.get(position);

        if (item instanceof SearchFoodRecipe) {
            SearchFoodRecipe food = (SearchFoodRecipe) item;
            holder.foodName.setText(food.getTitle());
            Glide.with(holder.itemView.getContext())
                    .load(food.getImage())
                    .into(holder.foodImage);
            holder.itemView.setOnClickListener(v -> listener.onItemClick(food));
        } else if (item instanceof foodRecipe) {
            foodRecipe food = (foodRecipe) item;
            holder.foodName.setText(food.getFoodName());
            Glide.with(holder.itemView.getContext())
                    .load(food.getFoodImage())  // Încarcă imaginea din URL-ul alimentului
                    .into(holder.foodImage);
            holder.itemView.setOnClickListener(v -> listener.onItemClick(food));
        }
    }
    @Override
    public int getItemCount() {
        return foodFetched != null ? foodFetched.size() : 0;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        ImageView foodImage;
        ViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodImage = itemView.findViewById(R.id.food_image);
        }
    }
}
