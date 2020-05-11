package ch.ffhs.esa.sensoren;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import android.hardware.SensorEventListener;

public abstract class Meter implements SensorEventListener {
    private SensorActivity activity;

    public Meter(SensorActivity activity){
        this.activity = activity;
    }

    protected SensorActivity getSensrActivity(){
        return this.activity;
    }

    @Override
    public abstract void onSensorChanged(SensorEvent event);

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

}

