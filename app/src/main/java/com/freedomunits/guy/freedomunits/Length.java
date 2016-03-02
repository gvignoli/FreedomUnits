package com.freedomunits.guy.freedomunits;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guy on 2/26/2016.
 */
public class Length extends Fragment implements AdapterView.OnItemSelectedListener{
    // Store instance variables
    private String title;
    private int page;
    private Spinner  spinner2;
    private ListView dataAdapter;
    public Length(){

    }

    // newInstance constructor for creating fragment with arguments
    public static Length newInstance(int page, String title) {
        Length fragmentFirst = new Length();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 1);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_length, container, false);
        Button kgToLbs = (Button) view.findViewById(R.id.button);
        final EditText meter = (EditText) view.findViewById(R.id.meterLength);
        final EditText feet = (EditText) view.findViewById(R.id.feetLength);
        kgToLbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //need to convert to big decimal then round up to safety in precision and keeping two decimal places
                BigDecimal bd = new BigDecimal(Double.parseDouble(meter.getText().toString()) * 3.28084);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                feet.setText(String.valueOf(bd));
            }
        });
        addItemsOnSpinner2(view);
        return view;
    }
    public void addItemsOnSpinner2(View v) {
        spinner2 = (Spinner) v.findViewById(R.id.spinnerLength);
        List<String> list = new ArrayList<String>();
        list.add("cg");//centigram
        list.add("dg"); //decigram
        list.add("g"); //gram
        list.add("dag");//dekagram
        list.add("hg"); //hectogram
        list.add("kg");//kilogram
        list.add("t");//metric ton
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        spinner2.setAdapter(dataAdapter);
        spinner2.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String temp = "";
        temp = String.valueOf(parent.getItemAtPosition(pos));
        System.out.println("temp: " + temp);//works!!

    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
