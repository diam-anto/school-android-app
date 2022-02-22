package com.android.finproj.myapplication;


import java.util.ArrayList;

public class Question  {

    private String QueText; // keimeno erwthsh
    private ArrayList<String> Answers; // array list answers
    private int CorrectAns; //poia einai swsth
    private  int UserAns; //poia apanthsh edv o xrhsths

    public Question () {
        Answers = new ArrayList ();
        CorrectAns = -1; // arxikh timh
        UserAns = -1; //akoma h ervthsh den exei apanththei
    }

    public String getQueText () {
        return QueText;
    }

    public void setQueText(String queText) { //orizei keimeno ervthshs
        QueText = queText;
    }

    public int getCorrectAns() { // pairnei ton arithmo ths swsths apanthshs
        return CorrectAns;
    }

    public void setCorrectAns(int correctAns) { // orizei ton arithmo thw swsths apanthshs
        CorrectAns = correctAns;
    }

    public int getUserAns() {
        return UserAns;
    }

    public void setUserAns (int userAns) { // orizei ton arithmo ths apanthshs poy epeleke o xrhsths
        UserAns = userAns;
    }


    public String getAnswer (int AnsNum) { // pairnoyme to keimeno ths apanthshs
        return Answers.get(AnsNum);
    }

    public void AddAnswer (String Ans) { // otan ftiaxnoyme erwthmatologio thn kaloyme
        Answers.add (Ans);
    }

    public boolean isAnswered () {
        if (UserAns == -1)
            return false;
        else
            return true; // exei katagrafei apanthsh
    }

    public boolean isCorrect () {
        return UserAns == CorrectAns;
    }

    public int GetNoAnswers () {  // to plhthos tvn apanthsewn
        return Answers.size();
    }

}
