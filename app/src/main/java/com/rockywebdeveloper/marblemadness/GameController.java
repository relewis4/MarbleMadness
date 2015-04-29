package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Handler;
import android.os.SystemClock;

import java.util.ArrayList;

/**
 * Created by relewis on 4/28/2015.
 * Timer code is not written by me. The author os the Time code is Nikos Maravitsas
 * from http://examples.javacodegeeks.com/android/core/os/handler/android-timer-example/
 */
public class GameController {
    private ArrayList<RectF> walls;
    private Ball homeBall;
    private AwayBall awayBall;
    private float[] gameBounds;

    private Handler customHandler = new Handler();
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;
    private String timerValue;

    public  GameController(int homeBallX, int homeBallY, Context context){
        walls = new ArrayList();
        homeBall = new Ball(homeBallX, homeBallY, context);
    }

    public GameController(int homeBallX, int homeBallY, int awayBallX, int awayBallY, Context context) {
        walls = new ArrayList();
        homeBall = new Ball(homeBallX, homeBallY, context);
        awayBall = new AwayBall(awayBallX, awayBallY, context);
        //gameBounds = new float[]{circle.height(), circle.width()};
    }

    public void addWall(RectF wall) {
        walls.add(wall);
    }

    public ArrayList<RectF> getWalls() {
        return walls;
    }

    public void setHomeBallImage(Bitmap ballImage) {
        homeBall.setImage(ballImage);
    }

    public Bitmap getHomeBallImage() {
        return homeBall.getImage();
    }

    public void setAwayBallImage(Bitmap ballImage) {
        awayBall.setImage(ballImage);
    }

    public Bitmap getAwayBallImage() {
        return awayBall.getImage();
    }

    public int getHomeBallX() {
        return homeBall.getX();
       /* if (x - (newX * 50) <= 0) {
            x = 0;
            if (xROC > 1) {
                setImage();
            }
        } else if (x - (newX * 50) >= (canvasWidtht - ballWidtht)) {
            x = canvasWidtht - ballWidtht;
            if (xROC > 1) {
                setImage();
            }
        } else {
            x = (int) (x - (newX * 50));
        }*/
    }

    public int getHomeBallY() {
        return homeBall.getY();
        /*  if (y - (newY * 50) <= 0) {
            y = 0;
            if(yROC > .5){
                setImage();
            }
        } else if(y - (newY * 50) >= (canvasHeight - ballHeight)) {
            y = canvasHeight - ballHeight;
            if(yROC > .5){
                setImage();
            }
        } else {
            y = (int) (y - (newY * 50));
        }*/
    }

    public int getAwayBallX() {
        return awayBall.getX();
       /* if (x - (newX * 50) <= 0) {
            x = 0;
            if (xROC > 1) {
                setImage();
            }
        } else if (x - (newX * 50) >= (canvasWidtht - ballWidtht)) {
            x = canvasWidtht - ballWidtht;
            if (xROC > 1) {
                setImage();
            }
        } else {
            x = (int) (x - (newX * 50));
        }*/
    }

    public int getAwayBallY() {
        return awayBall.getY();
        /*  if (y - (newY * 50) <= 0) {
            y = 0;
            if(yROC > .5){
                setImage();
            }
        } else if(y - (newY * 50) >= (canvasHeight - ballHeight)) {
            y = canvasHeight - ballHeight;
            if(yROC > .5){
                setImage();
            }
        } else {
            y = (int) (y - (newY * 50));
        }*/
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

    public void onResume(){
        homeBall.onResume();
        awayBall.onResume();
    }

    public void onPause(){
        homeBall.onPause();
        awayBall.onPause();
    }
}
