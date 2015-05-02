package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by stephanieadams on 4/29/15.
 */
public class TwoPlayerView extends View {

    private GameController mGameController;
    private static int canvasWidth;
    private static int canvasHeight;
    private float top;
    private float bottom;
    private float left;
    private float right;
    private int homeBallX;
    private int homeBallY;
    private int awayBallX;
    private int awayBallY;

    public TwoPlayerView(Context context, GameController gameController) {
        super(context);
        mGameController = gameController;

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint sPaint = new Paint();
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        top = 0;
        bottom = canvasHeight;
        left = canvasWidth/4;
        right = (canvasWidth/2) +left;
        sPaint.setColor(Color.WHITE);
        canvas.drawRect(left, top, right, bottom, sPaint);
        mGameController.setBounds(canvasHeight,canvasWidth);
        mGameController.setGameBounds(top,bottom,left,right);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        setHomeBall(mGameController.getHomeBallX(), mGameController.getHomeBallY());
        setAwayBall(mGameController.getAwayBallX(), mGameController.getAwayBallY());
        canvas.drawBitmap(mGameController.getHomeBallImage(), homeBallX, homeBallY, new Paint());
        canvas.drawBitmap(mGameController.getAwayBallImage(), awayBallX, awayBallY, new Paint());

        invalidate();
    }

    private void setHomeBall(int x, int y) {
        homeBallX = x;
        homeBallY = y;
    }

    private void setAwayBall(int x, int y) {
        awayBallX = x;
        awayBallY = y;
    }
}