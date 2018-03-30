package com.bma.arf;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectCusSeller extends AppCompatActivity {
    Spinner spi_selc;
    List<String> list_cust,list_cus_name;
    ArrayAdapter<String> dataAdapter;
    Button bu_sel_cust;
    String cus_name,cuss_name;
    int count,temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cus);
        spi_selc = (Spinner)findViewById(R.id.spi_sel_cus);
        bu_sel_cust = (Button)findViewById(R.id.bt_sel_cus);

        list_cust = new ArrayList<String>();
        list_cus_name = new ArrayList<String>();

        list_cus_name.add("Select customer");
        DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
        SQLiteDatabase db = dbcreation.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * from users", null);
        String tableName = "users";
        String cus_type = "Seller";
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " where type = '" +cus_type + "'" , null);
        cursor.moveToFirst();
        count = cursor.getCount();
        Log.d("COUNTTTT",String.valueOf(count));
        for (int i=0;i<count;i++){
            list_cust.add(cursor.getString(cursor.getColumnIndex(DBUtils.C_ID)));
            list_cus_name.add(cursor.getString(cursor.getColumnIndex(DBUtils.C_NAME)));
            Toast.makeText(getApplicationContext(),list_cus_name.get(i),Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_cus_name);
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spi_selc.setAdapter(dataAdapter);


        spi_selc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cus_name = adapterView.getItemAtPosition(i).toString();
                temp = adapterView.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bu_sel_cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cuss_name = list_cust.get(temp-1);
                Log.e("CUSSSNAME",cuss_name);

                if (cus_name.equals("Select customer")){
                    Toast.makeText(getApplicationContext(),"select any customer",Toast.LENGTH_SHORT).show();
                }else {
                    Intent in_gene_bill = new Intent(getApplicationContext(), Genbill2.class);
                    Bundle bu_gene_bill = new Bundle();
                    bu_gene_bill.putString("cname", cus_name);
                    bu_gene_bill.putString("cid",cuss_name);
                    in_gene_bill.putExtras(bu_gene_bill);
                    startActivity(in_gene_bill);
                }
            }
        });
    }
}
