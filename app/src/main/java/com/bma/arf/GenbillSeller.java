package com.bma.arf;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GenbillSeller extends AppCompatActivity {
        LinearLayout rad_lay;
        LinearLayout quant_lay;
        LinearLayout kg_lay;
        LinearLayout price_lay;
        LinearLayout loose_lay;
        DBCreation dbcreation;
        SQLiteDatabase db;
        Cursor cursor,cursor1,cursor2;
        int count;
        List<String> list_sel_itemm;

        ArrayList<String> list_selected_itemm;
        ArrayList<String> list_quant;
        ArrayList<String> list_kg;
        ArrayList<String> list_price;
        ArrayList<String> list_loose_kg;

        ArrayList<Float> list_totdrum;
        ArrayList<Float> list_retdrum;

        EditText ed1,ed2,ed3,ed4;
        Button btn_sub,btn_cal;

        RadioGroup rg;
        RadioButton[] rb;
        float i_boxes,i_kg,i_price,i_lkg = 1,tot,tot_dis,con_tot_val;

        CheckBox cb;
        TextView txt_tot_price,txt_gr_tot_price,txt_tot_drums,txt_remaining_amnt,txt_remaining_drum;
        Button bu_gr_tot,bu_sub_one,bu_sub_drum;
        EditText ed_discount,ed_paid_amnt,ed_return_drum;
        public float grand_tot = 0;
        String cus_name,cus_id;
        AlertDialog.Builder alertDialogBuilder;
        float tot_f_tot_boxes = 0;
        String str_paid;
        float fd_rem;
        float a = 0;
        float b = 0;
        Float f_remai_amnt;
        String bal_amnt;
    String str_bal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genbill2);

        list_totdrum = new ArrayList<Float>();
        list_retdrum = new ArrayList<Float>();


        Bundle bu_gene_bill = getIntent().getExtras();
        cus_name = bu_gene_bill.getString("cname");
        cus_id = bu_gene_bill.getString("cid");

        rad_lay = (LinearLayout)findViewById(R.id.radio_item);
        quant_lay = (LinearLayout)findViewById(R.id.ed_quant_item);
        kg_lay = (LinearLayout)findViewById(R.id.ed_kg_item);
        price_lay = (LinearLayout)findViewById(R.id.ed_price_item);
        loose_lay = (LinearLayout)findViewById(R.id.ed_loose_kg);

        btn_sub = (Button)findViewById(R.id.butt_sub_two);
        btn_cal = (Button)findViewById(R.id.butt_calc);
        bu_gr_tot = (Button)findViewById(R.id.butt_calc_grand);
        bu_sub_one = (Button)findViewById(R.id.butt_sub_one);

        bu_sub_drum = (Button)findViewById(R.id.butt_sub_drum);

        txt_tot_price = (TextView)findViewById(R.id.txt_tot_price);
        txt_gr_tot_price = (TextView)findViewById(R.id.txt_grand_tot_price);
        txt_tot_drums = (TextView)findViewById(R.id.tot_drums);
        txt_remaining_amnt = (TextView)findViewById(R.id.txt_remaing_amount);
        txt_remaining_drum = (TextView)findViewById(R.id.txt_remaing_drum);

        ed_discount = (EditText)findViewById(R.id.ed_dis);
        ed_paid_amnt = (EditText)findViewById(R.id.ed_paid_amnt);
        ed_return_drum = (EditText)findViewById(R.id.ed_return_drum);

        list_sel_itemm = new ArrayList<String>();
        list_selected_itemm = new ArrayList<String>();
        list_quant = new ArrayList<String>();
        list_price = new ArrayList<String>();
        list_kg = new ArrayList<String>();
        list_loose_kg = new ArrayList<String>();



        alertDialogBuilder = new AlertDialog.Builder(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,6,5,6);


        dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
        db = dbcreation.getWritableDatabase();
        cursor = db.rawQuery("SELECT pname from product", null);
        cursor.moveToFirst();
        count = cursor.getCount();
        Log.d("COUNTTTT",String.valueOf(count));

        for (int i=0;i<count;i++){
            list_sel_itemm.add(cursor.getString(cursor.getColumnIndex(DBUtils.P_NAME)));
            cursor.moveToNext();
        }


        //checkbox
        for(int i=0;i<count;i++){
            cb = new CheckBox(getApplicationContext());
            cb.setId(i);
            cb.setLayoutParams(params);
            cb.setText(list_sel_itemm.get(i));
            cb.setTextColor(Color.BLUE);
            int id = Resources.getSystem().getIdentifier("btn_check_holo_light", "drawable", "android");
            cb.setButtonDrawable(id);
            rad_lay.addView(cb);
        }

        ///  Create edittext
        for (int i = 0;i<count;i++){
            ed1 = new EditText(getApplicationContext());
            ed1.setId(i+100);
            ed1.setHint("Boxes");
            ed1.setTextColor(Color.BLACK);
            ed1.setHintTextColor(Color.BLACK);
            ed1.setInputType(InputType.TYPE_CLASS_NUMBER);
            quant_lay.addView(ed1);
        }
        ///  Create edittext2

        ///  Create edittext2
        for (int i = 0;i<count;i++){
             ed2 = new EditText(getApplicationContext());
            ed2.setId(i+200);
            ed2.setHint("Kg");
            ed2.setTextColor(Color.BLACK);
            ed2.setHintTextColor(Color.BLACK);
            ed2.setInputType(InputType.TYPE_CLASS_NUMBER);
            kg_lay.addView(ed2);
        }
        ///  Create edittext2

        ///  Create edittext3
        for (int i = 0;i<count;i++){
            ed3 = new EditText(getApplicationContext());
            ed3.setId(i+300);
            ed3.setHint("Price");
            ed3.setTextColor(Color.BLACK);
            ed3.setHintTextColor(Color.BLACK);
            ed3.setInputType(InputType.TYPE_CLASS_NUMBER);
            price_lay.addView(ed3);
        }
        ///  Create edittext3

///  Create edittext3
        for (int i = 0;i<count;i++){
            ed4 = new EditText(getApplicationContext());
            ed4.setId(i+400);
            ed4.setHint("Loose kg");
            ed4.setInputType(InputType.TYPE_CLASS_NUMBER);
            ed4.setTextColor(Color.BLACK);
            ed4.setHintTextColor(Color.BLACK);
            loose_lay.addView(ed4);
        }
        ///  Create edittext3

        bu_gr_tot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_discount.getText().toString().isEmpty()){
                    tot_dis = grand_tot/100*Float.parseFloat(ed_discount.getText().toString());
                }else {
                    tot_dis = grand_tot/100 * 0;
                }
                con_tot_val = grand_tot - tot_dis;
                Log.d("DISCOUNT",String.valueOf(tot_dis));
                Log.d("CONTOTVAL",String.valueOf(con_tot_val));
                txt_gr_tot_price.setText("Grand (final) Total price is :"+String.valueOf(con_tot_val));
            }
        });


        bu_sub_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_paid_amnt.getText().toString().equals("")){
                    str_paid = ed_paid_amnt.getText().toString();
                    f_remai_amnt = con_tot_val - Float.parseFloat(str_paid);
                    txt_remaining_amnt.setText("Remaining balance amount is :"+String.valueOf(f_remai_amnt));
                }else {
                    Toast.makeText(getApplicationContext(),"First enter the paid amount",Toast.LENGTH_SHORT).show();
                }
                for (int i = 0 ; i<count ; i++){
                    String str_quant = list_quant.get(i);
                    float f_tot_boxes = Float.parseFloat(str_quant);
                    tot_f_tot_boxes = f_tot_boxes + tot_f_tot_boxes;
                }
                txt_tot_drums.setText("Total drums are :"+String.valueOf(tot_f_tot_boxes));
            }
        });

        bu_sub_drum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_cus_id = cus_id;
                String str_cus_name = cus_name;
                String str_tot_drums = String.valueOf(tot_f_tot_boxes);
                String str_remi_drum = ed_return_drum.getText().toString();
                dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                db = dbcreation.getWritableDatabase();
                cursor = db.rawQuery("SELECT c_name from t_bal_drum", null);
                cursor.moveToFirst();
                count = cursor.getCount();
                Log.d("COUNTTTTnewn",String.valueOf(count));
                if (count==0){
                    String str_rem_drum = String.valueOf(tot_f_tot_boxes);
                    txt_remaining_drum.setText("Remaing drums to return :"+str_tot_drums);
                    ContentValues data = new ContentValues();
                    data.put(DBUtils.C_D_BAL_CUS_ID, str_cus_id);
                    data.put(DBUtils.C_D_BAL_CUS_NAME, str_cus_name);
                    data.put(DBUtils.C_BAL_CUS_TOT_DRUM, str_tot_drums);
                    data.put(DBUtils.C_BAL_CUS_RET_DRUM, str_remi_drum);//str_remi_drum
                    data.put(DBUtils.C_BAL_CUS_BAL_DRUM, str_tot_drums);
                    long rowId = db.insert(DBUtils.T_CUS_DRUM, null, data);

                    alertDialogBuilder.setTitle("Remaining Drums");
                    alertDialogBuilder.setMessage("Remaining drums to return is "+str_tot_drums);
                    alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialogBuilder.show();
                }else if (count>0){
                    dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    db = dbcreation.getWritableDatabase();
                    cursor = db.rawQuery("SELECT * from t_bal_drum", null);
                    cursor.moveToLast();

                    String str_pre_bal_drum = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_BAL_DRUM));
                    float int_pre_bal_drum = Float.parseFloat(str_pre_bal_drum);
                    float float_ret_drum = Float.parseFloat(str_remi_drum);
                    float bal_dum = (tot_f_tot_boxes + int_pre_bal_drum) - float_ret_drum;
                    String str_bal_drums = String.valueOf(bal_dum);


                    txt_remaining_drum.setText("Remaing drums to return :"+str_bal_drums);
                    ContentValues data = new ContentValues();
                    data.put(DBUtils.C_D_BAL_CUS_ID, str_cus_id);
                    data.put(DBUtils.C_D_BAL_CUS_NAME, str_cus_name);
                    data.put(DBUtils.C_BAL_CUS_TOT_DRUM, str_tot_drums);
                    data.put(DBUtils.C_BAL_CUS_RET_DRUM, str_remi_drum);
                    data.put(DBUtils.C_BAL_CUS_BAL_DRUM, str_bal_drums);
                    long rowId = db.insert(DBUtils.T_CUS_DRUM, null, data);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    alertDialogBuilder.setTitle("Remaining Drums");
                    alertDialogBuilder.setMessage("Remaining drums to return is "+str_bal_drums);
                    alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialogBuilder.show();
                }
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                db = dbcreation.getWritableDatabase();
                String tableName = "t_bal_amnt";
                cursor = db.rawQuery("SELECT * FROM " + tableName + " where c_id = '" +cus_id + "'" , null);
                cursor.moveToFirst();
                count = cursor.getCount();
                Log.d("COUNTTTTnewnpaytbl",String.valueOf(count));

                if (count == 0){
                   float bal = con_tot_val - Float.parseFloat(str_paid);
                    bal_amnt = String.valueOf(bal);

                    DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    SQLiteDatabase db = dbcreation.getWritableDatabase();
                    ContentValues data = new ContentValues();
                    data.put(DBUtils.C_BAL_CUS_ID, cus_id);
                    data.put(DBUtils.C_BAL_CUS_NAME, cus_name);
                    data.put(DBUtils.C_BAL_CUS_TOT_AMNT, String.valueOf(con_tot_val));
                    data.put(DBUtils.C_BAL_CUS_PAID_AMNT, str_paid);
                    data.put(DBUtils.C_BAL_CUS_BAL_AMNT, bal_amnt);
                    long rowId = db.insert(DBUtils.T_CUS_BAL, null, data);
                    Toast.makeText(getApplicationContext(), "SuccessPay", Toast.LENGTH_SHORT).show();
                }else if (count>0){
                    dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    db = dbcreation.getWritableDatabase();
//                    cursor = db.rawQuery("SELECT * from t_bal_drum", null);
                    cursor = db.rawQuery("SELECT * FROM " + tableName + " where c_id = '" +cus_id + "'" , null);
                    cursor.moveToLast();

                    String str_pre_bal = cursor.getString(cursor.getColumnIndex(DBUtils.C_BAL_CUS_BAL_AMNT));
                    float int_pre_bal = Float.parseFloat(str_pre_bal);
                    float float_paid = Float.parseFloat(str_paid);
                    float float_bal_amnt = (con_tot_val + int_pre_bal) - float_paid;
                    bal_amnt = String.valueOf(float_bal_amnt);


                    DBCreation dbcreation = new DBCreation(getApplicationContext(), DBUtils.DBName, null, DBUtils.VER);
                    SQLiteDatabase db = dbcreation.getWritableDatabase();
                    ContentValues data = new ContentValues();
                    data.put(DBUtils.C_BAL_CUS_ID, cus_id);
                    data.put(DBUtils.C_BAL_CUS_NAME, cus_name);
                    data.put(DBUtils.C_BAL_CUS_TOT_AMNT, String.valueOf(con_tot_val));
                    data.put(DBUtils.C_BAL_CUS_PAID_AMNT, str_paid);
                    data.put(DBUtils.C_BAL_CUS_BAL_AMNT, bal_amnt);
                    long rowId = db.insert(DBUtils.T_CUS_BAL, null, data);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                    cursor1 = db.rawQuery("SELECT * from t_bal_amnt", null);
                    cursor.moveToFirst();
                    count = cursor.getCount();
                    Log.d("TOTBALCHECK",String.valueOf(count));
                }



                alertDialogBuilder.setMessage("Total amount including discount is "+con_tot_val);
                alertDialogBuilder.setPositiveButton("Next",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Bundle b=new Bundle();
                                b.putString("keycid",cus_id);
                                b.putString("keycname",cus_name);
                                b.putStringArrayList("keyitem",list_selected_itemm);
                                b.putStringArrayList("keyboxes",list_quant);
                                b.putStringArrayList("keykg",list_kg);
                                b.putStringArrayList("keyprice",list_price);
                                b.putStringArrayList("keyloosekg",list_loose_kg);
                                b.putString("keydiscount",ed_discount.getText().toString());
                                b.putString("keygrandtot",String.valueOf(grand_tot));
                                b.putString("keycontot",String.valueOf(con_tot_val));
                                b.putString("keycamnt",str_paid);
                                b.putString("keybalamnt",bal_amnt);
                                Intent i=new Intent(getApplicationContext(),BillOne.class);
                                i.putExtras(b);
                                startActivity(i);
                            }
                        });
                alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0;i<count;i++){
                    CheckBox cb = (CheckBox)findViewById(i);
                    if (cb.isChecked()){
                        ed1 = (EditText)findViewById(i+100);
                        ed2 = (EditText)findViewById(i+200);
                        ed3 = (EditText)findViewById(i+300);
                        ed4 = (EditText)findViewById(i+400);

                        list_selected_itemm.add(cb.getText().toString());
                        list_quant.add(ed1.getText().toString());
                        list_kg.add(ed2.getText().toString());
                        list_price.add(ed3.getText().toString());
                        if (!ed4.getText().toString().isEmpty()){
                            list_loose_kg.add(ed4.getText().toString());
                        }else {
                            list_loose_kg.add("0");
                        }
                        i_boxes = Float.parseFloat(ed1.getText().toString());
                        i_kg = Float.parseFloat(ed2.getText().toString());
                        i_price = Float.parseFloat(ed3.getText().toString());
                        if (!ed4.getText().toString().isEmpty()){
                            i_lkg = Float.parseFloat(ed4.getText().toString());
                        }else {
                            i_lkg = 0;
                        }
                        tot = ((i_boxes * i_kg) + i_lkg) * i_price  ;
                        Log.d("TOTVALtot",String.valueOf(tot));
                        grand_tot = grand_tot + tot;

                        Log.d("TOTVAL",String.valueOf(grand_tot));
                        txt_tot_price.setText("Total price is :"+grand_tot);

                        i_boxes = 0;
                        i_kg = 1;
                        i_price = 0;
                        i_lkg = 1;
                    }
                }
            }
        });
    }
}
