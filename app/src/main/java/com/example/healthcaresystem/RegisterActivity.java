package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

/* This page handles the registration  process of new user. It primarily WRITES to DB.*/
public class RegisterActivity extends AppCompatActivity {
    // Set the components of the page as Objects
    EditText edUserName, edEmail, edPasswd, edConfirmPasswd;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUserName = findViewById(R.id.etUserNameSignUp);
        edEmail = findViewById(R.id.etEmailSignUp);
        edPasswd = findViewById(R.id.etPasswordSignUp);
        edConfirmPasswd = findViewById(R.id.etPasswordSignUpConfirm);
        btn = findViewById(R.id.buttonSignUp);
        tv = findViewById(R.id.tvSignUp);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUserName.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPasswd.getText().toString();
                String confirmPasswd = edConfirmPasswd.getText().toString();

                //Create the DB
                Database  db = new Database(getApplicationContext(), "healthsystemdb", null, 1);


                if(username.length()< 8) //|| password.length() < 8 ||email.length()<8 ||(confirmPasswd) != (password))
                {
                    Toast.makeText(getApplicationContext(), "Username and Password must be longer than 8 characters",
                            Toast.LENGTH_SHORT).show();
                    System.out.println(username.length());
                    System.out.println(email);
                    System.out.println(confirmPasswd);
                    System.out.println(password);
                }
                else
                {
                    if(password.compareTo(confirmPasswd)==0){
                        Toast.makeText(getApplicationContext(), "Successfully Signed Up", Toast.LENGTH_SHORT).show();

                        //Register  a new user to Database.
                        db.registerUser(username, email, password);
                        Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent (RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Passwords Do Not Match",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}