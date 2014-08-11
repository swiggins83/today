package com.swiggins.today.model;

import java.util.Date;

public class DayForecast {

    private static Date date;
    public Weather weather;
    public ForecastTemp forecastTemp;
    public long timestamp;

    public class ForecastTemp {
        public float day,
                     min,
                     max,
                     night,
                     eve,
                     morning;
    }

    public String getForecast(int day) {
        return this.date.toString();
    }
}
