package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.wifi.p2p.WifiP2pInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/*
 *    ConnectedActivity.java created by Andrew Schaefer on 3/1/15.
 *    Modified into TwoPlayerActivity by Zach Nelson.
 */
public class TwoPlayerActivity extends WifiP2pActivity {

    private GameController mGameController;
    private TwoPlayerView view;
    private final int PORT_1 = 49502;
    private final int PORT_2 = 49501;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameController = new GameController(300, 300, 900, 900, BitmapFactory.decodeResource(getResources(), R.drawable.ball1), BitmapFactory.decodeResource(getResources(), R.drawable.ball2),this);
        view = new TwoPlayerView(this, mGameController);
        setContentView(view);
        mGameController.setGameInProgress(true);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        WifiP2pInfo info = bundle.getParcelable("Info");

        if(info.isGroupOwner){
            ServerAsyncTask serverAsyncTask = new ServerAsyncTask(getApplicationContext(), mGameController);
            serverAsyncTask.execute(PORT_1);
            //ClientAsyncTask clientAsyncTask = new ClientAsyncTask(getApplicationContext(), info.groupOwnerAddress, mGameController);
            //clientAsyncTask.execute(PORT_2);
        } else {
            //ServerAsyncTask serverAsyncTask = new ServerAsyncTask(getApplicationContext(), mGameController);
            //serverAsyncTask.execute(PORT_2);
            ClientAsyncTask clientAsyncTask = new ClientAsyncTask(getApplicationContext(), info.groupOwnerAddress, mGameController);
            clientAsyncTask.execute(PORT_1);
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
