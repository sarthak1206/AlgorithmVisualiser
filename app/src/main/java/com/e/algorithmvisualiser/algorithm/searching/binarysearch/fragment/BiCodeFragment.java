package com.e.algorithmvisualiser.algorithm.searching.binarysearch.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.e.algorithmvisualiser.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BiCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BiCodeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private WebView Detail;
    public BiCodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BiCodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BiCodeFragment newInstance(String param1, String param2) {
        BiCodeFragment fragment = new BiCodeFragment();
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
        View root = inflater.inflate(R.layout.fragment_code, container, false);

        Detail = (WebView) root.findViewById(R.id.code);
        Detail.loadUrl("file:///android_asset/detail_binary.html");

        return root;
    }
}