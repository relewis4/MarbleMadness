package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

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
        setHomeBall(mGameController.getHomeBallX(), mGameController.getHomeBallY());
        canvas.drawBitmap(mGameController.getHomeBallImage(), homeBallX, homeBallY, new Paint());
        top = 510;
        left = 0;
        right = 980;
        bottom = 540;
        sPaint.setColor(Color.BLACK);
        canvas.drawRect(left, top, right , bottom, sPaint);

        top = 250;
        left = 400;
        right = 1200;
        bottom = 280;
        canvas.drawRect(left, top, right, bottom, sPaint);

        top = 250;
        left = 100;
        right = 120;
        bottom = 510;
        canvas.drawRect(left, top, right, bottom, sPaint);

        top = 0;
        left = 900;
        right = 920;
        bottom = 250;
        canvas.drawRect(left, top, right, bottom, sPaint);

        top = 250;
        left = 0;
        right = 120;
        bottom = 280;
        sPaint.setColor(Color.RED);
        canvas.drawRect(left, top, right, bottom, sPaint);

        invalidate();

    }

    private void setHomeBall(int x, int y) {
        homeBallX = x;
        homeBallY = y;
    }
}
