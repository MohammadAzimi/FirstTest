package com.example.msi.myapplication.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.msi.myapplication.DataFakeGenerator;
import com.example.msi.myapplication.R;
import com.example.msi.myapplication.adapter.MenuAdapter;

public class MainActivity extends AppCompatActivity {

    private MenuAdapter menuAdapter;
    private ConnectivityListener connectivityListener;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar connectivityMessageSnackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        connectivityListener = new ConnectivityListener();
        registerReceiver(connectivityListener,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(connectivityListener);
    }


    private void setUpViews() {
        setUpRecyclerView();
        setUpToolbar();
        setUpNavigationView();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_activity_coordinator_layout);
        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.float_action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(coordinatorLayout,"شناور کلیک شد!", Snackbar.LENGTH_LONG)
                        .setAction("دوباره کلیک کن! -_- ", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "کلیک دوباره انجام شد", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    private void setUpRecyclerView() {
        RecyclerView menuRecyclerView = (RecyclerView) findViewById(R.id.menu_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 2;
                else
                    return 1;
            }
        });
        menuRecyclerView.setLayoutManager(gridLayoutManager);
        menuAdapter = new MenuAdapter(this, DataFakeGenerator.getMenuItem(this));
        menuRecyclerView.setAdapter(menuAdapter);
    }

    private void setUpToolbar() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, 0, 0);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void setUpNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;
                    case R.id.navigation_menu_store:
                        startActivity(new Intent(MainActivity.this, ClothActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    private class ConnectivityListener extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = networkInfo!=null && networkInfo.isConnected();

            if (isConnected){
                if (connectivityMessageSnackbar!=null) connectivityMessageSnackbar.dismiss();
            }else {
                connectivityMessageSnackbar = Snackbar.make(coordinatorLayout,"No INTERNET connection",Snackbar.LENGTH_INDEFINITE);
                connectivityMessageSnackbar.show();
            }
        }
    }
}
