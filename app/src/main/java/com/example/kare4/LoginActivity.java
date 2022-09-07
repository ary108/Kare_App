package com.example.kare4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etLogEmail;
    EditText etLogPassword;
    Button loginButton, registerButton22;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        etLogEmail = findViewById(R.id.etLogEmail);
        etLogPassword = findViewById(R.id.etLogPassword);
        loginButton = findViewById(R.id.loginButton);
        registerButton22 = findViewById(R.id.registerButton22);

        mAuth = FirebaseAuth.getInstance();

        registerButton22.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        loginButton.setOnClickListener(view -> {
            loginUser();            
        });
    }

    private void loginUser() {

        String email = etLogEmail.getText().toString();
        String password = etLogPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etLogEmail.setError("Email box is empty!");
            etLogEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etLogEmail.setError("Password box is empty!");
            etLogEmail.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "You're logged in!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, ReportCreatorActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Sorry, there was an error!" + task.getException(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}