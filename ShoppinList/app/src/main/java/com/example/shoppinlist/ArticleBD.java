package com.example.shoppinlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

public class ArticleBD extends SQLiteOpenHelper {
   /* public  static final String Db_Name= "shoppinglist.db";
    public  static final String Table_Name= "shoppinglist";
    public  static final int Db_Version = 1;
    public  static final String article = "article";
    public static final String image = "image";
    public  static final String quantity = "quantity";
    public  static final String amount = "amount";*/

    public ArticleBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void queryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String s="create table shoppinglist(article Text,image Blob, quantity INTEGER,amount INTEGER);" ;
        //db.execSQL(s);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void insertArticle(String Name_art ,String Price , String Amount, byte[] image){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO SHOPPINGLIST VALUES(?, ?, ?, ?);";

        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, Name_art);
        statement.bindString(2,Price);
        statement.bindString(3,Amount);
        statement.bindBlob(4,image);

        statement.executeInsert();

    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }


}
