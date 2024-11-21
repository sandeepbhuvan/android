// MainActivity.java
package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox answer1Option1, answer1Option2, answer1Option3;
    private CheckBox answer2Option1, answer2Option2, answer2Option3;
    private CheckBox answer3Option1, answer3Option2, answer3Option3;
    private Button submitButton;

    // Correct answers array for three questions
    private boolean[][] correctAnswers = {
            {true, false, false},  // Question 1: Paris
            {true, false, false},  // Question 2: Mars
            {false, false, true}   // Question 3: Pacific Ocean
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize CheckBoxes for each question
        answer1Option1 = findViewById(R.id.answer1_option1);
        answer1Option2 = findViewById(R.id.answer1_option2);
        answer1Option3 = findViewById(R.id.answer1_option3);

        answer2Option1 = findViewById(R.id.answer2_option1);
        answer2Option2 = findViewById(R.id.answer2_option2);
        answer2Option3 = findViewById(R.id.answer2_option3);

        answer3Option1 = findViewById(R.id.answer3_option1);
        answer3Option2 = findViewById(R.id.answer3_option2);
        answer3Option3 = findViewById(R.id.answer3_option3);

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = 0;
                boolean[][] userAnswers = {
                        {answer1Option1.isChecked(), answer1Option2.isChecked(), answer1Option3.isChecked()},
                        {answer2Option1.isChecked(), answer2Option2.isChecked(), answer2Option3.isChecked()},
                        {answer3Option1.isChecked(), answer3Option2.isChecked(), answer3Option3.isChecked()}
                };

                for (int i = 0; i < correctAnswers.length; i++) {
                    boolean isCorrect = true;
                    for (int j = 0; j < correctAnswers[i].length; j++) {
                        if (userAnswers[i][j] != correctAnswers[i][j]) {
                            isCorrect = false;
                            break;
                        }
                    }
                    if (isCorrect) {
                        score++;
                    }
            }
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });
    }
}
