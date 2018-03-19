package com.example.hp.emicalci;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.text.DecimalFormat;

public class EmiDetailsActivity extends AppCompatActivity {

    Button btnDetails;
    Button btnChart;

    double loan_amount, interest, months, emi, total_interest, total_payment;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_details);

        if(getActionBar()!=null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loan_amount = getIntent().getExtras().getDouble("loan_amount");
        interest = getIntent().getExtras().getDouble("interest");
        months = getIntent().getExtras().getDouble("months");
        emi = getIntent().getExtras().getDouble("emi");
        total_interest = getIntent().getExtras().getDouble("total_interest");
        total_payment = getIntent().getExtras().getDouble("total_payment");


        bundle = new Bundle();
        bundle.putDouble("loan_amount", loan_amount);
        bundle.putDouble("interest", interest);
        bundle.putDouble("months", months);
        bundle.putDouble("emi", emi);
        bundle.putDouble("total_interest", total_interest);
        bundle.putDouble("total_payment", total_payment);
        // set Fragmentclass Arguments
        DetailsFragment dfobj = new DetailsFragment();
        dfobj.setArguments(bundle);

        MultiStateToggleButton button =  findViewById(R.id.mstb_multi_id);
        button.setColorRes(R.color.color_pressed, R.color.color_released);
        button.setValue(0);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.placeholder, new DetailsFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();


        button.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if(position == 0){
                    // Begin the transaction
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.placeholder, new DetailsFragment());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();
                }
                else if(position == 1){
                    // Begin the transaction
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.placeholder, new ChartFragment());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();
                }
            }
        });


        /*// Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.placeholder, new DetailsFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

        btnDetails = findViewById(R.id.BtnDetails);
        btnChart = findViewById(R.id.BtnChart);

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set Fragmentclass Arguments
                DetailsFragment dfobj = new DetailsFragment();
                dfobj.setArguments(bundle);

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.placeholder, new DetailsFragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        });

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set Fragmentclass Arguments
                ChartFragment cfobj = new ChartFragment();
                cfobj.setArguments(bundle);


                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.placeholder, new ChartFragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        });*/
    }
}
