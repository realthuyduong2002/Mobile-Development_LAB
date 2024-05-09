package com.example.health_measure_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText weightEditText, heightEditText;
    private Button calculateButton;
    private TextView bmiTextView, exerciseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        bmiTextView = findViewById(R.id.bmiTextView);
        exerciseTextView = findViewById(R.id.exerciseTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString()) / 100; // Convert height to meters
        double bmi = weight / (height * height);

        String bmiResult = String.format("BMI: %.2f", bmi);
        bmiTextView.setText(bmiResult);

        // Suggesting exercises based on BMI
        String exerciseSuggestion;
        if (bmi < 18.5) {
            exerciseSuggestion = "You need to gain weight and exercise regularly.";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            exerciseSuggestion = "You can maintain your current weight and exercise to maintain your health.";
        } else {
            exerciseSuggestion = "You may need to lose weight and exercise to improve your health.";
        }
        exerciseTextView.setText(exerciseSuggestion);
    }
}
