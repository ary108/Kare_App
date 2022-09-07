package com.example.kare4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class ReportCreatorActivity extends AppCompatActivity {

    SeekBar SeekBarSleep, SeekBarEat, SeekBarBehave, SeekBarLearn;
    EditText titleText;
    Button generateButtonNew, viewReportButton, profileButton;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_creator);

        SeekBarSleep = findViewById(R.id.seekBarSleep);
        SeekBarEat = findViewById(R.id.seekBarEat);
        SeekBarBehave = findViewById(R.id.seekBarBehave);
        SeekBarLearn = findViewById(R.id.seekBarLearn);

        generateButtonNew = findViewById(R.id.generateButtonNew);
        viewReportButton = findViewById(R.id.viewReportButton);
        profileButton = findViewById(R.id.profileButton);

        titleText = findViewById(R.id.titleText);

        generateButtonNew.setOnClickListener(view -> {
            saveReport();
        });

        viewReportButton.setOnClickListener(view -> {
            startActivity(new Intent(ReportCreatorActivity.this, ViewReportActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(ReportCreatorActivity.this, ProfileCreator.class));
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveReport() {
        int sleep = SeekBarSleep.getProgress();
        int eat = SeekBarEat.getProgress();
        int behave = SeekBarBehave.getProgress();
        int learn = SeekBarLearn.getProgress();
        String title = titleText.getText().toString();

        if (title.equals("Enter Date - Child Name")) {
            Toast.makeText(ReportCreatorActivity.this, "Don't forget to add a title!", Toast.LENGTH_LONG).show();
        } else {

            ActivityCompat.requestPermissions(ReportCreatorActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

            Report report =  new Report(sleep, eat,behave, learn, title);

            if ((MyViewModel.getTable()).get(title) != null){
                Toast.makeText(ReportCreatorActivity.this, "A report with this title already exists!", Toast.LENGTH_LONG).show();
            } else {

                (MyViewModel.getTable()).add(title, report);

                System.out.println("title: " + title);
                ((Report)((MyViewModel.getTable()).get(title))).printOut();

                report.genPDF();

                Toast.makeText(ReportCreatorActivity.this, "PDF Generated", Toast.LENGTH_LONG).show();
            }
        }
    }
}