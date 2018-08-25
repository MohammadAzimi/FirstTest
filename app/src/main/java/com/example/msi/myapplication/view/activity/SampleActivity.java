package com.example.msi.myapplication.view.activity;


import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.view.fragment.SampleFragment;
import com.example.msi.myapplication.view.fragment.SampleFragment2;

public class SampleActivity extends AppCompatActivity {
    private static final String TAG = "SampleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_sample);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction  transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, new SampleFragment());
        transaction.commit();

        final SampleFragment2 sampleFragment2 = new SampleFragment2();
        Button btnReplaceFragment = (Button)findViewById(R.id.replace_fragment);
        btnReplaceFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction replaceTransaction = fragmentManager.beginTransaction();
                replaceTransaction.replace(R.id.fragment_container, sampleFragment2);
                replaceTransaction.commit();
            }
        });

        Button btnRemoveFragment = (Button)findViewById(R.id.remove_fragment);
        btnRemoveFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction removeTransaction = fragmentManager.beginTransaction();
                removeTransaction.remove(sampleFragment2);
                removeTransaction.commit();
            }
        });

        Button btnShowDialog = (Button)findViewById(R.id.show_dialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SampleActivity.this);
                builder.setTitle("Title")
                        .setMessage("You have to choose one of the keys!\n" +
                                "No out_box_clicking for canceling!")
                        .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(SampleActivity.this, "اوکی رو میگیه", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(SampleActivity.this, "این همون کنسله مثلا", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }


}
