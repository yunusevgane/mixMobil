package com.financemoney.yoga.adapters;

import android.view.View;

public interface ViewItemclicklistener {
    void currentprogress(int i, int i2);

    void delete(String str, int i);

    void onClick(View view, int i);

    void save(String str, String str2, String str3);

    void update(String str, int i, boolean z, String str2);

    void viewaction(int i);
}
