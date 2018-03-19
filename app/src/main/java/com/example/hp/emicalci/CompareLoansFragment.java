package com.example.hp.emicalci;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompareLoansFragment extends Fragment {

    EditText LA1, LA2, I1, I2, T1, T2;
    TextView EMI1, EMI2, IP1, IP2, TP1, TP2, d1,d2,d3;
    Button calculate,reset;
    View hide;
    double la1,la2,i1,i2,t1,t2,emi1,emi2,ip1,ip2,tp1,tp2,dif1,dif2,dif3;
    double oneMinterest1,oneMinterest2,time1,time2;
    String di1,di2,di3;


    public CompareLoansFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LA1 = view.findViewById(R.id.LA1);
        LA2 = view.findViewById(R.id.LA2);
        I1 = view.findViewById(R.id.I1);
        I2 = view.findViewById(R.id.I2);
        T1 = view.findViewById(R.id.T1);
        T2 = view.findViewById(R.id.T2);
        calculate = view.findViewById(R.id.calculate);
        reset = view.findViewById(R.id.reset);
        hide = view.findViewById(R.id.hide);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                la1 = Double.parseDouble(LA1.getText().toString());
                la2 = Double.parseDouble(LA2.getText().toString());
                i1 = Double.parseDouble(I1.getText().toString());
                i2 = Double.parseDouble(I2.getText().toString());
                t1  = Double.parseDouble(T1.getText().toString());
                t2  = Double.parseDouble(T2.getText().toString());

                oneMinterest1 = i1/(12*100);
                time1 = (t1*12);
                emi1 = (la1*oneMinterest1*Math.pow(1+oneMinterest1,time1))/(Math.pow(1+oneMinterest1,time1)-1);

                oneMinterest2 = i2/(12*100);
                time2 = (t2*12);
                emi2 = (la2*oneMinterest2*Math.pow(1+oneMinterest2,time2))/(Math.pow(1+oneMinterest2,time2)-1);

                if(emi1 > emi2){
                    dif1 = emi1-emi2;
                }else {
                    dif1 = emi2 - emi1;
                }
                DecimalFormat df = new DecimalFormat("#.##");
                di1 = "Difference: "+df.format(dif1);
                if (d1 != null) {
                    d1.setText(di1);
                }

                tp1 = (oneMinterest1*la1*time1)/(1-Math.pow(1+oneMinterest1,-time1));
                tp2 = (oneMinterest2*la2*time2)/(1-Math.pow(1+oneMinterest2,-time2));
                if(tp1 > tp2){
                    dif3 = tp1-tp2;
                }else {
                    dif3 = tp2 - tp1;
                }


                di3 = "Difference: "+df.format(dif3);
                if (d3 != null) {
                    d3.setText(di3);
                }

                ip1 = tp1 - la1;
                ip2 = tp2 - la2;
                if(ip1 > ip2){
                    dif2 = ip1-ip2;
                }else {
                    dif2 = ip2 - ip1;
                }
                di2 = "Difference: "+df.format(dif2);
                if (d2 != null) {
                    d2.setText(di2);
                }

                hide.setVisibility(View.VISIBLE);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LA1.setText(null);
                LA2.setText(null);
                I1.setText(null);
                I2.setText(null);
                T1.setText(null);
                T2.setText(null);
                hide.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compare_loans, container, false);
    }

}
