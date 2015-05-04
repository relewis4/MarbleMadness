package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;



/**
 * Created by relewis on 3/11/2015.
 */
public class BallView extends View {

    private GameController mGameController;
    private static int canvasWidth;
    private static int canvasHeight;
    private int homeBallX;
    private int homeBallY;
    private int awayBallX;
    private int awayBallY;

    public BallView(Context context, GameController gameController) {
        super(context);
        mGameController = gameController;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        setHomeBall(mGameController.getHomeBallX(), mGameController.getHomeBallY());
        setAwayBall(mGameController.getAwayBallX(), mGameController.getAwayBallY());
        canvas.drawBitmap(mGameController.getHomeBallImage(), homeBallX, homeBallY, new Paint());
        canvas.drawBitmap(mGameController.getAwayBallImage(), awayBallX, awayBallY, new Paint());
        invalidate();
    }

    public float[] getBounds() {
        float[] bounds = new float[]{canvasHeight, canvasWidth};
        return bounds;
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
