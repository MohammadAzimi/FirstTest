package com.example.msi.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadImageTask extends AsyncTask<Void, Integer, Void> {

    private Context context;
    List<String> urls = new ArrayList<>();

    public DownloadImageTask(Context context, String url) {
        urls.add(url);
        this.context = context;
    }

    public DownloadImageTask(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < urls.size(); i++) {
            try {
                Bitmap bitmap = Picasso.with(context).load(urls.get(i)).get();
                String url = urls.get(i);

                //This File is a pointer for SDCard Direction
                File extImageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                String imageName = url.substring(url.lastIndexOf("/")+1, url.length());

                //This File is an Image which name is imageName and is placed at extImageDir
                File image = new File(extImageDir, imageName);
                FileOutputStream fos = new FileOutputStream(image);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
