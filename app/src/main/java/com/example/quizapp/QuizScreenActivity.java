package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class QuizScreenActivity extends AppCompatActivity {

    //Variables (objects of widgets)
    private ProgressBar ProgressBar; //progress bar
    private int CurrentProgress = 1; //counter for progress bar
    private String userName;
    private final String submit = "submit";
    private final String next = "next";
    private TextView questionTextView;
    private TextView status_progress_bar;
    private final Button[] buttonAnswers = new Button[4]; //array of button answers
    private int questionIndex = 0;
    private int score = 0;
    private int selectedButton = 0;

    //data
    List<String> questions = Arrays.asList(
            "What is Java?",
            "Which statement is used to exit from a loop?",
            "What is a class?",
            "Which keyword is used to create a new object?",
            "What is the name of the method that is automatically called when an object is created?");

    List<String> options = Arrays.asList(
            "Object-oriented programming", "Markup Language", "Scripting language", "Procedural Programming",
            "continue", "break", "exit", "return",
            "Object type", "Method type", "Loop type", "Conditional type",
            "new", "this", "that", "class",
            "start()", "main()", "run()", "constructor()");

    List<String> correctAnsList = Arrays.asList(
            "Object-oriented programming",
            "break",
            "Object type",
            "new",
            "constructor()");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        //declare objects to layout
        userName = getIntent().getStringExtra("name"); //get name from MainActivity input
        questionTextView = findViewById(R.id.questionTextView); //question title
        ProgressBar = (ProgressBar) findViewById(R.id.ProgressBar); //progress bar
        status_progress_bar = findViewById(R.id.status_progress_bar); //progress bar status
        Button buttonSubmit = findViewById(R.id.buttonSubmit); //submit button
        TextView helloMessage = findViewById(R.id.helloMessage); //hello to user
        helloMessage.setText("Hello " + userName + "!"); //hello text

        //declare array objects to layout
        buttonAnswers[0] = findViewById(R.id.buttonAnswer1);
        buttonAnswers[1] = findViewById(R.id.buttonAnswer2);
        buttonAnswers[2] = findViewById(R.id.buttonAnswer3);
        buttonAnswers[3] = findViewById(R.id.buttonAnswer4);
        showQuestion(); //show question

        //store button selection data
        buttonAnswers[0].setOnClickListener(v -> {
            selectedButton = 0;
        });
        //store button selection data
        buttonAnswers[1].setOnClickListener(v -> {
            selectedButton = 1;
        });
        //store button selection data
        buttonAnswers[2].setOnClickListener(v -> {
            selectedButton = 2;
        });
        //store button selection data
        buttonAnswers[3].setOnClickListener(v -> {
            selectedButton = 3;
        });

        //button submit for questions & progress bar
        buttonSubmit.setOnClickListener(v -> {
            Log.v("checking", "Button is clicked!"); //check log
            //check if we are showing answers or selecting options
            if (buttonSubmit.getText().toString().equals("next") && questionIndex < questions.size()-1) {
                updateScreen();
                questionIndex++;
                showQuestion();
                buttonSubmit.setText(submit);
            } else if (buttonSubmit.getText().toString().equals("submit") && questionIndex < questions.size()) {
                checkAnswer();
                buttonSubmit.setText(next);
            } else if (questionIndex == questions.size()-1 && buttonSubmit.getText().toString().equals("next")){
                Intent intent = new Intent(QuizScreenActivity.this, ScoreScreenActivity.class); //new activity
                intent.putExtra("name", userName);
                intent.putExtra("score", Integer.toString(score));
                startActivity(intent);
                finish();
            }
        });
    }

    private void showQuestion() {
        //show question to TextView
        questionTextView.setText(questions.get(questionIndex));
        //set question options to buttonAnswers
        int indexCounter = 4 * questionIndex;
        for (int i = 0; i < buttonAnswers.length; i++) {
            //if questionIndex is not 0, set buttonIndex *4, counter to increase option index
            if (questionIndex > 0){
                buttonAnswers[i].setText(options.get(indexCounter));
                indexCounter++;
            } else {
                buttonAnswers[i].setText(options.get(i));
            }
        }
    }

    private void checkAnswer() {
        //find selected button
        if (selectedButton == 0) {
            checkSelected( 0, buttonAnswers[0]);
        } else if (selectedButton == 1) {
            checkSelected(1, buttonAnswers[1]);
        } else if (selectedButton == 2) {
            checkSelected(2, buttonAnswers[2]);
        } else if (selectedButton == 3) {
            checkSelected(3, buttonAnswers[3]);
        }
    }

    private void checkSelected(int i, Button button) {
        String answerToString = correctAnsList.get(questionIndex);
        String buttonToString = buttonAnswers[i].getText().toString();
        if (buttonToString.equals(answerToString)){
            button.setBackgroundColor(Color.parseColor("#FF808000")); //green
            score++;
        } else {
            button.setBackgroundColor(Color.parseColor("#e51c23")); //red
            findAnswer(button, answerToString); //show correct answer
        }
    }

    private void findAnswer(Button button, String answerToString) {
        for (Button buttonAnswer : buttonAnswers) {
            String findButtonString = buttonAnswer.getText().toString();
            if (findButtonString.equals(answerToString)) {
                buttonAnswer.setBackgroundColor(Color.parseColor("#FF808000")); //green
            }
        }
    }

    private void updateScreen() {
        //reset colour
        for (Button buttonAnswer : buttonAnswers) {
            buttonAnswer.setBackgroundColor(Color.parseColor("#FF6200EE")); //purple
        }
        //update progress bar
        CurrentProgress++; //increase progress by 1 for every click
        ProgressBar.setProgress(CurrentProgress); //set Progress
        ProgressBar.setMax(questions.size());
        //number for progress bar
        int questionNum = 5;
        status_progress_bar.setText(CurrentProgress + "/" + questionNum);
    }
}


