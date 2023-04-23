package com.e.algorithmvisualiser.algorithm.sorting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.sorting.logs.LogData;
import com.e.algorithmvisualiser.algorithm.sorting.logs.LogViewHolder;

import java.util.Collections;
import java.util.List;

public class SortingAdapter extends RecyclerView.Adapter<SortViewHolder> {


    private Context context;
    List<SortData> list = Collections.emptyList();
    public SortingAdapter(List<SortData> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SortViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater.inflate(R.layout.activity_sort_adapter, parent, false);

        SortViewHolder viewHolder = new SortViewHolder(photoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SortViewHolder viewHolder, final int position)
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
