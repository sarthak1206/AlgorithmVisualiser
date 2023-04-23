package com.e.algorithmvisualiser.algorithm.searching;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.sorting.SortData;
import com.e.algorithmvisualiser.algorithm.sorting.SortViewHolder;

import java.util.Collections;
import java.util.List;


public class SearchingAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    List<SearchData> list = Collections.emptyList();
    public SearchingAdapter(List<SearchData> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater.inflate(R.layout.activity_sort_adapter, parent, false);

        SearchViewHolder viewHolder = new SearchViewHolder(photoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder viewHolder, final int position)
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
