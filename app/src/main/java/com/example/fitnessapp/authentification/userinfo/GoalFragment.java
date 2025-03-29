package com.example.fitnessapp.authentification.userinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnessapp.R;

public class GoalFragment extends Fragment implements QuizData {

    private CardView loseWeightCard, maintainWeightCard, gainWeightCard;
    private String selectedGoal = "";
    SharedPreferences sharedPreferences;

    public GoalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("QuizPreferences", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal, container, false);

        initComponentsAndSelectCard(view);

        selectedGoal = sharedPreferences.getString("SelectedGoal", "");

        if (!selectedGoal.isEmpty()) {
            if (selectedGoal.equals("Lose Weight")) {
                loseWeightCard.setCardElevation(100);
                //loseWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lightOrange)));
            } else if (selectedGoal.equals("Maintain Weight")) {
                maintainWeightCard.setCardElevation(100);
                //maintainWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lightOrange)));
            } else if (selectedGoal.equals("Gain Weight")) {
                gainWeightCard.setCardElevation(100);
                //gainWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lightOrange)));
            }
        }

        return view;
    }
    private void initComponentsAndSelectCard(View view) {
        loseWeightCard = view.findViewById(R.id.card_lose_weight);
        maintainWeightCard = view.findViewById(R.id.card_maintain_weight);
        gainWeightCard = view.findViewById(R.id.card_gain_weight);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("QuizPreferences",
                getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        loseWeightCard.setOnClickListener(v -> {
            selectedGoal = "Lose Weight";
            resetColors();
            loseWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lightOrange)));
            editor.putString("SelectedGoal","Lose Weight");
            editor.apply();
        });

        maintainWeightCard.setOnClickListener(v -> {
            selectedGoal = "Maintain Weight";
            resetColors();
            maintainWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lightOrange)));

            editor.putString("SelectedGoal","Maintain Weight");
            editor.putString("Progress","0");
            editor.apply();
        });

        gainWeightCard.setOnClickListener(v -> {
            selectedGoal = "Gain Weight";
            resetColors();
            gainWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lightOrange)));
            editor.putString("SelectedGoal","Gain Weight");
            editor.apply();
        });
    }
    @Override
    public String getQuizData() {
        if (isValid()) {
            return "Goal: " + selectedGoal;
        }
        return null;
    }
    private boolean isValid() {
        if (selectedGoal.isEmpty()) {
            Toast.makeText(getContext(), "Select a goal", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void resetColors(){
        gainWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
        loseWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
        maintainWeightCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
    }
    public String getSelectedGoal() {
        return selectedGoal;
    }
    public void resetGoal(){
        selectedGoal = "";
    }
}