package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;


public class GamesPlayActivity extends Activity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mMagSensor;
    private Sensor mAccelSensor;
    private float[] mGravity;
    private float[] mGeomagnetic;
    private BallView mBallView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBallView = new BallView(GamesPlayActivity.this);
        setContentView(mBallView);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float axisX = orientation[2];
                float axisY = orientation[1];
                mBallView.setXandY(axisX, axisY);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAccelSensor, mSensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagSensor, mSensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this,mAccelSensor);
        mSensorManager.unregisterListener(this,mMagSensor);
    }

}
