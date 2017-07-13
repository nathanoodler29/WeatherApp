package prototype.android.darkweather.prototype.android.darkweather.Time;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import prototype.android.darkweather.prototype.android.model.Weather;


/**
 * created by Noodle on 09/07/2017.
 */

public class TimeHandler {
    private Weather weather = new Weather();


    /**
     * Converts a given unix time stamp to the following format 10:53am
     *
     * @param time The unix time stamp, is the expected param.
     * @return The time in a readable format.
     */
    public String convertUnixTimeStampToCurrentTime(long time) {

        //convert seconds to milliseconds
        Date date = new Date(time * 1000L);
        // format of the time.
        SimpleDateFormat jdf = new SimpleDateFormat("HH:mm a");
        //converts the time into a string.
        String java_date = jdf.format(date);

        return java_date;
    }

    /**
     * Returns the current day of the week.
     */
    public String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();


        return String.valueOf(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));

    }



}
