package com.example.fitnessapp.authentification.userinfo;

import static android.provider.Settings.System.DATE_FORMAT;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class NameFragment extends Fragment implements QuizData {

    private TextInputEditText tietFirstName,tietAge;
    private RadioGroup genderRadioGroup;
    int selectedOption;
    String gender;
    SharedPreferences sharedPreferences;

    public NameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("QuizPreferences", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        tietFirstName = view.findViewById(R.id.first_name_input);
        tietAge = view.findViewById(R.id.age_name_input);
        genderRadioGroup = view.findViewById(R.id.gender_radio_group);

        return view;
    }
    @Override
    public String getQuizData() {
        if (isValid()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String firstName = tietFirstName.getText().toString().trim();
            int age = Integer.parseInt(tietAge.getText().toString().trim());
            int selectedOption = genderRadioGroup.getCheckedRadioButtonId();
            String gender = (selectedOption != -1) ?
                    ((RadioButton) genderRadioGroup.findViewById(selectedOption)).getText().toString() :
                    "Not selected";

            editor.putInt("Age",age);
            editor.putString("Gender",gender);
            editor.putString("FirstName",firstName);
            editor.apply();
            return "Age: " + age + "First Name: " + firstName +  " Gender: " + gender;
        }
        return null;
    }

    private boolean isValid() {
        boolean isValueValid = true;
        if (tietFirstName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Provide first name", Toast.LENGTH_SHORT).show();
            isValueValid = false;
        }
        if (tietAge.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Provide age", Toast.LENGTH_SHORT).show();
            isValueValid = false;
        }
        if (genderRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Select a gender", Toast.LENGTH_SHORT).show();
            isValueValid = false;
        }
        return isValueValid;
    }
}
