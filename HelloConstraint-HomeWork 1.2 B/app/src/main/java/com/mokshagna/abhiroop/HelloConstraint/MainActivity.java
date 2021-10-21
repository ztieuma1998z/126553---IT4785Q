package com.mokshagna.abhiroop.HelloConstraint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    Button zerobutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView)findViewById(R.id.show_count);
        zerobutton = findViewById(R.id.button_zero);
    }

    public void showToast(View view) {
        Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT).show();
    }

    public void countUp(View view) {
        mCount++;
        zerobutton.setBackgroundResource(R.color.myZeroColor);
        if(mShowCount!=null){
            mShowCount.setText(Integer.toString(mCount));

            if(mCount%2==0){
                setGreen(findViewById(R.id.button_count));
            }
            else{
                setRed(findViewById(R.id.button_count));
            }
        }
    }

    public void setDefault(View view){
        view.setBackgroundResource(R.color.colorPrimary);
    }

    public void setGreen(View view){
        view.setBackgroundResource(R.color.myEvenColor);
    }

    public void setRed(View view){
        view.setBackgroundResource(R.color.myOddColor);
    }

    public void setZero(View view) {
        mCount = 0;
        if(mShowCount!=null){
            mShowCount.setText(Integer.toString(mCount));
            zerobutton.setBackgroundResource(R.color.myZeroInitialColor);
                setDefault(findViewById(R.id.button_count));
        }
    }
}
