package ch.ffhs.esa.threaddemo_ausgangslage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Test extends AppCompatActivity {
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }



    private int langBerecnung(){
        int count = 0;
        Random meinRandomizer = new Random();
        while (meinRandomizer.nextInt() % 200000000 != 0){
            count++;
        }
        return count;
    }


    public void onClickStartBtn(final View btn){
        TextView output = (TextView) findViewById(R.id.info);
//        final ProgressDialog verlauf = ProgressDialog.show(this,
//                "Bitte warten ...",
//                "Berechnung laeuft",
//                true, false);
//        Thread t = new Thread(){
//            public void run(){
//                counter = langBerecnung();
//                verlauf.dismiss();
//            }
//        };
//        t.start();
        counter = langBerecnung();
        output.setText("Zufall % 1000000000 = 0:   " + counter);
    }


    public void onClickStopBtn(final View btn){
        TextView output = (TextView) findViewById(R.id.info);
        output.setText("Stopped");
    }
}
