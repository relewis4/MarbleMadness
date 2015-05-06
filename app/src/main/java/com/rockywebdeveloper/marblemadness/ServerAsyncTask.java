package com.rockywebdeveloper.marblemadness;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/*
 *    Created by Andrew Schaefer on 3/1/15.
 *    Modified by Zach Nelson.
 */
public class ServerAsyncTask extends AsyncTask<Integer, String, Void> {

    private ServerSocket server;
    private Socket client;
    private int port;
    private Context context;
    private GameController mGameController;

    public ServerAsyncTask(Context context, GameController controller){
        this.context = context;
        mGameController = controller;
    }

    @Override
    protected Void doInBackground(Integer... params){
        try {
            port = params[0];
            server = new ServerSocket(port);
            client = server.accept();

            ClientAsyncTask clientAsyncTask = new ClientAsyncTask(context, client.getInetAddress(), mGameController);
            clientAsyncTask.execute(port+1);

            while(mGameController.isGameInProgress()){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String temp = bufferedReader.readLine();
                    publishProgress(temp);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {}
        return null;
    }

    protected void onProgressUpdate(String... progress){
        try {
            String[] coordinates = progress[0].split(",");         //x then y
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            mGameController.setAwayBallXandY(x, y);
        } catch (ArrayIndexOutOfBoundsException e){
            //ignore
        } catch (NumberFormatException e1){
            //ignore
        }
    }
}
