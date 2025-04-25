package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(view -> {
            String name = editTextName.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("USER_NAME", name);
                startActivity(intent);
                finish();
            }
        });
    }
}
