package com.android.finproj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    Button BtCont;
    TextView TvSpMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen_lay);
        BtCont = findViewById(R.id.BtCont);
        BtCont.setOnClickListener (this);
//        if (!FileExists ("Inform_technology.db"))
//        {
//            Toast Tst = Toast.makeText(getApplicationContext(), "Could not find DB. First Run?\nCopy from Assets", Toast.LENGTH_LONG);
//            Tst.show();
//            CopyDB ("Inform_technology.db"); // gia metafora apo ta assets ston sklhro disko
//        }
//        else
//        { // ean t arxeio yparxei
//            Toast Tst = Toast.makeText(getApplicationContext(), "DB in position... Let us continue....", Toast.LENGTH_LONG);
//            Tst.show();
//            BtCont.setEnabled (true);
//        }
    }

//    boolean FileExists (String Fn)
//    {
//        File file = new File (getApplicationContext (). getFilesDir(), Fn); // to fakelo poy prepei na vazoyme ta arxeia mas
//        return file.exists ();
//    }
//
//    void CopyDB (String Fn)
//    {
//        AssetManager AssetMan = getAssets();
//        InputStream Inp; // to arxeio poy tha diavastei
//        OutputStream Outp; // to arxeio poy tha graftei
//        byte[] Buffer; // endiamesh mnhmh
//        int BR;
//        try
//        {
//            Inp = AssetMan.open (Fn);
//            File OutFile = new File (getApplicationContext (). getFilesDir(), Fn);
//            Outp = new FileOutputStream(OutFile);
//            Buffer = new byte[1024];
//            while ((BR = Inp.read (Buffer)) != -1)
//                Outp.write (Buffer, 0, BR);
//            Inp.close();
//            Outp.flush();
//            Outp.close();
//            BtCont.setEnabled (true);
//            Toast Tst = Toast.makeText(getApplicationContext(), "Database now in: " + OutFile.getAbsolutePath (), Toast.LENGTH_LONG);
//            Tst.show();
//            TvSpMess.setText ("Database now in: " + OutFile.getAbsolutePath ());
//
//        }
//        catch(IOException e)
//        {
//            System.out.println ("*** IOException: " + e.getMessage ());
//            Toast tst = Toast.makeText (getApplicationContext (), "IO Error during DB copy...", Toast.LENGTH_LONG);
//            tst.show ();
//        }
//    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(WelcomeScreen.this, Login.class);
        startActivity(intent);
    }
}

