package com.swiggins.today.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.swiggins.today.model.DayForecast;
import com.swiggins.today.model.Weather;
import com.swiggins.today.model.WeatherForecast;

public class WeatherJsonUtil {

    public static Weather getWeather(String data) throws JSONException  {
        Weather weather = new Weather();
        JSONObject jsonObject = new JSONObject(data);

        JSONArray jsonWeatherArray = jsonObject.getJSONArray("weather");

        JSONObject jsonWeather = jsonWeatherArray.getJSONObject(0);
        String desc = getString("description", jsonWeather);
        weather.currentCondition.setDescription(desc);
        weather.currentCondition.setCondition(getString("main", jsonWeather));
        weather.currentCondition.setIcon(getString("icon", jsonWeather));

        JSONObject mainTemp = getObject("main", jsonObject);
        weather.temperature.setMax(getFloat("temp_max", mainTemp));
        weather.temperature.setMin(getFloat("temp_min", mainTemp));
        weather.temperature.setTemp(getFloat("temp", mainTemp));

        // We download the icon to show

        return weather;
    }


    public static WeatherForecast getWeatherForecast(String data) throws JSONException {
        WeatherForecast forecast = new WeatherForecast();

        JSONObject json = new JSONObject(data);

        JSONArray jsonArray = json.getJSONArray("list");

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject jsonDayForecast = jsonArray.getJSONObject(i);

            DayForecast dayForecast = new DayForecast();

            dayForecast.timestamp = jsonDayForecast.getLong("dt");

            JSONObject temp = jsonDayForecast.getJSONObject("temp");

            dayForecast.forecastTemp.day = (float) temp.getDouble("day");
            dayForecast.forecastTemp.min = (float) temp.getDouble("min");
            dayForecast.forecastTemp.max = (float) temp.getDouble("max");
            dayForecast.forecastTemp.night = (float) temp.getDouble("night");
            dayForecast.forecastTemp.eve = (float) temp.getDouble("eve");
            dayForecast.forecastTemp.morning = (float) temp.getDouble("morning");

            JSONArray jsonWeather = jsonDayForecast.getJSONArray("weather");
            JSONObject jsonWeatherObject = jsonWeather.getJSONObject(0);
            dayForecast.weather.currentCondition.setCondition(getString("main", jsonWeatherObject));
            dayForecast.weather.currentCondition.setDescription(getString("description", jsonWeatherObject));
            dayForecast.weather.currentCondition.setIcon(getString("icon", jsonWeatherObject));

            forecast.addForecast(dayForecast);
        }

        return forecast;
    }

    private static JSONObject getObject(String tagName, JSONObject obj) throws JSONException {
        return obj.getJSONObject(tagName);
    }

    private static String getString(String tagName, JSONObject obj) throws JSONException {
        return obj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject obj) throws JSONException {
        return (float) obj.getDouble(tagName);
    }
}
