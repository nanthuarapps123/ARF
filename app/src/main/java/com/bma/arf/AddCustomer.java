package com.bma.arf;

import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {
    EditText ed_cus_id,ed_cus_name,ed_con_num,ed_addre;
    Spinner spi_toc;
    Button bt_add_cus;
    String str_cus_type;
    String [] s_tick_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
//        ed_cus_id = (EditText) findViewById(R.id.input_cus_id);
        ed_cus_name = (EditText) findViewById(R.id.input_cus_name);
        ed_con_num = (EditText) findViewById(R.id.input_cus_number);
        ed_addre = (EditText) findViewById(R.id.input_cus_address);

        spi_toc = (Spinner)findViewById(R.id.spi_toc);

        String[] State = {"Select customer type","Buyer","Seller"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spi_toc.setAdapter(adapter);
        spi_toc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    str_cus_type = "select";
                }else if (i==1){
                    str_cus_type = "Buyer";
                }else if (i==2){
                    str_cus_type = "seller";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bt_add_cus = (Button)findViewById(R.id.btb_add_cus);

        bt_add_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ed_cus_id.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Fill customer id", Toast.LENGTH_SHORT).show();
//                } else
                if (ed_cus_name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill customer name", Toast.LENGTH_SHORT).show();
                } else if (ed_con_num.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill customer contact number", Toast.LENGTH_SHORT).show();
                } else if (ed_addre.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill customer address", Toast.LENGTH_SHORT).show();
                } else if (str_cus_type.equals("select")) {
                    Toast.makeText(getApplicationContext(), "Choose customer type", Toast.LENGTH_SHORT).show();
                } else {
                    DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    SQLiteDatabase db = dbcreation.getWritableDatabase();
                    ContentValues data = new ContentValues();
                    data.put(DBUtils.C_NAME, ed_cus_name.getText().toString());
                    data.put(DBUtils.C_CON_NUM, ed_con_num.getText().toString());
                    data.put(DBUtils.C_ADDRESS, ed_addre.getText().toString());
                    data.put(DBUtils.C_TYPE, str_cus_type);
                    long rowId = db.insert(DBUtils.TNAME, null, data);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Customer.class));
//                    Cursor cursor = db.rawQuery("SELECT name from users", null);
//                    cursor.moveToFirst();
//                    s_tick_no = new String[cursor.getCount()];
////                     String textout="";
//                     for (int i=0;i<s_tick_no.length;i++){
//                         s_tick_no[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_NAME));
//                         Toast.makeText(getApplicationContext(),s_tick_no[i],Toast.LENGTH_SHORT).show();
//                         cursor.moveToNext();
//                }
                }
            }
        });

        }
}
