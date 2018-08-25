package com.example.msi.myapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.msi.myapplication.datamodel.Post;

import java.util.List;

public class AddPostsTask extends AsyncTask<Void, Integer, String> {

    private static final String TAG = "AddPostsTask";
    private Context context;
    private List<Post> posts;
    private SampleDatabaseOpenHelper openHelper;

    private ProgressDialog progressDialog;

    public AddPostsTask(Context context, List<Post> posts, SampleDatabaseOpenHelper openHelper){

        this.context = context;
        this.posts = posts;
        this.openHelper = openHelper;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("پردازش در حال انجام!");
        progressDialog.setMessage("در حال ذخیره سازی پست ها. لطفا منتظر بمانید...");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (int i=0; i<posts.size(); i++){
            ContentValues cv = new ContentValues();
            Post post = posts.get(i);
            cv.put(SampleDatabaseOpenHelper.COL_ID, post.getId());
            cv.put(SampleDatabaseOpenHelper.COL_TITLE, post.getTitle());
            cv.put(SampleDatabaseOpenHelper.COL_CONTENT, post.getContent());
            cv.put(SampleDatabaseOpenHelper.COL_POST_IMAGE_URL, post.getPostImageUrl());
            cv.put(SampleDatabaseOpenHelper.COL_IS_VISITED, post.isVisited());
            cv.put(SampleDatabaseOpenHelper.COL_DATE, post.getDate());

            SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
            long isInserted = sqLiteDatabase.insert(SampleDatabaseOpenHelper.POST_TABLE_NAME, null, cv);
            Log.i(TAG, "doInBackground: "+isInserted);
            publishProgress((i*100)/posts.size());
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }
}
