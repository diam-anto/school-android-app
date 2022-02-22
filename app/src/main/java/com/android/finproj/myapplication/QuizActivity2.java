package com.android.finproj.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class QuizActivity2 extends AppCompatActivity implements View.OnClickListener {

    TextView TvQueNumber; // number of question
    TextView TvQuestion; // current question
    TextView TvCount;
    RadioGroup RbGroup;
    RadioButton[] RbAnswers; // possible answers
    Button BtPrev;
    Button BtOK;
    Button BtNext;
    Questionnaire2 AllQuests;
    Question CurQ;
    Question CurQCorr;
    int CurQNum;
    int SelAns;
    ColorStateList BackDraw;
    private static QuizActivity2 Instance = null;
    private static Context Cont;
    private int myscore = 0;
    int correctAns;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_lay);
        TvQueNumber = findViewById(R.id.TvQueNumber);
        TvQuestion = findViewById(R.id.TvQuestion);
        TvCount = findViewById(R.id.TvCount);
        RbGroup = findViewById(R.id.RbGroup);
        RbAnswers = new RadioButton[4];
        RbAnswers[0] = findViewById(R.id.RbAnswer1);
        RbAnswers[1] = findViewById(R.id.RbAnswer2);
        RbAnswers[2] = findViewById(R.id.RbAnswer3);
        RbAnswers[3] = findViewById(R.id.RbAnswer4);
        for (int i = 0; i < 4; i++)
            RbAnswers[i].setOnClickListener(this);
        BtPrev = findViewById(R.id.BtPrevious);
        BtOK = findViewById(R.id.BtOK);
        BtNext = findViewById(R.id.BtNext);
        BtPrev.setOnClickListener(this);
        BtOK.setOnClickListener(this);
        BtNext.setOnClickListener(this);
        BackDraw = RbAnswers[0].getTextColors();
        AllQuests = Questionnaire2.GetInstance(this);
        AllQuests.PrintAll();

        DoNext ();
    }


    @Override
    public void onClick(View v) {
        if (v == BtPrev) {
            AllQuests.GoPrevUnAns();
            AllQuests.setCurQuest(AllQuests.getCurQuest() - 1);
            DoNext();
        }
        if (v == BtOK) {
            if (SelAns == -1)
                return;
            CurQ.setUserAns(SelAns);
            System.out.println ("**** " + SelAns + " " + CurQ.getCorrectAns ());
            if (CurQ.getUserAns() == CurQ.getCorrectAns() - 1) {
                Toast Tst = Toast.makeText(getApplicationContext(), "Right Answer...", Toast.LENGTH_SHORT);
                Tst.show();
                myscore = myscore + 1;
            }
            else
            {
                Toast Tst = Toast.makeText(getApplicationContext(), "Wrong Answer...", Toast.LENGTH_SHORT);
                Tst.show();
            }
            DoNext();
        }
        if (v == BtNext) {
            DoNext();
        }
        for (int i = 0; i < 4; i++) {
            if (v == RbAnswers[i]) {
                SelAns = i;
            }
        }
        DoChangeAnswer (); //allazei to xrwma thw energhs apanthshs
    }

    void DoNext () {

        RbGroup.clearCheck();
        CurQNum = AllQuests.GoNextUnAns();

        if (CurQNum == -1) {
            Toast Tst = Toast.makeText(getApplicationContext(), "ΤΕΛΟΣ ΕΡΩΤΗΜΑΤΟΛΟΓΙΟΥ...", Toast.LENGTH_LONG);
            Tst.show();
            AllQuests.PrintAnswers();
            Intent intent = new Intent(QuizActivity2.this, Results2.class);

            // pass the current score to the next screen
            intent.putExtra("Αποτέλεσμα", myscore);
            startActivity(intent);
        } else {
            CurQ = AllQuests.GetQuestion();
            RbAnswers[0].setVisibility(View.VISIBLE);
            RbAnswers[1].setVisibility(View.VISIBLE);
            RbAnswers[2].setVisibility(View.VISIBLE);
            RbAnswers[3].setVisibility(View.VISIBLE);

            for (int i = 0; i < 4; i++)
                RbAnswers[i].setEnabled(true);
            TvQueNumber.setText("ΕΡΩΤΗΣΗ  " + (CurQNum + 1));
            TvQuestion.setText(CurQ.getQueText());
            //CurQ.setCorrectAns(correctAns);   Δεν ξέρω τι είναι αυτό...
            for (int i = 0; i < CurQ.GetNoAnswers(); i++)
                RbAnswers[i].setText(CurQ.getAnswer(i));

            for (int i = CurQ.GetNoAnswers(); i < 4; i++) {
                RbAnswers[i].setEnabled(false);
                RbAnswers[i].setText("");
                RbAnswers[i].setVisibility(View.INVISIBLE);
            }

        }


        SelAns = -1;
    }

    void DoChangeAnswer () {
        int i;
        for (i = 0; i < 4; i++) {

            RbAnswers[i].setTextColor (BackDraw);
            if (i == SelAns)
                RbAnswers[i].setTextColor(Color.rgb(0 , 150, 136));
        }
    }
}



