package com.android.finproj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class StartTest3 extends AppCompatActivity implements View.OnClickListener  {



    Button BtStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_test);

        BtStartQuiz = findViewById(R.id.BtStartQuiz);
        BtStartQuiz.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(StartTest3.this, QuizActivity3.class);
        startActivity(intent);
    }

}
