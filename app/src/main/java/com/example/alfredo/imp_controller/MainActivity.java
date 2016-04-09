package com.example.alfredo.imp_controller;

/**
 * Created by alfred on 2/14/16.
 */


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends ActionBarActivity {


    String ledtostr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.alfredo.imp_controller.R.layout.activity_main);
        StrictMode.enableDefaults();

        final TextView switch_Status = (TextView) findViewById(com.example.alfredo.imp_controller.R.id.switchStatus);
        final TextView ledStatus = (TextView) findViewById(com.example.alfredo.imp_controller.R.id.LedStatus);
        Switch mySwitch = (Switch) findViewById(com.example.alfredo.imp_controller.R.id.mySwitch);
        Switch AutoSwitch = (Switch) findViewById(com.example.alfredo.imp_controller.R.id.AutoSwitch);

        //set the switch to ON
        mySwitch.setChecked(false);
        AutoSwitch.setChecked(false);
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    ledtostr = "1";
                    ledStatus.setText("Spread salt ON:");
                }else{
                    ledtostr = "0";
                    ledStatus.setText("Spread salt OFF:");
                }

            }
        });

        AutoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    switch_Status.setText("Device ON");
                    try {

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("https://agent.electricimp.com/LcD9l4csFvbl?auto=1");
                        httpclient.execute(httppost);

                    } catch (Exception e) {
                        Log.e("log_tag", "Error in http connection" + e.toString());
                    }

                }else{
                    switch_Status.setText("Device OFF");
                    try {

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("https://agent.electricimp.com/LcD9l4csFvbl?auto=0");
                        httpclient.execute(httppost);

                    } catch (Exception e) {
                        Log.e("log_tag", "Error in http connection" + e.toString());
                    }

                }

            }
        });

        Button buttonclick = (Button)findViewById(com.example.alfredo.imp_controller.R.id.button);
        buttonclick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendData();
            }
        });
    }

    public void sendData() {


        EditText time=(EditText)findViewById(com.example.alfredo.imp_controller.R.id.time);
        TextView resultView=(TextView)findViewById(com.example.alfredo.imp_controller.R.id.textview);
        String timetostr = time.getText().toString();
       // System.out.println("111");
        System.out.println(ledtostr);
        System.out.println(timetostr);

        String address="https://agent.electricimp.com/LcD9l4csFvbl?led="+ ledtostr +"&timer="+timetostr;
        System.out.println(address);

        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(address);
            httpclient.execute(httppost);

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection" + e.toString());
            resultView.setText("Could not connect to the agent");
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.example.alfredo.imp_controller.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.example.alfredo.imp_controller.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // calling a new activity
    public void onButtonClick(View view){
        // checking if the button pressed is the setting button
        if(view.getId() == com.example.alfredo.imp_controller.R.id.settings){

            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        }
    }
}
