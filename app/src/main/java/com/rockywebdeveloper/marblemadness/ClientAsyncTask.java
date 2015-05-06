package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/*
 *    Created by Andrew Schaefer on 3/1/15.
 *    Modified by Zach Nelson.
 */
public class ClientAsyncTask extends AsyncTask<Integer, String, Void> {
    private Socket client;
    private int port;
    private InetAddress host;
    private Context context;
    private GameController mGameController;

    public ClientAsyncTask(Context context, InetAddress host, GameController controller){
        this.context = context;
        this.host = host;
        mGameController = controller;
    }

    @Override
    protected Void doInBackground(Integer... params){
        port = params[0];
        client = new Socket();

        try{
            client.connect((new InetSocketAddress(host, port)), 0);

            ServerAsyncTask serverAsyncTask = new ServerAsyncTask(context, mGameController);
            serverAsyncTask.execute(port+1);

            while(mGameController.isGameInProgress()){
                PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
                pw.println(String.valueOf(mGameController.getHomeBallX()) + "," + String.valueOf(mGameController.getHomeBallY()));
                pw.flush();
            }

            client.close();
        } catch (IOException e){
            publishProgress(e.getMessage());
            e.printStackTrace();
        }

        publishProgress("Connection closed");
        return null;
    }

    protected void onProgressUpdate(String... progress){
        Toast.makeText(context, progress[0], Toast.LENGTH_LONG).show();
    }
}
