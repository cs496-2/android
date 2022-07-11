package com.example.cs496_2.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cs496_2.R;

import java.util.Calendar;

public class DatePickerDialog extends Dialog implements View.OnClickListener {

    private static final int MAX_YEAR = 2030;
    private static final int MIN_YEAR = 2020;

    private android.app.DatePickerDialog.OnDateSetListener listener;
    public Calendar cal = Calendar.getInstance();

    TextView btn_ok;
    NumberPicker yearPicker;
    NumberPicker monthPicker;
    NumberPicker dayPicker;

    public DatePickerDialog(@NonNull Context context) {
        super(context);
    }

    public void setListener(android.app.DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_datepicker);

        yearPicker = findViewById(R.id.picker_year);
        monthPicker = findViewById(R.id.picker_month);
        dayPicker = findViewById(R.id.picker_day);

    }
//
//    private void initPicker() {
//        yearPicker.setMinValue(MIN_YEAR);
//        yearPicker.setMaxValue(MAX_YEAR);
//        yearPicker.setValue(cal.get(Calendar.YEAR));
//
//        monthPicker.setMinValue(1);
//        monthPicker.setMaxValue(12);
//        monthPicker.setValue(cal.get(Calendar.MONTH) + 1);
//
//        dayPicker.setMinValue(1);
//        dayPicker.setMaxValue(31);
//
//    }

    @Override
    public void onClick(View view) {

    }
}
