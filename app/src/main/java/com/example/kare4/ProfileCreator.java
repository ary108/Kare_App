package com.example.kare4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProfileCreator extends AppCompatActivity {

    EditText childName, childAge;
    Button createProfileButton, previousButton2, getProfileButton;
    TextView showProfileText, nameText10, ageText10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creator);

        childName = findViewById(R.id.childName);
        childAge = findViewById(R.id.childAge);
        createProfileButton = findViewById(R.id.createProfileButton);
        previousButton2 = findViewById(R.id.previousButton2);
        getProfileButton = findViewById(R.id.getProfileButton);
        showProfileText = findViewById(R.id.showProfileText);
        nameText10 = findViewById(R.id.nameText10);
        ageText10 = findViewById(R.id.ageText10);

        createProfileButton.setOnClickListener(view -> {
            System.out.println("Click worked");
            System.out.println((childName.getText().toString()));
            System.out.println((childAge.getText().toString()));
            createChild((childName.getText().toString()), (childAge.getText().toString()));
//            try {
//                eraseLast();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            RecordHolderClass.getRecordHolder().remove(RecordHolderClass.getRecordHolder().size() - 1);
        });

        getProfileButton.setOnClickListener(view -> {
            showProfileText.setText(readFromFile(ProfileCreator.this));
            Child child = RecordHolderClass.getRecordHolder().get(RecordHolderClass.getRecordHolder().size() - 1);
            nameText10.setText(child.getName());
            ageText10.setText(child.getAge());
        });

        previousButton2.setOnClickListener(view -> {
            startActivity(new Intent(ProfileCreator.this, ReportCreatorActivity.class));
        });

    }

    public void createChild(String name, String age) {
        Child newChild = new Child(name, age);
        RecordHolderClass.getRecordHolder().add(newChild);
        writeToFile((stringCreator(name, age)), ProfileCreator.this);
        Toast.makeText(ProfileCreator.this, ("Data stored"), Toast.LENGTH_LONG).show();
        System.out.println("data stored: " + readFromFile(ProfileCreator.this));

    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("nameRecord.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String str = "";

        try {
            InputStream inputStream = context.openFileInput("nameRecord.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                str = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return str;
    }

    public static String stringCreator(String str1, String str2) {
        String string = str1 + ", " + str2 + " ";
        return string;
    }

}