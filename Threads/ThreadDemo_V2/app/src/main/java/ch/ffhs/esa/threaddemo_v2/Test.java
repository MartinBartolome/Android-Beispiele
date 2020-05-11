package ch.ffhs.esa.threaddemo_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class Test extends AppCompatActivity {

    private BackGroundThread calcThread = null;
    private TextView output = null;
    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        output = (TextView) findViewById(R.id.info);
        output.setText("Warten auf Start");
        if (handler == null){
            handler = new Handler();
        }
        calcThread = null;
    }



    public void onClickStartBtn(final View btn){

        if (calcThread == null) {
            calcThread = new BackGroundThread(show, handler);
            calcThread.setStarted(true);
            calcThread.start();
            output.setText("Gestartet!");
        }else{
            output.setText("Bereits gestartet!");
        }

    }


    public void onClickStopBtn(final View btn){
        if (calcThread != null) {
            calcThread.setStarted(false);
            try {
                calcThread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            }
            calcThread = null;
            output.setText("Gestoppt -> Warten auf Start");
        }else{
            output.setText("Bereits gestoppt -> Warten auf Start");
        }
    }

    private Runnable show = new Runnable() {
        public void run() {
            int counter = 0;
            if(calcThread != null){
                counter = calcThread.getCounter();
            }else{
                counter = -1;
            }
            output.setText("Zufall % 10000000 == 0:     " + counter);
        }
    };
}
