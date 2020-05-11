package ch.ffhs.esa.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceStarter extends AppCompatActivity {
    final static private String TAG = TimeService.TAG + "::STARTER";
    private boolean serviceStarted = false;
    private TimeBroadcastReceiver br = null;

    /** inner class implements the broadcast receiver */
    public class TimeBroadcastReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(TimeService.TIMER_AKTION)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {

                    TextView timeTextView = (TextView) findViewById(R.id.Time);
                    timeTextView.setText((String) bundle.get("TIME"));
                    TextView counterTextView = (TextView) findViewById(R.id.Counter);
                    counterTextView.setText((String) bundle.get("COUNTER"));
                    TextView pidTextView = (TextView) findViewById(R.id.PID);
                    pidTextView.setText((String) bundle.get("PID"));
                    TextView tidTextView = (TextView) findViewById(R.id.TID);
                    tidTextView.setText((String) bundle.get("TID"));

                    Button btn = (Button) findViewById(R.id.start_stop);
                    if((boolean) bundle.get("STATE")){
                        serviceStarted = true;
                        btn.setText(getResources().getText(R.string.stop));
                    }else{
                        serviceStarted = false;
                        btn.setText(getResources().getText(R.string.start));
                    }
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_starter);

        TextView myTextView = (TextView) findViewById(R.id.Info);

        br = new TimeBroadcastReceiver();
        registerReceiver(br, new IntentFilter(TimeService.TIMER_AKTION));

        /* Testen, ob service bereist laeuft */
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if(TimeService.class.getName().equals(service.service.getClassName())){
                serviceStarted = true;
                Button btn = (Button) findViewById(R.id.start_stop);
                btn.setText(getResources().getText(R.string.stop));
            }
        }

        if (!serviceStarted) {
            String text = String.format(getResources().getString(R.string.disp_text_off),
                    android.os.Process.myPid(),
                    android.os.Process.myTid());
            myTextView.setText(text);
        }else{
            String text = String.format(getResources().getString(R.string.disp_text_on),
                    android.os.Process.myPid(),
                    android.os.Process.myTid());
            myTextView.setText(text);
        }
        Log.d(TAG, "onCreate .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid());
    }


    public void onStartStopClick(View v){
        Button btn = (Button) v;

        if(!serviceStarted){
            startService(new Intent(this, TimeService.class));
            serviceStarted = true;
            btn.setText(getResources().getText(R.string.stop));
        }else{
            stopService(new Intent(this, TimeService.class));
            serviceStarted = false;
            btn.setText(getResources().getText(R.string.start));
        }
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid());
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid());
        super.onStart();
    }


    @Override
    protected void onResume() {
        Log.d(TAG, "onResume .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid());
        super.onResume();
    }


    @Override
    protected void onPause() {
        Log.d(TAG, "onPause .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(br);
        Log.d(TAG, "onDestroy .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid());
        super.onDestroy();
    }
}
