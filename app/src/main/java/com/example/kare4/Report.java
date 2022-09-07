package com.example.kare4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Report {
    private int sleep;
    private int eat;
    private int behave;
    private int learn;
    private String title;

    public int getSleep() {
        return sleep;
    }

    public int getEat() {
        return eat;
    }

    public int getBehave() {
        return behave;
    }

    public int getLearn() {
        return learn;
    }

    public String getTitle() {
        return title;
    }




    public Report(int sleep, int eat, int behave, int learn, String title) {

        this.sleep = sleep;
        this.eat = eat;
        this.behave = behave;
        this.learn = learn;
        this.title = title;

    }

    public void printOut() {
        System.out.println("sleep: " + this.sleep + " , eat: " + this.eat+ " , behave: " + this.behave +
                " , learn: " + this.learn);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void genPDF() {

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        int pWidth = 1200;

        PdfDocument pdf = new PdfDocument();
        Paint titlePaint = new Paint();
        Paint bodyPaint = new Paint();

        PdfDocument.PageInfo pageInfo1 = new PdfDocument.PageInfo.
                Builder(1200,2010,1).create();

        PdfDocument.Page page1 = pdf.startPage(pageInfo1);
        Canvas canvas = page1.getCanvas();

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(60);

        canvas.drawText("Gokool Childcare Report", pWidth/2, 270, titlePaint);

        bodyPaint.setTextAlign(Paint.Align.LEFT);
        bodyPaint.setTextSize(30);

        canvas.drawText(title, 20, 400, bodyPaint);

        canvas.drawText("On a scale of 1 to 10, your child's amount of sleep was rated as" + sleep + "/ 10", 20, 550, bodyPaint);
        canvas.drawText("On a scale of 1 to 10, your child's eating habits today was rated as" + eat + "/ 10", 20, 600, bodyPaint);
        canvas.drawText("On a scale of 1 to 10, your child's behavior and cooperation was a " + behave + "/ 10", 20, 650, bodyPaint);
        canvas.drawText("On a scale of 1 to 10, your child's learning and mental development was a  " + learn + "/ 10", 20, 700, bodyPaint);

        pdf.finishPage(page1);

        File file = new File(pdfPath, "/" + title + ".pdf");

        try {
            pdf.writeTo(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdf.close();

    }

}
