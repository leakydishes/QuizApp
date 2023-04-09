package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreenActivity extends AppCompatActivity {

    //Variables (objects of widgets)
    private TextView finalScore, finalMessage;
    String userName, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        //declare objects to layout
        finalScore = findViewById(R.id.finalScore); //final score
        finalMessage = findViewById(R.id.finalMessage); //final score
        Button buttonTakeNewQuiz = findViewById(R.id.buttonTakeNewQuiz); //to take a new quiz
        Button buttonFinished = findViewById(R.id.buttonFinished); //to finish app

        //get data from previous activity
        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
        score = intent.getStringExtra("score");

        //print user & score
        finalMessage.setText("Congratulations " + userName + "!"); //display message
        finalScore.setText("Your score is: " + score + "/5"); //display score

        //Listen for new quiz
        buttonTakeNewQuiz.setOnClickListener(v -> {
            Intent newIntent = new Intent(ScoreScreenActivity.this, QuizScreenActivity.class);
            newIntent.putExtra("name", userName);
            startActivity(newIntent); //start activity
            finish();
        });

        buttonFinished.setOnClickListener(v -> finishAffinity()); //close application
    }
}