package com.example.hussain_pc.netpoint.NETPoint;

public interface NETCallback {
    /**
     * This routine will be called when your callback is triggered.
     *
     * @param httpUrl The end-point that triggered your callback
     * @param receivedData The data parsed from reading that end-point
     */
    void RequestComplete ( NETUrl httpUrl, String receivedData );
}
