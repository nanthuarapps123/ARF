package com.bma.arf;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BillOne extends AppCompatActivity {
    ArrayList<String> rlist_selected_itemm;
    ArrayList<String> rlist_quant;
    ArrayList<String> rlist_kg;
    ArrayList<String> rlist_price;
    ArrayList<String> rlist_loose_kg;
    String str_dis,str_grand_tot,str_con_tot;
    String str_cname,str_cid,str_camnt;
    int gr_tot = 0,dis,con_tot;
//    ListView lv;
    Button bu_savedb;
    ArrayList<HashMap<String, String>> contactList;

    DBCreation dbcreation;
    SQLiteDatabase db;
    Cursor cursor,cursor1,cursor2;
    int count;

    float quant = 0,price = 0,tot_price = 0,cus_amnt = 0;
    String str_quant,str_price,str_tot_price,str_cus_amnt;
    String rid,rec_id;
    int irid,rrid;

    ArrayList<Float> al_quant,al_price;
    ArrayList<String> str_al_quant,str_al_price;

    TextView txt_cname,txt_tot_amnt,txt_tot_drum;
    String formattedDate;

    TextView tv1,tv2,tv3,tv4,tv5,tv_date,txt_tot,txt_paid;
    TextView tv_boxes,tv_weight,tv_loosekg;
    String sno;
    String str_bal_amnt;
    TextView txt_bal_amnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_one);

         tv1=(TextView)findViewById(R.id.Tic_no);
         tv2=(TextView)findViewById(R.id.Tic_source);
         tv3=(TextView)findViewById(R.id.Tic_dest);
         tv4=(TextView)findViewById(R.id.Tic_traveldate);
         tv5=(TextView)findViewById(R.id.Tic_Traintime);
         tv_date = (TextView)findViewById(R.id.date);
         txt_tot = (TextView)findViewById(R.id.txt_tot_amnt);
         txt_paid = (TextView)findViewById(R.id.txt_paid_amnt);
        tv_boxes =(TextView)findViewById(R.id.boxes);
        tv_weight =(TextView)findViewById(R.id.weight);
        tv_loosekg = (TextView)findViewById(R.id.loosekg);
        txt_bal_amnt = (TextView)findViewById(R.id.txt_bal_amnt);

        rlist_selected_itemm = new ArrayList<String>();
        rlist_quant = new ArrayList<String>();
        rlist_price = new ArrayList<String>();
        rlist_kg = new ArrayList<String>();
        rlist_loose_kg = new ArrayList<String>();

        al_quant = new ArrayList<Float>();
        al_price = new ArrayList<Float>();

        str_al_quant = new ArrayList<String>();
        str_al_price = new ArrayList<String>();

//        lv = (ListView) findViewById(R.id.list);
        bu_savedb = (Button)findViewById(R.id.btn_savedb);

        txt_cname = (TextView)findViewById(R.id.cus_name) ;
//        txt_tot_amnt = (TextView)findViewById(R.id.tot_amout) ;
//        txt_tot_drum = (TextView)findViewById(R.id.tot_drum) ;

        contactList = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        str_cid = b.getString("keycid");
        str_cname = b.getString("keycname");
        rlist_selected_itemm = b.getStringArrayList("keyitem");
        rlist_quant = b.getStringArrayList("keyboxes");
        rlist_kg = b.getStringArrayList("keykg");
        rlist_price = b.getStringArrayList("keyprice");
        rlist_loose_kg = b.getStringArrayList("keyloosekg");
        str_dis = b.getString("keydiscount");
        str_grand_tot = b.getString("keygrandtot");
        str_con_tot = b.getString("keycontot");
        str_camnt = b.getString("keycamnt");
        str_bal_amnt = b.getString("keybalamnt");



        txt_cname.setText(str_cname);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());
        tv_date.setText(formattedDate);

        for (int i = 0;i<rlist_selected_itemm.size();i++){
            HashMap<String, String> contact = new HashMap<>();
            contact.put("item",rlist_selected_itemm.get(i));//////////////////////////////////////

            float fquant = Float.parseFloat(rlist_quant.get(i));
            Log.d("FQUANT",String.valueOf(fquant));
            float fkg = Float.parseFloat(rlist_kg.get(i));
            Log.d("FKILOGRAM",String.valueOf(fkg));
            float flkg = Float.parseFloat(rlist_loose_kg.get(i));
            Log.d("FLOOSEKG",String.valueOf(flkg));

            float ftot_ind_quant = (fquant * fkg) + flkg;
            Log.d("FINDQUNANT",String.valueOf(ftot_ind_quant));

            contact.put("kg",String.valueOf(ftot_ind_quant));////////////////////////////////////
            str_al_quant.add(String.valueOf(ftot_ind_quant));
            float f_ind_price = ftot_ind_quant * Float.parseFloat(rlist_price.get(i));
            contact.put("price",String.valueOf(f_ind_price));///////////////////////////////////
            str_al_price.add(String.valueOf(f_ind_price));
            contactList.add(contact);

            Log.d("CHECHJK", String.valueOf(contactList.get(i)));
        }


//
//        ListAdapter adapter = new SimpleAdapter(BillOne.this, contactList, R.layout.list_item, new String[]{"item", "kg", "price"}, new int[]{R.id.sel_item, R.id.sel_quant, R.id.sel_price});
//        lv.setAdapter(adapter);

        for (int k = 0; k<rlist_selected_itemm.size();k++){

            float fquant = Float.parseFloat(rlist_quant.get(k));
            float fkg = Float.parseFloat(rlist_kg.get(k));
            float flkg = Float.parseFloat(rlist_loose_kg.get(k));
            float ftot_ind_quant = (fquant * fkg) + flkg;
            quant = ftot_ind_quant + quant;
            Log.d("FINALQUANTCHECK",String.valueOf(quant));
            al_quant.add(quant);
            str_quant = String.valueOf(quant);

            String a = rlist_price.get(k);
            float aa = Float.parseFloat(a);

            tot_price = aa + tot_price;
            Log.d("FINALRICECHECK",String.valueOf(tot_price));
            str_tot_price = String.valueOf(tot_price);


        }
        txt_bal_amnt.setText(str_bal_amnt);
        txt_tot.setText(str_con_tot);
        txt_paid.setText(str_camnt);

        bu_savedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                db = dbcreation.getWritableDatabase();
                cursor = db.rawQuery("SELECT b_rec_id from bill", null);
                cursor.moveToFirst();
                count = cursor.getCount();
                Log.d("COUNTTTbill",String.valueOf(count));

                if (count == 0){
                    rec_id = "1";
                    for (int i = 0;i < rlist_selected_itemm.size();i++){
                        ContentValues data = new ContentValues();
                        data.put(DBUtils.BILL_REC_ID, rec_id);
                        data.put(DBUtils.BILL_DATE, formattedDate);
                        data.put(DBUtils.BILL_CUS_ID, str_cid);
                        data.put(DBUtils.BILL_PRO_NAME, rlist_selected_itemm.get(i));
//                        data.put(DBUtils.BILL_PRO_QUANT, rlist_quant.get(i));
//                        data.put(DBUtils.BILL_PRO_PRICE, rlist_price.get(i));
                        data.put(DBUtils.BILL_PRO_QUANT, str_al_quant.get(i));
                        data.put(DBUtils.BILL_PRO_PRICE, str_al_price.get(i));
                        data.put(DBUtils.BILL_PRO_TOT_PRICE, str_tot_price);
                        data.put(DBUtils.BILL_CUS_AMNT, str_camnt);

                        long rowId = db.insert(DBUtils.TBILL, null, data);
                        Toast.makeText(getApplicationContext(), "Successbill", Toast.LENGTH_SHORT).show();
                        dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                        db = dbcreation.getWritableDatabase();
                        cursor = db.rawQuery("SELECT * from bill", null);
                        cursor.moveToFirst();
                        Log.d("======","" +cursor.getString(cursor.getColumnIndex(DBUtils.BILL_ID)) + "" +cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_QUANT)) + "" +cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_PRICE)) + "" +cursor.getString(cursor.getColumnIndex(DBUtils.BILL_PRO_TOT_PRICE)) + "" +cursor.getString(cursor.getColumnIndex(DBUtils.BILL_CUS_AMNT)));
                        Log.d("======","============================================================");
                    }
                }else if (count>0){
                    dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    db = dbcreation.getWritableDatabase();
                    cursor = db.rawQuery("SELECT b_rec_id from bill", null);
                    cursor.moveToLast();


                        rid = cursor.getString(cursor.getColumnIndex(DBUtils.BILL_REC_ID));
                        irid = Integer.parseInt(rid);
                        rrid = irid + 1;
                        rec_id = String.valueOf(rrid);
                        Log.d("RECIDtest",rec_id);

                    for (int i = 0 ; i<rlist_selected_itemm.size();i++){
                        ContentValues data = new ContentValues();
                        data.put(DBUtils.BILL_REC_ID, rec_id);
                        data.put(DBUtils.BILL_DATE, formattedDate);
                        data.put(DBUtils.BILL_CUS_ID, str_cid);
                        data.put(DBUtils.BILL_PRO_NAME, rlist_selected_itemm.get(i));
                        data.put(DBUtils.BILL_PRO_QUANT, rlist_quant.get(i));
                        data.put(DBUtils.BILL_PRO_PRICE, rlist_price.get(i));
                        data.put(DBUtils.BILL_PRO_TOT_PRICE, str_con_tot);
                        data.put(DBUtils.BILL_CUS_AMNT, str_camnt);
                        long rowId = db.insert(DBUtils.TBILL, null, data);
                    }

                    Toast.makeText(getApplicationContext(), "Successbill", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String textout="";
        for (int i=0;i<rlist_selected_itemm.size();i++){

            {
                int isno = i+1;
                sno = String.valueOf(isno);

            }
            textout +=sno + "\n \n";
        }tv1.setText(textout);


        String textout1="";
        for (int i=0;i<rlist_selected_itemm.size();i++){
            textout1 +=rlist_selected_itemm.get(i) + "\n \n";
        }tv2.setText(textout1);

        String textoutboxes="";
        for (int i=0;i<rlist_quant.size();i++){
            textoutboxes +=rlist_quant.get(i) + "\n \n";
        }tv_boxes.setText(textoutboxes);

        String textoutkg="";
        for (int i=0;i<rlist_kg.size();i++){
            textoutkg +=rlist_kg.get(i) + "\n \n";
        }tv_weight.setText(textoutkg);

        String textoutloosekg="";
        for (int i=0;i<rlist_loose_kg.size();i++){
            textoutloosekg +=rlist_loose_kg.get(i) + "\n \n";
        }tv_loosekg.setText(textoutloosekg);


        String textout2="";
        for (int i=0;i<str_al_quant.size();i++){
            textout2 +=str_al_quant.get(i) + "\n \n";
        }tv3.setText(textout2);

        String textout3="";
        for (int i=0;i<rlist_price.size();i++){
            textout3 +=rlist_price.get(i) + "\n \n";
        }tv4.setText(textout3);

        String textout4="";
        for (int i=0;i<str_al_price.size();i++){
            textout4 +=str_al_price.get(i) + "\n \n";
        }tv5.setText(textout4);

    }
}

