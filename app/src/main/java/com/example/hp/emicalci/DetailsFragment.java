package com.example.hp.emicalci;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    ListView listView;
    

    double month, principle, interest, Balance;
    double Tamount, Tinterest, Tmonths, Temi, TtotalInterest, TtotalPayment;
    TextView TVamount;
    TextView TVinterest;
    TextView TVmonths;
    TextView TVmonthlyEmi , TVtotalInterest , TVtotalPayment;


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null) {
            Bundle bundle = getArguments();
            Tamount = bundle.getDouble("loan_amount");
            Tinterest = getArguments().getDouble("interest");
            Tmonths = getArguments().getDouble("months");
            Temi = getArguments().getDouble("emi");
            TtotalInterest = getArguments().getDouble("total_interest");
            TtotalPayment = getArguments().getDouble("total_payment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_details, container, false);

        listView = view.findViewById(R.id.list);
        final ArrayList<ListDetail> listDetails = new ArrayList<>();

        TVamount = view.findViewById(R.id.TVamount);

        TVinterest = view.findViewById(R.id.TVinterest);

        TVmonths = view.findViewById(R.id.TVmonths);

        TVmonthlyEmi = view.findViewById(R.id.TVmonthlyEmi);
        TVtotalInterest = view.findViewById(R.id.TVtotalInterest);
        TVtotalPayment = view.findViewById(R.id.TVtotalPayment);


        DecimalFormat df = new DecimalFormat("#.##");
        TVamount.setText(String.valueOf(Tamount));

        TVinterest.setText(df.format(Tinterest));
        TVmonths.setText(df.format(Tmonths));
        TVmonthlyEmi.setText(df.format(Temi));

        month = 1;
        Balance = Tamount;


        while(month<=Tmonths){
            interest = (Tinterest/1200)*Balance;
            principle = Temi - interest;
            Balance = Balance - (Temi - interest);
            ListDetail ld = new ListDetail(month,principle,interest,Balance);
            listDetails.add(ld);
            month++;
        }

        ListDetailAdapter detailListAdapter = new ListDetailAdapter(getActivity(),R.layout.list_item,listDetails);
        if(listView!=null) {
            listView.setAdapter(detailListAdapter);
            listView.setVisibility(View.VISIBLE);
        }


        return view;
    }


}
