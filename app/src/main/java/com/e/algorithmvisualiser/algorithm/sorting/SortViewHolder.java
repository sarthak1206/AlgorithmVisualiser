package com.e.algorithmvisualiser.algorithm.sorting;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.sorting.bubblesort.BubbleMain;
import com.e.algorithmvisualiser.algorithm.sorting.insertionsort.InsertionMain;
import com.e.algorithmvisualiser.algorithm.sorting.quicksort.QuickMain;
import com.e.algorithmvisualiser.algorithm.sorting.selectionsort.SelectionMain;

public class SortViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView LogText;
    ImageView BulletImage;
    View view;

    SortViewHolder(View itemView)
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
            Intent intent = new Intent(view.getContext(), BubbleMain.class);
            view.getContext().startActivity(intent);
        }
        else if(pos==1)
        {
            Intent intent = new Intent(view.getContext(), InsertionMain.class);
            view.getContext().startActivity(intent);
        }
        else if(pos==2)
        {
            Intent intent = new Intent(view.getContext(), SelectionMain.class);
            view.getContext().startActivity(intent);
        }
        else if(pos==3)
        {
            Intent intent = new Intent(view.getContext(), QuickMain.class);
            view.getContext().startActivity(intent);
        }
        else if(pos==4)
        {

        }
    }
}
