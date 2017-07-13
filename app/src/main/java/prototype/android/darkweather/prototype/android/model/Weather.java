package prototype.android.darkweather.prototype.android.model;

/**
 * created by Noodle on 09/07/2017.
 */

public class Weather {

    private String weatherType;

    private String weatherTime;

    private String weatherIcon;
    private String weatherTemperature;
    private String windSpeed;
    private String windBearing;

    public double getPercentageRain() {
        return percentageRain;
    }

    public void setPercentageRain(double percentageRain) {
        this.percentageRain = percentageRain;
    }

    private double percentageRain;


    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(String windBearing) {
        this.windBearing = windBearing;
    }

    public String getWeatherFeelsLike() {
        return weatherFeelsLike;
    }

    public void setWeatherFeelsLike(String weatherFeelsLike) {
        this.weatherFeelsLike = weatherFeelsLike;
    }

    private String weatherFeelsLike;

    public String getTest() {
        return test;
    }

    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    /**
     * Default constructor
     */
    public Weather() {

    }

    /**
     * Object useed to display weather records in the recycler view.
     * @param weatherType Refers to clear or rainy for example.
     * @param weatherTime The current time that the weather is given
     * @param weatherTemperature The temperature related to the weather.
     */
    public Weather(String weatherType, String weatherTime, String weatherTemperature,String weatherFeelsLike,String windSpeed,String windBearing,
                   double percentageRain) {
        this.weatherType = weatherType;
        this.weatherTime = weatherTime;
        this.weatherTemperature = weatherTemperature;
        this.weatherFeelsLike = weatherFeelsLike;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
        this.percentageRain = percentageRain;


    }


    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }


    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(String weatherTime) {
        this.weatherTime = weatherTime;
    }

    public String getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(String weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }


}
