package com.android.finproj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Results2 extends AppCompatActivity implements View.OnClickListener {


    private static Results2 Instance = null;
    private static QuizActivity2 Instance2 = null;
    TextView TvResults;
    TextView TvQueNumber;
    TextView TvQuestion;
    TextView TvUserAns;
    TextView TvCorrAns;
    Button BtNextQuest;

    Questionnaire2 AllQuests;
    Question CurQ;
    int CurQNum;
    int SelAns;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_lay);
        TvResults = findViewById(R.id.TvResults);
        TvQueNumber = findViewById(R.id.TvQueNumber);
        TvQuestion = findViewById(R.id.TvQuestion);
        TvCorrAns = findViewById(R.id.TvCorrAns);
        TvUserAns = findViewById(R.id.TvUserAns);
        BtNextQuest = findViewById(R.id.BtNextQuest);
        BtNextQuest.setOnClickListener(this);

        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score= intent.getIntExtra("Αποτέλεσμα", 0);

        // display current score
        TvResults.setText("Το αποτέλεσμα σου: " + score);
        AllQuests = Questionnaire2.GetInstance(this);
        AllQuests.PrintAll();
        DoNextAns();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Results2.this, Section3.class);
        startActivity(intent);
    }


    void DoNextAns () {

        CurQNum = AllQuests.GoNextUnAns();

        if (CurQNum == -1) {
            Toast Tst = Toast.makeText(getApplicationContext(), "ΤΕΛΟΣ ΑΠΑΝΤΗΣΕΩΝ...", Toast.LENGTH_LONG);
            Tst.show();
            //Intent intent = new Intent(Results2.this, StartTest3.class);
        } else {
            CurQ = AllQuests.GetQuestion();
            TvQueNumber.setText("ΕΡΩΤΗΣΗ  " + (CurQNum + 1));
            TvQuestion.setText(CurQ.getQueText());
            TvUserAns.setText(CurQ.getUserAns());
            TvCorrAns.setText(CurQ.getCorrectAns());

        }

        SelAns = -1;
    }

}
