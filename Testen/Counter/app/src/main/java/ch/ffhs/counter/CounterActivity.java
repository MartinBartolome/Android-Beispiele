package ch.ffhs.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class CounterActivity extends AppCompatActivity implements Observer {
    private static final String TAG = "CounterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate without savedInstanceState");
        } else {
            Log.d(TAG, "onCreate with savedInstanceState");
        }
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Counter.getInstance().addObserver(this);
        TextView txtValue = (TextView) findViewById(R.id.txt_value);
        Counter.getInstance().reset();
        Log.d(TAG, "... onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Counter.getInstance().deleteObserver(this);
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }


    /**
     * listener on- up and down-Button
     * @param v
     */
    public void upDown(View v) {
        Button btn = (Button) v;

        Counter model = Counter.getInstance();

        switch (btn.getId()) {

            case R.id.btn_up:
                model.up();
                Log.d(TAG, "up -> " + model.getValueAsString());
                break;

            case R.id.btn_down:
                model.down();
                Log.d(TAG, "down -> " + model.getValueAsString());
                break;

            default:
                Log.d(TAG, "unknown cmd in up_down (" + v.toString() + ")");
        }
    }


    @Override
    public void update(Observable observable, Object data) {

        if (observable instanceof Counter) {
            Counter model = (Counter) observable;
            TextView txtValue = (TextView) findViewById(R.id.txt_value);
            txtValue.setText(model.getValueAsString());
            Log.d(TAG, "update from  observable = counter-object = " + model.getValueAsString());
        } else {
            Log.d(TAG, "update from unkown observable-object");
        }
    }
}
