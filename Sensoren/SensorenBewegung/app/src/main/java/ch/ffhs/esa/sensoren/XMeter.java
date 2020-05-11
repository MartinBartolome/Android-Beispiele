package ch.ffhs.esa.sensoren;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import java.util.List;


import ch.ffhs.esa.sensoren.SensorActivity;
import ch.ffhs.esa.sensoren.Meter;

public class XMeter extends Meter {
    int viewFields = 0;

    public XMeter(SensorActivity activity, int type, int views){
        super(activity);

        SensorManager sensorManager = (SensorManager) activity.getSystemService(android.content.Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(type);

        this.viewFields = views;

        if(sensorList.size() > 0){
            sensorManager.registerListener(this,
                    sensorManager.getDefaultSensor(type),
                    sensorManager.SENSOR_DELAY_NORMAL);
        }else{
            activity.markerNotAvailable(viewFields);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        getSensrActivity().writeValue(viewFields, event.values[0]);
    }

}
