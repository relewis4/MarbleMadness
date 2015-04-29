package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

/**
 * Created by laurenmccarthy on 4/29/15.
 */
public class AwayBall {
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
    private Context mContext;
    private Boolean broken;


    public AwayBall(int x, int y, Context context){
        mContext = context;
        broken = false;
        this.x = x;
        this.y = y;
        i = 1;
        r = 1;
    }

    public void setImage(Bitmap image) {
        ball = image;
    }

    private void setXandY(float newX, float newY) {
        y = (int) (y - (newY * 50));
        x = (int) (x - (newX * 50));
    }

    public Boolean isBroken(){
        return broken;
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

    public float getxROC() {
        return xROC;
    }

    public float getyROC() {
        return yROC;
    }

}
