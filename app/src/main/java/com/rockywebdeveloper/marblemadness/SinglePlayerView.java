package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.Timer;

/**
 * Created by stephanieadams on 4/29/15.
 */
public class SinglePlayerView extends View {
    private GameController mGameController;
    private int homeBallX;
    private int homeBallY;
    private static int canvasWidth;
    private static int canvasHeight;
    private float top;
    private float bottom;
    private float left;
    private float right;
    private float heightIndex;
    private float widthIndex;


    public SinglePlayerView(Context context, GameController gameController) {
        super(context);
        mGameController = gameController;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint sPaint = new Paint();
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        mGameController.setBounds(canvasHeight,canvasWidth);
        String start = "START!";
        heightIndex = canvasHeight / 3;
        widthIndex = canvasWidth / 4;

        //horizontal bottom line
        top = heightIndex * 2;
        left = widthIndex + heightIndex;
        right = widthIndex * 3;
        bottom = top + 30;
        sPaint.setColor(Color.BLACK);
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        //horizontal top line left
        top = heightIndex;
        left = widthIndex / 3;
        right = widthIndex * 2;
        bottom = top + 30;
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        //horizontal top line right
        top = heightIndex;
        left = widthIndex * 2 + top;
        right = widthIndex * 3 + top /2;
        bottom = top + 30;
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        //vertical line first
        top = heightIndex;
        left = widthIndex / 3 + top /2;
        right = left + 30;
        bottom = heightIndex * 2 +30;
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        //vertical line top
        top = 0;
        left = widthIndex * 3;
        right = left + 30;
        bottom = heightIndex;
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        //vertical line rightmost
        top = heightIndex * 2;
        left = widthIndex * 3;
        right = left + 30;
        bottom = canvasHeight;
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        //finish line bottom
        top = heightIndex;
        left = 0;
        right = widthIndex / 3;
        bottom = top + 30;
        sPaint.setColor(Color.RED);
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        //finish line top
        top = 0;
        left = 0;
        right = widthIndex / 3;
        bottom = top + 30;
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.addWall(new RectF(left, top, right, bottom));

        setHomeBall(mGameController.getHomeBallX(), mGameController.getHomeBallY());
        canvas.drawBitmap(mGameController.getHomeBallImage(), homeBallX, homeBallY, new Paint());
            
            float startX = 150;
            float startY = 500;
            sPaint.setColor(Color.BLUE);
            sPaint.setTextSize(300);
            mGameController.startTimer();
            canvas.drawText(mGameController.getTimer(), startX, startY, sPaint);


        invalidate();
    }

    private void setHomeBall(int x, int y) {
        homeBallX = x;
        homeBallY = y;
    }



}
