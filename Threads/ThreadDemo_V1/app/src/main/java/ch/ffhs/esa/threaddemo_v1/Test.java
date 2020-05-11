package ch.ffhs.esa.threaddemo_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Test extends AppCompatActivity implements Runnable  {

    private static final String TAG = "Test";
    private Thread clacThread = null;
    private TextView output = null;
//    private Handler handler = null;
    private int counter = 0;
    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        output = (TextView) findViewById(R.id.info);
        output.setText("Warten auf Start");
//        if (handler == null){
//            handler = new Handler();
//        }
        clacThread = null;
        Log.d(TAG, "Test");
    }

    public void onClickStartBtn(final View btn){
        started = true;

        if (clacThread == null) {
            clacThread = new Thread(this);
            clacThread.start();
            Log.d(TAG, "clacThread.start();");
        }
        output.setText("Gestartet !");
        Log.d(TAG, "onClickStartBtn");
    }


    public void onClickStopBtn(final View btn){
        started = false;
        if (clacThread != null) {
            try {
                clacThread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            }
            clacThread = null;
            output.setText("Gestoppt -> Warten auf Start");
        }else{
            output.setText("Bereits gestoppt -> Warten auf Start");
        }
        Log.d(TAG, "onClickStopBtn");
    }


//    private Runnable show = new Runnable() {
////        public void run() {
////            if(started){
////                output.setText("Zufall % 10000000 = 0: " + counter);
////            }
////            Log.d(TAG, "show::run");
////        }
////    };


    public void run() {
        Random randomizer = new Random();
        Log.d(TAG, "run");
        while (started){
            if (randomizer.nextInt() % 1000000 == 0) {
                counter++;
                //handler.post(show);
                output.setText("Zufall % 1000000000 = 0: " + counter);
                Log.d(TAG, "Zufall % 1000000000 = 0: " + counter);
            }
        }
    }
}
