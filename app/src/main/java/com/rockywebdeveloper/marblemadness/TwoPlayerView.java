package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by stephanieadams on 4/29/15.
 */
public class TwoPlayerView extends View{
    private static int canvasWidth;
    private static int canvasHeight;
    private float top;
    private float bottom;
    private float left;
    private float right;
    Paint paint = new Paint();


    public TwoPlayerView(Context context) {
        super(context);
        

    }

    @Override
    public void onDraw(Canvas canvas) {
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        top = 0;
        bottom = canvasHeight;
        left = canvasWidth/4;
        right = (canvasWidth/2) +left;

        paint.setColor(Color.WHITE);
        canvas.drawRect(left, top, right, bottom, paint);


    }


}
