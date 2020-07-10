package com.example.shoppinlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListToShopBD extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME ="shoppinlist.db";
    public static final String TABLE_NAME ="listedetails";
    public static final String COL_1 ="listename";
    private static final String DROP_TABLE = "drop table if exists "+TABLE_NAME;



    public ListToShopBD(Context context) {
        super(context, "shoppinlist", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table listedetails(listename Text, listeamount INTEGER, listedate Date);";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }


    public Boolean updateData(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues c  = new ContentValues();
        c.put(COL_1,name);
        database.update(TABLE_NAME,c,COL_1 +" = ?",new String[] {name});
        return true;
    }

    public int deleteData(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        int value = database.delete(TABLE_NAME,COL_1 + " = ?",new String[] {name});
        return value;
    }


}
