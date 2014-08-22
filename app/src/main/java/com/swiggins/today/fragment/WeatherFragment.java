package com.swiggins.today.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swiggins.today.R;
import com.swiggins.today.model.DayForecast;
import com.swiggins.today.model.Weather;
import com.swiggins.today.util.WeatherHttpUtil;
import com.swiggins.today.util.WeatherJsonUtil;

import org.json.JSONException;

public class WeatherFragment extends android.support.v4.app.Fragment {

    private String city = "clarkston, mi";
    private String lang = "en";

    private Weather weather = new Weather();
    private DayForecast dayForecast;

    private TextView conditionView;
    private TextView temperatureView;
    private ImageView weatherIcon;

    boolean temperatureIsClicked = false;

    public WeatherFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.weather_layout, container, false);

        conditionView = (TextView) v.findViewById(R.id.conditionTextView);

        temperatureView = (TextView) v.findViewById(R.id.degreesTextView);
        temperatureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDegreesView(view);
            }
        });

        weatherIcon = (ImageView) v.findViewById(R.id.weatherIcon);

        // should take out of if and check how long it's been since last retrieved
        JSONWeatherTask weatherTask = new JSONWeatherTask();
        weatherTask.execute(new String[]{city, lang});

        return v;
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            String data = ((new WeatherHttpUtil()).getWeatherData(params[0], params[1]));
            try {
                weather = WeatherJsonUtil.getWeather(data);
                weather.iconData = ((new WeatherHttpUtil()).getImage(weather.currentCondition.getIcon()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                weatherIcon.setImageBitmap(img);
            }

            conditionView.setText(weather.currentCondition.getDescription());
            temperatureView.setText(String.valueOf(weather.temperature.getTemp()) + "\u00B0");

            JSONWeatherIconTask weatherIconTask = new JSONWeatherIconTask();
            weatherIconTask.execute(new String[]{city, lang});
        }

    }

    private class JSONWeatherIconTask extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... params) {

            byte[] data = null;

            try {
                data = ((new WeatherHttpUtil()).getImage(params[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onPostExecute(byte[] data) {
            super.onPostExecute(data);

            if (data != null) {
                Bitmap img = BitmapFactory.decodeByteArray(data, 0, data.length);
                weatherIcon.setImageBitmap(img);
            }
        }

    }

    public void setForecast(DayForecast dayForecast) {
        this.dayForecast = dayForecast;
    }

    public void changeDegreesView(View v) {
        if (temperatureIsClicked) {
            temperatureView.setTextSize(50);
            temperatureView.setText(String.valueOf(weather.temperature.getTemp()) + "\u00B0");
        } else {
            temperatureView.setTextSize(15);
            temperatureView.setText("hi: " + String.valueOf(weather.temperature.getMax()) + "\u00B0" +
                    "lo: " + String.valueOf(weather.temperature.getMin()) + "\u00B0");
        }
        temperatureIsClicked = !temperatureIsClicked;
    }

}
