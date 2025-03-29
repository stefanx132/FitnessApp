package com.example.fitnessapp.authentification.userinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnessapp.MainActivity;
import com.example.fitnessapp.R;

import java.util.Arrays;
import java.util.List;

public class ActivityLevelFragment extends Fragment implements QuizData {

    private String selectedActivityLevel = null;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("QuizPreferences",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_level, container, false);

        MainActivity mainActivity = (MainActivity) requireActivity();
        selectedActivityLevel = mainActivity.getSelectedActivityLevel();

        initComponents(view);

        return view;
    }
    private void initComponents(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.activity_level_recycler_view);

        List<ActivityLevel> activityLevels = Arrays.asList(
                new ActivityLevel("Sedentary", "Little or no physical activity"),
                new ActivityLevel("Lightly Active", "Light activity or exercise 1-3 days per week"),
                new ActivityLevel("Moderately Active", "Moderate activity or exercise 3-5 days per week"),
                new ActivityLevel("Very Active", "Heavy activity or exercise 6-7 days per week"),
                new ActivityLevel("Super Active", "Very heavy activity or a physical job")
        );

        ActivityLevelAdapter adapter = new ActivityLevelAdapter(activityLevels, selected -> {
            selectedActivityLevel = selected;

            ((MainActivity) requireActivity()).setSelectedActivityLevel(selectedActivityLevel);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (selectedActivityLevel != null) {
            for (int i = 0; i < activityLevels.size(); i++) {
                if (activityLevels.get(i).getTitle().equals(selectedActivityLevel)) {
                    adapter.setSelectedItem(i);
                    break;
                }
            }
        }
    }
    @Override
    public String getQuizData() {
        if (isValid()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ActivityLevel",selectedActivityLevel);
            editor.apply();
            return "Activity Level: " + selectedActivityLevel;
        }
        return null;
    }
    private boolean isValid() {
        if (selectedActivityLevel == null) {
            Toast.makeText(getContext(), "Select an activity level", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}


