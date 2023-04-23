package com.e.algorithmvisualiser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.algorithm.searching.SearchData;
import com.e.algorithmvisualiser.algorithm.searching.SearchViewHolder;

import java.util.Collections;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder>{

    private Context context;
    List<HomeData> list = Collections.emptyList();
    public HomeAdapter(List<HomeData> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater.inflate(R.layout.activity_home_adapter, parent, false);

        HomeViewHolder viewHolder = new HomeViewHolder(photoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder viewHolder, final int position)
    {
        viewHolder.LogText.setText(list.get(position).name);
        viewHolder.BulletImage.setImageResource(list.get(position).ImageAccess);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }


}
