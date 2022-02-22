package com.android.finproj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ShowChapters extends AppCompatActivity  implements View.OnClickListener  {

    Button BtBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapters_lay);
        BtBegin = findViewById(R.id.BtBegin);
        BtBegin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Section2.class);
        startActivity(intent);
    }
}
