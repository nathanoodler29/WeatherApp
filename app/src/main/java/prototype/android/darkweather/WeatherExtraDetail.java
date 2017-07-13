package prototype.android.darkweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import prototype.android.darkweather.prototype.android.model.Weather;
import prototype.android.darkweather.prototype.android.model.WeatherSessionData;

public class WeatherExtraDetail extends AppCompatActivity {

    private TextView weatherChelc;
    private Weather weather = new Weather();
    private ImageView forecastIocn;
    private TextView actualTempeature;
    private TextView weatherTime;
    private TextView weatherFeelsLike;
    private TextView windSpeed;
    private TextView windBearing;

    private TextView percentageRaining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Uses the session from the selected weather tile, then fills in the fields.
        WeatherSessionData sessionData = new WeatherSessionData(this);
        setContentView(R.layout.activity_weather_extra_detail);
        weatherChelc = (TextView) findViewById(R.id.weather_forecast);
        //Returns the weather forecast, i.e cloduy.
        weatherChelc.setText(sessionData.getWeatherTypeFromSession(this));
        actualTempeature = (TextView) findViewById(R.id.actual_temp);
        //Returns the actual weather temperature.
        actualTempeature.setText(sessionData.getActualWeatherTempFromSession(this) + "°");
        //Returns the time of the weather.
        weatherTime = (TextView) findViewById(R.id.weather_time);
        weatherTime.setText(sessionData.getWeatherTime(this));
        //Returns the feel of the temp.
        weatherFeelsLike = (TextView) findViewById(R.id.weather_feels_like);
        weatherFeelsLike.setText("Feels like" + sessionData.getweatherFeel(this) + "°");
        //Displays the wind speed.
        windSpeed = (TextView) findViewById(R.id.wind_speed);
        windSpeed.setText(sessionData.getWindSpeed(this) + "mpg");
        //Displays the wind bearing.
        windBearing = (TextView) findViewById(R.id.wind_bearing);
        windBearing.setText(sessionData.getWindBearing(this) + "°");
        //Displays the percentage chance of rain.
        percentageRaining = (TextView) findViewById(R.id.percentage_rain);
        percentageRaining.setText("Probability "+sessionData.getRainPercentage(this) + "%");


    }


}
