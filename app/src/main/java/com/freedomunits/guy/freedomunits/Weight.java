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
    private Spinner  spinner2;
    private ListView dataAdapter;
    private HashMap<String, Double>  units;
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
        units = new HashMap(); //hash map to store unit anc conversion factor FROM GRAMS...must convert all things to grams first
        units.put("UNIT", 0.0);//multiply by 0
        units.put("cg", .01);//multiply # of gram * .01 to get cg
        units.put("dg", .1);//multiply # of gram * .1 to get dg
        units.put("g", 1.0);//multiply # of gram * 1 to get g
        units.put("dag", 10.0);//multiply # of gram * 10 to get g
        units.put("hg", 100.0);//multiply # of gram * 100 to get g
        units.put("kg", 1000.0);//multiply # of gram * 1000 to get g
        units.put("t", 10000.0);//multiply # of gram * 10000 to get g
        //addListenerOnButton();
        //addListenerOnSpinnerItemSelection();
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        addItemsOnSpinner2(view);

        Button kgToLbs = (Button) view.findViewById(R.id.button);
        final EditText weightKG = (EditText) view.findViewById(R.id.kgWeight);
        final EditText weightLB = (EditText) view.findViewById(R.id.lbWeight);
        kgToLbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //need to convert to big decimal then round up to safety in precision and keeping two decimal places
                BigDecimal converted = convert(String.valueOf(spinner2.getSelectedItem()), view);
                weightLB.setText(converted.toString());
            }
        });

        return view;
    }


    public void addItemsOnSpinner2(View v) {
        spinner2 = (Spinner) v.findViewById(R.id.spinnerWeight);
        List<String> list = new ArrayList<String>();
        list.add("UNIT");//blank unit
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

    private BigDecimal convert(String curUnit, View v){
        final EditText weightKG = (EditText) v.findViewById(R.id.kgWeight);
        BigDecimal toGram = new BigDecimal(0.00220462);
        units.get(curUnit);
        BigDecimal gram = new BigDecimal(Double.parseDouble(weightKG.getText().toString()) * units.get(curUnit));
        BigDecimal lb = gram.multiply(toGram);
        lb = lb.setScale(2, RoundingMode.HALF_UP);

        return lb;
    }
}