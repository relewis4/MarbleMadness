package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.wifi.p2p.WifiP2pInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class TwoPlayerActivity extends Activity {

    private GameController mGameController;
    private TwoPlayerView view;
    private final int PORT = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameController = new GameController(300, 300, 900, 900,this);
        mGameController.setHomeBallImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball1));
        mGameController.setAwayBallImage(BitmapFactory.decodeResource(getResources(), R.drawable.ball2));
        view = new TwoPlayerView(this, mGameController);
        setContentView(view);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        WifiP2pInfo info = bundle.getParcelable("Info");

        if(info.isGroupOwner){
            ServerAsyncTask serverAsyncTask = new ServerAsyncTask(getApplicationContext(), mGameController);
            serverAsyncTask.execute(PORT);
        } else {
            ClientAsyncTask clientAsyncTask = new ClientAsyncTask(getApplicationContext(), info.groupOwnerAddress, mGameController);
            clientAsyncTask.execute(PORT);
        }

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
