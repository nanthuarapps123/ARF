package com.bma.arf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Stockmain extends AppCompatActivity {
    TextView txt_all_bdetails,txt_i_bdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockmain);

        txt_all_bdetails = (TextView)findViewById(R.id.txt_aboxes_details);
        txt_i_bdetails = (TextView)findViewById(R.id.txt_iboxes_details);

        txt_all_bdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Detailsdrum.class));
            }
        });
        txt_i_bdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),IndiviDrum.class));
            }
        });

    }
}
