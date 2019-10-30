package com.saadatdevelopment.lifepulse.searchdialognew.apiController;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIController {
    private final String TAG = "APIController";

    public APIController()
    {

    }

    public String makeApiCall(String endpoint)
    {
        String response = "";

        try
        {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            response = stringBuilder.toString();
        }
        catch (MalformedURLException malformedException)
        {
            Log.e(TAG, malformedException.getMessage(), malformedException);
        }
        catch (IOException exception)
        {
            Log.e(TAG, exception.getMessage(), exception);
        }
        return response;
    }
}
