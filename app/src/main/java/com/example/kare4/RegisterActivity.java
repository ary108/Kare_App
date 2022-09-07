package com.example.kare4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegEmail;
    EditText etRegPassword;
    Button registerButton, loginButton22;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPassword);
        registerButton = findViewById(R.id.registerButton);
        loginButton22 = findViewById(R.id.loginButton22);

        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(view -> {
            createUser();
        });

        loginButton22.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

        }

    private void createUser() {
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email box is empty!");
            etRegEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etRegEmail.setError("Password box is empty!");
            etRegEmail.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "You're registered!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Sorry, there was an error!" + task.getException(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}

