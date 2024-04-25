package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SendPostRequestTask extends AsyncTask<Void, Void, Void> {

    private String token;
    private Context context;
    public SendPostRequestTask(String token) {
        this.token = token;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Line Notify API URL
            URL url = new URL("https://notify-api.line.me/api/notify");

            // Create HttpURLConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            urlConnection.setDoOutput(true);

            // JSON data
            OutputStream os = urlConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");

            writer.write( "message=" + "message from android !" );
            writer.flush();
            writer.close();
            os.close();

            // Send the request
            urlConnection.connect();

            // Check the response code
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Request successful
                // Handle the response if needed
            } else {
                // Request failed
                // Handle the error
            }

            // Disconnect HttpURLConnection
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
