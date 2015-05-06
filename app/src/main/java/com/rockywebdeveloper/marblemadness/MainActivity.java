package com.rockywebdeveloper.marblemadness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private String fileName;
    static final  int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = "userName.txt";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        EditText usernameEditText = (EditText) findViewById(R.id.username);
        String str = usernameEditText.getText().toString();

        try{
            FileOutputStream fOut;
            File file = new File(getFilesDir(), fileName);



           if(file.exists()){
                FileInputStream fIn;
                fIn = openFileInput(fileName);
                InputStreamReader isr = new InputStreamReader(fIn);

                char [] inputBuffer = new char[READ_BLOCK_SIZE];
                String s = "";

                int charRead;
                while((charRead = isr.read(inputBuffer))>0) {
                    String readString = String.copyValueOf(inputBuffer, 0 , charRead);
                    s += readString;

                    inputBuffer = new char[READ_BLOCK_SIZE];
                }
                ((Globals) this.getApplication()).setUsername(s);


                Toast.makeText(getBaseContext(), "A username Already Exists. Hello "+ s + "!",
                       Toast.LENGTH_LONG).show();

           }

            else{
            ((Globals) this.getApplication()).setUsername(usernameEditText.getText().toString());

            fOut = openFileOutput(fileName, MODE_PRIVATE);

            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(str);
            osw.flush();
            osw.close();

            Toast.makeText(getBaseContext(), "Username saved!", Toast.LENGTH_LONG).show();

            }

        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

        startActivity(new Intent(this, MainMenuActivity.class));
    }


}
