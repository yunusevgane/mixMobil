package com.financemoney.yoga;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.financemoney.yoga.adapters.Excerise_list_adapter;
import com.financemoney.yoga.adapters.ViewItemclicklistener;
import com.financemoney.yoga.database.Favourite_databse;
import com.financemoney.yoga.database.Favourite_helper;
import com.financemoney.yoga.helper.Youga_helper;

import java.util.ArrayList;

public class Favourite_activity extends AppCompatActivity {
    Excerise_list_adapter adapter;
    LinearLayout bannerContainer;
    Favourite_databse db;
    ImageView exitbtn;
    RecyclerView favlist;

    public ArrayList<Youga_helper> favyogahelpers = new ArrayList<>();
    private ArrayList<Favourite_helper> favouritehelpers = new ArrayList<>();
    TextView headertitle;
     ArrayList<Youga_helper> yougahelpers =  new ArrayList<>();


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_favourite_activity);

        this.headertitle = (TextView) findViewById(R.id.header_title);
        this.bannerContainer = (LinearLayout) findViewById(R.id.bannerContainer);
        this.headertitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf"));
        this.db = new Favourite_databse(this);
        this.yougahelpers = (ArrayList) getIntent().getSerializableExtra("youga_helpers");
        this.exitbtn = (ImageView) findViewById(R.id.exit_btn);
        this.favlist = (RecyclerView) findViewById(R.id.fav_list);
        this.favlist.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new Excerise_list_adapter(this, this.favyogahelpers);
        this.favlist.setAdapter(this.adapter);
        this.exitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Favourite_activity.this.finish();
            }
        });
        this.adapter.setItemclickListener(new ViewItemclicklistener() {


            @Override
            public void currentprogress(int i, int i2) {
                Log.d("", "ssd");

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
                Log.d("", "ssd");
            }


            public void onClick(View view, int i) {
                Intent intent = new Intent(Favourite_activity.this, Perform_excrise_activity.class);
                intent.putExtra("excerise_name", ((Youga_helper) Favourite_activity.this.favyogahelpers.get(i)).getYouganame());
                intent.putExtra("excerise_image", ((Youga_helper) Favourite_activity.this.favyogahelpers.get(i)).getYougaimage());
                intent.putExtra("youga_helpers", Favourite_activity.this.favyogahelpers);
                intent.putExtra("current_position", i);
                Favourite_activity.this.startActivity(intent);
            }
        });
        new Main_application().loadBanner(this.bannerContainer, this);
    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        this.favouritehelpers.clear();
        this.favyogahelpers.clear();
        this.favouritehelpers = this.db.getData();
        for (int i = 0; i < this.yougahelpers.size(); i++) {
            Youga_helper yougahelper = this.yougahelpers.get(i);
            for (int i2 = 0; i2 < this.favouritehelpers.size(); i2++) {
                Favourite_helper favouritehelper = this.favouritehelpers.get(i2);
                if (favouritehelper.getId().equals(yougahelper.getId()) && favouritehelper.getIsfav().equals("1")) {
                    this.favyogahelpers.add(yougahelper);
                }
            }
        }
        this.adapter.notifyDataSetChanged();
    }
}
