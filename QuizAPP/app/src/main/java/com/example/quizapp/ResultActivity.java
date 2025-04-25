package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView textViewResult, textViewUsername;
    Button buttonTakeNewQuiz, buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.textViewResult);
        textViewUsername = findViewById(R.id.textViewUsername);
        buttonTakeNewQuiz = findViewById(R.id.buttonTakeNewQuiz);
        buttonFinish = findViewById(R.id.buttonFinish);

        String userName = getIntent().getStringExtra("USER_NAME");
        int score = getIntent().getIntExtra("SCORE", 0);

        textViewUsername.setText("Hello, " + userName);
        textViewResult.setText("Your Score: " + score);

        buttonTakeNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        buttonFinish.setOnClickListener(v -> finish());
    }
}
