package com.example.adf11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String ex = null;
            System.out.println(ex.charAt(0));
        } catch (NullPointerException e) {
            Log.e("MainActivity", "Happy Birthday to Jackie", e);
        }
    }
}
