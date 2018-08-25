package com.example.msi.myapplication;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.msi.myapplication.datamodel.WeatherInfo;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider {
    public static final String INTENT_ACTION_UPDATE_DATA="com.example.msi.myapplication.UPDATE_DATA";


    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, WeatherInfo weatherInfo) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        views.setTextViewText(R.id.text_weather_temp, String.valueOf((int)weatherInfo.getWeatherTemperature()) + " \u00b0");

        switch (weatherInfo.getId() / 100) {
            case 2:
                views.setImageViewResource(R.id.image_weather_icon, R.drawable.adidas);
                break;
            case 3:
                break;
            default:
                break;
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        ApiService apiService = new ApiService(context);
        apiService.getCurrentWeather(new ApiService.OnWeatherInfoReceived() {
            @Override
            public void onReceived(WeatherInfo weatherInfo) {
                if (weatherInfo != null) {
                    for (int appWidgetId : appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId, weatherInfo);
                    }
                }
            }
        }, "Tehran");


    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(INTENT_ACTION_UPDATE_DATA)){
            WeatherInfo weatherInfo = intent.getParcelableExtra("data");
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

