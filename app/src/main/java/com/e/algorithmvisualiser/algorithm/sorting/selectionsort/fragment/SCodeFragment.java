package com.e.algorithmvisualiser.algorithm.sorting.selectionsort.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.e.algorithmvisualiser.R;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SCodeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextInputLayout language_text_lyt;
    AutoCompleteTextView language_auto_tv;
    WebView webView;

    public SCodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SCodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SCodeFragment newInstance(String param1, String param2) {
        SCodeFragment fragment = new SCodeFragment();
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

        language_text_lyt = (TextInputLayout) root.findViewById(R.id.language_spinner);
        language_auto_tv = (AutoCompleteTextView) root.findViewById(R.id.autoCompleteTextView);
        webView = (WebView) root.findViewById(R.id.code);

        loaddata();

        ArrayAdapter<String> langarray = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.languages));

        language_auto_tv.setAdapter(langarray);

        language_auto_tv.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loaddata();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return root;
    }

    public void loaddata()
    {
        String item = language_text_lyt.getEditText().getText().toString();

        if(item.equals("C"))
        {
            webView.loadUrl("file:///android_asset/selection_c.html");
            System.out.println("C language is selected");
        }
        else if(item.equals("C++"))
        {
            webView.loadUrl("file:///android_asset/selection_cpp.html");
            System.out.println("C language is selected");
        }
        else if(item.equals("Java"))
        {
            webView.loadUrl("file:///android_asset/selection_java.html");
            System.out.println("C language is selected");
        }
        else if(item.equals("Python"))
        {
            webView.loadUrl("file:///android_asset/selection_python.html");
            System.out.println("C language is selected");
        }
        else if(item.equals("JavaScript"))
        {
            webView.loadUrl("file:///android_asset/selection_js.html");
            System.out.println("C language is selected");
        }
    }
}