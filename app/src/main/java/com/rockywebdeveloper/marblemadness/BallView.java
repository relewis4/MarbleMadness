package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;



/**
 * Created by relewis on 3/11/2015.
 */
public class BallView extends View {

    private static int canvasWidth;
    private static int canvasHeight;
    private Ball mBall;
    public BallView(Context context, Ball ball) {
        super(context);
        mBall = ball;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        mBall.setBounds(canvasWidth, canvasHeight);
        int x = mBall.getX();
        int y = mBall.getY();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawText("X rate of change: " + mBall.getXROC() + "\nY rate of change: " + mBall.getYROC(), 10, 10, paint);
        canvas.drawBitmap(mBall.getImage(), x, y, new Paint());
        if(!mBall.isBroken()) {
            invalidate();
        }
    }

}
