package com.e.algorithmvisualiser.algorithm.searching.binarysearch.fragment;

import android.content.Context;
import android.os.Bundle;

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
import com.e.algorithmvisualiser.algorithm.searching.logs.LogAdapter;
import com.e.algorithmvisualiser.algorithm.searching.logs.LogData;
import com.e.algorithmvisualiser.algorithm.searching.logs.PaintView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BiExecutionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BiExecutionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView LogChat;
    LogAdapter logAdapter;
    List<LogData> list = new ArrayList<>();

    private RelativeLayout relativeLayout;

    EditText Elements;
    Button Play;
    EditText Searcher;

    ArrayList<Integer> arrayList;
    private int counter = 0, flag = 0;
    PaintView paintView;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    private boolean started = false;

    private final Handler mHandler = new Handler();

    public BiExecutionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BiExecutionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BiExecutionFragment newInstance(String param1, String param2) {
        BiExecutionFragment fragment = new BiExecutionFragment();
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
        View root = inflater.inflate(R.layout.fragment_search_execution, container, false);

        LogChat = (RecyclerView) root.findViewById(R.id.log_chat);
        relativeLayout = (RelativeLayout) root.findViewById(R.id.layout2);
        Elements = (EditText) root.findViewById(R.id.asking2);
        Play = (Button) root.findViewById(R.id.play_btn);
        Searcher = (EditText) root.findViewById(R.id.asking3);

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
                if (!started) {
                    try {
                        started = true;

                        if (logAdapter != null) {
                            counter = 0;
                            x.clear();
                            list.clear();
                            logAdapter.notifyDataSetChanged();
                        }

                        if (paintView != null) {
                            paintView.resetCanvas();
                        }

                        list.add(new LogData("Binary Search Starts", "1"));
                        logAdapter = new LogAdapter(list, getContext());
                        LogChat.setAdapter(logAdapter);
                        LogChat.setLayoutManager(new LinearLayoutManager(getContext()));

                        String element = Elements.getText().toString();


                        // Declaring the array and setting the values.
                        arrayList = new ArrayList<Integer>();
                        for (String s : element.split(",")) {
                            int num;
                            num = Integer.parseInt(s);
                            arrayList.add(num);
                        }

                        paintView = new PaintView(getContext(), arrayList);
                        relativeLayout.addView(paintView);

                        if(binarySearch()==-1)
                        {
                            x.add(-1);
                        }
                        counterstart();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Input not given properly.", Toast.LENGTH_SHORT).show();
                        started = false;
                        counter = 0;
                        x.clear();
                        list.clear();
                        logAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getContext(), "Already Running", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return root;
    }

    int binarySearch()
    {
        Collections.sort(arrayList);
        int xi = Integer.parseInt(Searcher.getText().toString());
        int l = 0, r = arrayList.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arrayList.get(m) == xi)
            {
                x.add(1);
                y.add(m);
                return m;
            }
            else
            {
                x.add(0);
                y.add(m);
            }

            if (arrayList.get(m) < xi)
            {
                l = m+1;
            }
            else{
                r = m - 1;
            }
        }

        return -1;
    }

    public void counterstart()
    {
        System.out.println("Say:" +y);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(x.get(counter)==1)
                {
                    paintView.highlightYes(y.get(counter));
                    list.add(new LogData("Element: "+Integer.parseInt(Searcher.getText().toString())+" found at index: "+y.get(counter), "3"));
                    logAdapter.notifyItemInserted(list.size()-1);
                    LogChat.scrollToPosition(list.size()-1);
                }
                else if(x.get(counter)==0)
                {
                    paintView.highlightNo(y.get(counter));
                    list.add(new LogData("Element: "+Integer.parseInt(Searcher.getText().toString())+" is not at index: "+y.get(counter), "3"));
                    logAdapter.notifyItemInserted(list.size()-1);
                    LogChat.scrollToPosition(list.size()-1);
                }
                else if(x.get(counter)==-1)
                {
                    list.add(new LogData("Element: "+Integer.parseInt(Searcher.getText().toString())+" not found.", "3"));
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
                    paintView.onCompleted();
                    started=false;
                }
            }
        };

        mHandler.postDelayed(runnable, 1000);
    }

    public void hidekeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}