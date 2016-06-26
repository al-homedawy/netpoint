package com.example.hussain_pc.netpoint;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netpoint.NETCallback;
import com.example.netpoint.NETPoint;
import com.example.netpoint.NETUrl;

import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    public class testingClass implements NETCallback {
        public testingClass() {

        }

        @Override
        public void RequestComplete(NETUrl httpUrl, String receivedData) {
            Log.d("RequestComplete ", "I've been called");
        }

        @Override
        public void RequestComplete(NETUrl httpUrl, JSONObject jsonData) {
            Log.d("RequestComplete ", "I've been called");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Initialize our NETPoint class
        NETPoint netPoint = new NETPoint();

        // Create our end-points
        NETUrl netUrl = new NETUrl("http://www.google.com", true);
        NETUrl netUrl2 = new NETUrl("http://www.facebook.com", false);
        NETUrl netUrl3 = new NETUrl("http://www.al-homedawy.com", false);
        NETUrl netUrl4 = new NETUrl("http://maps.googleapis.com/maps/api/geocode/json", true);
        netUrl4.addParameter("latlng", "0");
        netUrl4.addParameter("sensor", "false");

        // Poll a single end-point
        netPoint.pollSingleEndPoint(netUrl4, new testingClass());

        // Add our end-points to our net-point class
        netPoint.addEndPoint(netUrl, new testingClass());
        netPoint.addEndPoint(netUrl2, new testingClass());
        netPoint.addEndPoint(netUrl3, new testingClass());
        netPoint.addEndPoint(netUrl4, new testingClass());
        netPoint.pollAllEndPoints();


        return view;
    }
}
