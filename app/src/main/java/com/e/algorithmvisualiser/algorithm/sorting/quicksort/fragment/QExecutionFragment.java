package com.e.algorithmvisualiser.algorithm.sorting.quicksort.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.e.algorithmvisualiser.R;
import com.e.algorithmvisualiser.algorithm.sorting.logs.LogAdapter;
import com.e.algorithmvisualiser.algorithm.sorting.logs.LogData;
import com.e.algorithmvisualiser.algorithm.sorting.logs.PaintView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QExecutionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QExecutionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView LogChat;
    LogAdapter logAdapter;
    List<LogData> list = new ArrayList<>();

    private InterstitialAd mInterstitialAd;

    private RelativeLayout relativeLayout;

    EditText Elements;
    Button Play;

    ArrayList<Integer> arrayList;
    private int counter = 0;
    PaintView paintView;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    private boolean started = false;

    private final Handler mHandler = new Handler();


    public QExecutionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QExecutionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QExecutionFragment newInstance(String param1, String param2) {
        QExecutionFragment fragment = new QExecutionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_execution, container, false);

        LogChat = (RecyclerView) root.findViewById(R.id.log_chat);
        relativeLayout = (RelativeLayout) root.findViewById(R.id.layout2);
        Elements = (EditText) root.findViewById(R.id.asking2);
        Play = (Button) root.findViewById(R.id.play_btn);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        setAds();

        Elements.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    Play.performClick();
                }
                return false;
            }
        });

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hidekeyboard();
                if(!started)
                {
                    if(mInterstitialAd!=null)
                    {
                        mInterstitialAd.show(getActivity());
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                loader();
                            }
                        });
                    }
                    else
                    {
                        loader();
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "Already Running", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return root;
    }

    public void loader()
    {
        try {
            started = true;

            if(logAdapter!=null)
            {
                counter=0;
                x.clear();
                y.clear();
                list.clear();
                logAdapter.notifyDataSetChanged();
            }

            if(paintView!=null)
            {
                paintView.resetCanvas();
            }

            list.add(new LogData("Quick Sort Starts", "1"));
            logAdapter=new LogAdapter(list, getContext());
            LogChat.setAdapter(logAdapter);
            LogChat.setLayoutManager(new LinearLayoutManager(getContext()));

            String element = Elements.getText().toString();



            // Declaring the array and setting the values.
            arrayList =new ArrayList<Integer>();
            for (String s:element.split(",")) {
                int num;
                num = Integer.parseInt(s);
                arrayList.add(num);
            }


            paintView = new PaintView(getContext(), arrayList);
            relativeLayout.addView(paintView);
            quicksort();

            arrayList.clear();
            for (String s:element.split(",")) {
                int num;
                num = Integer.parseInt(s);
                arrayList.add(num);
            }

            counterstart();
        }
        catch(Exception e)
        {
            Toast.makeText(getContext(), "Input not given properly.", Toast.LENGTH_SHORT).show();
            started=false;
            counter=0;
            x.clear();
            y.clear();
            list.clear();
            mInterstitialAd = null;
            setAds();
            logAdapter.notifyDataSetChanged();
        }
    }

    public void hidekeyboard()
    {
        try {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAds()
    {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getContext(),
                "ca-app-pub-5493516980230071/3924345989", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

    public void counterstart()
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Integer num1 = x.get(counter);
                Integer num2 = y.get(counter);

                if(num1==-1)
                {
                    paintView.highlightTrace(num2);
                    String str = "Pivot element is: "+(num2);
                    list.add(new LogData(str, "2"));
                    logAdapter.notifyItemInserted(list.size()-1);
                    LogChat.scrollToPosition(list.size()-1);
                }
                else if(num1==-2)
                {
                    String str = "  ";
                    list.add(new LogData(str, "3"));
                    logAdapter.notifyItemInserted(list.size()-1);
                    LogChat.scrollToPosition(list.size()-1);
                }
                else
                {
                    paintView.highlightSwap(num1,num2);
                    String str = "Swapping index: "+num1+" and "+num2;
                    Collections.swap(arrayList, num1, num2);
                    list.add(new LogData(str, "3"));
                    logAdapter.notifyItemInserted(list.size()-1);
                    LogChat.scrollToPosition(list.size()-1);
                }

                counter++;
                if(x.size()>counter)
                {
                    mHandler.postDelayed(this, 1000);
                }
                else
                {
                    mInterstitialAd = null;
                    setAds();
                    paintView.onCompleted();
                    started = false;
                }

            }
        };

        mHandler.postDelayed(runnable, 1000);
    }

    public void quicksort()
    {
        int low = 0;
        int high = arrayList.size()-1;

        quick_sort(low, high);
    }

    public void quick_sort(int low, int high)
    {
        if(low < high)
        {
            int pi = partition(low, high);

            quick_sort(low, pi-1);
            quick_sort(pi+1, high);
        }
    }

    public int partition(int low, int high)
    {
        int pivot = arrayList.get(high);

        x.add(-1);
        y.add(pivot);

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            if(arrayList.get(j)<pivot)
            {
                i++;
                Collections.swap(arrayList, i, j);
                x.add(i);
                y.add(j);
            }
        }

        Collections.swap(arrayList, i+1, high);
        x.add(i+1);
        y.add(high);

        x.add(-2);
        y.add(0);
        return (i+1);
    }
}