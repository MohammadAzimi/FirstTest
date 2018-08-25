package com.example.msi.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.datamodel.MenuItem;

import java.util.List;

/**
 * Created by msi on 7/15/2018.
 */
public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Typeface typeFace;
    private Context context;
    private List<MenuItem> menuItems;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_DEFAULT_ITEM = 1;

    public MenuAdapter(Context context, List<MenuItem> menuItems) {

        this.context = context;
        this.menuItems = menuItems;
        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/BYekan.ttf");
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_menu_top_banner, parent, false);
                return new MenuBannerViewHolder(view, typeFace);
            }
            case VIEW_TYPE_DEFAULT_ITEM: {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_menu, parent, false);
                return new MenuItemViewHolder(view, typeFace);
            }
            default:
                return null;
        }

    }

    //--- onBindViewHolder for times, when we wanna do some actions on items(holders)
    //--- for  example
    //--- 1. to set Listener for items
    //--- 2. to set data on each item like text or image (picasso)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MenuItemViewHolder){
            MenuItemViewHolder viewHolder = (MenuItemViewHolder) holder;
            viewHolder.bindMenuItem(menuItems.get(position - 1));
        }


    }

    @Override
    public int getItemCount() {
        return menuItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == VIEW_TYPE_HEADER) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_DEFAULT_ITEM;
        }
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView menuImage;
        private TextView menuTitle;


        public MenuItemViewHolder(View itemView, Typeface font) {
            super(itemView);
            menuImage = (ImageView) itemView.findViewById(R.id.menu_item_image);
            menuTitle = (TextView) itemView.findViewById(R.id.menu_item_title);

            menuTitle.setTypeface(font);

        }

        public void bindMenuItem(final MenuItem menuItem) {
            menuImage.setImageDrawable(menuItem.getMenuImage());
            menuTitle.setText(menuItem.getMenueTitleText());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(),
                            menuItem.getDestinationActivity()));
                }
            });
        }
    }

    public class MenuBannerViewHolder extends RecyclerView.ViewHolder {
        private TextView menuLabel;

        public MenuBannerViewHolder(View itemView, Typeface typeface) {
            super(itemView);
            menuLabel = (TextView) itemView.findViewById(R.id.label_list_menu_banner);
            menuLabel.setTypeface(typeface);
        }
    }
}
