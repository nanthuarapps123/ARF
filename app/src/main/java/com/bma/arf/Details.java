package com.bma.arf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Details extends AppCompatActivity {
        TextView txt_pay,txt_drum,txt_bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_pay = (TextView)findViewById(R.id.pay_details);
        txt_drum = (TextView)findViewById(R.id.drum_details);
        txt_bill = (TextView)findViewById(R.id.bill_details);

        txt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DetailsPay.class));
            }
        });

        txt_drum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Detailsdrum.class));
            }
        });

        txt_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),DetailsBill.class));
            }
        });

    }
}
