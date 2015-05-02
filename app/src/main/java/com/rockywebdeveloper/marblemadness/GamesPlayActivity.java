package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class GamesPlayActivity extends Activity{
    private GameController mGameController;
    private BallView view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameController = new GameController(300, 300, 900, 900,this);
        mGameController.setHomeBallImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball1));
        mGameController.setAwayBallImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball2));
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
