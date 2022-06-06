package com.financemoney.yoga.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.financemoney.yoga.R;
import com.financemoney.yoga.helper.Setting_helper;
import java.util.ArrayList;

public class Setting_list_adapter extends RecyclerView.Adapter<Setting_list_adapter.ViewHolder> {
    private Context context;

     ViewItemclicklistener itemclickListener;
    private ArrayList<Setting_helper> settinghelpers;

    public Setting_list_adapter(Context context2, ArrayList<Setting_helper> arrayList) {
        this.context = context2;
        this.settinghelpers = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.setting_view, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.settingname.setText(this.settinghelpers.get(i).getMenuname());
        viewHolder.settingname.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/baloo_regular.ttf"));
        Glide.with(this.context).asBitmap().load(Integer.valueOf(this.settinghelpers.get(i).getMenuicon())).into(viewHolder.imageicon);
    }

    public void setItemclickListener(ViewItemclicklistener viewItemclickListener) {
        this.itemclickListener = viewItemclickListener;
    }

    public int getItemCount() {
        return this.settinghelpers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

         ImageView imageicon;

         TextView settingname;

        public ViewHolder(View view) {
            super(view);
            this.imageicon = (ImageView) view.findViewById(R.id.image_icon);
            this.settingname = (TextView) view.findViewById(R.id.setting_name);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            Setting_list_adapter.this.itemclickListener.onClick(view, getPosition());
        }

        public boolean onLongClick(View view) {
            Setting_list_adapter.this.itemclickListener.onClick(view, getPosition());
            return true;
        }
    }
}
