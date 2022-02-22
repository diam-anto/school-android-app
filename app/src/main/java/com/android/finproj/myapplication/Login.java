package com.android.finproj.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

//    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^" +
//                    "(?=\\S+$)" +           //no white spaces
//                    ".{4,}" +               //at least 4 characters
//                    "$");

    EditText EtEmail;
    EditText EtPass;
    Button BtLogin;
    Button BtReg;

    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.login_lay);



        firebaseAuth = FirebaseAuth.getInstance();
        EtEmail = findViewById(R.id.EtEmail);
        EtPass = findViewById(R.id.EtPass);
        BtLogin = findViewById(R.id.BtLogin);
        BtReg = findViewById(R.id.BtReg);

            // checks whether the user is logged in and executes the  code

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null){

                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent home = new Intent(Login.this, ShowChapters.class);

                    startActivity(home);

                }

                else{

                    Toast.makeText(Login.this, "Please Login", Toast.LENGTH_SHORT).show();

                }

            }

        };

        BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = EtEmail.getText().toString(); // gets the user email

                String password = EtPass.getText().toString();



                if (email.isEmpty()){

                    EtEmail.setError("PLEASE ENTER EMAIL ADDRESS");

                    EtEmail.requestFocus();

                }

                else if (password.isEmpty()){

                    EtPass.setError("PLEASE INPUT PASSWORD");

                    EtPass.requestFocus();

                }

                else if (email.isEmpty() && password.isEmpty()){

                    Toast.makeText(Login.this, " BOTH FIELDS ARE EMPTY!!", Toast.LENGTH_SHORT).show();

                }

                else if (!(email.isEmpty() && !password.isEmpty())){

                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this,

                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                    else{

                                        Intent dashboard = new Intent(Login.this, ShowChapters.class);

                                        startActivity(dashboard);

                                    }
                                }

                            });

                }

                else {

                    Toast.makeText(Login.this, "Error Occured",Toast.LENGTH_SHORT).show();

                }
            }

        });



        BtReg.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent signup = new Intent(Login.this, Register.class);

                startActivity(signup);

            }

        });





    }

    @Override

    protected void onStart() {

        super.onStart(); // attach the firebase listener when the activity starts

        firebaseAuth.addAuthStateListener(authStateListener);

    }



    @Override

    protected void onStop() {

        super.onStop(); // remove the firebase listener when the activity stops

        if (authStateListener != null) {

            firebaseAuth.removeAuthStateListener(authStateListener);



        }

    }



//        setupUI();
//        setupListeners();
//    }
//
//    private void setupUI() {
//        EtEmail = findViewById(R.id.EtEmail);
//        EtPass = findViewById(R.id.EtPass);
//        BtLogin = findViewById(R.id.BtLogin);
//        BtReg = findViewById(R.id.BtReg);
//    }
//
//    private void setupListeners() {
//        BtLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkUserName();
//            }
//        });
//        BtReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Login.this, Register.class);
//                startActivity(i);
//            }
//        });
//    }
//
//    boolean isEmail(EditText text) {
//        CharSequence email = text.getText().toString();
//        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
//    }
//
//    boolean isEmpty(EditText text) {
//        CharSequence str = text.getText().toString();
//        return TextUtils.isEmpty(str);
//    }
//
//    private boolean validatePassword(EditText text) {
//
//        CharSequence password = text.getText().toString();
//        return (!TextUtils.isEmpty(password) && PASSWORD_PATTERN.matcher(password).matches());
////        String passwordInput = EtPass.getText().toString().trim();
////        if (passwordInput.isEmpty()) {
////            EtPass.setError("Field can't be empty");
////            return false;
////        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
////            EtPass.setError("Password too weak");
////            return false;
////        } else {
////            EtPass.setError(null);
////            return true;
////        }
//    }
//
//
//    void checkUserName() {
//        boolean isValid =  true;
//        if (isEmpty(EtEmail)) {
//            EtEmail.setError("Πληκτρολόγησε το e-mail σου!");
//            isValid = false;
//        }
//        if (!isEmail(EtEmail)) {
//            EtEmail.setError("Πληκτρολόγησε ένα σωστό e-mail!");
//            isValid = false;
//        }
//
//        if (isValid) {
//            String usernameValue = EtEmail.getText().toString();
//            String passwordValue = EtPass.getText().toString();
////            if (usernameValue.equals(EtEmail) && passwordValue.equals(EtPass)) {
//                //everything checked we open new activity
//                Intent intent = new Intent(Login.this, ShowChapters.class);
//                startActivity(intent);
//                //we close this activity
//                this.finish();
//        } else {
//            Toast tst = Toast.makeText(this, "Λάθος email ή password!", Toast.LENGTH_SHORT);
//            tst.show();
//        }
//    }

}
