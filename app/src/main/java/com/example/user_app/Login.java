package com.example.user_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

//        Initialize all the variables
        TextView tvRegistration = findViewById(R.id.tvRegLink);
        TextView tvForgotCredentials = findViewById(R.id.tvForgotPass);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPass);
        Button btnLogin = findViewById(R.id.btnRegister);

//        If tvRegistration is clicked go to Registration Page
        tvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

//        If tvForgotCredentials is clicked go to ForgotPassword page
        tvForgotCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

//        If btnLogin is pressed validate the credentials provided in etEmail and etPassword
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

//                If credentials are valid go to home screen of user.
                if(validCredentials(email, password)) {
                    Intent intent = new Intent(Login.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }
//                else return to this page and display proper error message.
                else {
                    Toast.makeText(Login.this, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    Makes API call to backend and validates credentials
    private boolean validCredentials(String email, String password) {

        return true;
    }
}