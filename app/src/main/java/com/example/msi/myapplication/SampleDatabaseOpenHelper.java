package com.example.msi.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.msi.myapplication.datamodel.Post;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by msi on 5/31/2018.
 */
public class SampleDatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseOpenHelper";


    private Context context;

    private static final String DATABASE_NAME = "db_sample";
    private static final int DATABASE_VERSION = 1;

    public static final String POST_TABLE_NAME = "tbl_post";
    public static final String COL_ID = "col_id";
    public static final String COL_TITLE = "col_title";
    public static final String COL_CONTENT = "col_content";
    public static final String COL_POST_IMAGE_URL = "col_post_image_url";
    public static final String COL_IS_VISITED = "col_is_visited";
    public static final String COL_DATE = "col_date";


    private static final String SQL_COMMAND_CREATE_POST_TABLE = "CREATE TABLE IF NOT EXISTS " +POST_TABLE_NAME+"(" +
            COL_ID + " INTEGER PRIMARY KEY,"+
            COL_TITLE + " TEXT,"+
            COL_CONTENT + " TEXT,"+
            COL_POST_IMAGE_URL + " TEXT,"+
            COL_IS_VISITED + " INTEGER DEFAULT 0,"+
            COL_DATE + " TEXT);";


    public SampleDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_COMMAND_CREATE_POST_TABLE);
        }
        catch (SQLException e) {
            Log.e(TAG, "onCreate: "+e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public boolean addPost(Post post){
//        ContentValues cv = new ContentValues();
//        cv.put(COL_ID, post.getId());
//        cv.put(COL_TITLE, post.getTitle());
//        cv.put(COL_CONTENT, post.getContent());
//        cv.put(COL_POST_IMAGE_URL, post.getPostImageUrl());
//        cv.put(COL_IS_VISITED, post.isVisited());
//        cv.put(COL_DATE, post.getDate());
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long isInserted = sqLiteDatabase.insert(POST_TABLE_NAME, null, cv);
//
//
//        if (isInserted>0){
//            Log.i(TAG, "addPost: "+isInserted);
//            return true;
//        }
//        else{
//            Log.i(TAG, "addPost: "+isInserted);
//            return false;
//        }
//    }

    public void addPosts(List<Post> posts){
//        for (int i = 0; i < posts.size(); i++) {
//            if(!checkPostExist(posts.get(i).getId())){
//                addPost(posts.get(i));
//            }
//        }
        AddPostsTask addPostsTask = new AddPostsTask(context, posts, this);
        addPostsTask.execute();
    }

    public List<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+POST_TABLE_NAME, null);
        if (cursor.getCount()>0){
            while(!cursor.isAfterLast()){
                Post post = new Post();
                post.setId(cursor.getInt(0));
                post.setTitle(cursor.getString(1));
                post.setContent(cursor.getString(2));
                post.setPostImageUrl(cursor.getString(3));
                post.setDate(cursor.getString(4));
                post.setIsVisited(cursor.getInt(5));
                posts.add(post);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
            return posts;
        }
        else{
            return posts;
        }
    }

    public void setPostIsVisited(int postId, int isVisited){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_IS_VISITED, isVisited);
        int rowAffected = sqLiteDatabase.update(POST_TABLE_NAME, cv, COL_ID + " = ?", new String[]{String.valueOf(postId)});
        sqLiteDatabase.close();
        Log.i(TAG, "setPostIsVisited: rowAffected "+rowAffected);
    }

    public void clearPosts(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long isAllDeleted = sqLiteDatabase.delete(POST_TABLE_NAME, null, null);
        Log.i(TAG, "clearPosts: "+isAllDeleted);
    }

    private boolean checkPostExist(int postId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +
                POST_TABLE_NAME + " WHERE " + COL_ID + " = ?"
                , new String[]{String.valueOf(postId)});
        return cursor.moveToFirst();
    }
}
