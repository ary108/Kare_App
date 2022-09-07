package com.example.kare4;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewReportActivity extends AppCompatActivity {

    TextView sleepResult, eatResult, behaveResult, learnResult;
    EditText searchBar;
    Button searchButton, button_second;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        searchBar = ViewReportActivity.this.findViewById(R.id.childName);
        sleepResult =  ViewReportActivity.this.findViewById(R.id.sleepResult);
        eatResult =  ViewReportActivity.this.findViewById(R.id.eatResult);
        behaveResult =  ViewReportActivity.this.findViewById(R.id.behaviorResult);
        learnResult =  ViewReportActivity.this.findViewById(R.id.learnResult);
        button_second = findViewById(R.id.previousButton2);
        searchButton = findViewById(R.id.createProfileButton);

        button_second.setOnClickListener(view -> {
            startActivity(new Intent(ViewReportActivity.this, ReportCreatorActivity.class));
        });

        searchButton.setOnClickListener(view -> {
            if (searchBar.getText().equals("Enter Date - Child Name")) {
                Toast.makeText(ViewReportActivity.this, "Don't forget to enter the name of the report!", Toast.LENGTH_LONG).show();
            } else {

                String search = searchBar.getText().toString();
//                MyViewModel model = new ViewModelProvider(ViewReportActivity.this).get(MyViewModel.class);

                System.out.println(MyViewModel.getTable().size());

                if ((MyViewModel.getTable()).get(search) == null) {
                    Toast.makeText(ViewReportActivity.this, "This report doesn't exist", Toast.LENGTH_LONG).show();
                } else {

                    Report fetch = (Report) (MyViewModel.getTable()).get(search);

                    fetch.printOut();

                    int sleepInt = fetch.getSleep();
                    int eatInt = fetch.getEat();
                    int behaveInt = fetch.getBehave();
                    int learnInt = fetch.getLearn();

                    sleepResult.setText(("Sleep Rating: " + sleepInt));
                    eatResult.setText(("Eating Rating: " + eatInt));
                    behaveResult.setText(("Behavior Rating: " + behaveInt));
                    learnResult.setText(("Learning Rating: " + learnInt));
                }
            }
        });

    }
}