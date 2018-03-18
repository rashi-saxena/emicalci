package com.example.hp.emicalci;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 18-03-2018.
 */

public class ListDetailAdapter extends ArrayAdapter<ListDetail> {

    double m;

    public ListDetailAdapter(@NonNull Context context, int resource, @NonNull List<ListDetail> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        ListDetail currentListDetail = getItem(position);

        TextView month = v.findViewById(R.id.list_month);
        month.setText(Double.toString(currentListDetail.getMonth()));

        TextView principle = v.findViewById(R.id.list_principle);
        principle.setText(Double.toString(currentListDetail.getPrinciple()));

        TextView interest = v.findViewById(R.id.list_interest);
        interest.setText(Double.toString(currentListDetail.getInterest()));

        TextView balance = v.findViewById(R.id.list_balance);
        balance.setText(Double.toString(currentListDetail.getBalance()));

        m = currentListDetail.getMonth();
        if(m%2 == 0){
            //background white
        } else{
            //background gray
        }

        return v;
    }
}
