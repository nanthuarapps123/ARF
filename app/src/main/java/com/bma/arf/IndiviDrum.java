package com.bma.arf;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IndiviDrum extends AppCompatActivity {
    EditText ed_uid;
    Button btn_check;
    String str_user_id;
    String tableName;

    DBCreation dbcreation;
    SQLiteDatabase db;
    Cursor cursor;

    String[] s_tick_no,s_tick_source,s_tick_dest,s_tick_traveldate;
    TextView tv1,tv2,tv3,tv4,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indivi_drum);

        ed_uid = (EditText)findViewById(R.id.ed_uid);
        btn_check = (Button) findViewById(R.id.btn_check_iuser);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_uid.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter user Id",Toast.LENGTH_SHORT).show();
                }else {
                    str_user_id = ed_uid.getText().toString();
                    tableName = "t_bal_drum";
                    tv1=(TextView)findViewById(R.id.Tic_no);
                    DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    SQLiteDatabase db = dbcreation.getWritableDatabase();

//                    cursor = db.rawQuery("SELECT * FROM t_bal_drum" , null);
                    cursor = db.rawQuery("SELECT * FROM " + tableName + " where c_id = '" +str_user_id + "'" , null);
                    cursor.moveToFirst();
                    s_tick_no=new String[cursor.getCount()];
                    String textout="";

                    for (int i=0;i<s_tick_no.length;i++){

                        {
                            s_tick_no[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_D_BAL_CUS_NAME));
                            cursor.moveToNext();
                        }
                        textout +=s_tick_no[i] + "\n \n";
                    }tv1.setText(textout);


                    TextView tv2=(TextView)findViewById(R.id.Tic_source);
                    cursor = db.rawQuery("SELECT * FROM " + tableName + " where c_id = '" +str_user_id + "'" , null);
                    cursor.moveToFirst();
                    s_tick_source=new String[cursor.getCount()];
                    String textout1="";
                    for (int i=0;i<s_tick_source.length;i++){

                        {
                            s_tick_source[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_TOT_DRUM));
                            cursor.moveToNext();
                        }



                        textout1 +=s_tick_source[i] + "\n \n";
                    }tv2.setText(textout1);

                    TextView tv3=(TextView)findViewById(R.id.Tic_dest);

                    cursor = db.rawQuery("SELECT * FROM " + tableName + " where c_id = '" +str_user_id + "'" , null);
                    cursor.moveToFirst();
                    s_tick_dest=new String[cursor.getCount()];
                    String textout2="";
                    for (int i=0;i<s_tick_dest.length;i++){

                        {
                            s_tick_dest[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_RET_DRUM));
                            cursor.moveToNext();
                        }



                        textout2 +=s_tick_dest[i] + "\n \n";
                    }tv3.setText(textout2);




                    TextView tv4=(TextView)findViewById(R.id.Tic_traveldate);
                    cursor = db.rawQuery("SELECT * FROM " + tableName + " where c_id = '" +str_user_id + "'" , null);
                    cursor.moveToFirst();
                    s_tick_traveldate=new String[cursor.getCount()];
                    String textout3="";
                    for (int i=0;i<s_tick_traveldate.length;i++){

                        {
                            s_tick_traveldate[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_BAL_DRUM));
                            cursor.moveToNext();
                        }

                        textout3 +=s_tick_traveldate[i] + "\n \n";
                    }tv4.setText(textout3);

        }
            }
        });

    }
}
