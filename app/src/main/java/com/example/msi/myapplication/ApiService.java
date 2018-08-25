package com.example.msi.myapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.msi.myapplication.datamodel.Post;
import com.example.msi.myapplication.datamodel.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 8/19/2017.
 */
public class ApiService {
    private static final String TAG = "ApiService";
    private Context context;

    public ApiService(Context context){

        this.context = context;
    }

    public void getCurrentWeather(final OnWeatherInfoReceived onWeatherInfoReceived, String cityName){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&apikey=47e0bce9de6545d6a35bb6ff9e64132b",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse:" + response.toString());
                onWeatherInfoReceived.onReceived(parseResponseToWeatherInfo(response));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.toString());
                onWeatherInfoReceived.onReceived(null);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(8000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(request);

    }

    public void getPosts(final onPostsReceived onPostsReceived){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "http://192.168.1.102/ApiService/GetPosts.php",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Post> posts = new ArrayList<>();
                for (int i = 0; i < response.length(); i++){
                    Post post = new Post();
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                            Log.i("JSONObject"," "+i);
                        post.setId(jsonObject.getInt("id"));
                            Log.i("id", " " + post.getId());
                        post.setTitle(jsonObject.getString("title"));
                        post.setContent(jsonObject.getString("content"));
                        post.setDate(jsonObject.getString("my_date"));
                        post.setPostImageUrl(jsonObject.getString("image_url"));

                        posts.add(post);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                onPostsReceived.onReceived(posts, "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse : " + error.toString());
                onPostsReceived.onReceived(null , error.toString());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    private WeatherInfo parseResponseToWeatherInfo(JSONObject response){
        WeatherInfo weatherInfo = new WeatherInfo();
        try {
            JSONArray weatherJsonArray = response.getJSONArray("weather");
            JSONObject weatherJsonObject = weatherJsonArray.getJSONObject(0);
            weatherInfo.setId(weatherJsonObject.getInt("id"));
            weatherInfo.setWeatherName(weatherJsonObject.getString("main"));
            weatherInfo.setWeatherDescription(weatherJsonObject.getString("description"));
            JSONObject mainJsonObject = response.getJSONObject("main");
            weatherInfo.setWeatherTemperature((float) mainJsonObject.getDouble("temp"));
            weatherInfo.setHumidity(mainJsonObject.getInt("humidity"));
            weatherInfo.setPressure(mainJsonObject.getInt("pressure"));
            weatherInfo.setMinTemperature((float) mainJsonObject.getDouble("temp_min"));
            weatherInfo.setMaxTemperature((float) mainJsonObject.getDouble("temp_max"));
            JSONObject windJsonObject = response.getJSONObject("wind");
            weatherInfo.setWindSpeed((float) windJsonObject.getDouble("speed"));
            weatherInfo.setWindDegree((float)windJsonObject.getDouble("deg"));


            return weatherInfo;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void signUpUser(JSONObject requestJsonObject, final OnSignUpComplete onSignUpComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "www.google.com", requestJsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        onSignUpComplete.onSignUp(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onSignUpComplete.onSignUp(false);
                    }
                });
        request.setRetryPolicy(new DefaultRetryPolicy(8000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public interface OnSignUpComplete{
        void onSignUp(boolean success);
    }

    public interface OnWeatherInfoReceived {
        void onReceived(WeatherInfo weatherInfo);
    }

    public interface onPostsReceived {
        void onReceived(List<Post> posts, String error);
    }
}
