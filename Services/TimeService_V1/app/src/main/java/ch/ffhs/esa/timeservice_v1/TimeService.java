package ch.ffhs.esa.timeservice_v1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {

    public final static String TAG = "TimeService";
    private Timer myTimer = null;
    private BroadcastTimerTask sendTime = null;
    public static final String TIMER_AKTION = "SERVICE.TIMER_AKTION";

    /** inner class implements the broadcast timer*/
    private class BroadcastTimerTask extends TimerTask {
        private int counter = 0;

        public void run() {
            Intent intent = new Intent(TIMER_AKTION);
            String theTime = "Time: " + System.currentTimeMillis() + ",  Counter = " + Integer.toString(counter);
            counter++;
            intent.putExtra("TIME", theTime);
            sendBroadcast(intent);
        }
    };

    public TimeService() {
        Log.d(TAG, "Default Constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        myTimer.cancel();
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myTimer = new Timer("myTimer");
        sendTime = new BroadcastTimerTask();
        myTimer.scheduleAtFixedRate(sendTime, 0, 1000);
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}
