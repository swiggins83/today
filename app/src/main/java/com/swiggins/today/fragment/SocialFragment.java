package com.swiggins.today.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swiggins.today.R;

public class SocialFragment extends android.support.v4.app.Fragment {

    public SocialFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.social_layout, container, false);

        // JSONSocialTask socialTask = new JSONSocialTask();
        // socialTask.execute();

        return v;
    }

}
