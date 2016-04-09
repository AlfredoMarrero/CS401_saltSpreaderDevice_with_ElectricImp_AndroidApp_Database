package com.example.alfredo.imp_controller;

/**
 * Created by alfred on 3/9/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


public class Settings extends AppCompatActivity {

    int NUMBER_OF_CITIES = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.alfredo.imp_controller.R.layout.activity_settings);


        Button saveBtn = (Button) findViewById(com.example.alfredo.imp_controller.R.id.saveButton);
        saveBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                EditText city=(EditText)findViewById(com.example.alfredo.imp_controller.R.id.city);
                //TextView cityText=(TextView)findViewById(R.id.textview);
                String cityName = city.getText().toString();

                EditText country=(EditText)findViewById(com.example.alfredo.imp_controller.R.id.country);
                //TextView cityText=(TextView)findViewById(R.id.textview);
                String countryName = country.getText().toString();

                EditText timer=(EditText)findViewById(com.example.alfredo.imp_controller.R.id.timer);
                //TextView cityText=(TextView)findViewById(R.id.textview);
                String  sleepTimerForImp= timer.getText().toString();


                if(sleepTimerForImp.equals("")){

                    Toast.makeText(getApplicationContext(), "Error: Enter the correct time", Toast.LENGTH_LONG).show();
                    return;

                }

                int number = Integer.parseInt(sleepTimerForImp);


                // setting 24 as the maximum number of requests to te weather database that the agent will do in a day
                if(number > 24){

                    Toast.makeText(getApplicationContext(), "Error: Enter a number smaller than 24", Toast.LENGTH_LONG).show();
                    return;
                }





                System.out.println("The number is: " + number);

                if(cityName.equals("")){

                  Toast.makeText(getApplicationContext(), "Error: Enter a city", Toast.LENGTH_LONG).show();
                  return;
                }

                if(countryName.equals("")){

                    Toast.makeText(getApplicationContext(), "Error: Enter a country", Toast.LENGTH_LONG).show();
                    return;
                }

                if(countryName.equalsIgnoreCase("Canada")){

                    countryName = "CA";

                }
                else if (countryName.equalsIgnoreCase("United States")|| countryName.equalsIgnoreCase("US")){
                    countryName = "US";
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sorry, device is only available in Canada and the US. Enter a correct country name.", Toast.LENGTH_LONG).show();
                    return;
                }
                String cityID =getCityID(cityName, countryName);


                Toast.makeText(getApplicationContext(), "Returned " + cityID, Toast.LENGTH_LONG).show();
                System.out.println("--------------------  "+ sleepTimerForImp);
                // send info to electric imp
                String address="https://agent.electricimp.com/LcD9l4csFvbl?cityId="+ cityID +"&sleepTimer="+sleepTimerForImp;
                //String address="https://agent.electricimp.com/LcD9l4csFvbl?led="+ ledtostr +"&timer="+timetostr;
                System.out.println(address);

                try {

                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(address);
                    httpclient.execute(httppost);

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection" + e.toString());
                    Toast.makeText(getApplicationContext(), "Error: No internet access.", Toast.LENGTH_LONG).show();
                }



                if(v.getId() == com.example.alfredo.imp_controller.R.id.settings){

                    Intent intent = new Intent(Settings.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });

    }



    public String getCityID(String cityEntered, String countryEntered){

         //dataType[] arrayRefVar = new dataType[arraySize];
        CityDataStructure[] city = new CityDataStructure[NUMBER_OF_CITIES];


        // getting the country ID which is sent to electric imp to querry the weather database
        city[0] = new CityDataStructure("5946768", "Edmonton","CA");
        city[1] = new CityDataStructure("6176823","Waterloo", "CA");
        city[2] = new CityDataStructure("5991370","Keswick","CA");
        city[3] = new CityDataStructure("6150174", "Smiths Falls","CA");
        city[4] = new CityDataStructure("6166142","Thunder Bay", "CA");
        city[5] = new CityDataStructure("6173331",	"Vancouver","CA");
        city[6] = new CityDataStructure("5946820", "Regina", "CA");
        city[7] = new CityDataStructure("5551665",	"Arizona City","US");


            for(int i = 0 ; i < NUMBER_OF_CITIES; i++){

                if(city[i].getCityName().equalsIgnoreCase(cityEntered) && city[i].getCountry().equalsIgnoreCase(countryEntered)){

                    return city[i].getCityID();
                }
            }

            //Toast.makeText(getApplicationContext(), "Entraste lo correcto", Toast.LENGTH_LONG).show();
            return null;
    }

}
