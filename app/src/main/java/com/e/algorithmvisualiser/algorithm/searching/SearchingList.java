package com.e.algorithmvisualiser.algorithm.searching;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.AlgoList;
import com.e.algorithmvisualiser.algorithm.sorting.SortData;
import com.e.algorithmvisualiser.algorithm.sorting.SortingAdapter;
import com.e.algorithmvisualiser.algorithm.sorting.SortingList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class SearchingList extends AppCompatActivity {

    private RecyclerView recyclerView;

    private SearchingAdapter searchingAdapter;

    List<SearchData> list = new ArrayList<>();

    private AdView mAdView;

    private String SearchTitle[] = {
            "Linear \nSearch",
            "Binary \nSearch"
    };

    private Integer SearchImage[] = {
            R.drawable.linearsearch_icon,
            R.drawable.binarysearch_icon
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
        SearchingList.this.setSupportActionBar(toolbar);
        SearchingList.this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SearchingList.this.getSupportActionBar().setTitle("Searching");


        recyclerView=(RecyclerView) findViewById(R.id.list_report);

        for(int i=0;i<SearchImage.length;i++)
        {
            list.add(new SearchData(SearchTitle[i], SearchImage[i]));
        }

        searchingAdapter = new SearchingAdapter(list, SearchingList.this);
        recyclerView.setAdapter(searchingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchingList.this));

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
