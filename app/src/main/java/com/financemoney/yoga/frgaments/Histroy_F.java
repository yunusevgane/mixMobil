package com.financemoney.yoga.frgaments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.financemoney.yoga.Main_application;
import com.financemoney.yoga.R;
import com.financemoney.yoga.utils.CalendarDay;
import com.financemoney.yoga.database.Favourite_databse;
import com.financemoney.yoga.database.HistroyHelper;
import com.financemoney.yoga.decorators.EventDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.threeten.bp.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Histroy_F extends Fragment implements OnDateSelectedListener, OnMonthChangedListener, OnDateLongClickListener {
    Favourite_databse databse;
    ArrayList<HistroyHelper> histroyHelpers;
    RecyclerView histroylist;
    int month = -1;

    MaterialCalendarView widget;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Main_application.loadIndestrialads(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_histroy_, viewGroup, false);


        this.databse = new Favourite_databse(getActivity());
        this.histroylist = (RecyclerView) inflate.findViewById(R.id.histroy_list);
        this.histroylist.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        this.widget = (MaterialCalendarView) inflate.findViewById(R.id.calendarView);
        this.widget.setOnDateChangedListener(this);
        this.widget.setOnDateLongClickListener(this);
        this.widget.setOnMonthChangedListener(this);
        this.month = this.widget.getCurrentDate().getMonth();
        this.histroyHelpers = this.databse.getHistroy();
        makefilter();
        this.widget.setSelectedDate(LocalDate.now());
        return inflate;
    }

    public void makefilter() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    if (Histroy_F.this.histroyHelpers != null) {
                        for (int i = 0; i < Histroy_F.this.histroyHelpers.size(); i++) {
                            Date parse = new SimpleDateFormat("yyyy/MM/dd").parse(((HistroyHelper) Histroy_F.this.histroyHelpers.get(i)).getDate());
                            Calendar instance = Calendar.getInstance();
                            instance.setTimeInMillis(parse.getTime());
                            try {
                                arrayList.add(new CalendarDay(instance.get(1), instance.get(2) + 1, instance.get(5)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Histroy_F.this.widget.addDecorator(new EventDecorator(SupportMenu.CATEGORY_MASK, arrayList));
                    }
                } catch (ParseException e2) {
                    e2.printStackTrace();
                    Log.d("TAG", "run: " + e2.getMessage());
                }
            }
        }).start();
    }


    @Override
    public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull com.prolificinteractive.materialcalendarview.CalendarDay date) {

        Log.d("", "sd");
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull com.prolificinteractive.materialcalendarview.CalendarDay date, boolean selected) {
        this.month = date.getMonth();
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, com.prolificinteractive.materialcalendarview.CalendarDay date) {
        Log.d("", "sd");
    }
}
