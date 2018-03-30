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

public class Item extends AppCompatActivity {
    Button bt_con_item,bt_home;
    String [] s_tick_no;
    ListView listView;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        bt_con_item = (Button)findViewById(R.id.con_add_item);
        bt_home = (Button)findViewById(R.id.btn_home);
        listView = (ListView) findViewById(R.id.pro_list);

        DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
        SQLiteDatabase db = dbcreation.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT pname from product", null);
        cursor.moveToFirst();
        s_tick_no = new String[cursor.getCount()];
        for (int i=0;i<s_tick_no.length;i++){
            s_tick_no[i] = cursor.getString(cursor.getColumnIndex(DBUtils.P_NAME));
            Toast.makeText(getApplicationContext(),s_tick_no[i],Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }
        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, s_tick_no);
        listView.setAdapter(adapter);


        bt_con_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddItem.class));
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
