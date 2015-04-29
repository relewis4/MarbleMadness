package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAsyncTask extends AsyncTask<Integer, String, Void> {

    private ServerSocket server;
    private Socket client;
    private int port;
    private Context context;

    public ServerAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Integer... params){
        try {
            publishProgress("Waiting for connection");
            port = params[0];
            server = new ServerSocket(port);
            publishProgress("Server port opened:"+server.getLocalPort());
            client = server.accept();

            publishProgress("Connected to Client");

            while(client.isConnected()){
                //get X,Y data from Rocky's Controller class
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {}

        publishProgress("Connection Closed");
        return null;
    }

    protected void onProgressUpdate(String... progress){
        Toast.makeText(context, progress[0], Toast.LENGTH_SHORT).show();
    }
}
