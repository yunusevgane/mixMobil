package com.financemoney.yoga;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.financemoney.yoga.utils.AppPref;

import java.util.Calendar;

public class Reminder extends AppCompatActivity {
    Calendar calendar;
    SwitchCompat dialynotification;
    int mHour;
    int mMinute;
    TextView remindertime;
    LinearLayout timerdialog;
    Toolbar toolbar;
    FrameLayout frameLayout;
    ImageView exit_btn;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_reminder);
        init();

        Main_application.refreshAd(this.frameLayout, Reminder.this);

        if (AppPref.isDAILY(getApplicationContext())) {
            this.dialynotification.setChecked(true);
            this.timerdialog.setVisibility(View.VISIBLE);
            return;
        }
        this.timerdialog.setVisibility(View.GONE);
        this.dialynotification.setChecked(false);
    }


    private void init() {
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(AppPref.getReminderTime(getApplicationContext()));
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);

        exit_btn = (ImageView) findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Reminder.this.onSupportNavigateUp();
            }
        });
        this.frameLayout = (FrameLayout) findViewById(R.id.f2_adplaceholderr);

        this.remindertime = (TextView) findViewById(R.id.remindertime);
        this.timerdialog = (LinearLayout) findViewById(R.id.timerdialog);
        this.dialynotification = (SwitchCompat) findViewById(R.id.dailynotification);
        if (this.calendar.get(11) >= 12) {
            TextView textView = this.remindertime;
            StringBuilder sb = new StringBuilder();
            sb.append(this.calendar.get(10) == 0 ? 12 : this.calendar.get(10));
            sb.append(":");
            sb.append(this.calendar.get(12) < 10 ? "0" : "");
            sb.append(this.calendar.get(12));
            sb.append(" PM");
            textView.setText(sb.toString());
        } else {
            TextView textView2 = this.remindertime;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.calendar.get(10) == 0 ? 12 : this.calendar.get(10));
            sb2.append(":");
            sb2.append(this.calendar.get(12) < 10 ? "0" : "");
            sb2.append(this.calendar.get(12));
            sb2.append(" AM");
            textView2.setText(sb2.toString());
        }
        this.dialynotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Log.d("ischecknotification", "" + z);
                if (z) {
                    Reminder.this.timerdialog.setVisibility(View.VISIBLE);
                } else {
                    Reminder.this.timerdialog.setVisibility(View.GONE);
                }
                AppPref.setDAILY(Reminder.this.getApplicationContext(), z);
            }
        });
        this.timerdialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Reminder reminder = Reminder.this;
                reminder.mHour = reminder.calendar.get(11);
                Reminder reminder2 = Reminder.this;
                reminder2.mMinute = reminder2.calendar.get(12);
                new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int i, int i2) {
                        Reminder.this.calendar.set(11, i);
                        Reminder.this.calendar.set(12, i2);
                        Reminder.this.calendar.set(13, 0);
                        if (i >= 12) {
                            TextView textView = Reminder.this.remindertime;
                            StringBuilder sb = new StringBuilder();
                            sb.append(Reminder.this.calendar.get(10) == 0 ? 12 : Reminder.this.calendar.get(10));
                            sb.append(":");
                            sb.append(Reminder.this.calendar.get(12) < 10 ? "0" : "");
                            sb.append(Reminder.this.calendar.get(12));
                            sb.append(" PM");
                            textView.setText(sb.toString());
                        } else {
                            TextView textView2 = Reminder.this.remindertime;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(Reminder.this.calendar.get(10) == 0 ? 12 : Reminder.this.calendar.get(10));
                            sb2.append(":");
                            sb2.append(Reminder.this.calendar.get(12) < 10 ? "0" : "");
                            sb2.append(Reminder.this.calendar.get(12));
                            sb2.append(" AM");
                            textView2.setText(sb2.toString());
                        }
                        AppPref.setReminderTime(Reminder.this.getApplicationContext(), Reminder.this.calendar.getTimeInMillis());
                        AppPref.setDAILY(Reminder.this.getApplicationContext(), true);
                    }
                }, Reminder.this.mHour, Reminder.this.mMinute, false).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
