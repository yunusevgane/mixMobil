package com.financemoney.yoga;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Static_activity extends AppCompatActivity {
     ImageView exitbtn;
     TextView headerlabel;
     WebView webview;


     @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_static_activity);
        this.exitbtn = (ImageView) findViewById(R.id.exit_btn);
        this.webview = (WebView) findViewById(R.id.web_view);
        this.headerlabel = (TextView) findViewById(R.id.header_label);
        this.headerlabel.setText("Privacy Policy");
        this.webview.loadUrl("https://www.google.com/");
        this.exitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Static_activity.this.finish();
            }
        });
    }
}
