package com.example.msi.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BroadCastReceiver", "onReceive called with : "+ "context = ["+context+" ] intent = ["+intent+"]");
    }
}
