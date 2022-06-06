package com.financemoney.yoga.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


import com.financemoney.yoga.receiver.DailyAlarmReceiver;

import java.util.Calendar;

import static androidx.core.app.NotificationCompat.CATEGORY_ALARM;

public class Constant {





    public static void remind24(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(CATEGORY_ALARM);
        Intent intent = new Intent(context, DailyAlarmReceiver.class);
        intent.putExtra("alarmRequestCode", 111);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 111, intent, 134217728);
        Calendar instance = Calendar.getInstance();
        instance.set(11, 24);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        if (Build.VERSION.SDK_INT < 23) {
            Log.i("`ww", "onReceive: 11111!");
            alarmManager.set(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), broadcast);
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 23) {
            Log.i("`ww", "onReceive: 22222!");
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), broadcast);
        } else if (Build.VERSION.SDK_INT >= 23) {
            Log.i("`ww", "onReceive: 33333!");
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), broadcast);
        }
    }

    public static void remind3hour(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(CATEGORY_ALARM);
        Intent intent = new Intent(context, DailyAlarmReceiver.class);
        intent.putExtra("alarmRequestCode", 112);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 112, intent, 134217728);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(AppPref.getReminderTime(context));
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, instance.get(11));
        instance2.set(12, instance.get(12));
        instance2.set(13, 0);
        instance2.set(14, 0);
        if (System.currentTimeMillis() > instance2.getTimeInMillis()) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, instance2.getTimeInMillis(), broadcast);
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 23) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, instance2.getTimeInMillis(), broadcast);
        } else if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, instance2.getTimeInMillis(), broadcast);
        }
    }


}
