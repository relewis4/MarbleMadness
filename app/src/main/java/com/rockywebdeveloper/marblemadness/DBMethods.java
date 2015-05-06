package com.rockywebdeveloper.marblemadness;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;


public class DBMethods {
    private Context mCtx;
    private DBHelper mDBHelper;

    public DBMethods(Context ctx){
        mCtx = ctx;
        mDBHelper = new DBHelper(ctx);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        db.close();
    }

    //Opens database
    private SQLiteDatabase open(){return mDBHelper.getWritableDatabase();}
    //Closes database
    private void close(SQLiteDatabase db){db.close();}

    public ArrayList<Score> readScores(){
        SQLiteDatabase db = open();

        Cursor c = db.query(
                DBHelper.TABLE_SCORE,
                new String[]{
                        DBHelper.COLUMN_SCORE_USERNAME,
                        DBHelper.COLUMN_LAYOUT_NAME,
                        DBHelper.COLUMN_HIGH_SCORE,
                        DBHelper.COLUMN_TIME_STAMP},
                null, null, null, null, DBHelper.COLUMN_HIGH_SCORE);

        ArrayList<Score> scores = new ArrayList<>();
        if(c.moveToFirst()){
            for(int i=0; i<10; i++){
                Score score = new Score(
                        getStringByColumnName(c, DBHelper.COLUMN_SCORE_USERNAME),
                        getStringByColumnName(c, DBHelper.COLUMN_LAYOUT_NAME),
                        getIntByColumnName(c, DBHelper.COLUMN_HIGH_SCORE),
                        getStringByColumnName(c, DBHelper.COLUMN_TIME_STAMP)
                );
                scores.add(score);
                c.moveToNext();
            }
        }
        c.close();
        close(db);
        return scores;
    }

    //public String getUsername(int n){
    //    SQLiteDatabase db = open();
    //    Cursor c = db.query(
    //            DBHelper.TABLE_USER,
    //            new String[]{DBHelper.COLUMN_USERNAME, BaseColumns._ID},
    //            BaseColumns._COUNT + "=" + n,
    //            null, null, null, null);

 //       String username;
   //     if(c.moveToFirst()){
    //        username = getStringByColumnName(c, DBHelper.COLUMN_USERNAME);
     //       return username;
     //   }
     //   return "";
    //}

    private String getStringByColumnName(Cursor c, String s){
        int columnIndex = c.getColumnIndex(s);
        return c.getString(columnIndex);
    }

    private int getIntByColumnName(Cursor c, String s){
        int columnIndex = c.getColumnIndex(s);
        return c.getInt(columnIndex);
    }

    public void create(Score s){
        SQLiteDatabase db = open();
        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_SCORE_USERNAME, s.getScoreName());
        values.put(DBHelper.COLUMN_LAYOUT_NAME, s.getLayout());
        values.put(DBHelper.COLUMN_HIGH_SCORE, s.getScore());
        values.put(DBHelper.COLUMN_TIME_STAMP, s.getTime());
        db.insert(DBHelper.TABLE_SCORE, null, values);

        db.setTransactionSuccessful();
        db.endTransaction();
        close(db);
    }

    //public void create(User u){
      //  SQLiteDatabase db = open();
      //  db.beginTransaction();

      //  ContentValues values = new ContentValues();
      //  values.put(DBHelper.COLUMN_USERNAME, u.getName());
      //  db.insert(DBHelper.TABLE_USER, null, values);

      //  db.setTransactionSuccessful();
      //  db.endTransaction();
      //  close(db);
   // }

}
