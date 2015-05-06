package com.rockywebdeveloper.marblemadness;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final int DATABASE_VERSION = 1;

    //username table
    //public final static String TABLE_USER = "user";
    //public final static String COLUMN_USERNAME = "username";

    //high score table
    public final static String TABLE_SCORE = "scoreTable";
    public final static String COLUMN_SCORE_USERNAME = "scoreName";
    public final static String COLUMN_LAYOUT_NAME = "layout";
    public final static String COLUMN_HIGH_SCORE = "time";
    public final static String COLUMN_TIME_STAMP = "date";

    //public final static String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USER + "("
      //      + BaseColumns._ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
          //  + COLUMN_USERNAME + "TEXT"
        //    + ")";

    public final static String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SCORE_USERNAME + " TEXT,"
            + COLUMN_LAYOUT_NAME + " TEXT,"
            + COLUMN_HIGH_SCORE + " INTEGER,"
            + COLUMN_TIME_STAMP + " TEXT"
            + ")";
            //+ "FOREIGN KEY(" + COLUMN_SCORE_USERNAME + ") REFERENCES CLASSES(_ID))";

    public DBHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SCORE);
        onCreate(db);
    }
}
