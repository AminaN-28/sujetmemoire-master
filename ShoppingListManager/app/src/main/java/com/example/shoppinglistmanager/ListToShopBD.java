package com.example.shoppinglistmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

class ListToShopBD extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="shoppinlist.db";
    public static final String TABLE_NAME ="listedetails";
    public static final String COL_1 ="listename";
    public static final String COL_2 ="listedate";
    public static final String COL_3 = "listeamount";
    private static final String DROP_TABLE = "drop table if exists "+TABLE_NAME;

    public ListToShopBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // String s = "create table listedetails(id INTEGER PRIMARY KEY AUTOINCREMENT ,listename Text, listeamount INTEGER, listedate Date);";
        //db.execSQL(s);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL(DROP_TABLE);
        //onCreate(db);

    }
    public  void queryData(String sql){
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    public void updateData(String name, String date, String amount, int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE listedetails SET listename = ?, listeamount= ?, listedate=? WHERE id = ?";
        SQLiteStatement statement =database.compileStatement(sql);


        statement.bindString(1, name);
        statement.bindString(2, date);
        statement.bindString(3, amount);
        statement.bindDouble(5,(double) id);


        statement.execute();
        database.close();
    }

    public void deleteData(int idlist){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM listedetails WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.clearBindings();
        statement.bindDouble(1,(double) idlist);
        statement.execute();
        database.close();
    }

    public void insertData(String NameArt, String Amount, String  Date){
        SQLiteDatabase database =getWritableDatabase();
        String sql = "INSERT INTO listedetails VALUES (NULL, ?, ?, ?)";
        SQLiteStatement statement =database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,NameArt);
        statement.bindString(2,Amount);
        statement.bindString(3,Date);
        //statement.bindBlob(4,);

        statement.executeInsert();

    }


}
