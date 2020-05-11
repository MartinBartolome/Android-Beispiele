package ch.ffhs.esa.sensoren;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class SensorActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    //Sensor-Indices
    public final static int VALUE_X = 0;
    public final static int VALUE_Y = 1;
    public final static int VALUE_Z = 2;

    private HashMap<Integer, Meter> sensors = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        SensorManager sensorManager = (SensorManager) getSystemService(android.content.Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);


        for(Sensor s: sensorList){
            Log.d("SENSOREN", s.getName() + "( " + s.getType() + ")");
        }

        createSensors();
    }



    private void createSensors(){
        sensors = new HashMap<Integer, Meter>();
        sensors.put(Sensor.TYPE_ACCELEROMETER,
                new XYZMeter(this,
                        Sensor.TYPE_ACCELEROMETER,
                        new int[] {R.id.tx_hardware_sensoren_accel_x,
                                R.id.tx_hardware_sensoren_accel_y,
                                R.id.tx_hardware_sensoren_accel_z}));

        sensors.put(Sensor.TYPE_ORIENTATION,
                new XYZMeter(this,
                        Sensor.TYPE_ORIENTATION,
                        new int[] {R.id.tx_hardware_sensoren_gravity_x,
                                R.id.tx_hardware_sensoren_gravity_y,
                                R.id.tx_hardware_sensoren_gravity_z}));


        sensors.put(Sensor.TYPE_GYROSCOPE,
                new XYZMeter(this,
                        Sensor.TYPE_GYROSCOPE,
                        new int[] {R.id.tx_hardware_sensoren_rotation_x,
                                R.id.tx_hardware_sensoren_rotation_y,
                                R.id.tx_hardware_sensoren_rotation_z}));

        sensors.put(Sensor.TYPE_MAGNETIC_FIELD,
                new XYZMeter(this,
                        Sensor.TYPE_MAGNETIC_FIELD,
                        new int[] {R.id.tx_hardware_sensoren_magnet_x,
                                R.id.tx_hardware_sensoren_magnet_y,
                                R.id.tx_hardware_sensoren_magnet_z}));


        sensors.put(Sensor.TYPE_LIGHT, new XMeter(this, Sensor.TYPE_LIGHT, R.id.tx_hardware_sensoren_light));
        sensors.put(Sensor.TYPE_TEMPERATURE, new XMeter(this, Sensor.TYPE_TEMPERATURE, R.id.tx_hardware_sensoren_tempertature));
        sensors.put(Sensor.TYPE_PROXIMITY, new XMeter(this, Sensor.TYPE_PROXIMITY, R.id.tx_hardware_sensoren_proximity));
    }



    public void markerNotAvailable(int[] ids){
        for(int i = ids.length; --i >= 0;){
            markerNotAvailable(ids[i]);
        }
    }

    public void markerNotAvailable(int id){
        TextView textView = (TextView)(findViewById(id));
        textView .setText(R.string.txt_hardware_sensors_na);
    }


    public void writeValue(int[] ids, float[] values){
        for(int i = ids.length; --i >= 0;){
            writeValue(ids[i], values[i]);
        }
    }

    public void writeValue(int id, float value){
        TextView textView = (TextView)(findViewById(id));
        textView .setText("" + value);
    }


}
