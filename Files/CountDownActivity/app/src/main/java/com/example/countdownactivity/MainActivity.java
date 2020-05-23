package com.example.countdownactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {

    private static final String TAG = "CountDownActivity";
    Button btn;
    private Thread CountdownThread = null;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.Countdownbutton);
    }


    public void onBtnClick(View v) {
        if (CountdownThread == null) {
            CountdownThread = new Thread(this);
            CountdownThread.start();
        }
    }

    public void run() {

        if (btn.getText().equals(getResources().getString(R.string.stop))) {
            btn.setText(R.string.count_down);
            counter = 0;
        } else {
            EditText nbrTxt = findViewById(R.id.start_wert);
            counter = Integer.valueOf(nbrTxt.getText().toString());
            btn.setText("Stop");
        }


        TextView value = findViewById(R.id.value);
        value.setText(String.valueOf(counter));
        while (counter > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter--;
            value.setText(String.valueOf(counter));
        }
    }
}

