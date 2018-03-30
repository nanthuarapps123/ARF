package com.bma.arf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCreation extends SQLiteOpenHelper
{


    public DBCreation(Context context, String dbname, CursorFactory factory, int dbversion) {
        super(context, dbname, factory, dbversion);
        // TODO Auto-generated constructor stub
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DBUtils.T_CREA);
        db.execSQL(DBUtils.TP_CREA);
        db.execSQL(DBUtils.T_BAL_CREA);
        db.execSQL(DBUtils.T_BAL_DRUM_CREA);
        db.execSQL(DBUtils.T_BILL_CREA);


    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DBUtils.T_DROP);
        db.execSQL(DBUtils.TP_DROP);
        db.execSQL(DBUtils.T_BAL_DROP);
        db.execSQL(DBUtils.T_DRUM_BAL_DROP);
        db.execSQL(DBUtils.T_BILL_DROP);
        onCreate(db);

    }

}
