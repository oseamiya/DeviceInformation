package com.oseamiya.deviceinformation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.List;

public class SensorInformation {
    private final Context context;

    public SensorInformation(Context context){
        this.context = context;
    }

    /**
     * It will return all the sensors list available in the device.
     * TO get power/version/resolution/vendor etc  of these sensors,
     * use for-each to get sensor one by one and use other methods
     * @return all sensors in List<Sensor>
     */
    public List<Sensor> getSensorList(){
        SensorManager sensorManager = (SensorManager) this.context.getSystemService(Context.SENSOR_SERVICE);
        return sensorManager.getSensorList(Sensor.TYPE_ALL);
    }

    /**
     * It will return the total number of sensors in the device
     * @return sensor's counts in int
     */
    public int getTotalNumberOfSensors(){
        return getSensorList().size();
    }

    /**
     * It will return the sensor's vendor
     * @param sensor
     * @return sensor's vendor in String
     */
    public String getSensorVendor(Sensor sensor){
        return sensor.getVendor();
    }

    /**
     * It will return the sensor's version
     * @param sensor
     * @return sensor's version in the int
     */
    public int getSensorVersion(Sensor sensor){
        return sensor.getVersion();
    }

    /**
     * It will return the sensor's resolution
     * @param sensor
     * @return sensor's resolution in float
     */
    public float getSensorResolution(Sensor sensor){
        return sensor.getResolution();
    }

    /**
     * It will return the sensor's power
     * @param sensor
     * @return sensor's power in float( in mAh)
     */
    public float getSensorPower(Sensor sensor){
        return sensor.getPower();
    }

    /**
     * It will return the sensor's maximum range in float
     * @param sensor
     * @return sensor's maximum range in float
     */
    public float getSensorMaximumRange(Sensor sensor){
        return sensor.getMaximumRange();
    }

    /**
     * It will return name of sensor from sensor
     * I've used switch-case and compared with sensor type to give String name
     * @param sensor
     * @return Sensor name in String
     */
    public String getSensorName(Sensor sensor){
        int sensorType = sensor.getType();
        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                return "Accelerometer";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "Ambient Temperature";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "Game Rotation Vector";
            case Sensor.TYPE_GRAVITY:
                return "Gravity";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "Geomagnetic Rotation Vector";
            case Sensor.TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor.TYPE_HEART_BEAT:
                return "Heart Beat";
            case Sensor.TYPE_HEART_RATE:
                return "Heart Rate";
            case Sensor.TYPE_LIGHT:
                return "Light";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Linear Acceleration";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Magnetic Field";
            case Sensor.TYPE_PRESSURE:
                return "Pressure";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Humidity";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Rotation Vector";
            case Sensor.TYPE_STEP_COUNTER:
                return "Step Counter";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "Significant Motion";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Step Detector";
            default:
                return "Unknown";
        }
    }
}
