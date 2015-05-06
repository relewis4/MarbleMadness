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
import android.support.v7.internal.app.WindowDecorActionBar;

/**
 * Created by relewis on 4/7/2015.
 */
public class Ball {
    private Bitmap ball;
    private int ballHeight;
    private int ballWidth;
    private int xCoord;
    private int yCoord;


    private Boolean broken;


    public Ball(int x, int y, Bitmap image){
        xCoord = x;
        yCoord = y;
        ball = image;
        setBallHeight(ball.getHeight());
        setBallWidth(ball.getWidth());
        broken = false;

    }

    public Boolean isBroken(){
        return broken;
    }

    public Bitmap getImage(){
        return ball;
    }

    public void setXandY(int x, int y){
        xCoord = x;
        yCoord = y;
    }

    public int getX(){
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public int getBallHeight(){
        return ballHeight;
    }

    public int getBallWidth(){
        return ballWidth;
    }

    public void setBallHeight(int ballHeight) {
        this.ballHeight = ballHeight;
    }

    public void setBallWidth(int ballWidth) {
        this.ballWidth = ballWidth;
    }

}
