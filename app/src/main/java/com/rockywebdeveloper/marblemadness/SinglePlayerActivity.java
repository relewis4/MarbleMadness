package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class SinglePlayerActivity extends Activity{
    private GameController mGameController;
    private SinglePlayerView view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameController = new GameController(300, 300, this);
        mGameController.setHomeBallImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball1));
        view = new SinglePlayerView(this, mGameController);
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
