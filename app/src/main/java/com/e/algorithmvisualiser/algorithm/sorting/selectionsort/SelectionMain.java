package com.e.algorithmvisualiser.algorithm.sorting.selectionsort;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.sorting.quicksort.QuickMain;
import com.e.algorithmvisualiser.algorithm.sorting.quicksort.fragment.QCodeFragment;
import com.e.algorithmvisualiser.algorithm.sorting.quicksort.fragment.QDetailFragment;
import com.e.algorithmvisualiser.algorithm.sorting.quicksort.fragment.QExecutionFragment;
import com.e.algorithmvisualiser.algorithm.sorting.selectionsort.fragment.SCodeFragment;
import com.e.algorithmvisualiser.algorithm.sorting.selectionsort.fragment.SDetailFragment;
import com.e.algorithmvisualiser.algorithm.sorting.selectionsort.fragment.SExecutionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SelectionMain extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_main);

        if(Build.VERSION.SDK_INT >=21)
        {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.btm_bg));
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_nav);

        openFragment(new SExecutionFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.details:
                        openFragment(new SDetailFragment());
                        return true;

                    case R.id.execution:
                        openFragment(new SExecutionFragment());
                        return true;

                    case R.id.code:
                        openFragment(new SCodeFragment());
                        return true;

                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_nav);
        int selectedItemId = bottomNavigationView.getSelectedItemId();
        if (R.id.execution != selectedItemId) {
            setHomeItem(SelectionMain.this);
        } else {
            finish();
        }
    }

    public static void setHomeItem(Activity activity) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                activity.findViewById(R.id.btm_nav);
        bottomNavigationView.setBackgroundResource(R.drawable.ic_baseline_execute_24);
        bottomNavigationView.setSelectedItemId(R.id.execution);
    }

    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frm_lyt, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
