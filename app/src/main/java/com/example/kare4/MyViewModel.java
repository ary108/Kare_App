package com.example.kare4;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyViewModel {
    public static ReportTable<String, Report> table = new ReportTable<String, Report>();

    public static ReportTable getTable() {
        return table;
    }

}
