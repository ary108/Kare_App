package com.example.kare4;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.util.Scanner;

public class Person {

    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

}
