package com.bma.arf;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {
    EditText ed_pro_id,ed_pro_name;
    Button bt_add_item;
    String[] s_tick_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
//        ed_pro_id = (EditText) findViewById(R.id.input_pro_id);
        ed_pro_name = (EditText) findViewById(R.id.input_pro_name);
        bt_add_item = (Button)findViewById(R.id.btn_add_item);

        bt_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ed_pro_name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Fill Product name",Toast.LENGTH_SHORT).show();
                }else {
                    DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    SQLiteDatabase db = dbcreation.getWritableDatabase();
                    ContentValues data = new ContentValues();
                    data.put(DBUtils.P_NAME, ed_pro_name.getText().toString());
                    long rowId = db.insert(DBUtils.TPNAME, null, data);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Item.class));
                    Cursor cursor = db.rawQuery("SELECT pname from product", null);
                    cursor.moveToFirst();
                    s_tick_no = new String[cursor.getCount()];
//                     String textout="";
                     for (int i=0;i<s_tick_no.length;i++){
                         s_tick_no[i] = cursor.getString(cursor.getColumnIndex(DBUtils.P_NAME));
                         Toast.makeText(getApplicationContext(),s_tick_no[i],Toast.LENGTH_SHORT).show();
                         cursor.moveToNext();
                }
                }
            }
        });
    }
}
