package com.financemoney.yoga.frgaments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.financemoney.yoga.R;
import com.financemoney.yoga.bmi.CalculateBMI;
import com.financemoney.yoga.bmi.bmidatatable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BMI_calculatore_F extends Fragment implements View.OnClickListener {
    CalculateBMI calculateBMI;
    Date date = new Date();
    TextInputEditText feetfield;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    TextInputEditText inchfield;
    TextInputLayout inplay1;
    TextInputLayout inplay2;
    TextInputLayout inplay3;
    TextWatcher textWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
            Log.d("", "asa");
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Log.d("", "asa");
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            BMI_calculatore_F.this.resultsect.setVisibility(View.GONE);
        }
    };

    LinearLayout resultsect;
    View rootview;
    TextView tvcalculate;
    TextView tvheading;
    TextView tvheight;
    TextView tvmassstatus;
    TextView tvresult;
    TextView tvyoubmi;
    TextView valbmi;
    TextView valmasstatus;
    TextInputEditText weightfield;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootview = layoutInflater.inflate(R.layout.fragment_b_m_i_calculatore_, viewGroup, false);
        this.weightfield = (TextInputEditText) this.rootview.findViewById(R.id.weight_field);
        this.inchfield = (TextInputEditText) this.rootview.findViewById(R.id.inch_field);
        this.feetfield = (TextInputEditText) this.rootview.findViewById(R.id.feet_field);
        this.resultsect = (LinearLayout) this.rootview.findViewById(R.id.result_sect);
        this.tvheight = (TextView) this.rootview.findViewById(R.id.tv_height);
        this.tvresult = (TextView) this.rootview.findViewById(R.id.tv_result);
        this.inplay1 = (TextInputLayout) this.rootview.findViewById(R.id.inp_lay1);
        this.inplay2 = (TextInputLayout) this.rootview.findViewById(R.id.inp_lay2);
        this.tvmassstatus = (TextView) this.rootview.findViewById(R.id.tv_mass_status);
        this.inplay3 = (TextInputLayout) this.rootview.findViewById(R.id.inp_lay3);
        this.valmasstatus = (TextView) this.rootview.findViewById(R.id.val_mas_status);
        this.tvheading = (TextView) this.rootview.findViewById(R.id.tv_heading);
        this.tvcalculate = (TextView) this.rootview.findViewById(R.id.tv_calculate);
        this.tvyoubmi = (TextView) this.rootview.findViewById(R.id.tv_you_bmi);
        this.valbmi = (TextView) this.rootview.findViewById(R.id.val_bmi);
        Typeface createFromAsset = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BubbleBobble.ttf");
        this.weightfield.setTypeface(createFromAsset);
        this.inchfield.setTypeface(createFromAsset);
        this.feetfield.setTypeface(createFromAsset);
        this.inplay1.setTypeface(createFromAsset);
        this.inplay2.setTypeface(createFromAsset);
        this.inplay3.setTypeface(createFromAsset);
        this.tvheight.setTypeface(createFromAsset);
        this.tvcalculate.setTypeface(createFromAsset);
        this.tvheading.setTypeface(createFromAsset);
        this.tvresult.setTypeface(createFromAsset);
        this.tvmassstatus.setTypeface(createFromAsset);
        this.valmasstatus.setTypeface(createFromAsset);
        this.tvyoubmi.setTypeface(createFromAsset);
        this.valbmi.setTypeface(createFromAsset);
        this.weightfield.addTextChangedListener(this.textWatcher);
        this.feetfield.addTextChangedListener(this.textWatcher);
        this.inchfield.addTextChangedListener(this.textWatcher);
        this.tvcalculate.setOnClickListener(this);
        return this.rootview;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tv_calculate) {
            String obj = this.weightfield.getText().toString();
            String obj2 = this.feetfield.getText().toString();
            String obj3 = this.inchfield.getText().toString();
            if (isvalid(obj, obj2, obj3)) {
                try {
                    Double valueOf = Double.valueOf(Double.parseDouble(obj2));
                    if (obj3.isEmpty()) {
                        obj3 = "0";
                    }
                    this.calculateBMI = new CalculateBMI(valueOf.doubleValue(), Double.valueOf(Double.parseDouble(obj3)).doubleValue(), Double.valueOf(Double.parseDouble(obj)).doubleValue());
                    double camlculatebmi = this.calculateBMI.camlculatebmi(this.calculateBMI.getInputkg(), this.calculateBMI.getInputinches(), this.calculateBMI.getInputfeet());
                    String str = this.calculateBMI.getbmitype(camlculatebmi);
                    String format = this.formatter.format(this.date);
                    bmidatatable bmidatatable = new bmidatatable(getActivity());
                    bmidatatable.openDB();
                    bmidatatable.insertRecord(format, Double.toString(camlculatebmi), str);
                    bmidatatable.closeDB();
                    FragmentActivity activity = getActivity();
                    Toast.makeText(activity, "Your BMI" + camlculatebmi + " " + str, Toast.LENGTH_SHORT).show();
                    this.resultsect.setVisibility(View.VISIBLE);
                    TextView textView = this.valbmi;
                    textView.setText(camlculatebmi + "");
                    this.valmasstatus.setText(str);
                    char c = 65535;
                    switch (str.hashCode()) {
                        case -2068796604:
                            if (str.equals("Over Weight")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -1963325487:
                            if (str.equals("Normal Weight")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -1385714896:
                            if (str.equals("Underweight")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 809773:
                            if (str.equals("Obesity")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 74345766:
                            if (str.equals("Extremely Obesity")) {
                                c = 4;
                                break;
                            }
                            break;

                        default:
                            break;
                    }
                    if (c == 0 || c != 1) {
                        Log.d("", "sds");
                    }
                } catch (Exception e) {
                    FragmentActivity activity2 = getActivity();
                    Toast.makeText(activity2, "Enter Valid Input" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public boolean isvalid(String str, String str2, String str3) {
        if (str.isEmpty()) {
            Toast.makeText(getActivity(), "Weight must be required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!str2.isEmpty()) {
            return true;
        } else {
            Toast.makeText(getActivity(), "Height must be required", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
