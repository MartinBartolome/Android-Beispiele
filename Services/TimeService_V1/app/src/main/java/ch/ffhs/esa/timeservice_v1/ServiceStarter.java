package ch.ffhs.esa.timeservice_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ServiceStarter extends AppCompatActivity {
    private final static String TAG = TimeService.TAG + "::Starter";
    private TimeBroadcastReceiver br = null;
    private boolean serviceOwner = false;

    /** inner class implements the broadcast receiver */
    public class TimeBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(TimeService.TIMER_AKTION)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    TextView myTextView = (TextView) findViewById(R.id.Info);
                    myTextView.setText((String) bundle.get("TIME"));
                }
                Log.d(TAG, (String) bundle.get("TIME"));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_service_starter);
        TextView myTextView = (TextView) findViewById(R.id.Info);

        br = new TimeBroadcastReceiver();
        registerReceiver(br, new IntentFilter(TimeService.TIMER_AKTION));

        if (isServiceRunning(TimeService.class)) {
            myTextView.setText("Service war gestartet...");
            serviceOwner = false;
            Log.d(TAG, "service is already running");
        } else {
            startService(new Intent(this, TimeService.class));
            serviceOwner = true;
            myTextView.setText("Service wird gestartet...");
            Log.d(TAG, "service not running");

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
        unregisterReceiver(br);
//        if(serviceOwner){
//            stopService(new Intent(this, TimeService.class));
//            Log.d(TAG, "service stopped");
//        }
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private boolean isServiceRunning(Class<?> serviceClass){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
