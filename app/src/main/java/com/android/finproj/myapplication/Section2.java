package com.android.finproj.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class Section2 extends AppCompatActivity implements View.OnTouchListener, ViewTreeObserver.OnScrollChangedListener{
    ScrollView scrollView;
    WebView WvSect;
    Button BtNextStep;


    public String fileName = "section-2.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sections_lay);
        scrollView = findViewById(R.id.scrollView);
        BtNextStep = findViewById(R.id.BtNextStep);
        WvSect = findViewById(R.id.WvSect);
        scrollView.setOnTouchListener(this);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(this);
        WebSettings webSettings = WvSect.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WvSect.loadUrl("file:///android_asset/" + fileName);
        setupListeners();
    }

    private void setupListeners() {
        BtNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScrollChanged();
            }
        });
    }


    public void onScrollChanged(){
        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
        int bottomDetector = view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY());
        if(bottomDetector == 0 ){
            Intent intent = new Intent(Section2.this, StartTest2.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

}

