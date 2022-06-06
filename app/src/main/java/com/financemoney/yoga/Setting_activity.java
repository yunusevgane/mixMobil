package com.financemoney.yoga;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.financemoney.yoga.adapters.Setting_list_adapter;
import com.financemoney.yoga.adapters.ViewItemclicklistener;
import com.financemoney.yoga.helper.Setting_helper;
import com.financemoney.yoga.internalstorage.Constants;
import com.financemoney.yoga.internalstorage.Mypreference;

import java.util.ArrayList;

public class Setting_activity extends AppCompatActivity {
     Setting_list_adapter adapter;
    FrameLayout bannerContainer;

     int count = 0;
     ImageView exitbtn;
     TextView headertitle;
     ArrayList<Setting_helper> settinghelpers = new ArrayList<>();
     RecyclerView settinglist;

    static int access008(Setting_activity settingactivity) {
        int i = settingactivity.count;
        settingactivity.count = i + 1;
        return i;
    }

    static int access010(Setting_activity settingactivity) {
        int i = settingactivity.count;
        settingactivity.count = i - 1;
        return i;
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_setting_activity);


        this.exitbtn = (ImageView) findViewById(R.id.exit_btn);
        this.headertitle = (TextView) findViewById(R.id.header_title);
        this.bannerContainer = (FrameLayout) findViewById(R.id.f2_adplaceholderr);
        this.headertitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf"));
        this.settinglist = (RecyclerView) findViewById(R.id.setting_list);
        fillsetting();
        this.settinglist.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        this.adapter = new Setting_list_adapter(this, this.settinghelpers);
        this.settinglist.setAdapter(this.adapter);
        this.exitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.adapter.setItemclickListener(new ViewItemclicklistener() {


            @Override
            public void currentprogress(int i, int i2) {
                Log.d("","ssd");
            }

            public void delete(String str, int i) {
                Log.d("", "sd");
            }

            public void save(String str, String str2, String str3) {
                Log.d("", "sd");
            }

            public void update(String str, int i, boolean z, String str2) {
                Log.d("", "sd");
            }

            @Override
            public void viewaction(int i) {
                Log.d("","ssd");
            }


            public void onClick(View view, int i) {
                if (i == 0) {
                    Setting_activity.this.startActivity(new Intent(Setting_activity.this, Static_activity.class));
                } else if (i == 1) {
                    Setting_activity.this.startActivity(new Intent(Setting_activity.this, Reminder.class));
                } else if (i == 2) {
                    Setting_activity settingactivity = Setting_activity.this;
                    settingactivity.showworkoudificulty(settingactivity);
                } else if (i == 3) {
                    Setting_activity settingactivity2 = Setting_activity.this;
                    settingactivity2.shownextexcerise(settingactivity2);
                } else if (i == 4) {
                    Setting_activity settingactivity3 = Setting_activity.this;
                    settingactivity3.showresttimedialog(settingactivity3);
                } else if (i == 5) {
                    Setting_activity.this.ratethisapp();
                }
            }
        });
        Main_application.refreshAd(this.bannerContainer, Setting_activity.this);
    }

    public void ratethisapp() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
        intent.addFlags(1208483840);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void fillsetting() {
        Setting_helper settinghelper = new Setting_helper();
        settinghelper.setMenuicon(R.mipmap.ic_new_about);
        settinghelper.setMenuname("Privacy Policy");
        this.settinghelpers.add(settinghelper);
        Setting_helper settinghelper2 = new Setting_helper();
        settinghelper2.setMenuicon(R.mipmap.ic_new_reminder);
        settinghelper2.setMenuname("Set Reminder");
        this.settinghelpers.add(settinghelper2);
        Setting_helper settinghelper3 = new Setting_helper();
        settinghelper3.setMenuicon(R.mipmap.ic_new_wrokout);
        settinghelper3.setMenuname("Workout Difficulty");
        this.settinghelpers.add(settinghelper3);
        Setting_helper settinghelper4 = new Setting_helper();
        settinghelper4.setMenuicon(R.mipmap.ic_new_nextmove);
        settinghelper4.setMenuname("Next Exercises");
        this.settinghelpers.add(settinghelper4);
        Setting_helper settinghelper5 = new Setting_helper();
        settinghelper5.setMenuicon(R.mipmap.ic_new_rest);
        settinghelper5.setMenuname("Set Rest Time");
        this.settinghelpers.add(settinghelper5);
        Setting_helper settinghelper6 = new Setting_helper();
        settinghelper6.setMenuicon(R.mipmap.ic_new_rate);
        settinghelper6.setMenuname("Rate this app");
        this.settinghelpers.add(settinghelper6);
    }

    public void showworkoudificulty(Context context) {
        final View inflate = LayoutInflater.from(context).inflate(R.layout.workout_dificulty_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.cancel_btn);
        TextView textView2 = (TextView) inflate.findViewById(R.id.ok_btn);
        final RadioGroup radioGroup = (RadioGroup) inflate.findViewById(R.id.radioGroup);
        final Mypreference mypreference = new Mypreference(context);
        RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton) inflate.findViewById(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton) inflate.findViewById(R.id.radioButton3);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf");
        radioButton.setTypeface(createFromAsset);
        radioButton2.setTypeface(createFromAsset);
        radioButton3.setTypeface(createFromAsset);
        textView.setTypeface(createFromAsset);
        textView2.setTypeface(createFromAsset);
        ((TextView) inflate.findViewById(R.id.tv_label)).setTypeface(createFromAsset);
        if (mypreference.getInt(Constants.WORKOUT_SECOND) == 20) {
            radioButton.setChecked(true);
        } else if (mypreference.getInt(Constants.WORKOUT_SECOND) == 30) {
            radioButton2.setChecked(true);
        } else if (mypreference.getInt(Constants.WORKOUT_SECOND) == 40) {
            radioButton3.setChecked(true);
        } else {
            radioButton.setChecked(true);
        }
        final Dialog dialog = new Dialog(context, R.style.MyAlertDialogTheme);
        dialog.requestWindowFeature(1);
        dialog.setContentView(inflate);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        final Dialog dialog2 = dialog;
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RadioButton radioButton = (RadioButton) inflate.findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton.getText().equals("Easy")) {
                    mypreference.setInt(Constants.WORKOUT_SECOND, 20);
                } else if (radioButton.getText().equals("Medium")) {
                    mypreference.setInt(Constants.WORKOUT_SECOND, 30);
                } else if (radioButton.getText().equals("Hard")) {
                    mypreference.setInt(Constants.WORKOUT_SECOND, 40);
                }
                dialog2.dismiss();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(-1, -2);
    }

    public void shownextexcerise(Context context) {
        final View inflate = LayoutInflater.from(context).inflate(R.layout.next_excerise_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.ok_btn);
        final RadioGroup radioGroup = (RadioGroup) inflate.findViewById(R.id.radioGroup);
        final Mypreference mypreference = new Mypreference(context);
        RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton) inflate.findViewById(R.id.radioButton2);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf");
        ((TextView) inflate.findViewById(R.id.tv_label)).setTypeface(createFromAsset);
        radioButton.setTypeface(createFromAsset);
        radioButton2.setTypeface(createFromAsset);
        textView.setTypeface(createFromAsset);
        if (mypreference.getString(Constants.EXCERISE_OPTION).equals(Constants.MENUALY)) {
            radioButton.setChecked(true);
        } else if (mypreference.getString(Constants.EXCERISE_OPTION).equals(Constants.AUTOMATICALLY)) {
            radioButton2.setChecked(true);
        }
        Dialog dialog = new Dialog(context, R.style.MyAlertDialogTheme);
        dialog.requestWindowFeature(1);
        dialog.setContentView(inflate);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        final Dialog dialog2 = dialog;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RadioButton radioButton = (RadioButton) inflate.findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton.getText().equals("Manual")) {
                    mypreference.setString(Constants.EXCERISE_OPTION, Constants.MENUALY);
                } else if (radioButton.getText().equals("Automatic")) {
                    mypreference.setString(Constants.EXCERISE_OPTION, Constants.AUTOMATICALLY);
                } else {
                    mypreference.setString(Constants.EXCERISE_OPTION, Constants.MENUALY);
                }
                dialog2.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(-1, -2);
    }

    public void showresttimedialog(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rest_time_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.ok_btn);
        TextView textView2 = (TextView) inflate.findViewById(R.id.minus_btn);
        final TextView textView3 = (TextView) inflate.findViewById(R.id.counter);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf");
        ((TextView) inflate.findViewById(R.id.tv_title)).setTypeface(createFromAsset);
        textView.setTypeface(createFromAsset);
        textView2.setTypeface(createFromAsset);
        textView3.setTypeface(createFromAsset);
        final Dialog dialog = new Dialog(context, R.style.MyAlertDialogTheme);
        dialog.requestWindowFeature(1);
        dialog.setContentView(inflate);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        final Mypreference mypreference = new Mypreference(context);
        this.count = mypreference.getInt(Constants.WORKOUT_REST);
        textView3.setText(this.count + "");
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Setting_activity.this.count > 10) {
                    Setting_activity.access010(Setting_activity.this);
                    mypreference.setInt(Constants.WORKOUT_REST, Setting_activity.this.count);
                    TextView textView = textView3;
                    textView.setText(Setting_activity.this.count + "");
                }
            }
        });
        ((TextView) inflate.findViewById(R.id.pluse_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Setting_activity.this.count < 50) {
                    Setting_activity.access008(Setting_activity.this);
                    mypreference.setInt(Constants.WORKOUT_REST, Setting_activity.this.count);
                    TextView textView = textView3;
                    textView.setText(Setting_activity.this.count + "");
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(-1, -2);
    }
}
