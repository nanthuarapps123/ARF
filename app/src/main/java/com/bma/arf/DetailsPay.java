package com.bma.arf;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsPay extends AppCompatActivity {
    DBCreation dbcreation;
    SQLiteDatabase db;
    Cursor cursor;

    String[] s_tick_no,s_tick_source,s_tick_dest,s_tick_traveldate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_pay);

        TextView tv1=(TextView)findViewById(R.id.Tic_no);
        DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
        SQLiteDatabase db = dbcreation.getWritableDatabase();

        cursor = db.rawQuery("SELECT * FROM t_bal_amnt" , null);
        cursor.moveToFirst();
        s_tick_no=new String[cursor.getCount()];
        String textout="";

        for (int i=0;i<s_tick_no.length;i++){

            {
                s_tick_no[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_NAME));
                cursor.moveToNext();
            }
            textout +=s_tick_no[i] + "\n \n";
        }tv1.setText(textout);


        TextView tv2=(TextView)findViewById(R.id.Tic_source);
        cursor = db.rawQuery("SELECT * FROM t_bal_amnt" , null);
        cursor.moveToFirst();
        s_tick_source=new String[cursor.getCount()];
        String textout1="";
        for (int i=0;i<s_tick_source.length;i++){

            {
                s_tick_source[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_TOT_AMNT));
                cursor.moveToNext();
            }



            textout1 +=s_tick_source[i] + "\n \n";
        }tv2.setText(textout1);

        TextView tv3=(TextView)findViewById(R.id.Tic_dest);

        cursor = db.rawQuery("SELECT * FROM t_bal_amnt" , null);
        cursor.moveToFirst();
        s_tick_dest=new String[cursor.getCount()];
        String textout2="";
        for (int i=0;i<s_tick_dest.length;i++){

            {
                s_tick_dest[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_PAID_AMNT));
                cursor.moveToNext();
            }



            textout2 +=s_tick_dest[i] + "\n \n";
        }tv3.setText(textout2);




        TextView tv4=(TextView)findViewById(R.id.Tic_traveldate);
        cursor = db.rawQuery("SELECT * FROM t_bal_amnt" , null);
        cursor.moveToFirst();
        s_tick_traveldate=new String[cursor.getCount()];
        String textout3="";
        for (int i=0;i<s_tick_traveldate.length;i++){

            {
                s_tick_traveldate[i] = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_BAL_AMNT));
                cursor.moveToNext();
            }



            textout3 +=s_tick_traveldate[i] + "\n \n";
        }tv4.setText(textout3);

    }
}
