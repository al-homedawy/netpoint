package com.example.netpoint;

import org.json.JSONObject;

public interface NETCallback {
    /**
     * This routine will be called when your callback is triggered. It will
     * display the raw data retrieved from the end-point. This is only called
     * if 'httpJson' is set to false in httpUrl or if there was an error in formatting
     * JSON data
     *
     * @param httpUrl The end-point that triggered your callback
     * @param rawData The data parsed from reading that end-point
     */
    void RequestComplete(NETUrl httpUrl, String rawData);

    /**
     * This routine will be called when your callback is triggered. It will
     * provide data formatted in json. This routine will only be called if
     * 'httpJson' is set to true in httpUrl
     *
     * @param httpUrl  The end-point that triggered your callback
     * @param jsonData The data parsed from reading that end-point formatted in JSon
     */
    void RequestComplete(NETUrl httpUrl, JSONObject jsonData);
}
