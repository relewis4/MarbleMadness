package com.rockywebdeveloper.marblemadness;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*
 *    MainActivity.java created by Andrew Schaefer on 3/1/15.
 *    Modified into PeerListActivity.java by Zach Nelson.
 */
public class PeerListActivity extends WifiP2pActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_peer);
    }

    @Override
    protected void setWifiP2pEnabled(){
        super.setWifiP2pEnabled();
        discoverPeers();
    }

    @Override
    protected void setConnected() {
        super.setConnected();
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        super.onConnectionInfoAvailable(info);

        if(info.groupFormed) {

            /**
             * change ConnectedActivity.class to Stephanie's multiplayer level class and add the following to her onCreate:
             * Intent intent = this.getIntent();
               Bundle bundle = intent.getExtras();

               WifiP2pInfo info = bundle.getParcelable("Info");

               if(info.isGroupOwner) {
                    ServerAsyncTask serverAsyncTask = new ServerAsyncTask(getApplicationContext(), findViewById(R.id.textViewPeerName), info.groupOwnerAddress);
                    serverAsyncTask.execute(port);
               } else {
                    ClientAsyncTask clientAsyncTask = new ClientAsyncTask(getApplicationContext(), findViewById(R.id.textViewPeerName), info.groupOwnerAddress);
                    clientAsyncTask.execute(port);
               }
             */
            Intent intent = new Intent(this, TwoPlayerActivity.class);
            Bundle bundle = new Bundle();

            bundle.putParcelable("Info", info);

            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers){
        super.onPeersAvailable(peers);
        ArrayAdapter<WifiP2pDevice> adapter = new ArrayAdapter<WifiP2pDevice>(this, android.R.layout.simple_list_item_1);
        adapter.addAll(peers.getDeviceList());
        ListView listViewPeers = (ListView) findViewById(R.id.listViewPeers);
        listViewPeers.setAdapter(adapter);
        listViewPeers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WifiP2pDevice device = (WifiP2pDevice) parent.getItemAtPosition(position);
                connect(device);
            }
        });
    }
}
