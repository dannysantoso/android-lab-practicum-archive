package com.example.bookwormproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.text.TextUtils.isEmpty;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btRegister, btLogin;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        db = new DatabaseHelper(this);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);

        btRegister = findViewById(R.id.registerButton);
        btLogin = findViewById(R.id.loginButton);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Home.class);

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(isEmpty(username)){
                    etUsername.setError("you must fill this field");
                }else if (isEmpty(password)) {
                    etPassword.setError("you must fill this field");
                }else {
                    if (db.cekLogin(username, password) == true) {
                        startActivity(intent);
                    } else {
                        etUsername.setError("username and password not registered");
                        etPassword.setError("username and password not registered");
                    }
                }
            }
        });

    }
}
