package com.rockywebdeveloper.marblemadness;

import android.app.Application;

public class Globals extends Application {
    private String username;

    public String getUsername(){
        return username;
    }

    public void setUsername(String user){
        username = user;
    }
}