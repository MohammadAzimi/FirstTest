package com.example.msi.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.msi.myapplication.ApiService;
import com.example.msi.myapplication.DataFakeGenerator;
import com.example.msi.myapplication.DownloadImageTask;
import com.example.msi.myapplication.SampleDatabaseOpenHelper;
import com.example.msi.myapplication.adapter.NewsAdapter;
import com.example.msi.myapplication.R;
import com.example.msi.myapplication.datamodel.Post;

import java.util.ArrayList;
import java.util.List;

public class LastNewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_news);

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.news_progress_bar);

        recyclerViewSetup();
        ApiService apiService = new ApiService(this);
        progressBar.setVisibility(View.VISIBLE);
        apiService.getPosts(new ApiService.onPostsReceived() {
            @Override
            public void onReceived(List<Post> posts, String error) {
                if (posts != null) {
                    //adding posts to SQLite database
                    SampleDatabaseOpenHelper openHelper = new SampleDatabaseOpenHelper(LastNewsActivity.this);
                    //openHelper.clearPosts();
                    openHelper.addPosts(posts);

                    NewsAdapter newsAdapter = new NewsAdapter(LastNewsActivity.this, posts);
                    recyclerView.setAdapter(newsAdapter);
                    progressBar.setVisibility(View.INVISIBLE);

                    //saveImagesInSDCard();
                    //DownloadImageTask task = new DownloadImageTask(LastNewsActivity.this, )
                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LastNewsActivity.this,"خطا در دریافت اطالاعات از سرور"+"\n" + error, Toast.LENGTH_LONG).show();
                }
            }
        });



        /*RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        NewsAdapter newsAdapter = new NewsAdapter(this, );
        //NewsAdapter newsAdapter = new NewsAdapter(this, DataFakeGenerator.getData(this));
        // how to view
        //other types :
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2,LinearLayoutManager.VERTICAL, false));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredLinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)); //normal vertically
        recyclerView.setAdapter(newsAdapter);*/
    }

    private void recyclerViewSetup(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(LastNewsActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    private void getPostsFromDatabase(){
        SampleDatabaseOpenHelper databaseOpenHelper = new SampleDatabaseOpenHelper(this);
        List<Post> posts = databaseOpenHelper.getPosts();
        NewsAdapter newsAdapter = new NewsAdapter(this, posts);
        recyclerView.setAdapter(newsAdapter);

    }

    private void saveImagesInSDCard(){
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            urls.add(posts.get(i).getPostImageUrl());
        }
    }
}
