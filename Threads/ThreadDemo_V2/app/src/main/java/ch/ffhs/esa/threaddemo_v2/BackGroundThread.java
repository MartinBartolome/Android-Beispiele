package ch.ffhs.esa.threaddemo_v2;

import android.os.Handler;

import java.util.Random;


public class BackGroundThread extends Thread {
    private Handler handler = null;
    private int counter = 0;
    private boolean started = false;
    private Runnable show = null;

    public BackGroundThread(Runnable show, Handler handler){
        this.handler = handler;
        this.show = show;
        this.started = true;
    }

    public boolean setStarted(boolean started){
        this.started = started;
        return this.started;
    }

    public boolean getStarted(){
        return this.started;
    }
    public int getCounter(){
        return this.counter;
    }

    public void run() {
        Random randomizer = new Random();
        while (started){
            if (randomizer.nextInt() % 10000000 == 0) {
                counter++;
                handler.post(show);
            }
        }
    }

}
