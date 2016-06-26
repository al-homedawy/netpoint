package com.example.netpoint;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class NETUrl {
    private String httpUrl;
    private Map httpParameters;
    private boolean httpJson;

    /**
     * @param httpUrl  Must be a url conforming to proper protocol
     *                 standards, ex: http://www.google.com
     * @param httpJson True if the data at the end-point is formatted
     *                 with JSon
     */
    public NETUrl(String httpUrl, boolean httpJson) {
        this.httpUrl = httpUrl;
        this.httpParameters = new LinkedHashMap();
        this.httpJson = httpJson;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public boolean getHttpJson() {
        return httpJson;
    }

    public void setHttpJson(boolean httpJson) {
        this.httpJson = httpJson;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public void clearParameters() {
        httpParameters.clear();
    }

    /**
     * Add or update a parameter. Note that parameters are passed into the URL
     * of the endpoint. For example, /foo.php?variable=bar
     *
     * @param key The name of the parameter to be passed into the URL.
     *            From our example above this would be "variable"
     */
    public void addParameter(String key) {
        httpParameters.put(key, null);
    }

    /**
     * Add or update a parameter. Note that parameters are passed into the URL
     * of the endpoint. For example, /foo.php?variable=bar
     *
     * @param key   The name of the parameter to be passed into the URL.
     *              From our example above this would be "variable"
     * @param value The value of the parameter that will be passed into the URL. Make sure that
     *              Object implements an appropriate ToString() value. From our example above this
     *              would be "bar"
     */
    public void addParameter(String key, Object value) {
        httpParameters.put(key, value);
    }

    public String getConnectionString() {
        Set parameters = httpParameters.keySet();

        StringBuilder URLBuilder = new StringBuilder(getHttpUrl());

        if (parameters.size() > 0) {
            if (URLBuilder.charAt(URLBuilder.length() - 1) != '?') {
                URLBuilder.append('?');
            }
        }

        int index = 0;

        for (Object parameter : parameters) {
            String param = parameter.toString();
            String value = httpParameters.get(parameter) != null ? httpParameters.get(parameter).toString() : null;

            if (index < parameters.size() &&
                    index > 0 && value != null) {
                URLBuilder.append("&");
            }

            if (value != null) {
                URLBuilder.append(param + "=" + value);
            }

            ++index;
        }

        return URLBuilder.toString();
    }
}
