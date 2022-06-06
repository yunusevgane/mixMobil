package com.financemoney.yoga.bmi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "bmi";

    public static final String TABLE_BMIDATA = "bmidata";
    public static final String TABLE_BMIDATAID = "bmiid";
    public static final String TABLE_BMIDATE = "bmidate";
    public static final String TABLE_BMISTATE = "bmitype";
    public static final String TABLE_BMIVALUE = "bmivalue";
    String creatTable = "CREATE TABLE `bmidata` (\n\t`bmiid`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n\t`bmidate`\tTEXT,\n\t`bmivalue`\tTEXT,\n\t`bmitype`\tTEXT\n)";

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d("","asa");
    }

    public DBHelper(Context context) {
        super(context, DBNAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.creatTable);
    }
}
