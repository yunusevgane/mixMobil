package com.financemoney.yoga.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AppPref {
    static final String IS_DAILY = "IS_DAILY";
    static final String MY_PREFRENCE = "userPref";
    static final String REMINDER_TIME = "reminderTime";


    public static boolean isDAILY(Context context) {
        return context.getApplicationContext().getSharedPreferences(MY_PREFRENCE, 0).getBoolean(IS_DAILY, true);
    }

    public static void setDAILY(Context context, boolean z) {
        Log.d("isDaily", "setDaily" + z);
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(MY_PREFRENCE, 0).edit();
        edit.putBoolean(IS_DAILY, z);
        edit.commit();
        if (z) {
            Constant.remind24(context);
            Constant.remind3hour(context);
        }
    }

    public static void setReminderTime(Context context, long j) {
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(MY_PREFRENCE, 0).edit();
        edit.putLong(REMINDER_TIME, j);
        edit.commit();
    }

    public static long getReminderTime(Context context) {
        return context.getApplicationContext().getSharedPreferences(MY_PREFRENCE, 0).getLong(REMINDER_TIME, 1548207000000L);
    }


}
