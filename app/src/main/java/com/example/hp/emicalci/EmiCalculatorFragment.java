package com.example.hp.emicalci;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmiCalculatorFragment extends Fragment {

    public EmiCalculatorFragment() {
        // Required empty public constructor
    }

    EditText ETamount;
    EditText ETinterest;
    EditText ETyears;
    EditText ETmonth;
    TextView TVemi;
    Button BtnCalculate;
    Button BtnReset;
    Button BtnDetail;
    TextView TVmonthlyEmi;
    TextView TVtotalInterest;
    TextView TVtotalPayment;
    TableLayout TLtableSmallest;
    double amount, interest, emi, oneMinterest, totalInterest, totalPayment;
    String roundoff1, roundoff2, roundoff3;
    int years, month, time;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ETamount = view.findViewById(R.id.ETamount);
        ETinterest = view.findViewById(R.id.ETinterest);
        ETyears = view.findViewById(R.id.ETyears);
        ETmonth = view.findViewById(R.id.ETmonth);
        TVemi = view.findViewById(R.id.TVemi);
        BtnCalculate = view.findViewById(R.id.BtnCalculate);
        BtnReset = view.findViewById(R.id.BtnReset);
        BtnDetail = view.findViewById(R.id.BtnDetail);
        TVmonthlyEmi = view.findViewById(R.id.TVmonthlyEmi);
        TVtotalInterest = view.findViewById(R.id.TVtotalInterest);
        TVtotalPayment = view.findViewById(R.id.TVtotalPayment);
        TLtableSmallest = view.findViewById(R.id.table_smallest);


        BtnCalculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                amount = Double.parseDouble(ETamount.getText().toString());
                interest = Double.parseDouble(ETinterest.getText().toString());

                if(ETmonth.getText().toString().matches("")){
                    month = 0;
                } else {
                    month = Integer.parseInt(ETmonth.getText().toString());
                }
                if(ETyears.getText().toString().matches("")){
                    years = 0;
                } else {
                    years = Integer.parseInt(ETyears.getText().toString());
                }


                oneMinterest = interest/(12*100);
                time = (years*12)+month;

                emi = (amount*oneMinterest*Math.pow(1+oneMinterest,time))/(Math.pow(1+oneMinterest,time)-1);
                DecimalFormat df = new DecimalFormat("#.##");
                roundoff1 = df.format(emi);
                TVemi.setText(roundoff1);
                BtnDetail.setVisibility(View.VISIBLE);

                TVmonthlyEmi.setText(roundoff1);

                totalPayment = (oneMinterest*amount*time)/(1-Math.pow(1+oneMinterest,-time));
                roundoff3 = df.format(totalPayment);
                totalInterest = totalPayment - amount;
                roundoff2 = df.format(totalInterest);

                TVtotalInterest.setText(roundoff2);
                TVtotalPayment.setText(roundoff3);

                TLtableSmallest.setVisibility(View.VISIBLE);
            }
        });

        BtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ETamount.setText(null);
                ETinterest.setText(null);
                ETyears.setText(null);
                ETmonth.setText(null);
                TVemi.setText(null);
                TVmonthlyEmi.setText(null);
                TVtotalPayment.setText(null);
                TVtotalInterest.setText(null);
                TLtableSmallest.setVisibility(View.INVISIBLE);
                BtnDetail.setVisibility(View.INVISIBLE);
            }
        });

        BtnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),EmiDetailsActivity.class);
                intent.putExtra("loan_amount",amount);
                intent.putExtra("interest",interest);
                intent.putExtra("months",time);
                intent.putExtra("emi",emi);
                intent.putExtra("total_interest",totalInterest);
                intent.putExtra("total_payment",totalPayment);
                startActivity(intent);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emi_calculator, container, false);

    }




}
