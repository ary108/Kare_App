package com.example.kare4;

import java.util.ArrayList;

public class RecordHolderClass {
    private static ArrayList<Child> recordHolder = new ArrayList<>();

    public static ArrayList<Child> getRecordHolder() {
        return recordHolder;
    }
}
