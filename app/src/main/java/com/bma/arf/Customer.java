package com.bma.arf;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Customer extends AppCompatActivity {
Button bt_con_cus,bt_home;
    String [] s_tick_no;
    ListView listView;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        bt_con_cus = (Button)findViewById(R.id.con_add_cus);
        bt_home = (Button)findViewById(R.id.btn_home);
        listView = (ListView) findViewById(R.id.cus_list);

        DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
        SQLiteDatabase db = dbcreation.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name from users", null);
        cursor.moveToFirst();
        s_tick_no = new String[cursor.getCount()];
        for (int i=0;i<s_tick_no.length;i++){
            s_tick_no[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_NAME));
            Toast.makeText(getApplicationContext(),s_tick_no[i],Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }
        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, s_tick_no);
        listView.setAdapter(adapter);

        bt_con_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddCustomer.class));
            }
        });
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
