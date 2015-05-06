package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Handler;



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
    private float mCanvasHeight;
    private float mCanvasWidth;
    private Context mContext;
    private int minutes;
    private int seconds;

    private Handler customHandler = new Handler();
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;
    private String timerValue;
    private boolean gameInProgress;
    private boolean twoPlayerGame;

    public  GameController(int homeBallX, int homeBallY, Context context){
        mContext = context;
        walls = new ArrayList();
        homeBall = new Ball(homeBallX, homeBallY, context);
        twoPlayerGame = false;
    }

    public GameController(int homeBallX, int homeBallY, int awayBallX, int awayBallY, Context context) {
       mContext = context;
        walls = new ArrayList();
        homeBall = new Ball(homeBallX, homeBallY, context);
        awayBall = new AwayBall(awayBallX, awayBallY, context);
        twoPlayerGame = true;
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

    public void setBounds(float canvasHeight, float canvasWidth) {
        mCanvasHeight = canvasHeight;
        mCanvasWidth = canvasWidth;
    }

    public void setGameBounds(float top, float bottom, float left, float right){
        gameBounds = new float[]{top,bottom,left,right};
    }

    public int getHomeBallX() {
        int x = homeBall.getX();
        if (x <= 0) {
            x = 0;
            //if (xROC > 1) {
                //setImage();
            //}
        } else if (x >= (mCanvasWidth - homeBall.getBallWidth())) {
            x = (int) mCanvasWidth - homeBall.getBallWidth();
            //if (xROC > 1) {
                //setImage();
            //}
        }

        return x;
    }

    public int getHomeBallY() {
        int y = homeBall.getY();
          if (y <= 0) {
            y = 0;
            //if(yROC > .5){
                //setImage();
            //}
        } else if(y  >= (mCanvasHeight - homeBall.getBallHeight())) {
            y = (int) mCanvasHeight - homeBall.getBallHeight();
            //if(yROC > .5){
                //setImage();
            //}
        }
        return y;
    }

    public int getAwayBallX() {
        int x = awayBall.getX();
        if (x <= 0) {
            x = 0;
            //if (xROC > 1) {
                //setImage();
            //}
        } else if (x  >= (mCanvasWidth - awayBall.getBallWidtht())) {
            x = (int) mCanvasWidth - awayBall.getBallWidtht();
            //if (xROC > 1) {
                //setImage();
            //}
        }
        return x;
    }

    public int getAwayBallY() {
        int y = awayBall.getY();
        if (y <= 0) {
            y = 0;
            //if(yROC > .5){
                //setImage();
            //}
        } else if(y >= (mCanvasHeight - awayBall.getBallHeight())) {
            y = (int) mCanvasHeight - awayBall.getBallHeight();
            //if (yROC > .5) {
                //setImage();
            //}
        }
        return y;
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
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
            timeInMilliseconds = System.currentTimeMillis() - startTime;

            int secs = (int) timeInMilliseconds / 1000;
            int mins = secs / 60;
            secs = secs % 60;
            setUiTimer(mins, secs);
            customHandler.post(new Runnable() {
                @Override
                public void run() {
                    timerValue = uiTimer();
                }
            });

            customHandler.postDelayed(this, 0);
        }
    };

    private void setUiTimer(int mins, int secs){
        minutes = mins;
        seconds = secs;
    }

    private String uiTimer(){
        return timerValue = String.format("%d:%02d", minutes, seconds);
    }

    public void onResume(){
        homeBall.onResume();
        if(twoPlayerGame) {
            awayBall.onResume();
        }
    }

    public void onPause(){
        homeBall.onPause();
        if(twoPlayerGame) {
            awayBall.onPause();
        }
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }
}
