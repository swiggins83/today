package com.swiggins.today.model;

import java.util.List;
import java.util.ArrayList;

public class WeatherForecast {

    private List<DayForecast> daysForecast = new ArrayList<DayForecast>();

    public void addForecast(DayForecast forecast) {
        daysForecast.add(forecast);
        System.out.println("Add forecast ["+forecast+"]");
    }

    public DayForecast getForecast(int day) {
        return daysForecast.get(day);
    }
}
