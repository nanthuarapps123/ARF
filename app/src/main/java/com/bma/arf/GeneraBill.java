package com.bma.arf;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneraBill extends AppCompatActivity {

    Spinner spi_selc,spi_seli;
    EditText ed_item_price,ed_item_quant,ed_discount,ed_item_kg;
    Button btn_add_more,btn_next,btn_cancel;
    String str_item_name,str_price,str_quant,str_box_kg,str_tot_price,str_cust_name,str_tot_quant;
    int int_price,int_quant,int_box_kg;
    int int_tot_price,int_tot_quant;

    ArrayList<String> al_sel_item;
    ArrayAdapter<String> adapter_sel_item;

    String str_discount;

    ArrayList<String> al_item;
    ArrayList<String> al_tot_quant;
    ArrayList<String> al_tot_price;

    String cus_name;

    DBCreation dbcreation;
    SQLiteDatabase db;
    Cursor cursor;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genera_bill);

        Bundle bu_gene_bill = getIntent().getExtras();
        cus_name = bu_gene_bill.getString("cname");

        btn_add_more = (Button)findViewById(R.id.btn_add_more);
        btn_next = (Button)findViewById(R.id.btn_next);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        spi_selc = (Spinner)findViewById(R.id.spi_sel_cus);
        spi_seli = (Spinner)findViewById(R.id.spi_sel_item);

        ed_item_price = (EditText)findViewById(R.id.ed_item_price);
        ed_item_quant = (EditText)findViewById(R.id.ed_item_quant);
        ed_discount = (EditText)findViewById(R.id.ed_discount);
        ed_item_kg = (EditText)findViewById(R.id.ed_item_kg);


        al_sel_item=new ArrayList<String>();

        additemspi();

        al_item=new ArrayList<String>();
        al_tot_quant=new ArrayList<String>();
        al_tot_price=new ArrayList<String>();



        String[] sel_item = {"Select item","Cutla","Jilebi","Rogu"};

        ArrayAdapter<String> adapter_item = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sel_item);
        spi_seli.setAdapter(adapter_item);


        btn_add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_price = ed_item_price.getText().toString();
                str_quant = ed_item_quant.getText().toString();
                str_box_kg = ed_item_kg.getText().toString();

                int_price = Integer.parseInt(str_price);
                int_quant = Integer.parseInt(str_quant);
                int_box_kg = Integer.parseInt(str_box_kg);

                int_tot_quant = int_quant * int_box_kg;
                int_tot_price = int_price * int_quant * int_box_kg;

                str_tot_price = String.valueOf(int_tot_price);
                str_tot_quant = String.valueOf(int_tot_quant);

                al_item.add(str_item_name);
                al_tot_quant.add(str_tot_quant);
                al_tot_price.add(str_tot_price);

                Toast.makeText(getApplicationContext(),String.valueOf(int_tot_price),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),String.valueOf(int_tot_quant),Toast.LENGTH_SHORT).show();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in_bill_one = new Intent(getApplicationContext(),BillOne.class);
                Bundle bu_bill_one = new Bundle();
                bu_bill_one.putString("cname",cus_name);
                bu_bill_one.putStringArrayList("item",al_item);
                bu_bill_one.putStringArrayList("quant",al_tot_quant);
                bu_bill_one.putStringArrayList("price",al_tot_price);
                in_bill_one.putExtras(bu_bill_one);
                startActivity(in_bill_one);
            }
        });

//        spi_selc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i == 0){
//                    Toast.makeText(getApplicationContext(),"Select customer",Toast.LENGTH_SHORT).show();
//                }else {
//                    str_cust_name = adapterView.getItemAtPosition(i).toString();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        spi_seli.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Toast.makeText(getApplicationContext(),"Select item",Toast.LENGTH_SHORT).show();
                }else {
                    str_item_name = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void additemspi() {
        dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
        db = dbcreation.getWritableDatabase();
        cursor = db.rawQuery("SELECT pname from product", null);
        cursor.moveToFirst();
        count = cursor.getCount();

        for (int i=0;i<count;i++){
            al_sel_item.add(cursor.getString(cursor.getColumnIndex(DBUtils.P_NAME)));
            cursor.moveToNext();
        }
        adapter_sel_item = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, al_sel_item);
        spi_seli.setAdapter(adapter_sel_item);
    }
}
