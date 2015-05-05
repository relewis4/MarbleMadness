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
        setHomeBall(mGameController.getHomeBallX(), mGameController.getHomeBallY());
        canvas.drawBitmap(mGameController.getHomeBallImage(), homeBallX, homeBallY, new Paint());

        heightIndex = canvasHeight / 3;
        widthIndex = canvasWidth / 4;
        top = heightIndex * 2;
        left = 0;
        right = widthIndex * 3;
        bottom = top + 30;
        sPaint.setColor(Color.BLACK);
        canvas.drawRect(left, top, right , bottom, sPaint);

        top = heightIndex;
        left = widthIndex;
        right = canvasWidth;
        bottom = top + 30;
        canvas.drawRect(left, top, right, bottom, sPaint);

        top = heightIndex;
        left = widthIndex / 3;
        right = left + 30;
        bottom = heightIndex * 2;
        canvas.drawRect(left, top, right, bottom, sPaint);

        top = 0;
        left = widthIndex * 3;
        right = left + 30;
        bottom = heightIndex;
        canvas.drawRect(left, top, right, bottom, sPaint);

        top = heightIndex;
        left = 0;
        right = widthIndex / 3;
        bottom = top + 30;
        sPaint.setColor(Color.RED);
        canvas.drawRect(left, top, right, bottom, sPaint);

        top = 0;
        left = 0;
        right = widthIndex / 3;
        bottom = top + 30;
        sPaint.setColor(Color.RED);
        canvas.drawRect(left, top, right, bottom, sPaint);

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
}
