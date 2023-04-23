package com.e.algorithmvisualiser.algorithm;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class AlgoList extends AppCompatActivity {
    private RecyclerView recyclerView;

    private AlgoAdapter algoAdapter;
    List<AlgoData> list = new ArrayList<>();

    private AdView mAdView;

    private String AlgoTitle[] = {
            "Sorting",
            "Searching",
            "Stack"
    };

    private Integer AlgoImage[] = {
            R.drawable.sort_bg,
            R.drawable.search_bg,
            R.drawable.sort_bg
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sorting);

        if(Build.VERSION.SDK_INT >=21)
        {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        Toolbar toolbar = findViewById(R.id.action_bar);
        AlgoList.this.setSupportActionBar(toolbar);
        AlgoList.this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AlgoList.this.getSupportActionBar().setTitle("Data Structures");

        recyclerView=(RecyclerView) findViewById(R.id.list_report);

        for(int i=0;i<AlgoImage.length;i++)
        {
            list.add(new AlgoData(AlgoTitle[i], AlgoImage[i]));
        }

        algoAdapter = new AlgoAdapter(list, AlgoList.this);
        recyclerView.setAdapter(algoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AlgoList.this));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
