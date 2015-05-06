package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class HighScoreView extends Activity {

    private ListView lv;
    private ArrayAdapter<Score> adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        lv = (ListView) findViewById(R.id.scoreView);

        DBMethods dbm = new DBMethods(this);

        Score s = new Score("User", "layout", 5, "Tues May 29");
        dbm.create(s);
        s = new Score("User", "layout", 2, "Sun Sept 6");
        dbm.create(s);
        s = new Score("User", "layout", 8, "Fri May 21");
        dbm.create(s);
        s = new Score("User", "layout", 10, "Tues April 6");
        dbm.create(s);
        s = new Score("User", "layout", 1, "Tues May 29");
        dbm.create(s);
        s = new Score("User", "layout", 3, "Thurs March 5");
        dbm.create(s);
        s = new Score("User", "layout", 9, "Tues May 29");
        dbm.create(s);
        s = new Score("User", "layout", 4, "Wed Oct 31");
        dbm.create(s);
        s = new Score("User", "layout", 6, "Mon Aug 14");
        dbm.create(s);
        s = new Score("User", "layout", 7, "Sat May 2");
        dbm.create(s);


        ArrayList<Score> scores = new ArrayList<>();
        scores = dbm.readScores();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                scores
        );

        lv.setAdapter(adapter);

    }
}
