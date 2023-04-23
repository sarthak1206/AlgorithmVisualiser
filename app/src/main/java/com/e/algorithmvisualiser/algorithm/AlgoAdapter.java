package com.e.algorithmvisualiser.algorithm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;

import java.util.Collections;
import java.util.List;

public class AlgoAdapter extends RecyclerView.Adapter<AlgoViewHolder>{

    private Context context;
    List<AlgoData> list = Collections.emptyList();
    public AlgoAdapter(List<AlgoData> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AlgoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater.inflate(R.layout.activity_home_adapter, parent, false);

        AlgoViewHolder viewHolder = new AlgoViewHolder(photoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AlgoViewHolder viewHolder, final int position)
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
