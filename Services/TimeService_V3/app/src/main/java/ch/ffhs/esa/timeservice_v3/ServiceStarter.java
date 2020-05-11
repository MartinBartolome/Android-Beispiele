package ch.ffhs.esa.timeservice_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceStarter extends AppCompatActivity {

    public static final String MESSENGER = "MESSENGER";
    private static final String TAG = TimeService.TAG + "::Starter";
    private boolean serviceStarted = false;
    private final Messenger activityMessenger = new Messenger(new IncomingHandler());

    private class IncomingHandler extends Handler {
        private static final String TAG = TimeService.TAG + "::Handler";
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage");
            Bundle bundle = msg.getData();
            if (bundle != null) {
                TextView timeTextView = (TextView) findViewById(R.id.Time);
                timeTextView.setText("Time = " + bundle.getLong("TIME", 0));

                TextView counterTextView = (TextView) findViewById(R.id.Counter);
                counterTextView.setText("Counter = " + bundle.getInt("COUNTER", 0));

                TextView pidTextView = (TextView) findViewById(R.id.PID);
                pidTextView.setText("PID = " + bundle.getInt("PID", 0));

                TextView tidTextView = (TextView) findViewById(R.id.TID);
                tidTextView.setText("TID = " + bundle.getInt("TID", 0));
            }
            super.handleMessage(msg);
        }
    }

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
            //stopService(new Intent(this, TimeService.class));
        }
        super.onDestroy();

    }


    public void onStartStopClick(View v){
        Button btn = (Button) v;
        Intent timeServiceIntent = new Intent(this, TimeService.class);

        if(!serviceStarted){
            timeServiceIntent.putExtra(MESSENGER, activityMessenger);
            startService(timeServiceIntent);
            //bindService(timeServiceIntent, timeServiceConnection, Context.BIND_AUTO_CREATE);
            serviceStarted = true;
            btn.setText(getResources().getText(R.string.stop));
        }else{
            //unbindService(timeServiceConnection);
            stopService(timeServiceIntent);
            serviceStarted = false;
            btn.setText(getResources().getText(R.string.start));
        }
    }
}
