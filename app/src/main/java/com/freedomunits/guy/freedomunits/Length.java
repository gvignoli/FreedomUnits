package com.freedomunits.guy.freedomunits;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by guy on 2/26/2016.
 */
public class Length extends Fragment {
    // Store instance variables
    private String title;
    private int page;

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
        return view;
    }
}
