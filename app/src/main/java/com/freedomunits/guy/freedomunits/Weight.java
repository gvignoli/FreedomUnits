package com.freedomunits.guy.freedomunits;

//import android.app.Fragment;
        import android.content.DialogInterface;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;
        import android.view.View.OnClickListener;
        import java.math.BigDecimal;
        import java.math.RoundingMode;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import static android.R.layout.simple_spinner_dropdown_item;

/**
 * Created by guy on 2/24/2016.
 */
public  class Weight extends Fragment implements AdapterView.OnItemSelectedListener {
    // Store instance variables
    private String title;
    private int page;
    private Spinner spinner1, spinner2;
    private ListView dataAdapter;
    private HashMap  units;
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
        units = new HashMap();
        units.put("cg", .01);//multiply # of gram * .01 to get cg
        //addListenerOnButton();
        //addListenerOnSpinnerItemSelection();
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
        addItemsOnSpinner2(view);
        return view;
    }


    public void addItemsOnSpinner2(View v) {
        spinner2 = (Spinner) v.findViewById(R.id.spinnerWeight);
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