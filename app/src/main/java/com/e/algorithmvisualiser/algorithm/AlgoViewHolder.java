package com.e.algorithmvisualiser.algorithm;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.searching.SearchingList;
import com.e.algorithmvisualiser.algorithm.sorting.SortingList;
import com.e.algorithmvisualiser.algorithm.stack.StackMain;

public class AlgoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView LogText;
    ImageView BulletImage;
    View view;

    AlgoViewHolder(View itemView)
    {
        super(itemView);

        LogText = (TextView) itemView.findViewById(R.id.text_sort_title);
        BulletImage = (ImageView) itemView.findViewById(R.id.img_sort_title);

        itemView.setOnClickListener(this);
        view = itemView;
    }

    @Override
    public void onClick(View view) {
        int pos = getLayoutPosition();

        if(pos==0)
        {
            Intent intent = new Intent(view.getContext(), SortingList.class);
            view.getContext().startActivity(intent);
        }
        else if(pos==1)
        {
            Intent intent = new Intent(view.getContext(), SearchingList.class);
            view.getContext().startActivity(intent);
        }
        else if(pos==2)
        {
            Intent intent = new Intent(view.getContext(), StackMain.class);
            view.getContext().startActivity(intent);
        }
    }
}
