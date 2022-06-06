package com.financemoney.yoga;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.financemoney.yoga.adapters.Excerise_list_adapter;
import com.financemoney.yoga.adapters.ViewItemclicklistener;
import com.financemoney.yoga.helper.Youga_helper;
import java.util.ArrayList;

public class Excerise_list extends AppCompatActivity {
     Excerise_list_adapter adapter;
     ImageView exitbtn;
     TextView headertitle;
     RecyclerView listview;

     ArrayList<Youga_helper> yougahelpers = new ArrayList<>();


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_excerise_list);
        this.yougahelpers = (ArrayList) getIntent().getSerializableExtra("youga_helpers");
        this.exitbtn = (ImageView) findViewById(R.id.exit_btn);
        this.listview = (RecyclerView) findViewById(R.id.list_view);
        this.headertitle = (TextView) findViewById(R.id.header_title);
        this.headertitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf"));
        this.listview.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new Excerise_list_adapter(this, this.yougahelpers);
        this.listview.setAdapter(this.adapter);
        this.exitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Excerise_list.this.finish();
            }
        });
        this.adapter.setItemclickListener(new ViewItemclicklistener() {
            public void currentprogress(int i, int i2) {
                Log.d("","ssd");
            }



            public void delete(String str, int i) {
                Log.d("","sd");
            }

            public void save(String str, String str2, String str3) {
                Log.d("","sd");
            }

            public void update(String str, int i, boolean z, String str2) {
                Log.d("","sd");
            }
            public void viewaction(int i) {
                Log.d("","ssd");
            }



            public void onClick(View view, int i) {
                Intent intent = new Intent(Excerise_list.this, Perform_excrise_activity.class);
                intent.putExtra("excerise_name", ((Youga_helper) Excerise_list.this.yougahelpers.get(i)).getYouganame());
                intent.putExtra("excerise_image", ((Youga_helper) Excerise_list.this.yougahelpers.get(i)).getYougaimage());
                intent.putExtra("youga_helpers", Excerise_list.this.yougahelpers);
                intent.putExtra("current_position", i);
                Excerise_list.this.startActivity(intent);
            }
        });
    }
}
