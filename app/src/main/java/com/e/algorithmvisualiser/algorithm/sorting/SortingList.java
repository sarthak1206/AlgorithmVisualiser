package com.e.algorithmvisualiser.algorithm.sorting;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.searching.SearchingList;
import com.e.algorithmvisualiser.algorithm.sorting.bubblesort.BubbleMain;
import com.e.algorithmvisualiser.algorithm.sorting.insertionsort.InsertionMain;
import com.e.algorithmvisualiser.algorithm.sorting.quicksort.QuickMain;
import com.e.algorithmvisualiser.algorithm.sorting.selectionsort.SelectionMain;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class SortingList extends AppCompatActivity {

    private RecyclerView recyclerView;

    private SortingAdapter sortingAdapter;
    List<SortData> list = new ArrayList<>();

    private AdView mAdView;

    private String SortTitle[] = {
            "Bubble \nSort",
            "Insertion \nSort",
            "Selection \nSort",
            "Quick \nSort",
            "Merge \nSort"
    };

    private Integer SortImage[] = {
            R.drawable.bubble_icon,
            R.drawable.insertion_icon,
            R.drawable.selection_icon,
            R.drawable.quick_icon,
            R.drawable.merge_icon
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
        SortingList.this.setSupportActionBar(toolbar);
        SortingList.this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SortingList.this.getSupportActionBar().setTitle("Sorting");


        recyclerView=(RecyclerView) findViewById(R.id.list_report);

        for(int i=0;i<SortImage.length;i++)
        {
            list.add(new SortData(SortTitle[i], SortImage[i]));
        }

        sortingAdapter = new SortingAdapter(list, SortingList.this);
        recyclerView.setAdapter(sortingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SortingList.this));

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
