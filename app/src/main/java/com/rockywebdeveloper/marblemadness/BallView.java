package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;



/**
 * Created by relewis on 3/11/2015.
 */
public class BallView extends View {
    private Bitmap ball;
    private int x;
    private int y;
    public BallView(Context context) {
        super(context);
        ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball1);
        x = 0;
        y = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(x == 0 && y == 0){
            x = canvas.getWidth()/2;
            y = canvas.getHeight()/2;
        }
        canvas.drawBitmap(ball, x,y,new Paint());
    }

    public void setXandY(float newX, float newY){
        x = (int)(x + (newX*50));
        y = (int)(y - (newY*50));
        invalidate();
    }
}
