package ch.ffhs.esa.timeservice_v2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    public static final String TAG = "TimeService";
    private Timer myTimer = null;
    private int counter = 0;
    private long time = 0;

    private final IBinder timeServiceBinder = new TimeServiceBinder();


    /** inner class implements the broadcast timer*/
    private class TimeServiceTimerTask extends TimerTask {
        private static final String TAG = "TimeServiceTimerTask";
        public void run() {
            time   = + System.currentTimeMillis();
            counter++;
            Log.d(TAG, "counter = " + counter);
        }
    };

    public TimeService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        myTimer = new Timer("myTimer");
        myTimer.scheduleAtFixedRate( new TimeServiceTimerTask(), 0, 1000);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return timeServiceBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        myTimer.cancel();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        myTimer = null;
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }



    public class TimeServiceBinder extends Binder {
        private final String TAG = TimeService.TAG  + "::Binder";

        public int getCounter() {
            Log.d(TAG, "getCounter");
            return counter;
        }

        public long getTime() {
            Log.d(TAG, "getTime");
            return time;
        }

        public int getPID(){
            Log.d(TAG, "getPID");
            return android.os.Process.myPid();
        }

        public int getTID(){
            Log.d(TAG, "getTID");
            return android.os.Process.myTid();
        }

    }

}
