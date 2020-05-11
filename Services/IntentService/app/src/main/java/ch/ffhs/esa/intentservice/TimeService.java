package ch.ffhs.esa.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class TimeService extends IntentService {
    final static public String TAG = "INTENT_SERVICE";
    public static final String TIMER_AKTION = "INTENT_SERVICE.TIMER_AKTION";
    final static private int MAX_COUNTER = 10;
    private int procId = 0;
    private int threadId = 0;
    private int counter = 0;

    public TimeService() {
        super("TimeService");
        threadId = android.os.Process.myTid();
        procId = android.os.Process.myPid();
        Log.d(TAG, "TimeService: " + android.os.Process.myTid() + ", " + android.os.Process.myTid());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while(counter < MAX_COUNTER){
            Intent sendIntent = new Intent(TIMER_AKTION);
            String theTime    = "Time :    " + System.currentTimeMillis();
            String theCounter = "Counter = " + Integer.toString(counter);
            String thePID     = "PID :     " + android.os.Process.myTid();
            String theTID     = "TID :     " + android.os.Process.myTid();
            counter++;
            sendIntent.putExtra("TIME", theTime);
            sendIntent.putExtra("COUNTER", theCounter);
            sendIntent.putExtra("PID", thePID);
            sendIntent.putExtra("TID", theTID);
            sendIntent.putExtra("STATE", true);
            Log.d(TAG, "onHandleIntent .. " + android.os.Process.myPid() + ", " + android.os.Process.myTid() + " -> "+ theCounter);
            try {
                sendBroadcast(sendIntent);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Intent sendIntent = new Intent(TIMER_AKTION);
        sendIntent.putExtra("STATE", false);
        sendBroadcast(sendIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate .. " + android.os.Process.myTid() + ", " + android.os.Process.myTid() + " -> "+ counter);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind .. " + android.os.Process.myTid() + ", " + android.os.Process.myTid() + " -> "+ counter);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand .. " + android.os.Process.myTid() + ", " + android.os.Process.myTid() + " -> "+ counter);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind .. " + android.os.Process.myTid() + ", " + android.os.Process.myTid() + " -> "+ counter);
        counter = MAX_COUNTER;
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        counter = MAX_COUNTER;
        Log.d(TAG, "onDestroy .. " + android.os.Process.myTid() + ", " + android.os.Process.myTid() + " -> "+ counter);
    }

}
