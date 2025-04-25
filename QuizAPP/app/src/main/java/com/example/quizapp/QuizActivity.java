package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView textViewQuestion, textViewUsername;
    RadioGroup radioGroupAnswers;
    Button buttonSubmit;
    ProgressBar progressBar;
    String userName;
    int currentQuestionIndex = 0;
    int score = 0;
    Question[] questions = new Question[]{
            new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2),
            new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
            new Question("Which language is Android primarily written in?", new String[]{"Java", "Python", "C++", "Kotlin"}, 0),
            new Question("What is the largest planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2),
            new Question("Which country is known as the land of the rising sun?", new String[]{"China", "Japan", "India", "Australia"}, 1)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewUsername = findViewById(R.id.textViewUsername);
        radioGroupAnswers = findViewById(R.id.radioGroupAnswers);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        progressBar = findViewById(R.id.progressBar);

        userName = getIntent().getStringExtra("USER_NAME");
        textViewUsername.setText("Hello, " + userName);

        displayQuestion();

        buttonSubmit.setOnClickListener(v -> {
            int selectedAnswer = radioGroupAnswers.getCheckedRadioButtonId();
            if (selectedAnswer == -1) {
                Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadioButton = findViewById(selectedAnswer);
            int answerIndex = radioGroupAnswers.indexOfChild(selectedRadioButton);

            // Change colors for all radio buttons before moving to the next question
            for (int i = 0; i < radioGroupAnswers.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroupAnswers.getChildAt(i);
                if (i == questions[currentQuestionIndex].getCorrectAnswerIndex()) {
                    radioButton.setTextColor(getResources().getColor(android.R.color.holo_green_dark)); // Correct answer
                } else if (radioButton == selectedRadioButton) {
                    radioButton.setTextColor(getResources().getColor(android.R.color.holo_red_dark)); // Incorrect answer
                }
            }

            // Update score if the answer is correct
            if (answerIndex == questions[currentQuestionIndex].getCorrectAnswerIndex()) {
                score++;
            }

            // Update Progress Bar
            currentQuestionIndex++;
            updateProgressBar();

            // Delay the change of question to allow users to see the color change
            new Handler().postDelayed(() -> {
                // Move to the next question or finish the quiz
                if (currentQuestionIndex < questions.length) {
                    displayQuestion();
                } else {
                    showResult();
                }
            }, 1000); // Delay of 1 second
        });
    }

    private void displayQuestion() {
        Question currentQuestion = questions[currentQuestionIndex];
        textViewQuestion.setText(currentQuestion.getQuestionText());
        radioGroupAnswers.removeAllViews();

        for (String answer : currentQuestion.getAnswers()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer);
            radioGroupAnswers.addView(radioButton);
        }
    }

    private void updateProgressBar() {
        int progress = (int) ((currentQuestionIndex / (float) questions.length) * 100);
        progressBar.setProgress(progress);
    }

    private void showResult() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("USER_NAME", userName);
        intent.putExtra("SCORE", score);
        startActivity(intent);
        finish();
    }
}
