package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
/* This page handles the Log in process of the user. */
public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btn;

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName = findViewById(R.id.etUserNameSignUp);
        edPassword = findViewById(R.id.etPasswordSignUp);
        btn = findViewById(R.id.buttonSignUp);
        tv = findViewById(R.id.tvSignUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUserName.getText().toString();
                String password = edPassword.getText().toString();

                if(username.length() < 8 || password.length() < 8)
                {
                    Toast.makeText(getApplicationContext(), "Username and Password must be longer than 8 characters",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                }


            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });




    }
}