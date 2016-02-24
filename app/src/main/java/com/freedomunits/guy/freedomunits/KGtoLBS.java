package com.freedomunits.guy.freedomunits;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
/**
 * Created by guy on 2/23/2016.
 */
public class KGtoLBS extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    /*
    Button kgToLbs = (Button) findViewById(R.id.button);
    final EditText weightKG = (EditText) findViewById(R.id.kgWeight);
    final EditText weightLB = (EditText) findViewById(R.id.lbWeight);
    kgToLbs.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Double w;
            w = Double.parseDouble(weightKG.getText().toString())* 2.20462;
            weightLB.setText(Double.toString(w));
        }
    });
*/

}
