package com.swiggins.today.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.lang.StringBuffer;
import java.lang.Throwable;

import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpUtil {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";

    private static String BASE_FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&q=";


    public String getWeatherData(String location, String lang) {
        HttpURLConnection conn = null;
        InputStream stream = null;

        try  {

            String url = BASE_URL + location;
            if  (lang != null) {
                url = url + "&lang=" + lang;
            }

            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            StringBuffer buffer = new StringBuffer();
            stream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\r\n");
            }

            stream.close();
            conn.disconnect();

            return buffer.toString();

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (Throwable t) { }
            try {
                conn.disconnect();
            } catch (Throwable t) { }
        }

        return null;
    }

    public String getForecastWeatherData(String location, String lang, String forecastDayNum) {
        HttpURLConnection conn = null;
        InputStream stream = null;

        int forecastDay = Integer.parseInt(forecastDayNum);

        try {

            String url = BASE_FORECAST_URL + location;
            if (lang != null) {
                url = url + "&lang=" + lang;
            }

            url  = url + "&cnt=" + forecastDay;
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            StringBuffer buffer = new StringBuffer();
            stream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\r\n");
            }

            stream.close();
            conn.disconnect();

            return buffer.toString();

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (Throwable t) { }
            try {
                conn.disconnect();
            } catch (Throwable t) { }
        }

        return null;

    }

    public byte[] getImage(String code) {
        HttpURLConnection conn = null;

        InputStream stream = null;

        try {

            conn = (HttpURLConnection) (new URL(IMG_URL + code)).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            stream = conn.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (stream.read(buffer) != -1) {
                baos.write(buffer);
            }

            return baos.toByteArray();

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (Throwable t) { }
            try {
                conn.disconnect();
            } catch (Throwable t) { }
        }

        return null;
    }
}
