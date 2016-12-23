package com.epicodus.krause197.marbsocc;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public Accelerometer accelerometer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MainGamePanel(this));
        accelerometer = new Accelerometer();
//        accelerometer.resume(this);
        Log.d(TAG, "View Added");
    }

    @Override
    protected void  onDestroy() {
        Log.d(TAG, "Destroying ....");
        super.onDestroy();;
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopping ... ");
        super.onStop();
    }
}
