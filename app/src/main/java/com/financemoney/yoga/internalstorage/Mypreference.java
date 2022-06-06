package com.financemoney.yoga.internalstorage;

import android.content.Context;
import android.content.SharedPreferences;
import com.financemoney.yoga.R;

public class Mypreference {
    private Context context;

    public Mypreference(Context context2) {
        this.context = context2;
    }

    public void setString(String str, String str2) {
        Context context2 = this.context;
        SharedPreferences.Editor edit = context2.getSharedPreferences(context2.getString(R.string.app_name), 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public String getString(String str) {
        Context context2 = this.context;
        return context2.getSharedPreferences(context2.getString(R.string.app_name), 0).getString(str, "");
    }

    public void setLong(String str, long j) {
        Context context2 = this.context;
        SharedPreferences.Editor edit = context2.getSharedPreferences(context2.getString(R.string.app_name), 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public long getLong(String str) {
        Context context2 = this.context;
        return context2.getSharedPreferences(context2.getString(R.string.app_name), 0).getLong(str, 0);
    }

    public void setInt(String str, int i) {
        Context context2 = this.context;
        SharedPreferences.Editor edit = context2.getSharedPreferences(context2.getString(R.string.app_name), 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public int getInt(String str) {
        Context context2 = this.context;
        return context2.getSharedPreferences(context2.getString(R.string.app_name), 0).getInt(str, 0);
    }
}
