package com.freedomunits.guy.freedomunits;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by guy on 2/24/2016.
 */
public  class Weight extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    public Weight(){

    }

    // newInstance constructor for creating fragment with arguments
    public static Weight newInstance(int page, String title) {
        Weight fragmentFirst = new Weight();
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
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button kgToLbs = (Button) view.findViewById(R.id.button);
        final EditText weightKG = (EditText) view.findViewById(R.id.kgWeight);
        final EditText weightLB = (EditText) view.findViewById(R.id.lbWeight);
        kgToLbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //need to convert to big decimal then round up to safety in precision and keeping two decimal places
                BigDecimal bd = new BigDecimal(Double.parseDouble(weightKG.getText().toString()) * 2.20462);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                weightLB.setText(String.valueOf(bd));
            }
        });
        return view;
    }

}