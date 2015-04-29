package com.rockywebdeveloper.marblemadness;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.BitmapFactory;
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
    private GameController mGameController;
    private BallView view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameController = new GameController(300, 300, this);
        mGameController.setHomeBallImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball1));
        view = new BallView(this, mGameController);
        setContentView(view);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onResume(){
        super.onResume();
        mGameController.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mGameController.onPause();
    }

}
