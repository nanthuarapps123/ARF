package com.bma.arf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_add_cust,txt_add_item,txt_gen_bill,txt_stock_main,txt_pay_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_add_cust = (TextView)findViewById(R.id.menu_add_cus);
        txt_add_item = (TextView)findViewById(R.id.menu_add_item);
        txt_gen_bill = (TextView)findViewById(R.id.menu_make_bill);
        txt_stock_main = (TextView)findViewById(R.id.menu_stock_main);
        txt_pay_info = (TextView)findViewById(R.id.menu_pay_info);

        txt_add_cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddCustomer.class));
            }
        });

        txt_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddItem.class));
            }
        });

        txt_gen_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SelectCus.class));
            }
        });

        txt_stock_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Stockmain.class));
            }
        });
        txt_pay_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Details.class));
            }
        });

    }
}
