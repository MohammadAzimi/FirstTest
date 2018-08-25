package com.example.msi.myapplication.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.msi.myapplication.R;

import com.example.msi.myapplication.adapter.ClothesViewPagerAdapter;

public class ClothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        ClothesViewPagerAdapter adapter = new ClothesViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.clothes_toolbar);
        setSupportActionBar(toolbar);

        //To make a back bottom in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
