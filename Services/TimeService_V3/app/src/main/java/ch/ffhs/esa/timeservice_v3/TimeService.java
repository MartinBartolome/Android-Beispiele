package ch.ffhs.esa.timeservice_v3;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    public static final String TAG = "TimeService";
    private Timer myTimer = null;
    private int counter = 0;

    private Messenger activityMessenger = null;



    /** inner class implements the broadcast timer*/
    private class TimeServiceTimerTask extends TimerTask {
        private static final String TAG = TimeService.TAG + "::TimerTask";
        public void run() {
            counter++;
            Log.d(TAG, "counter = " + counter);
            Message msg = new Message();
            Bundle bundle = new Bundle();

            bundle.putLong("TIME", System.currentTimeMillis());
            bundle.putInt("COUNTER", counter);
            bundle.putInt("PID", android.os.Process.myPid());
            bundle.putInt("TID", android.os.Process.myTid());
            msg.setData(bundle);
            if(activityMessenger != null){

                try {
                    activityMessenger.send(msg);
                    Log.d(TAG, "send(" + msg + ")");
                } catch (RemoteException e) {
                    Log.d(TAG, "Send exception");
                }

            }else{
                Log.d(TAG, "No Messenger!");
            }
        }
    };


    public TimeService() {
        Log.d(TAG, "TimeService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        myTimer = new Timer("myTimer");
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        myTimer.scheduleAtFixedRate( new TimeServiceTimerTask(), 0, 1000);
        activityMessenger = intent.getParcelableExtra(ServiceStarter.MESSENGER);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activityMessenger = null;
        myTimer.cancel();
        myTimer = null;
        Log.d(TAG, "onDestroy");
    }

}
