package com.e.algorithmvisualiser.algorithm.stack.fragment;

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
import com.e.algorithmvisualiser.algorithm.stack.logs.LogAdapter;
import com.e.algorithmvisualiser.algorithm.stack.logs.LogData;
import com.e.algorithmvisualiser.algorithm.stack.logs.PaintView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StackExecutionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StackExecutionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView LogChat;
    LogAdapter logAdapter;
    List<LogData> list = new ArrayList<>();

    private InterstitialAd mInterstitialAd;

    private RelativeLayout relativeLayout;

    EditText Elements;
    Button Push, Pop, Peek;

    ArrayList<Integer> arrayList;
    private int counter = 0;
    PaintView paintView;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    private boolean started = false;

    private final Handler mHandler = new Handler();

    public StackExecutionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StackExecutionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StackExecutionFragment newInstance(String param1, String param2) {
        StackExecutionFragment fragment = new StackExecutionFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_stack_execution, container, false);

        LogChat = (RecyclerView) root.findViewById(R.id.log_chat);
        relativeLayout = (RelativeLayout) root.findViewById(R.id.layout2);
        Elements = (EditText) root.findViewById(R.id.asking2);
        Push = (Button) root.findViewById(R.id.play_btn1);
        Pop = (Button) root.findViewById(R.id.play_btn2);
        Peek = (Button) root.findViewById(R.id.play_btn3);

        Push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidekeyboard();
                pushelement();
            }
        });

        Pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidekeyboard();
                popelement();
            }
        });

        Peek.setOnClickListener(new View.OnClickListener() {
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


    public void hidekeyboard()
    {
        try {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pushelement()
    {
        try {
            started = true;

            String element = Elements.getText().toString();
            int num = Integer.parseInt(element);

            list.add(new LogData("Pushing Element in stack:", "1"));
            logAdapter=new LogAdapter(list, getContext());
            LogChat.setAdapter(logAdapter);
            LogChat.setLayoutManager(new LinearLayoutManager(getContext()));

            arrayList.add(num);

        }
        catch(Exception e)
        {
            Toast.makeText(getContext(), "Input not given properly.", Toast.LENGTH_SHORT).show();
            started=false;
        }
    }

    public void popelement()
    {
        try {
            started = true;

            list.add(new LogData("Popping Element in stack:", "1"));
            logAdapter=new LogAdapter(list, getContext());
            LogChat.setAdapter(logAdapter);
            LogChat.setLayoutManager(new LinearLayoutManager(getContext()));

            String element = Elements.getText().toString();

            int num = Integer.parseInt(element);

        }
        catch(Exception e)
        {
            Toast.makeText(getContext(), "Input not given properly.", Toast.LENGTH_SHORT).show();
            started=false;
        }
    }

    public void loader()
    {

    }

}