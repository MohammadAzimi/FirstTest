package com.example.msi.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.datamodel.Cloth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 8/16/2017.
 */
public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder> {


    private Context context;
    private List<Cloth> cloths;

    public ClothesAdapter(Context context, List<Cloth> cloths){

        this.context = context;
        this.cloths = cloths;
    }

    @Override
    public ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_clothing_item,parent,false);
        return new ClothesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClothesViewHolder holder, int position) {
        Cloth cloth = cloths.get(position);
        holder.clothImage.setImageDrawable(cloth.getImage());
        holder.clothTitle.setText(cloth.getTitle());
        holder.clothCount.setText(String.valueOf(cloth.getCount()));

    }

    @Override
    public int getItemCount() {
        return cloths.size();
    }


    public class ClothesViewHolder extends RecyclerView.ViewHolder{
        private ImageView clothImage;
        private TextView clothTitle;
        private TextView clothCount;

        public ClothesViewHolder(View itemView) {
            super(itemView);
            clothImage = (ImageView)itemView.findViewById(R.id.clothing_image);
            clothTitle = (TextView)itemView.findViewById(R.id.clothing_title);
            clothCount = (TextView)itemView.findViewById(R.id.clothing_view_count_text);
        }
    }
}
