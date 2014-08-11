package com.swiggins.today.fragment;

import android.app.Fragment;
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

public class CalendarFragment extends android.support.v4.app.Fragment {

    public CalendarFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_layout, container, false);

        // JSONCalendarTask calendarTask = new JSONCalendarTask();
        // calendarTask.execute();

        return v;
    }

}
