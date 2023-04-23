package com.e.algorithmvisualiser;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.algorithm.AlgoList;
import com.e.algorithmvisualiser.algorithm.sorting.bubblesort.BubbleMain;
import com.e.algorithmvisualiser.algorithm.sorting.insertionsort.InsertionMain;
import com.e.algorithmvisualiser.algorithm.sorting.quicksort.QuickMain;
import com.e.algorithmvisualiser.algorithm.sorting.selectionsort.SelectionMain;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView LogText;
    ImageView BulletImage;
    View view;

    HomeViewHolder(View itemView)
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
            Intent intent = new Intent(view.getContext(), AlgoList.class);
            view.getContext().startActivity(intent);
        }
        else if(pos==1)
        {
            Toast.makeText(view.getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
        }
    }
}
