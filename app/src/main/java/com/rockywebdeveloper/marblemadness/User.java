package com.rockywebdeveloper.marblemadness;


public class User {

    private String name;

    public User() {
    }

    public User(String n) {
        this.name = n;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        this.name = n;
    }
}

