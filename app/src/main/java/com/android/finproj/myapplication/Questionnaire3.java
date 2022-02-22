package com.android.finproj.myapplication;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Questionnaire3 {
    private ArrayList<Question> Questions; // antikeimena erwthsevn oxi mono ta keimena oloklhrh h klash
    private int CurQuest; // trexousa erwthsh ayth poy vlepei o xrhsths
    private int TestDuration; // posa xrono diarkei to test
    private static Questionnaire3 Instance = null;
    private static Context Cont;

    public static Questionnaire3 GetInstance (Context Co) // leitoyrgia enos singleton
    {
        Cont = Co;
        if (Instance == null)
            Instance = new Questionnaire3();
        return Instance;
    }

    public int getCurQuest ()
    {
        return CurQuest;
    }

    public void setCurQuest (int curQuest)
    {
        CurQuest = curQuest;
    }


    private Questionnaire3()
    {
        Questions = new ArrayList (); // katagrafontai ta keimena
        CurQuest = -1 ;
        TestDuration = 0;
        LoadDatabase ();
    }

    private void LoadDatabase ()
    {
        int NoQ;
        AssetManager AssMan = Cont.getAssets();
        System.out.println ("*** !!!!!");
        try (BufferedReader BR = new BufferedReader (new InputStreamReader (AssMan.open ("chapter3.txt"))))
        {
            System.out.println ("*** -----");
            NoQ = Integer.parseInt (BR.readLine ()); // diavazei th prvth grammh ton metatrepei s integer
            for (int i = 0; i < NoQ; i++)
            {
                System.out.println ("*** ///");
                Question Q = new Question ();
                Q.setQueText (BR.readLine ()); //
                int NoA = Integer.parseInt (BR.readLine ()); // arithmos apanthwsewn
                Q.setCorrectAns (Integer.parseInt (BR.readLine ())); //
                for (int j = 0; j < NoA; j++)
                    Q.AddAnswer (BR.readLine ()); // ftanontas edw exoyme etoimh erwthsh
                Questions.add (Q);
            }
        }
        catch (IOException e1)
        {
            System.err.println ("***Oh My God.... Exception in Reading Database");
        }
    }

    public int GetNoQuestions ()
    {
        return Questions.size ();
    }

    public int GetNoAnsweredQuestions ()
    {
        int Count;
        int i;
        for (Count = 0, i = 0; i < GetNoQuestions (); i++)
            if (GetQuestion (i).getUserAns () != -1)
                Count ++;
        return Count;
    }

    public int GetNoUnAnsweredQuestions ()
    {
        int Count;
        int i;
        for (Count = 0, i = 0; i < GetNoQuestions (); i++)
            if (GetQuestion (i).getUserAns () == -1)
                Count ++;
        return Count;
    }

    public Question GetQuestion (int QN) // moy epeistrefei to antikeimeno erwthshs
    {
        return Questions.get (QN);
    }

    public Question GetQuestion () //moy epistrefei trexoysa
    {
        return Questions.get (CurQuest);
    }


    public int GoNext () // auksanei kata 1
    {
        CurQuest++;
        if (CurQuest == GetNoQuestions ())
            CurQuest = 0;
        return CurQuest;
    }

    public int GoPrevious () //meiwnei kata 1
    {
        CurQuest--;
        if (CurQuest == -1)
            CurQuest = GetNoQuestions () - 1;
        return CurQuest;
    }

    public int GoNextUnAns () // paei sthn epomenh poy den exei apanththei
    {
        if (GetNoUnAnsweredQuestions () == 0)
            return -1;
        do
        {
            CurQuest++;
            if (CurQuest == GetNoQuestions ())
                CurQuest = 0;
        }
        while (GetQuestion (CurQuest).isAnswered ());
        return CurQuest;
    }

    public int GoPrevUnAns () // paei sth prohgoymenh poy den exei apanththei
    {
        if (GetNoUnAnsweredQuestions () == 0)
            return -1;
        do
        {
            CurQuest--;
            if (CurQuest == -1 )
                CurQuest = GetNoQuestions () - 1;
        }
        while (GetQuestion (CurQuest).isAnswered ());
        return CurQuest;
    }

    public void PrintAll () // typwnei
    {
        int i, j;
        System.out.println ("***HERE!!!!!!!");
        for (i = 0; i < GetNoQuestions (); i++)
        {
            Question Q = GetQuestion (i);
            System.out.println ("***" + "Question : " + (i + 1) + " " + Q.getQueText ());
            System.out.println ("***" + "Number of Answers : " + Q.GetNoAnswers ());
            System.out.println ("***" + "Correct Answer     : " + Q.getCorrectAns ());
            for (j = 0; j < Q.GetNoAnswers (); j++)
            {
                System.out.println ("***" + "Answer : "  + Q.getAnswer (j));
            }
        }

    }

    public void PrintAnswers ()
    {
        int i;
        for (i = 0; i < GetNoQuestions (); i++)
            System.out.println ("*** Question Number : " + (i + 1) + "Answer : " + GetQuestion (i).getUserAns ());
    }

}

