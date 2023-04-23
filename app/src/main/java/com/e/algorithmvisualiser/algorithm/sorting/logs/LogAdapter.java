package com.e.algorithmvisualiser.algorithm.sorting.logs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;

import java.util.Collections;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {

    Context context;
    List<LogData> list = Collections.emptyList();

    public LogAdapter(List<LogData> list, Context context)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater.inflate(R.layout.row_log_list, parent, false);

        LogViewHolder viewHolder = new LogViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LogViewHolder viewHolder, final int position)
    {
        viewHolder.LogText.setText(list.get(position).name);
        // Add Here Every thing.

        if(list.get(position).ImageAccess.toString().equals("3"))
        {
            viewHolder.BulletImage.setBackgroundResource(0);
        }
        else if(list.get(position).ImageAccess.toString().equals("2"))
        {
            viewHolder.BulletImage.setBackgroundResource(R.drawable.ic_baseline_play_arrow_black_24);
        }
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

}
