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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register  extends AppCompatActivity  {

//    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^" +
//                    "(?=\\S+$)" +           //no white spaces
//                    ".{4,}" +               //at least 4 characters
//                    "$");

    FirebaseDatabase database;

    DatabaseReference users;
    EditText EtName;
    EditText EtSurName;
    EditText EtEmail;
    EditText EtPass;
    Button BtRegister;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.register_lay);

        firebaseAuth = FirebaseAuth.getInstance();
        EtName = findViewById(R.id.EtName);
        EtSurName = findViewById(R.id.EtSurName);
        EtEmail = findViewById(R.id.EtEmail);
        EtPass = findViewById(R.id.EtPass);
        BtRegister = findViewById(R.id.BtRegister);
//        BtRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Register.this,Login.class));
//
////                checkDataEntered();
//            }
//        });


        BtRegister.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                final  String email = EtEmail.getText().toString().trim();

                String pwd = EtPass.getText().toString().trim();

                final String name = EtName.getText().toString().trim();
                final String surname = EtSurName.getText().toString().trim();
                // this condition checks to make sure the user inputs the correct details

                if(email.isEmpty()){

                    EtEmail.setError("Please enter email id");

                    EtEmail.requestFocus();

                }

                else  if(pwd.isEmpty()){

                    EtPass.setError("Please enter your password");

                    EtPass.requestFocus();

                }

                else  if(email.isEmpty() && pwd.isEmpty()){

                    Toast.makeText(Register.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();

                }

                else  if(!(email.isEmpty() && pwd.isEmpty())){

                    // this firebase method creates a user with email and password

                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful()){

                                Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }

                            else {

                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()

                                        .setDisplayName(name).build(); // saves the username

                                UserProfileChangeRequest profileUpdatesSur = new UserProfileChangeRequest.Builder()

                                        .setDisplayName(surname).build(); // saves the username

                                user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override

                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            Toast.makeText(Register.this, "name stored successful", Toast.LENGTH_LONG).show();

                                        }

                                    }

                                });

                                user.updateProfile(profileUpdatesSur).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override

                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            Toast.makeText(Register.this, "username stored successful", Toast.LENGTH_LONG).show();

                                        }

                                    }

                                });

                                startActivity(new Intent(Register.this, Login.class));

                                Toast.makeText(Register.this, "Registration is successful", Toast.LENGTH_SHORT).show();

                            }

                        }

                    });

                }

                else{

                    Toast.makeText(Register.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

//    boolean isEmail(EditText text) {
//        CharSequence email = text.getText().toString();
//        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
//    }
//
//    boolean isEmpty(EditText text) {
//        CharSequence str = text.getText().toString();
//        return TextUtils.isEmpty(str);
//    }

//    private boolean validateEmail() {
//        String emailInput = EtEmail.getText().toString().trim();
//        if (emailInput.isEmpty()) {
//            EtEmail.setError("Field can't be empty");
//            return false;
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            EtEmail.setError("Please enter a valid email address");
//            return false;
//        } else {
//            EtEmail.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validateUsername() {
//        String usernameInput = EtName.getText().toString().trim();
//        if (usernameInput.isEmpty()) {
//            EtName.setError("Field can't be empty");
//            return false;
//        } else if (usernameInput.length() > 15) {
//            EtName.setError("Username too long");
//            return false;
//        } else {
//            EtName.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validateUserSurname() {
//        String usernameInput = EtSurName.getText().toString().trim();
//        if (usernameInput.isEmpty()) {
//            EtSurName.setError("Field can't be empty");
//            return false;
//        } else if (usernameInput.length() > 15) {
//            EtSurName.setError("Username too long");
//            return false;
//        } else {
//            EtSurName.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validatePassword() {
//        String passwordInput = EtPass.getText().toString().trim();
//        if (passwordInput.isEmpty()) {
//            EtPass.setError("Field can't be empty");
//            return false;
//        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
//            EtPass.setError("Password too weak");
//            return false;
//        } else {
//            EtPass.setError(null);
//            return true;
//        }
//    }
//
//    public void checkDataEntered() {
//        if (!validateEmail() | !validateUsername() | !validateUserSurname() | !validatePassword()) {
//            return;
//        }
//        String input = "Email: " + EtEmail.getText().toString();
//        input += "\n";
//        input += "Username: " + EtName.getText().toString();
//        input += "\n";
//        input += "UserSurname: " + EtSurName.getText().toString();
//        input += "\n";
//        input += "Password: " + EtPass.getText().toString();
//        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Register.this, Login.class);
//        startActivity(intent);
//        this.finish();
//
//    }

//
//    void checkDataEntered () {
//        boolean isValid =  true;
//        if (isEmpty(EtName)) {
//            EtName.setError("Πληκτρολόγησε το Όνομά σου!");
//            isValid = false;
//        }
//        if (isEmpty(EtSurName)) {
//            EtName.setError("Πληκτρολόγησε το Επώνυμό σου!");
//            isValid = false;
//        }
//
//        if (isEmail(EtEmail) == false) {
//            EtEmail.setError("Πληκτρολόγησε ένα e-mail!");
//            isValid = false;
//        }
//
//        if (isValid) {
//            Intent intent = new Intent(Register.this, Login.class);
//            startActivity(intent);
//            this.finish();
//        }
    }

