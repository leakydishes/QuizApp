package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // button variable
        Button anotherActivityButton;

        // TODO link button variable with id
        anotherActivityButton = findViewById(R.id.button);

        //TODO set on button click listener

        anotherActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("checking", "Button is clicked!");
            }
        });

    }
}