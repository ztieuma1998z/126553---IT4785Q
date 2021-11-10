package com.hizbik.calcalatar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button butCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butCalculator = (Button) findViewById(R.id.butCalculator);
        butCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                openCalculatorPage();
            }
        });

    }

    public void openCalculatorPage() {
        Intent intent = new Intent(this, Calculator_Activity.class);
        startActivity(intent);
    }

}