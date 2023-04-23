package com.e.algorithmvisualiser.algorithm.searching.logs;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;

public class LogViewHolder extends RecyclerView.ViewHolder {

    TextView LogText;
    ImageView BulletImage;
    View view;

    LogViewHolder(View itemView)
    {
        super(itemView);

        LogText = (TextView) itemView.findViewById(R.id.log_text);
        BulletImage = (ImageView) itemView.findViewById(R.id.img_bullet);

        view = itemView;
    }
}
