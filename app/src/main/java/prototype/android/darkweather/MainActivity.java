package prototype.android.darkweather;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import prototype.android.darkweather.prototype.android.darkweather.Time.TimeHandler;
import prototype.android.darkweather.prototype.android.model.Weather;
import prototype.android.darkweather.protoype.android.adapter.WeatherAdapter;

public class MainActivity extends AppCompatActivity {

    //Access to the unix time conversion method.
    private TimeHandler timeHandler = new TimeHandler();
    //Weather data arraylist which stores the data from the get request.
    ArrayList<Weather> weatherData = new ArrayList<Weather>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView currentDayName = (TextView)findViewById(R.id.currentDay);
        currentDayName.setText(timeHandler.getCurrentDay());
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        //Changes the colour of the toolbar, as this will comply with api 19 while in xml backgroundTint isn't compatible.
        myToolbar.setBackgroundColor((Color.parseColor("#C5DBF2")));
        //Begins executing the Async task for pulling the json data from the get request and populating the recycler view on post execute.
        // ?units=uk2 converts values to UK units, i.e temperature.
        new PerformLookUpForDarkAPI().execute("https://api.darksky.net/forecast/inserkeyhere/55.9533,3.188?units=uk2");


    }


    private class PerformLookUpForDarkAPI extends AsyncTask<String, Void, ArrayList<Weather>> {

        private StringBuffer buffer = new StringBuffer();
        private String output;

        /**
         * Performs the get request on the dark API
         *
         * @param params The Arraylist is a param, and the url for the get request.
         * @return Weather data from the request as a Weather arraylist.
         */
        @Override
        protected ArrayList<Weather> doInBackground(String... params) {
            BufferedReader reader = null;
            URL url = null;
            try {
                //Goes to the dark api url
                url = new URL("https://api.darksky.net/forecast/insertkeyhere/55.9533,3.188?units=uk2");
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                int read;
                char[] chars = new char[1024];
                //Checks that next line exists
                while ((read = reader.read(chars)) != -1) {
                    //if so carry on reading.
                    buffer.append(chars, 0, read);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            output = buffer.toString();
            JSONObject jsonObject;
            try {
                //Stores the get request  data into a json object for querying.
                jsonObject = new JSONObject(output);
                //Accesses the hourly weather information object, then gains access to the data array related with the hourly object.
                JSONArray array = jsonObject.getJSONObject("hourly").getJSONArray("data");
                //Iterates through the json array.
                for (int i = 0; i < array.length(); i++) {
                    Weather weather = new Weather();
                    //Iterates through the json object.
                    JSONObject row = array.getJSONObject(i);
                    //Performs look up for the time key to get the time value.
                    int time = Integer.parseInt(row.getString("time"));
                    //Converts the unix timestamp to readable time in 24 hour clock format.
                    String convertedTime = timeHandler.convertUnixTimeStampToCurrentTime(time);
                    //Returns the weather summary.
                    String weatherSummary = row.getString("summary");
                    //Returns the weather icon.
                    String weatherIcon = row.getString("icon");
                    //Returns the weather temp.
                    String temperature = row.getString("temperature");
                    //Sets all values from the weather object, for each of the params in the constructor.

                    String feelsLike = row.getString("apparentTemperature");

                    String windBearing = row.getString("windBearing");
                    String windSpeed = row.getString("windSpeed");
                    weather.setWeatherType(weatherSummary);
                    weather.setWeatherIcon(weatherIcon);
                    weather.setWeatherTime(convertedTime);
                    weather.setWeatherTemperature(temperature);
                    weather.setWeatherFeelsLike(feelsLike);
                    weather.setWindBearing(windBearing);
                    weather.setWindSpeed(windSpeed);

                    //calculation for percentage of it raining
                    double rainIntensity = row.getDouble("precipIntensity");
                    double rainProbablity = row.getDouble("precipProbability");

                    Log.d("intenseity",""+rainIntensity);
                    Log.d("probabltiy",""+rainProbablity);
                    double chanceOfRain = rainIntensity*rainProbablity;
                    double convertedPercentage =  Double.parseDouble(new DecimalFormat("#.##").format(chanceOfRain));


                    weather.setPercentageRain(convertedPercentage);

                    //Adds the weather object to the weather data arraylist.
                    weatherData.add(weather);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return weatherData;

        }

        /**
         * Populates the recycler view with the data from the get request.
         *
         * @param result Relates to the arraylsit value of the weather data.
         */
        @Override
        protected void onPostExecute(ArrayList<Weather> result) {

            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.weather_recycler);
            //creates a linear layout
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            //sets a linear layout
            recyclerView.setLayoutManager(linearLayoutManager);
            //Populates the weather adapter with the data from the get request.
            WeatherAdapter mAdapter = new WeatherAdapter(getApplicationContext(), weatherData);
            recyclerView.setAdapter(mAdapter);
            //Checks to see if the adapter is no elements.
            if (mAdapter.getItemCount() == 0) {
                //Changes the error page activity, as this suggests phone has no internet connection or the API is down.
                Intent changeToErrorPage = new Intent(getApplicationContext(), ConnectionLost.class);
                startActivity(changeToErrorPage);
            }



        }


    }


}



