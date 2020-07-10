package com.example.shoppinlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SLmanager_DATABASE extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="shoppinlist.db";
    public static final String TABLE_NAME ="shoppinglistusers";
    public static final String COL_1 ="nom";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";


    public SLmanager_DATABASE(Context context) {
        super(context, "shoppinlist.db", null, 1);
    }

    public void SLmanager_DATABASE() {
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="create table  shoppinglistusers (nom Text, prenom Text, username Text,  password Text);" ;
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("shoppinglistusers",null,contentValues);
        db.close();
        return  res;
    }

    public Boolean checkUser(String user, String pwd) {
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { user, pwd };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;

    }
}
