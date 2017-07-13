package prototype.android.darkweather.prototype.android.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * created by Noodle on 13/07/2017.
 */

public class WeatherSessionData {
    private SharedPreferences sharedPreferences;
    public static String perferencesName = "weatherData";
    private int PRIVATE = 0;
    private Context context;
    private SharedPreferences.Editor editor;


    public static String weatherType;

    public WeatherSessionData(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(perferencesName, PRIVATE);

    }

    public String createSessionForWeather(String weather, String weatherTemp, String weatherTime,String weatherFeel
    ,String windBearing, String windSpeed, float rainPercentage) {
        //Makes preference editable
        editor = sharedPreferences.edit();
        //stores the user id via key value pairs.
        editor.putString("weathertype", weather);

        editor.putString("actualTemp",weatherTemp);

        editor.putString("weather_time",weatherTime);


        editor.putString("weather_feels_like",weatherFeel);
        //returns the values from above.

        editor.putString("wind_bearing",windBearing);

        editor.putString("wind_speed",windSpeed);

        editor.putFloat("rain_percentage",rainPercentage);

        editor.apply();

        return String.valueOf(weather);

    }


    public String getWeatherTypeFromSession(Context context){
        String name = sharedPreferences.getString("weathertype", "");
        return name;


    }

    public String getActualWeatherTempFromSession(Context context){
        String temp = sharedPreferences.getString("actualTemp", "");
        return temp;


    }

    public String getWeatherTime(Context context){
        String time = sharedPreferences.getString("weather_time","");
        return time;


    }

    public String getweatherFeel(Context context){
        String weatherFeel = sharedPreferences.getString("weather_feels_like","");
        return weatherFeel;


    }


    public String getWindSpeed(Context context){
        String windSpeed = sharedPreferences.getString("wind_speed","");
        return windSpeed;


    }

    public String getWindBearing(Context context){
        String windBearing = sharedPreferences.getString("wind_bearing","");
        return windBearing;


    }

    public float getRainPercentage(Context context){
        float rainPercentage = sharedPreferences.getFloat("rain_percentage",0F);
        return rainPercentage;


    }

    public void deleteSharedPref(Context context){
        SharedPreferences settings = context.getSharedPreferences("weatherData", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }
}


