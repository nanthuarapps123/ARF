package com.bma.arf;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsBill extends AppCompatActivity {
    DBCreation dbcreation;
    SQLiteDatabase db;
    Cursor cursor;
    int count;
    LinearLayout lay_bill;

    String[] s_tick_no,s_tick_source,s_tick_dest,s_tick_traveldate,s_tick_traintime,s_tick_adult,s_tick_child,s_tick_fare,sta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_bill);
//
//
//
//        dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
//        db = dbcreation.getWritableDatabase();
//
//        cursor = db.rawQuery("select * from bill"  , null);
//        cursor.moveToFirst();
//        count = cursor.getCount();
//
//        for (int j = 0 ; j<count ; j++){
//            String bid = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_ID));
//            String rec_id = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_REC_ID));
//            String bill_date = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_DATE));
//            String cus_id = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_CUS_ID));
//            String pro_name = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_NAME));
//            String pro_quant = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_QUANT));
//            String pro_price = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_PRICE));
//            String tot_rice = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_TOT_PRICE));
//            String amnt = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_CUS_AMNT));
//            Log.d("====GETBILL====","\n"+bid+""+rec_id+"\n"+bill_date+"\n"+cus_id+"\n"+pro_name+"\n"+pro_quant+"\n"+pro_price+"\n"+tot_rice+"\n"+amnt);
//
//            TextView textView = new TextView(getApplicationContext());
//            textView.setId(j);
//            textView.setText(rec_id);
//
//            TextView textView1 = new TextView(getApplicationContext());
//            textView1.setId(j);
//            textView1.setText(bill_date);
//
//            TextView textView2 = new TextView(getApplicationContext());
//            textView2.setId(j);
//            textView2.setText(cus_id);
//
//            TextView textView3 = new TextView(getApplicationContext());
//            textView3.setId(j);
//            textView3.setText(pro_name);
//
//            TextView textView4 = new TextView(getApplicationContext());
//            textView4.setId(j);
//            textView4.setText(pro_quant);
//
//            TextView textView5 = new TextView(getApplicationContext());
//            textView5.setId(j);
//            textView5.setText(pro_price);
//
//            TextView textView6 = new TextView(getApplicationContext());
//            textView6.setId(j);
//            textView6.setText(tot_rice);
//
//            TextView textView7 = new TextView(getApplicationContext());
//            textView7.setId(j);
//            textView7.setText(amnt);
//
//            LinearLayout lay_bill1 = new LinearLayout(this);
//            lay_bill1.setOrientation(LinearLayout.VERTICAL);
//            ScrollView sc = new ScrollView(this);
//
//            lay_bill1.addView(textView);
//            lay_bill1.addView(textView1);
//            lay_bill1.addView(textView2);
//            lay_bill1.addView(textView3);
//            lay_bill1.addView(textView4);
//            lay_bill1.addView(textView5);
//            lay_bill1.addView(textView6);
//            lay_bill1.addView(textView7);
//
//            sc.addView(lay_bill1);
//
//            cursor.moveToNext();
//        }


        TextView tv1=(TextView)findViewById(R.id.Tic_no);
        DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
        SQLiteDatabase db = dbcreation.getWritableDatabase();

        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_no=new String[cursor.getCount()];
        String textout="";

        for (int i=0;i<s_tick_no.length;i++){

            {
                s_tick_no[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_ID));
                cursor.moveToNext();
            }
            textout +=s_tick_no[i] + "\n \n";
        }tv1.setText(textout);
        //////
        /////
        TextView tv2=(TextView)findViewById(R.id.Tic_source);
        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_source=new String[cursor.getCount()];
        String textout1="";
        for (int i=0;i<s_tick_source.length;i++){

            {
                s_tick_source[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_REC_ID));
                cursor.moveToNext();
            }



            textout1 +=s_tick_source[i] + "\n \n";
        }tv2.setText(textout1);
        ///////
        ///////
        TextView tv3=(TextView)findViewById(R.id.Tic_dest);

        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_dest=new String[cursor.getCount()];
        String textout2="";
        for (int i=0;i<s_tick_dest.length;i++){

            {
                s_tick_dest[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_DATE));
                cursor.moveToNext();
            }



            textout2 +=s_tick_dest[i] + "\n \n";
        }tv3.setText(textout2);




        TextView tv4=(TextView)findViewById(R.id.Tic_traveldate);
        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_traveldate=new String[cursor.getCount()];
        String textout3="";
        for (int i=0;i<s_tick_traveldate.length;i++){

            {
                s_tick_traveldate[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_CUS_ID));
                cursor.moveToNext();
            }



            textout3 +=s_tick_traveldate[i] + "\n \n";
        }tv4.setText(textout3);


        TextView tv5=(TextView)findViewById(R.id.Tic_Traintime);
        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_traintime=new String[cursor.getCount()];
        String textout4="";
        for (int i=0;i<s_tick_traintime.length;i++){

            {
                s_tick_traintime[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_NAME));
                cursor.moveToNext();
            }



            textout4 +=s_tick_traintime[i] + "\n \n";
        }tv5.setText(textout4);


        TextView tv7=(TextView)findViewById(R.id.Tic_adult);
        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_adult=new String[cursor.getCount()];
        String textout6="";
        for (int i=0;i<s_tick_adult.length;i++){

            {
                s_tick_adult[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_QUANT));
                cursor.moveToNext();
            }



            textout6 +=s_tick_adult[i] + "\n \n";
        }tv7.setText(textout6);


        TextView tv8=(TextView)findViewById(R.id.Tic_child);

        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_child=new String[cursor.getCount()];
        String textout7="";
        for (int i=0;i<s_tick_child.length;i++){

            {
                s_tick_child[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_PRICE));
                cursor.moveToNext();
            }



            textout7 +=s_tick_child[i] + "\n \n";
        }tv8.setText(textout7);






        TextView tv9=(TextView)findViewById(R.id.Tic_fare);
        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        s_tick_fare=new String[cursor.getCount()];
        String textout8="";
        for (int i=0;i<s_tick_fare.length;i++){

            {
                s_tick_fare[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_TOT_PRICE));
                cursor.moveToNext();
            }



            textout8 +=s_tick_fare[i] + "\n \n";
        }tv9.setText(textout8);


        TextView tv10=(TextView)findViewById(R.id.Tic_status);
        cursor = db.rawQuery("SELECT * FROM bill" , null);
        cursor.moveToFirst();
        sta=new String[cursor.getCount()];
        String textout9="";
        for (int i=0;i<sta.length;i++){

            {
                sta[i] = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_CUS_AMNT));
                cursor.moveToNext();
            }



            textout9 +=sta[i] + "\n \n";
        }tv10.setText(textout9);



        /////////////////////////////////MAIN END/////////////////////////////
    }


}
