package com.example.hussain_pc.netpoint.NETPoint;


import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class NETPoint {
    private Map listOfEndPoints;

    public NETPoint() {
        // Initial capacity of 10, increases capacity by 20 when full
        listOfEndPoints = new Hashtable(10, 20);
    }

    /**
     * Add an network end-point to your list of connections
     *
     * @param httpUrl  An instance of NETUrl that is initialized to your desired URL
     * @param callback A class that inherits the NETCallback interface. The RequestComplete routine
     *                 will be invoked when an end-point is polled or sends a request
     */
    public boolean addEndPoint(NETUrl httpUrl, NETCallback callback) {
        if (httpUrl == null || callback == null || listOfEndPoints == null) {
            return false;
        } else {
            listOfEndPoints.put(httpUrl, callback);
            return true;
        }
    }

    public void pollAllEndPoints () {

    }

    public void pollSingleEndPoint () {

    }
}
