package com.example.covntech;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class worldQuery {

    public static final String LOG_TAG = MainActivity.class.getName();

    /**
         * Create a private constructor because no one should ever create a {@link worldQuery} object.
         * This class is only meant to hold static variables and methods, which can be accessed
         * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
         */
        private worldQuery() {
        }

        public static List<dataFormat> fetchWorldData(String requestUrl) {

            // Create URL object
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            URL url = createUrl(requestUrl);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = null;
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }

            // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
            List<dataFormat> worldData = extractFeatureFromJson(jsonResponse);

            // Return the list of {@link Earthquake}s
            return worldData;
        }
    public static List<dataFormat> fetchIndiaData(String requestUrl) {

        // Create URL object
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<dataFormat> indiaData = extractFeatureFromIndiaJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return indiaData;
    }

        private static URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Problem building the URL ", e);
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private static String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // Closing the input stream could throw an IOException, which is why
                    // the makeHttpRequest(URL url) method signature specifies than an IOException
                    // could be thrown.
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private static String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }


        public static List<dataFormat> extractFeatureFromJson(String dataJSON) {

            if (TextUtils.isEmpty(dataJSON)) {
                return null;
            }

            // Create an empty ArrayList that we can start adding earthquakes to
            List<dataFormat> dataStore = new ArrayList<>();

            try {
                JSONObject root = new JSONObject(dataJSON);
                JSONArray countryArray = root.getJSONArray("Countries");
                for(int i =0;i<countryArray.length();i++) {

                    JSONObject countryArrayObject = countryArray.getJSONObject(i);
                    String countryName = countryArrayObject.getString("Country");
                    long countryConfirmed = countryArrayObject.getLong("TotalConfirmed");
                    long countryRecovered = countryArrayObject.getLong("TotalRecovered");
                    long countryDeath = countryArrayObject.getLong("TotalDeaths");

                    dataStore.add(new dataFormat(countryName,countryConfirmed,countryRecovered,countryDeath));
                }
                JSONObject worldWide= root.getJSONObject("Global");
                long totalConfirmed = worldWide.getLong("TotalConfirmed");
                long totalRecovered = worldWide.getLong("TotalDeaths");
                long totalDeath = worldWide.getLong("TotalRecovered");
                System.out.println("print"+totalConfirmed +"\t"+  totalDeath +"\t" + totalRecovered);
                dataStore.add(new dataFormat(totalConfirmed,totalRecovered,totalDeath));
                }
            catch (JSONException e) {
//                 If an error is thrown when executing any of the above statements in the "try" block,
//                 catch the exception here, so the app doesn't crash. Print a log message
//                 with the message from the exception.
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            }
            // Return the list of earthquakes
            return dataStore;
        }
    public static List<dataFormat> extractFeatureFromIndiaJson(String indiadataJSON) {

        if (TextUtils.isEmpty(indiadataJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<dataFormat> indiaDataStore = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(indiadataJSON);
            JSONObject dataIndia =root.getJSONObject("data");
            JSONArray StateArray = dataIndia.getJSONArray("regional");
            for (int i = 0; i < StateArray.length(); i++) {

                JSONObject stateArrayObject = StateArray.getJSONObject(i);
                String stateName = stateArrayObject.getString("loc");
                long stateConfirmed = stateArrayObject.getLong("totalConfirmed");
                long stateRecovered = stateArrayObject.getLong("discharged");
                long stateDeath = stateArrayObject.getLong("deaths");
                indiaDataStore.add(new dataFormat(stateName, stateConfirmed, stateRecovered, stateDeath));
            }
        }
        catch (JSONException e) {
//                 If an error is thrown when executing any of the above statements in the "try" block,
//                 catch the exception here, so the app doesn't crash. Print a log message
//                 with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        // Return the list of earthquakes
        return indiaDataStore;
    }
}

