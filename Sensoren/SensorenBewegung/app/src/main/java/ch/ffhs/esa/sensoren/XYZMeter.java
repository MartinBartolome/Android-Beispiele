package ch.ffhs.esa.sensoren;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import java.util.List;

import ch.ffhs.esa.sensoren.SensorActivity;
import ch.ffhs.esa.sensoren.Meter;

public class XYZMeter extends Meter {
    int [] viewFields = null;

    public XYZMeter(SensorActivity activity, int type, int [] views){
        super(activity);

        SensorManager sensorManager = (SensorManager) activity.getSystemService(android.content.Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(type);
        viewFields = new int[views.length];
        for(int i = 0; i < views.length; i++){
            this.viewFields[i] = views[i];
        }

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
        getSensrActivity().writeValue(viewFields, new float[]{ event.values[SensorActivity.VALUE_X],
                event.values[SensorActivity.VALUE_Y],
                event.values[SensorActivity.VALUE_Z]});
    }

}
