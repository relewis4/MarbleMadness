package com.rockywebdeveloper.marblemadness;


public class Score {

    private String scoreName;
    private String layout;
    private int score;
    private String time;

    public Score(){}

    public Score(String n, String l, int s, String t){
        this.scoreName = n;
        this.layout = l;
        this.score = s;
        this.time = t;
    }

    public String getScoreName(){
        return scoreName;
    }

    public void setScoreName(String n){this.scoreName = n;}

    public String getLayout(){
        return layout;
    }

    public void setLayout(String l){
        this.layout = l;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int s){
        this.score = s;
    }

    public String getTime() {return time;}

    public void setTime(String t) {this.time = t;}

    public String toString(){
        return this.scoreName + " " + this.layout + " " + "Time: " + this.score + " " + this.time;
    }
}
