package com.example.fitnessapp.authentification.userinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class MeasurementsFragment extends Fragment implements QuizData {

    private TextInputEditText tietHeight;
    private TextInputEditText tietCurrentWeight;
    private TextInputEditText tietTargetWeight;
    private TextView targetWeightTextView;
    SharedPreferences sharedPreferences;

    public MeasurementsFragment() {
        // Required empty public constructor
    }
    public static MeasurementsFragment newInstance(String param1, String param2) {
        MeasurementsFragment fragment = new MeasurementsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        sharedPreferences = getContext().getSharedPreferences("QuizPreferences",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_measurements, container, false);

        String selectedGoal = sharedPreferences.getString("SelectedGoal","");

        tietHeight = view.findViewById(R.id.height_edit_text);
        tietCurrentWeight = view.findViewById(R.id.current_weight_edit_text);
        tietTargetWeight = view.findViewById(R.id.target_weight_edit_text);
        targetWeightTextView = view.findViewById(R.id.target_weight_text_view);


        if("Maintain Weight".equals(selectedGoal)){
            tietTargetWeight.setVisibility(View.GONE);
            targetWeightTextView.setVisibility(View.GONE);
        }else{
            tietTargetWeight.setVisibility(View.VISIBLE);
            targetWeightTextView.setVisibility(View.VISIBLE);
        }

        return view;
    }
    @Override
    public String getQuizData() {
        if (isValid()) {
            String selectedGoal = sharedPreferences.getString("SelectedGoal","");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String height = tietHeight.getText().toString().trim();
            String currentWeight = tietCurrentWeight.getText().toString().trim();
            String targetWeight = tietTargetWeight.getText().toString().trim();


            if(tietTargetWeight.getVisibility() == View.GONE){
                targetWeight = currentWeight;
            }

            editor.putString("Height",height);
            editor.putString("CurrentWeight",currentWeight);
            editor.putString("TargetWeight",targetWeight);
            editor.apply();

            return "Height: " + height + " cm, Current Weight: " + currentWeight + " kg, Target Weight: " + targetWeight + " kg";
        }
        return null;
    }
    private boolean isValid() {
        if (tietHeight.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Provide height", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tietCurrentWeight.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Provide current weight", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tietTargetWeight.getVisibility() == View.VISIBLE && tietTargetWeight.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Provide target weight", Toast.LENGTH_SHORT).show();
            return false;
        }

        String selectedGoal = sharedPreferences.getString("SelectedGoal", "");
        if("Maintain Weight".equals(selectedGoal)){
            return true;
        }
        else{
            double currentWeight = Double.parseDouble(tietCurrentWeight.getText().toString().trim());
            double targetWeight = Double.parseDouble(tietTargetWeight.getText().toString().trim());

            if ("Lose Weight".equals(selectedGoal) && targetWeight >= currentWeight) {
                Toast.makeText(getContext(), "Target weight must be less than current weight for weight loss", Toast.LENGTH_SHORT).show();
                return false;
            }

            if ("Gain Weight".equals(selectedGoal) && targetWeight <= currentWeight) {
                Toast.makeText(getContext(), "Target weight must be greater than current weight for weight gain", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}