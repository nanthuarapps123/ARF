package com.bma.arf;


public class DBUtils

{
    public static final String DBName = "ARUTS";
    public static final int VER = 1;

    public static final String TNAME = "users";
    public static final String C_ID = "id";
    public static final String C_NAME= "name";
    public static final String C_CON_NUM = "mobile";
    public static final String C_ADDRESS = "address";
    public static final String C_TYPE = "type";

    public static final String TPNAME = "product";
    public static final String P_ID = "pid";
    public static final String P_NAME= "pname";

    public static final String T_CUS_BAL = "t_bal_amnt";
    public static final String T_CB_ID = "id";
    public static final String C_BAL_CUS_ID = "c_id";
    public static final String C_BAL_CUS_NAME = "c_name";
    public static final String C_BAL_CUS_TOT_AMNT = "c_tot_amnt";
    public static final String C_BAL_CUS_PAID_AMNT = "c_paid_amnt";
    public static final String C_BAL_CUS_BAL_AMNT = "c_bal_amnt";


    public static final String T_CUS_DRUM = "t_bal_drum";
    public static final String T_CD_ID = "id";
    public static final String C_D_BAL_CUS_ID = "c_id";
    public static final String C_D_BAL_CUS_NAME = "c_name";
    public static final String C_BAL_CUS_TOT_DRUM = "c_tot_drum";
    public static final String C_BAL_CUS_RET_DRUM = "c_ret_drum";
    public static final String C_BAL_CUS_BAL_DRUM = "c_bal_drum";


    public static final String TBILL = "bill";
    public static final String BILL_ID = "b_id";
    public static final String BILL_REC_ID = "b_rec_id";
    public static final String BILL_DATE = "b_date";
    public static final String BILL_CUS_ID = "cus_id";
    public static final String BILL_PRO_NAME = "pro_name";
    public static final String BILL_PRO_QUANT = "pro_quant";
    public static final String BILL_PRO_PRICE = "pro_price";
    public static final String BILL_PRO_TOT_PRICE = "pro_tot_price";
    public static final String BILL_CUS_AMNT = "cus_amnt";



    public static final String T_CREA = "create table " + TNAME+""
            + "("
            + C_ID + " integer primary key autoincrement, " + ""
            + C_NAME + " varchar not null, " + ""
            + C_CON_NUM + " varchar not null, " + ""
            + C_ADDRESS + " varchar not null, " + ""
            + C_TYPE + " varchar not null)";

    public static final String TP_CREA = "create table " + TPNAME+""
            + "("
            + P_ID + " integer primary key autoincrement, " + ""
            + P_NAME + " varchar not null)";

    public static final String T_BAL_CREA = "create table " + T_CUS_BAL+""
            + "("
            + T_CB_ID + " integer primary key autoincrement, " + ""
            + C_BAL_CUS_ID + " varchar not null, " + ""
            + C_BAL_CUS_NAME + " varchar not null, " + ""
            + C_BAL_CUS_TOT_AMNT + " varchar not null, " + ""
            + C_BAL_CUS_PAID_AMNT + " varchar not null, " + ""
            + C_BAL_CUS_BAL_AMNT + " varchar not null)";

    public static final String T_BAL_DRUM_CREA = "create table " + T_CUS_DRUM+""
            + "("
            + T_CD_ID + " integer primary key autoincrement, " + ""
            + C_D_BAL_CUS_ID + " varchar not null, " + ""
            + C_D_BAL_CUS_NAME + " varchar not null, " + ""
            + C_BAL_CUS_TOT_DRUM + " varchar not null, " + ""
            + C_BAL_CUS_RET_DRUM + " varchar not null, " + ""
            + C_BAL_CUS_BAL_DRUM + " varchar not null)";

    public static final String T_BILL_CREA = "create table " + TBILL+""
            + "("
            + BILL_ID + " integer primary key autoincrement, " + ""
            + BILL_REC_ID + " varchar not null, " + ""
            + BILL_DATE + " varchar not null, " + ""
            + BILL_CUS_ID + " varchar not null, " + ""
            + BILL_PRO_NAME + " varchar not null, " + ""
            + BILL_PRO_QUANT + " varchar not null, " + ""
            + BILL_PRO_PRICE + " varchar not null, " + ""
            + BILL_PRO_TOT_PRICE + " varchar not null, " + ""
            + BILL_CUS_AMNT + " varchar not null)";


    public static final String T_DROP = "drop table if exists" + TNAME+"";
    public static final String TP_DROP = "drop table if exists" + TPNAME+"";
    public static final String T_BAL_DROP = "drop table if exists" + T_CUS_BAL+"";
    public static final String T_DRUM_BAL_DROP = "drop table if exists" + T_CUS_DRUM+"";
    public static final String T_BILL_DROP = "drop table if exists" + TBILL+"";
}