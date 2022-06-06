package com.financemoney.yoga;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_actvity extends AppCompatActivity {

    Handler handler = new Handler();


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.activity_splash_actvity);


        runsplash();
    }

    public void runsplash() {
        this.handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    Splash_actvity.this.handler.removeCallbacksAndMessages((Object) null);
                    Intent intent = new Intent(Splash_actvity.this, Dashboard.class);
                    intent.addFlags(67108864);
                    Splash_actvity.this.startActivity(intent);
                    Splash_actvity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 3000);
    }
}
