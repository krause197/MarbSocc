package com.epicodus.krause197.marbsocc;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;

/**
 * Created by Guest on 12/22/16.
 */
public class Accelerometer implements SensorEventListener {
    public static float X, Y, Z;
    private SensorManager manager;
    private Sensor sensor;
    private boolean success;
    private int screenRotation;
    private WindowManager windowManager;

    private static final int[][] AXIS = {
            {1, -1, 0, 1}, {-1, -1, 1, 0}, {-1, 1, 0, 1}, {1, 1, 1, 0}
    };

    public Accelerometer() {
        success = false;
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    public void onSensorChanged(SensorEvent e) {
        screenRotation = windowManager.getDefaultDisplay().getRotation();
        if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            final int[] axis = AXIS[screenRotation];
            X = (float) (axis[0]) * e.values[axis[2]];
            Y = (float) (axis[1]) * e.values[axis[3]];
            Z = e.values[2];
        }
    }

    public void resume(MainActivity parent){
        windowManager = (WindowManager) parent.getSystemService(Activity.WINDOW_SERVICE);
        manager = (SensorManager) parent.getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() > 0) sensor = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        if (!(success = manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)))
            throw new RuntimeException("Could not register sensorEventListener");
    }

    public void pause(){
        if (success) manager.unregisterListener(this, sensor);
    }


}
