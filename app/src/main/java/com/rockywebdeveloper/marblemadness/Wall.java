package com.rockywebdeveloper.marblemadness;

import android.graphics.RectF;

/**
 * Created by relewis on 4/13/2015.
 */
public class Wall extends RectF {
    public float mBottom;
    public float mLeft;
    public float mRight;
    public float mTop;

    public Wall(float left, float top, float right, float bottom){
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
    }

}
