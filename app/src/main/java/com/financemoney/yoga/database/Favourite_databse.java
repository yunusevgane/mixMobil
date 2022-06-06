package com.financemoney.yoga.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class Favourite_databse extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Favourite1.db";
    public static final String DATE = "date";
    public static final String ID = "id";
    public static final String IS_FAV = "is_fav";
    public static final String POSITION = "position";
    public static final String TABLE_NAME = "favourite";
    public static final String TABLE_NAME1 = "histroy";



    public Favourite_databse(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table favourite ( id INTEGER PRIMARY KEY AUTOINCREMENT,is_fav VARCHAR,position VARCHAR);");
        sQLiteDatabase.execSQL("create table histroy ( id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS favourite");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS histroy");
        onCreate(sQLiteDatabase);
    }

    public boolean deleteAll() {
        return ((long) getWritableDatabase().delete(TABLE_NAME, (String) null, (String[]) null)) != -1;
    }

    public boolean insertdata(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_FAV, str);
        contentValues.put(POSITION, str2);
        Log.d("TAG", "insertdata: " + contentValues);
        return writableDatabase.insert(TABLE_NAME, (String) null, contentValues) != -1;
    }

    public boolean inserthistroy(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, str);
        Log.d("TAG", "insertdata: " + contentValues);
        return writableDatabase.insert(TABLE_NAME1, (String) null, contentValues) != -1;
    }

    public ArrayList<HistroyHelper> getHistroy() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<HistroyHelper> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("select * from histroy", (String[]) null);
        try {
            if (rawQuery.getCount() > 0) {
                for (int i = 0; i < rawQuery.getCount(); i++) {
                    rawQuery.moveToNext();
                    arrayList.add(new HistroyHelper(rawQuery.getString(rawQuery.getColumnIndex(ID)), rawQuery.getString(rawQuery.getColumnIndex(DATE))));
                }
            }
            return arrayList;
        } finally {
            if (rawQuery != null) {
                rawQuery.close();
            }
        }
    }

    public boolean updatedata(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_FAV, str2);
        StringBuilder sb = new StringBuilder();
        sb.append("position='");
        sb.append(str);
        sb.append("'");
//        writableDatabase.close();
        return ((long) writableDatabase.update(TABLE_NAME, contentValues, sb.toString(), (String[]) null)) != -1;
    }

    public boolean isinserted(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from favourite where position = ");
        sb.append(str);
        return readableDatabase.rawQuery(sb.toString(), (String[]) null).getCount() > 0;
    }

    public boolean isdateinserted(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("Select * from histroy where date='");
        sb.append(str);
        sb.append("'");
        return readableDatabase.rawQuery(sb.toString(), (String[]) null).getCount() > 0;
    }

    public boolean deletebyid(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("id='");
        sb.append(str);
        sb.append("'");
        writableDatabase.close();
        return ((long) writableDatabase.delete(TABLE_NAME, sb.toString(), (String[]) null)) != -1;
    }

    public boolean isfav(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from favourite where position = ");
        sb.append(str);
        sb.append(" and ");
        sb.append(IS_FAV);
        sb.append(" ='1'");
        return readableDatabase.rawQuery(sb.toString(), (String[]) null).getCount() > 0;
    }

    public ArrayList<Favourite_helper> getData() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Favourite_helper> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("select * from favourite", (String[]) null);
        try {
            if (rawQuery.getCount() > 0) {
                for (int i = 0; i < rawQuery.getCount(); i++) {
                    rawQuery.moveToNext();
                    arrayList.add(new Favourite_helper(rawQuery.getString(rawQuery.getColumnIndex(ID)), rawQuery.getString(rawQuery.getColumnIndex(IS_FAV)), rawQuery.getString(rawQuery.getColumnIndex(POSITION))));
                }
            }
            return arrayList;
        } finally {
            if (rawQuery != null) {
                rawQuery.close();
            }
        }
    }
}
