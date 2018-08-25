package com.example.msi.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.SampleDatabaseOpenHelper;
import com.example.msi.myapplication.datamodel.Post;
import com.example.msi.myapplication.view.activity.NewsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by msi on 8/13/2017.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<Post> posts;

    public NewsAdapter(Context context, List<Post> posts){

        this.context = context;
        this.posts = posts;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_news, parent, false);
        Typeface koodakFont = Typeface.createFromAsset(context.getAssets(),"fonts/koodak.TTF");
        return new NewsViewHolder(view, koodakFont);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final Post post = posts.get(position);
        //holder.newsImage.setImageDrawable(post.getPostImageUrl());
        Picasso.with(context).load(post.getPostImageUrl().replace("127.0.0.1","http://192.168.1.102")).into(holder.newsImage);
        holder.title.setText(post.getTitle());
        holder.content.setText(post.getContent());
        holder.date.setText(post.getDate());

        if (post.isVisited() == 1){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.post_visited));
        }
        else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.post_not_visited));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra(SampleDatabaseOpenHelper.COL_ID, post.getId());
                intent.putExtra(SampleDatabaseOpenHelper.COL_TITLE, post.getTitle());
                intent.putExtra(SampleDatabaseOpenHelper.COL_CONTENT, post.getContent());
                intent.putExtra(SampleDatabaseOpenHelper.COL_POST_IMAGE_URL, post.getPostImageUrl());
                intent.putExtra(SampleDatabaseOpenHelper.COL_DATE, post.getDate());
                intent.putExtra(SampleDatabaseOpenHelper.COL_IS_VISITED, post.isVisited());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
        /*if (posts!=null)
            return posts.size();
        else
            return 0;*/
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView newsImage;
        private TextView title;
        private TextView content;
        private TextView date;

        public NewsViewHolder(View itemView, Typeface font) {
            super(itemView);
            newsImage = (ImageView)itemView.findViewById(R.id.news_image);
            title = (TextView)itemView.findViewById(R.id.news_title);
            content = (TextView)itemView.findViewById(R.id.news_content);
            date = (TextView)itemView.findViewById(R.id.news_date);

            title.setTypeface(font);
            content.setTypeface(font);
            date.setTypeface(font);
        }
    }
}
