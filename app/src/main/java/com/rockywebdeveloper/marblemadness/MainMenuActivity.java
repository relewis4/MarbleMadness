package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
    }

    public void handleSinglePlayerClick(View v){
        startActivity(new Intent(this, SinglePlayerActivity.class));
    }

    public void handleMultiplayerClick(View v){
        startActivity(new Intent(this, PeerListActivity.class));
    }

    public void handleHighScoresClick(View v){
        //insert code to start high scores activity
    }
}
