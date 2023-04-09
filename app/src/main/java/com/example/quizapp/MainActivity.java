package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Variables (objects of widgets)
    private EditText nameEditText; //user input name
    private TextView mainTitle; //main title

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variables (objects of widgets)
        nameEditText = findViewById(R.id.nameEditText); //user input text name
        mainTitle = findViewById(R.id.mainTitle); //main title
        Button startQuizButton = findViewById(R.id.startQuizButton); //button variable with id

        mainTitle.setText("Quiz App"); //hello text

        //button click listener
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = nameEditText.getText().toString().trim(); //store text to string
                Log.v("checking", "Button is clicked!"); //check log

                //check name is valid
            if (TextUtils.isEmpty(name)) {
                nameEditText.setError("Please enter your name");
                return;
            }
                //intent to (object) used to request an action from another component
                Intent intent = new Intent(MainActivity.this, QuizScreenActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}