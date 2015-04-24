package com.rockywebdeveloper.marblemadness;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


public class GamesPlayActivity extends Activity{
    private BallView mBallView;
    private Ball mBall;
    private RectF rec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBall = new Ball(100,100,this);
        rec = new RectF(1000,400,1100,1000);
        mBallView = new BallView(GamesPlayActivity.this,mBall,rec);
        setContentView(mBallView);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onResume(){
        super.onResume();
        mBall.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mBall.onPause();
    }

}
