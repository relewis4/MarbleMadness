package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;

import java.util.ArrayList;

/**
 * Created by relewis on 4/28/2015.
 * Timer code is not written by me. The author os the Time code is Nikos Maravitsas
 * from http://examples.javacodegeeks.com/android/core/os/handler/android-timer-example/
 */
public class GameController implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mMagSensor;
    private Sensor mAccelSensor;
    private int x;
    private int y;
    private int i;
    private int r;
    private float[] mGravity;
    private float[] mGeomagnetic;
    private Boolean firstTime = true;
    private float oldX;
    private float oldY;
    private float xROC;
    private float yROC;
    private ArrayList<RectF> walls;
    private Ball homeBall;
    private Ball awayBall;
    private float[] gameBounds;
    private float mCanvasHeight;
    private float mCanvasWidth;

    private Handler customHandler = new Handler();
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;
    private String timerValue;
    private boolean gameInProgress;
    private boolean twoPlayerGame;
    private boolean intersecting;

    public  GameController(int homeBallX, int homeBallY, Bitmap image, Context context){
        walls = new ArrayList();
        homeBall = new Ball(homeBallX, homeBallY, image);
        initalizeSensors(homeBallX, homeBallY, context);
        twoPlayerGame = false;
    }

    public GameController(int homeBallX, int homeBallY, int awayBallX, int awayBallY, Bitmap image1, Bitmap image2, Context context) {
        walls = new ArrayList();
        homeBall = new Ball(homeBallX, homeBallY, image1);
        awayBall = new Ball(awayBallX, awayBallY, image2);
        initalizeSensors(homeBallX, homeBallY, context);
        twoPlayerGame = true;
        intersecting = false;
    }
    private void initalizeSensors(int x, int y, Context context){
        this.x = x;
        this.y = y;
        i = 1;
        r = 1;
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }
    public void addWall(RectF wall) {
        walls.add(wall);
    }

    public ArrayList<RectF> getWalls() {
        return walls;
    }

    public Bitmap getHomeBallImage() {
        return homeBall.getImage();
    }

    public Bitmap getAwayBallImage() {
        return awayBall.getImage();
    }

    public void setBounds(float canvasHeight, float canvasWidth) {
        mCanvasHeight = canvasHeight;
        mCanvasWidth = canvasWidth;
    }

    public void setGameBounds(float top, float bottom, float left, float right){
        gameBounds = new float[]{top,bottom,left,right};
    }

    public int getHomeBallX() {
        return homeBall.getX();
    }

    public int getHomeBallY() {
        return homeBall.getY();
    }

    public int getAwayBallX() {
        return awayBall.getX();
    }

    public int getAwayBallY() {
        return awayBall.getY();
    }

    public void startTimer() {
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);

    }

    public void stopTimer() {
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    public String getTimer() {
        return timerValue;
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue = "" + mins + ":"
                            + String.format("%02d", secs) + ":"
                            + String.format("%03d", milliseconds);
            customHandler.postDelayed(this, 0);
        }
    };
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

    public void setAwayBallXandY(int newX, int newY){
        awayBall.setXandY(newX, newY);
    }

    private void setXandY(float newX, float newY) {
        int tempY = (int) (y - (newY * 50));
        int tempX = (int) (x - (newX * 50));
        if (tempY <= 0) {
            y = 0;
            //if(yROC > .5){
            //setImage();
            //}
        } else if(tempY  >= (mCanvasHeight - homeBall.getBallHeight())) {
            y = (int) mCanvasHeight - homeBall.getBallHeight();
            //if(yROC > .5){
            //setImage();
            //}
        } else {
            y = tempY;
        }
        if (tempX <= 0) {
            x = 0;
            //if (xROC > 1) {
            //setImage();
            //}
        } else if (tempX >= (mCanvasWidth - homeBall.getBallWidth())) {
            x = (int) mCanvasWidth - homeBall.getBallWidth();
            //if (xROC > 1) {
            //setImage();
            //}
        } else {
            x = tempX;
        }
        homeBall.setXandY(x,y);
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

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }
}
