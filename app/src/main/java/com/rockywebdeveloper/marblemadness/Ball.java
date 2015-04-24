package com.rockywebdeveloper.marblemadness;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by relewis on 4/7/2015.
 */
public class Ball implements SensorEventListener {
    private Bitmap ball;
    private int x;
    private int y;
    private int i;
    private int r;
    private float oldX;
    private float oldY;
    private float xROC;
    private float yROC;
    private int ballHeight;
    private int ballWidtht;
    private int canvasHeight;
    private int canvasWidtht;
    private SensorManager mSensorManager;
    private Sensor mMagSensor;
    private Sensor mAccelSensor;
    private float[] mGravity;
    private float[] mGeomagnetic;
    private Boolean x_block = false;
    private Boolean y_block = false;
    private Boolean firstTime = true;
    private Context mContext;
    private Boolean broken;
    private RectF mRectF;

    public Ball(int x, int y, Context context){
        ball = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball1);
        mContext = context;
        ballHeight = ball.getHeight();
        ballWidtht = ball.getWidth();
        broken = false;
        this.x = x;
        this.y = y;
        i = 1;
        r = 1;
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void setXandY(float newX, float newY) {
        if (y - (newY * 50) <= 0) {
            y = 0;
            if(yROC > .5){
                setImage();
            }
        } else if(y - (newY * 50) >= (canvasHeight - ballHeight)) {
           y = canvasHeight - ballHeight;
            if(yROC > .5){
                setImage();
            }
        } else if(onWall(x+ballWidtht - (newX*50),y-ballHeight - (newY*50))){

        } else {
            y = (int) (y - (newY * 50));
        }
        if (x - (newX * 50) <= 0) {
            x = 0;
            if (xROC > 1) {
                setImage();
            }
        } else if (x - (newX * 50) >= (canvasWidtht - ballWidtht)) {
            x = canvasWidtht - ballWidtht;
            if (xROC > 1) {
                setImage();
            }
        } else if(onWall(x+ballWidtht - (newX*50),y+ballHeight - (newY*50))){

        }else {
            x = (int) (x - (newX * 50));
        }
    }
    public Boolean isBroken(){
        return broken;
    }
    private void setImage(){
        ball = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.brokenball1);
        broken = true;
    }
    public Bitmap getImage(){
        return ball;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getBallHeight(){
        return ballHeight;
    }
    public int getBallWidtht(){
        return ballWidtht;
    }
    public void setBounds(int width, int height, RectF rec){
        canvasWidtht = width;
        canvasHeight = height;
        mRectF = rec;
    }

    public boolean onWall(float x, float y){
        return mRectF.contains(x,y);
    }
    public float getXROC (){
        return xROC;
    }
    public float getYROC (){
        return yROC;
    }

    @Override
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
                float axisX = orientation[1];
                float axisY = orientation[2];
                if(firstTime){
                    oldX = axisX;
                    oldY = axisY;
                    firstTime = false;
                }
                if (i == 0){
                    xROC = (axisX - oldX) * 10;
                    yROC = (axisY - oldY) * 10;
                    oldX = axisX;
                    oldY = axisY;
                }
                r++;
                i = r%20;
                setXandY(axisX, axisY);
            }
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onResume(){
        mSensorManager.registerListener(this, mAccelSensor, mSensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagSensor, mSensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause(){
        mSensorManager.unregisterListener(this,mAccelSensor);
        mSensorManager.unregisterListener(this,mMagSensor);
    }
}
