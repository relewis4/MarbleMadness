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

public class PeerListActivity extends WifiP2pActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_peer);
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

    @Override
    protected void setWifiP2pEnabled(){
        super.setWifiP2pEnabled();
        Toast.makeText(getApplicationContext(), "Wifi P2P is Enabled", Toast.LENGTH_SHORT).show();
        discoverPeers();
    }

    @Override
    protected void setConnected() {
        super.setConnected();
        Toast.makeText(getApplicationContext(), "Connected to Peer", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(this, ConnectedActivity.class);
            Bundle bundle = new Bundle();

            bundle.putParcelable("Info", info);

            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers){
        super.onPeersAvailable(peers);
        Toast.makeText(getApplicationContext(), "Peers discovered", Toast.LENGTH_SHORT).show();
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
