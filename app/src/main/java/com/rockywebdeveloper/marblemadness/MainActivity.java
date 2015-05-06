package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        EditText usernameEditText = (EditText) findViewById(R.id.username);

        ((Globals) this.getApplication()).setUsername(usernameEditText.getText().toString());

        startActivity(new Intent(this, MainMenuActivity.class));
    }


}
