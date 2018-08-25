package com.example.msi.myapplication.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.SampleDatabaseOpenHelper;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();

        int id = intent.getIntExtra(SampleDatabaseOpenHelper.COL_ID, 0);
        setPostVisited(id);

        String title = intent.getStringExtra(SampleDatabaseOpenHelper.COL_TITLE);
        String content = intent.getStringExtra(SampleDatabaseOpenHelper.COL_CONTENT);
        String imageUrl = intent.getStringExtra(SampleDatabaseOpenHelper.COL_POST_IMAGE_URL);
        String date = intent.getStringExtra(SampleDatabaseOpenHelper.COL_DATE);
        //int visited = intent.getIntExtra(SampleDatabaseOpenHelper.COL_IS_VISITED, 0);

        ImageView newsImage = (ImageView)findViewById(R.id.news_image);
        TextView newsTitle = (TextView)findViewById(R.id.news_title);
        TextView newsContent = (TextView)findViewById(R.id.news_content);
        TextView newsDate = (TextView)findViewById(R.id.news_date);

        Picasso.with(this).load(imageUrl).into(newsImage);
        newsTitle.setText(title);
        newsContent.setText(content);
        newsDate.setText(date);

        Typeface koodakFont = Typeface.createFromAsset(this.getAssets(),"fonts/koodak.TTF");
        newsTitle.setTypeface(koodakFont);
        newsContent.setTypeface(koodakFont);
        newsDate.setTypeface(koodakFont);
    }

    private void setPostVisited(int postId){
        SampleDatabaseOpenHelper databaseOpenHelper = new SampleDatabaseOpenHelper(this);
        databaseOpenHelper.setPostIsVisited(postId, 1);
    }
}
