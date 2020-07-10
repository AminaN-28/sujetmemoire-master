package com.example.shoppinglistmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class ArticleBase extends SQLiteOpenHelper {
    public ArticleBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  void queryData(String sql){
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String NameArt, String Amount, String  Qte, byte [] image){
        SQLiteDatabase database =getWritableDatabase();
        String sql = "INSERT INTO ARTICLEL VALUES (NULL,?, ?, ?, ?)";
        SQLiteStatement statement =database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,NameArt);
        statement.bindString(2,Amount);
        statement.bindString(3,Qte);
        statement.bindBlob(4,image);

        statement.executeInsert();

    }

    public void updateData(String NameArt, String Amount, String  Qte, byte [] image, int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE ARTICLEL SET article = ?, quantity= ?, amount= ?, image=? WHERE Id = ?";
        SQLiteStatement statement =database.compileStatement(sql);


        statement.bindString(1, NameArt);
        statement.bindString(2, Amount);
        statement.bindString(3,Qte);
        statement.bindBlob(4,image);
        statement.bindDouble(5,(double) id);


        statement.execute();
        database.close();
    }

    public void deleteDate(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM ARTICLEL WHERE Id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.clearBindings();
        statement.bindDouble(1,(double) id);
        statement.execute();
        database.close();
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
