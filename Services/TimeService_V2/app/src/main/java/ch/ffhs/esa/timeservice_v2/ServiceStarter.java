package ch.ffhs.esa.timeservice_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceStarter extends AppCompatActivity {
    private static final String TAG = TimeService.TAG + "::Starter";
    private boolean serviceStarted = false;
    private TimeService.TimeServiceBinder timeService = null;


    private ServiceConnection timeServiceConnection = new ServiceConnection() {
        private final String TAG = TimeService.TAG  + "::Connection";
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            timeService = (TimeService.TimeServiceBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            timeService = null;
            Log.d(TAG, "onServiceDisconnected");
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_starter);

        TextView myTextView = (TextView) findViewById(R.id.Info);
        String text = "Service wird gestartet von PID/TID( "
                + android.os.Process.myPid()
                + " / "
                + android.os.Process.myTid()
                + " ) !";
        myTextView.setText(text);
        Log.d(TAG, "onCreate");
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
        Log.d(TAG, "onResume");
        super.onResume();
    }


    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (serviceStarted) {
            unbindService(timeServiceConnection);
            stopService(new Intent(this, TimeService.class));
        }
        super.onDestroy();

    }


    public void onStartStopClick(View v){
        Button btn = (Button) v;
        Intent timeServiceIntent = new Intent(this, TimeService.class);

        if(!serviceStarted){
            //startService(timeServiceIntent);
            bindService(timeServiceIntent, timeServiceConnection, Context.BIND_AUTO_CREATE);
            serviceStarted = true;
            btn.setText(getResources().getText(R.string.stop));
        }else{
            unbindService(timeServiceConnection);
            stopService(timeServiceIntent);
            serviceStarted = false;
            btn.setText(getResources().getText(R.string.start));
        }
    }


    public void onGetDataClick(View v) {
        if (timeService != null) {
            String text;
            TextView timeTextView = (TextView) findViewById(R.id.readTime);
            text = "Time = " + timeService.getTime();
            timeTextView.setText(text);

            TextView counterTextView = (TextView) findViewById(R.id.readCounter);
            text = "Counter = " + timeService.getCounter();
            counterTextView.setText(text);

            TextView pidTextView = (TextView) findViewById(R.id.readPID);
            text = "PID = " + timeService.getPID();
            pidTextView.setText(text);

            TextView tidTextView = (TextView) findViewById(R.id.readTID);
            text = "TID = " + timeService.getTID();
            tidTextView.setText(text);
        }

    }

}
