// ResultActivity.java
package com.example.quiz;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreText = findViewById(R.id.scoreText);
        int score = getIntent().getIntExtra("score",0);
        scoreText.setText("Your Score: " + score + " out of 3");
    }
}