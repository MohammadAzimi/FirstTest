package com.example.msi.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.msi.myapplication.ApiService;
import com.example.msi.myapplication.datamodel.WeatherInfo;

public class WeatherInfoDownloaderService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ApiService apiService = new ApiService(this);
        apiService.getCurrentWeather(new ApiService.OnWeatherInfoReceived() {
            @Override
            public void onReceived(WeatherInfo weatherInfo) {
                if (weatherInfo!=null){
                    //we want to broadCast weatherInfo so we need Intent
                    Intent sendDataIntent1 = new Intent();
                    sendDataIntent1.putExtra("data",weatherInfo);
                }
            }
        }, "Tehran");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
