package com.financemoney.yoga;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.load.Key;
import com.financemoney.yoga.database.Favourite_databse;
import com.financemoney.yoga.frgaments.BMI_calculatore_F;
import com.financemoney.yoga.frgaments.Excerise_F;
import com.financemoney.yoga.frgaments.Histroy_F;
import com.financemoney.yoga.frgaments.Home_F;
import com.financemoney.yoga.helper.Youga_helper;
import com.financemoney.yoga.internalstorage.Constants;
import com.financemoney.yoga.internalstorage.Mypreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    LinearLayout bannerContainer;
    LinearLayout bmibtn;
    ImageView btnfav;

    LinearLayout btnsetting;

    int curretlyloadstack = 0;
    Favourite_databse db;
    LinearLayout excerisebtn;
    LinearLayout historybtn;
    LinearLayout homebtn;
    TextView tvcalc;
    TextView tvexcerise;
    TextView tvhistroy;
    TextView tvhome;
    ArrayList<Youga_helper> yougahelpers = new ArrayList<>();
    FrameLayout frameLayout;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_dashboard);

        this.db = new Favourite_databse(this);
        this.tvhome = (TextView) findViewById(R.id.tv_home);
        this.bannerContainer = (LinearLayout) findViewById(R.id.bannerContainer);
        this.frameLayout = (FrameLayout) findViewById(R.id.f2_adplaceholderr);
        this.tvexcerise = (TextView) findViewById(R.id.tv_excerise);
        this.tvcalc = (TextView) findViewById(R.id.tv_calc);
        this.tvhistroy = (TextView) findViewById(R.id.tv_histroy);
        this.homebtn = (LinearLayout) findViewById(R.id.home_btn);
        this.excerisebtn = (LinearLayout) findViewById(R.id.excerise_btn);
        this.bmibtn = (LinearLayout) findViewById(R.id.bmi_btn);
        this.historybtn = (LinearLayout) findViewById(R.id.history_Btn);
        this.btnfav = (ImageView) findViewById(R.id.btn_fav);

        this.btnsetting = (LinearLayout) findViewById(R.id.btn_setting);


        this.homebtn.setOnClickListener(this);
        this.excerisebtn.setOnClickListener(this);
        this.bmibtn.setOnClickListener(this);
        this.historybtn.setOnClickListener(this);
        this.btnfav.setOnClickListener(this);
        this.btnsetting.setOnClickListener(this);

        fiilyougahelper();
        setdefaultvalues();
        this.homebtn.performClick();
        setfonts();

        Main_application.loadBanner(this.bannerContainer, this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bmi_btn:
                replacefragment(new BMI_calculatore_F());
                this.curretlyloadstack = 2;
                return;
            case R.id.btn_fav:
                Intent intent = new Intent(this, Favourite_activity.class);
                intent.putExtra("youga_helpers", this.yougahelpers);
                startActivity(intent);
                return;


            case R.id.btn_setting:
                startActivity(new Intent(this, Setting_activity.class));
                return;


            case R.id.excerise_btn:
                Bundle bundle = new Bundle();
                bundle.putSerializable("yoga_helper", this.yougahelpers);
                Excerise_F exceriseF = new Excerise_F();
                exceriseF.setArguments(bundle);
                replacefragment(exceriseF);
                this.curretlyloadstack = 1;
                return;
            case R.id.history_Btn:
                replacefragment(new Histroy_F());

                this.curretlyloadstack = 3;
                return;
            case R.id.home_btn:
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("yoga_helper", this.yougahelpers);
                Home_F homeF = new Home_F();
                homeF.setArguments(bundle2);
                replacefragment(homeF);
                this.curretlyloadstack = 0;
                return;
            default:
                return;
        }
    }


    public void setdefaultvalues() {
        Mypreference mypreference = new Mypreference(this);
        if (mypreference.getInt(Constants.WORKOUT_SECOND) == 0) {
            mypreference.setInt(Constants.WORKOUT_SECOND, 20);
        }
        if (mypreference.getInt(Constants.WORKOUT_REST) == 0) {
            mypreference.setInt(Constants.WORKOUT_REST, 10);
        }
        if (mypreference.getString(Constants.EXCERISE_OPTION).equals("")) {
            mypreference.setString(Constants.EXCERISE_OPTION, Constants.MENUALY);
        }
    }

    public void fiilyougahelper() {
        try {
            JSONArray optJSONArray = new JSONObject(loadJSONFromAsset()).optJSONArray("steps");
            for (int i = 0; i < optJSONArray.length(); i++) {
                Youga_helper yougahelper = new Youga_helper();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("excerise_name");
                String optString2 = optJSONObject.optString("instruction");
                yougahelper.setYouganame(optString);
                yougahelper.setInstruction(optString2);
                yougahelper.setId(optJSONObject.optString(Favourite_databse.ID));
                yougahelper.setYougaimage("file:///android_asset/" + optJSONObject.optString("image") + ".png");
                if (!this.db.isinserted(optJSONObject.optString(Favourite_databse.ID))) {
                    this.db.insertdata("0", optJSONObject.optString(Favourite_databse.ID));
                }
                this.yougahelpers.add(yougahelper);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        try {
            InputStream open = getAssets().open("excerices.txt");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, Key.STRING_CHARSET_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void replacefragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    public void setfonts() {
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/BubbleBobble.ttf");
        this.tvhome.setTypeface(createFromAsset);
        this.tvexcerise.setTypeface(createFromAsset);
        this.tvcalc.setTypeface(createFromAsset);
        this.tvhistroy.setTypeface(createFromAsset);
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.curretlyloadstack == 0) {
                exitdialog(this);
            } else {
                this.homebtn.performClick();
                return false;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }


    public void exitdialog(final Activity activity) {
        if (activity != null) {
            View inflate = LayoutInflater.from(activity).inflate(R.layout.exit_confirm_dialog, (ViewGroup) null);
            Typeface createFromAsset = Typeface.createFromAsset(activity.getAssets(), "fonts/baloo_regular.ttf");


            ((TextView) inflate.findViewById(R.id.msg_title)).setTypeface(createFromAsset);
            ((TextView) inflate.findViewById(R.id.message)).setTypeface(createFromAsset);
            ((TextView) inflate.findViewById(R.id.cancel_text)).setTypeface(createFromAsset);
            ((TextView) inflate.findViewById(R.id.ok_text)).setTypeface(createFromAsset);
            final Dialog dialog = new Dialog(activity, R.style.MyAlertDialogTheme);
            dialog.requestWindowFeature(1);
            dialog.setContentView(inflate);
            dialog.setCancelable(true);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

            FrameLayout frame = dialog.findViewById(R.id.f2_adplaceholderr);
            Main_application.refreshAd(frame, Dashboard.this);


            ((RelativeLayout) inflate.findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            ((RelativeLayout) inflate.findViewById(R.id.ok_btn)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    dialog.dismiss();
                    Dashboard.super.onBackPressed();
                }
            });
            dialog.show();
            dialog.getWindow().setLayout(-1, -2);
        }
    }
}
