package com.airmap.sdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Vansh Gandhi on 6/17/16.
 */
public class Sdk {

    public void getFlightRules(String station, final Listener listener) {
        String url = "http://avwx.rest/api/metar.php?station=" + station + "&format=JSON";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject object = new JSONObject(json);
                    String rules = object.getString("Flight-Rules");
                    listener.onSuccess(rules);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
