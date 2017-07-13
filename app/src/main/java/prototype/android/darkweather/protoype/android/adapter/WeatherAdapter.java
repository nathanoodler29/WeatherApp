package prototype.android.darkweather.protoype.android.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import prototype.android.darkweather.R;
import prototype.android.darkweather.WeatherExtraDetail;
import prototype.android.darkweather.prototype.android.model.Weather;
import prototype.android.darkweather.prototype.android.model.WeatherSessionData;

/**
 * created by Noodle on 09/07/2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private ArrayList<Weather> weatherArrayList = new ArrayList<Weather>();
    private Context mContext;

    /**
     * This class handles the layout elements.
     */
    public static class ViewHolder extends
            RecyclerView.ViewHolder {
        private TextView weatherTime;
        private TextView weatherForecast;
        private ImageView weatherIcon;
        private CardView cardView;
        private TextView temperatureValue;


        public ViewHolder(View v) {
            super(v);
        }

    }

    /**
     * Refers to the adapter itself.
     *
     * @param context     Related to the current activity it's been called in.
     * @param weatherData Refers to the weather data, which is used to populate the adapter.
     */
    public WeatherAdapter(Context context, ArrayList<Weather> weatherData) {
        weatherArrayList = weatherData;
        mContext = context;

    }

    /**
     * Initialises the elements in the layout, from the 'generic_weather_item.xml' layout file
     *
     * @param parent   Refers to the current layout.
     * @param viewType Relates to the type of view in the application.
     * @return The view holder object with the layout.
     */
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(

                R.layout.generic_weather_item, parent, false);

        final WeatherAdapter.ViewHolder viewHolder = new WeatherAdapter.ViewHolder(v);
        viewHolder.weatherForecast = (TextView) v.findViewById(R.id.weather_forecast);
        viewHolder.weatherTime = (TextView) v.findViewById(R.id.time_text);
        viewHolder.temperatureValue = (TextView) v.findViewById(R.id.temp_text);

        viewHolder.weatherIcon = (ImageView) v.findViewById(R.id.weather_icon);

        viewHolder.cardView = (CardView) v.findViewById(R.id.weather_list);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);


                Intent intent = new Intent(context, WeatherExtraDetail.class);
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    /**
     * This method adds the data to the view holder created.
     * The method also changes the weather icons and colour of card views depending on the icon type from the get request.
     *
     * @param holder   Holder relates to the object, that has the layout.
     * @param position Relates to the number of items in the recycler view, which are indexed in the arraylist.
     */
    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {

        holder.weatherForecast.setText(weatherArrayList.get(position).getWeatherType());
        holder.weatherTime.setText(weatherArrayList.get(position).getWeatherTime());
        holder.temperatureValue.setText(weatherArrayList.get(position).getWeatherTemperature() + mContext.getResources().getString(R.string.degrees_icon));

        // This changes the colour to a yellow if the weather is shown as clear day.
        if (weatherArrayList.get(position).getWeatherIcon().matches("clear-day")) {
            //Changes the colour of the card view.
            holder.cardView.setCardBackgroundColor(Color.parseColor("#f68836"));
            //Changes the default icon declared in the generic list view to the sun icon.
            int clearDay = mContext.getResources().getIdentifier("sun_three", "drawable", mContext.getPackageName());
            //Sets the image.
            holder.weatherIcon.setImageResource(clearDay);
            // This changes the colour to a blue if the weather is shown as rain.
        } else if (weatherArrayList.get(position).getWeatherIcon().matches("rain")) {
            //Changes the colour of the card view.
            holder.cardView.setCardBackgroundColor(Color.parseColor("#6ac6f9"));
            //Changes the default icon declared in the generic list view to the rain icon.
            int rainIcon = mContext.getResources().getIdentifier("umberlla", "drawable", mContext.getPackageName());
            //Sets the image.
            holder.weatherIcon.setImageResource(rainIcon);
            // This changes the colour to a grey if the weather is shown as partly or mostly cloudy.
        } else if (weatherArrayList.get(position).getWeatherIcon().matches("partly-cloudy-day") |
                weatherArrayList.get(position).getWeatherIcon().matches("mostly-cloudy-day")) {
            //Changes the colour of the card view.
            holder.cardView.setCardBackgroundColor(Color.parseColor("#dfdfdf"));
            //Changes the default icon declared in the generic list view to the cloud icon.
            int cloudImage = mContext.getResources().getIdentifier("clouds", "drawable", mContext.getPackageName());
            //Sets the image.
            holder.weatherIcon.setImageResource(cloudImage);
            // This changes the colour to a dark purple  if the weather is shown as a clear night, to represent night hours.
        } else if (weatherArrayList.get(position).getWeatherIcon().matches("clear-night")) {
            //Changes the colour of the card view.
            holder.cardView.setCardBackgroundColor(Color.parseColor("#003366"));
            //Changes the default icon declared in the generic list view to the moon icon.
            int clearMoonIcon = mContext.getResources().getIdentifier("moon", "drawable", mContext.getPackageName());
            //Sets the image.
            holder.weatherIcon.setImageResource(clearMoonIcon);
            // This changes the colour to a grey if the weather is shown as partly or mostly cloudy at night.
        } else if (weatherArrayList.get(position).getWeatherIcon().matches("partly-cloudy-night") |
                weatherArrayList.get(position).getWeatherIcon().matches("mostly-cloudy-night")) {
            //Changes the colour of the card view.
            holder.cardView.setCardBackgroundColor(Color.parseColor("#003366"));
            //Changes the default icon declared in the generic list view to the cloud icon.
            int clouds = mContext.getResources().getIdentifier("clouds", "drawable", mContext.getPackageName());
            //Sets the image.
            holder.weatherIcon.setImageResource(clouds);
            // This changes the colour to a dark purple if the weather is as clear night.
        } else if (weatherArrayList.get(position).getWeatherIcon().matches("clear-night")) {
            //Changes the colour of the card view.
            holder.cardView.setCardBackgroundColor(Color.parseColor("#003366"));
            //Changes the default icon declared in the generic list view to the clear icon.
            int defaultImage = mContext.getResources().getIdentifier("sun_three", "drawable", mContext.getPackageName());
            //Sets the image.
            holder.weatherIcon.setImageResource(defaultImage);

            // This changes the colour to a darker grey if the weather is overcast.
        } else if (weatherArrayList.get(position).getWeatherType().matches("Overcast")) {
            //Changes the colour of the card view.
            holder.cardView.setCardBackgroundColor(Color.parseColor("#b7c2c4"));
            //Changes the default icon declared in the generic list view to the clear icon.
            int overcast = mContext.getResources().getIdentifier("overcast_icon", "drawable", mContext.getPackageName());
            //Sets the image.
            holder.weatherIcon.setImageResource(overcast);


        }

        //Returns the current position the adapter is on
        int pos = holder.getAdapterPosition();
        //Gets the weather forecast from the tile selected.
        String weatherType = weatherArrayList.get(pos).getWeatherType();
        //Gets the weather temp from the tile selected.
        String weatherTemp = weatherArrayList.get(pos).getWeatherTemperature();
        //Gets the weather time from the tile selected.
        String weatherTime = weatherArrayList.get(pos).getWeatherTime();
        //Gets the weather feel temp from the tile selected.
        String weatherFeel = weatherArrayList.get(pos).getWeatherFeelsLike();
        //Gets the wind speed from the tile selected.
        String windSpeed = weatherArrayList.get(pos).getWindSpeed();
        //Gets the wind bearing from the tile selected.
        String windBearing = weatherArrayList.get(pos).getWindBearing();
        //Gets the rain percentag from the tile selected.
        double rainPercentage = weatherArrayList.get(pos).getPercentageRain();
        //Instance of the session
        WeatherSessionData sessionData = new WeatherSessionData(mContext);
        //Removes any existing data that may be irrelevant.
        sessionData.deleteSharedPref(mContext);
        //Creates session, and adds all the data of interest
        sessionData.createSessionForWeather(weatherType, weatherTemp, weatherTime, weatherFeel, windBearing, windSpeed, (float) rainPercentage);


    }

    /**
     * States how many items are in the adapter.
     *
     * @return The number of items in the adapter as an integer.
     */
    @Override
    public int getItemCount() {
        return weatherArrayList.size();
    }
}