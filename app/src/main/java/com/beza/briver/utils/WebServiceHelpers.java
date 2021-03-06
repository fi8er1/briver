package com.beza.briver.utils;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceHelpers {

    public static HttpURLConnection openConnectionForUrl(String path, String RequestType, boolean tokenRequired)
            throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod(RequestType);
        connection.setRequestProperty("charset", "utf-8");
        if (tokenRequired) {
            connection.setRequestProperty("Authorization", "Token " + AppGlobals.getToken());
        }
        return connection;
    }

    public static String readResponse(HttpURLConnection connection)
            throws IOException, JSONException {
        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();
        while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        return response.toString();
    }

}
