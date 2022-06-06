package com.financemoney.yoga.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class bmidatatable {
    DBHelper dbhelper;
    SQLiteDatabase sqLiteDatabase;

    public bmidatatable(Context context) {
        this.dbhelper = new DBHelper(context);
    }

    public void openDB() {
        this.sqLiteDatabase = this.dbhelper.getWritableDatabase();
    }

    public void closeDB() {
        this.sqLiteDatabase.close();
    }

    public void insertRecord(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TABLE_BMIDATE, str);
        contentValues.put(DBHelper.TABLE_BMIVALUE, str2);
        contentValues.put(DBHelper.TABLE_BMISTATE, str3);
        this.sqLiteDatabase.insert(DBHelper.TABLE_BMIDATA, (String) null, contentValues);
    }

    public Cursor getAllRecords() {
        return this.sqLiteDatabase.rawQuery("select * from bmidata", (String[]) null);
    }

    public Cursor clearallRecords() {
        return this.sqLiteDatabase.rawQuery("delete from bmidata", (String[]) null);
    }
}
