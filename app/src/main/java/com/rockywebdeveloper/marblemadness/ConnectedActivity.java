package com.rockywebdeveloper.marblemadness;

/**
 * Used as a dummy test class for the moment.
 * Will be replaced by a mulitplayer level class later
 */

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.net.Socket;

public class ConnectedActivity extends ActionBarActivity {

    int port = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        WifiP2pInfo info = bundle.getParcelable("Info");

        if(info.isGroupOwner){
            ServerAsyncTask serverAsyncTask = new ServerAsyncTask(getApplicationContext());
            serverAsyncTask.execute(port);
        } else {
            ClientAsyncTask clientAsyncTask = new ClientAsyncTask(getApplicationContext(), info.groupOwnerAddress);
            clientAsyncTask.execute(port);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
