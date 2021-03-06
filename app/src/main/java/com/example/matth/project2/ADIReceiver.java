package com.example.matth.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class used to communicate with openweatherapi using the methods AsyncTask and volley
 * @author Matthias Falk
 */
public class ADIReceiver {
    private JSONObject data = new JSONObject();
    private Controller controller;

    /**
     * Constructor that recieves an instance of the controller
     * @param controller - the active instance of controller
     */
    public ADIReceiver(Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts up the AsyncTask reciever
     * @param town - the town of which we will recieve weather information about
     */
    public void startAsync(String town) {
        new GetViaAsync(town);
    }

    /**
     * Starts up the volley reciever
     * @param town - the town of which we will recieve weather information about
     * @param context - the active context
     */
    public void startVolley(String town, Context context){
        new GetViaVolley(town, context);
    }

    /**
     * Inner class that uses AsyncTask to recieve weather information
     * @author Matthias Falk
     */
    private class GetViaAsync {

        /**
         * Constructor that recieves an String of the town we want information about and starts the method getJSON()
         * @param town - the town of which we will recieve weather information about
         */
        private GetViaAsync(String town) {
            getJSON(town);
        }

        /**
         * gets an JSON from openweathermap.org
         * @param city - the city of which we will recieve weather information about
         */
        @SuppressLint("StaticFieldLeak")
        public void getJSON(final String city) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=21a384f58d255ec46273ff67f04c8660");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        BufferedReader reader =
                                new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuffer json = new StringBuffer(1024);
                        String tmp;
                        while ((tmp = reader.readLine()) != null)
                            json.append(tmp).append("\n");
                        reader.close();
                        data = new JSONObject(json.toString());
                        if (data.getInt("cod") != 200) {
                            System.out.println("Cancelled");
                            return null;
                        }
                    } catch (Exception e) {
                        System.out.println("Exception " + e.getMessage());
                        return null;
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(Void Void) {
                    if (data != null) {
                        controller.jsonReader(data, "ASYNCTask");
                    }
                }
            }.execute();
        }
    }

    /**
     *Inner class that uses Volley to recieve weather information
     *@author Matthias Falk
     *
     */
    private class GetViaVolley {

        /**
         * Constructor that recieves an String of the town we want information about and starts the method getJSON()
         * @param town - the town of which we will recieve weather information about
         * @param context - the active context
         */
        private GetViaVolley(String town, Context context) {
            getJSON(town, context);
        }

        /**
         * Gets an JSON from openweathermap.org
         * @param city - the city of which we will recieve weather information about
         * @param context - the active context
         */
        public void getJSON(final String city, final Context context) {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=21a384f58d255ec46273ff67f04c8660";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            controller.jsonReader(response, "Volley");
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });
            queue.add(jsonObjectRequest);
            queue.start();
        }
    }
}
