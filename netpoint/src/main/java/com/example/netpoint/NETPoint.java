package com.example.netpoint;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
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

    /**
     * Retrieve information from all end-points that have been added to the NETPoint class. This
     * will trigger each callback corresponding to each end-point.
     */
    public void pollAllEndPoints() {
        Object[] objEndPoints = listOfEndPoints.keySet().toArray();

        for (Object objEndPoint : objEndPoints) {
            NETUrl httpUrl = (NETUrl) objEndPoint;
            pollSingleEndPoint(httpUrl);
        }
    }

    /**
     * Retreive information from an end-point on your server
     *
     * @param httpUrl An instance of NETUrl setup to your end-point. Ensure that
     *                parameters are added to your httpUrl instance if they are required
     *                by your end-point
     */
    public void pollSingleEndPoint(NETUrl httpUrl) {
        if (httpUrl == null) {
            return;
        }

        NETCallback callback = (NETCallback) listOfEndPoints.get(httpUrl);

        if (callback != null) {
            // Download data from the cloud and execute callback
            new DownloadFromCloud().execute(httpUrl, callback);
        }
    }

    public void pollSingleEndPoint(NETUrl httpUrl, NETCallback callback) {
        if (httpUrl == null) {
            return;
        }

        if (callback != null) {
            // Download data from the cloud and execute callback
            new DownloadFromCloud().execute(httpUrl, callback);
        }
    }

    private class DownloadFromCloud extends AsyncTask<Object, Void, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            final NETUrl httpUrl = (NETUrl) params[0];
            final NETCallback urlCallback = (NETCallback) params[1];

            // Obtain the connection url
            String urlConnection = httpUrl.getConnectionString();


            // Connect and download data
            try {
                final String responseFromCloud = downloadUrl(urlConnection);

                // Invoke the callback in another thread and finish
                Thread callbackThread = new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        // Invoke the callback
                        if (httpUrl.getHttpJson() == false) {
                            urlCallback.RequestComplete(httpUrl, responseFromCloud);
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(responseFromCloud);
                                urlCallback.RequestComplete(httpUrl, jsonObject);
                            } catch (Exception e) {
                                urlCallback.RequestComplete(httpUrl, responseFromCloud);
                            }
                        }
                    }
                };

                callbackThread.start();
            } catch (Exception e) {
                Log.d("DownloadFromCloud", "downloadUrl failed");
            }

            return null;
        }

        private String downloadUrl(String httpUrl) throws IOException {
            InputStream inputStream = null;

            try {
                URL url = new URL(httpUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Setup our connection
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(10000);

                // Connect and obtain response from connection
                connection.connect();

                inputStream = connection.getInputStream();

                if (inputStream != null) {
                    String response = readInputStream(inputStream);

                    // Close the input stream and return
                    inputStream.close();
                    return response;

                } else {
                    Log.d("DownloadFromCloud", "Failed to open an input stream");
                }
            } catch (Exception e) {
                Log.d("DownloadFromCloud", "An exception has occured " + e.getMessage());
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }

            return null;
        }

        private String readInputStream(InputStream stream) throws IOException, UnsupportedEncodingException {
            StringBuilder response = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

            String line = null;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        }
    }
}
