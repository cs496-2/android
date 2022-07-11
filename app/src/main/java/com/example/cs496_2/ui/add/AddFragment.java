package com.example.cs496_2.ui.add;

import androidx.annotation.Nullable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cs496_2.R;

import java.util.Calendar;


public class AddFragment extends Fragment {

    int isWon = 1;
    int category = 0;
    Boolean isPersonalSpend =true;


    private TextView spend_create_date;
    private TextView spend_create_time;
    private TextView currency;

    private Calendar calendar = Calendar.getInstance();
    private Button save_spend;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*입력 날짜*/
        spend_create_date = getView().findViewById(R.id.tv_spend_create_date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                spend_create_date.setText(i + "년 " + (i1 + 1) + "월 " + i2 + "일");
            }
        }, year, month, day);
        datePickerDialog.updateDate(year, month, day);
        spend_create_date.setText(year + "년 " + (month + 1) + "월 " + day + "일");
        spend_create_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        /*입력 시간*/
        spend_create_time = getView().findViewById(R.id.tv_spend_create_time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                spend_create_time.setText(i + "시 " + i1 + "분");
            }
        }, hour, minute, false);
        timePickerDialog.updateTime(hour, minute);
        spend_create_time.setText(hour + "시 " + minute + "분");
        spend_create_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        /*입력 화폐*/
        currency = getView().findViewById(R.id.tv_show_currency);
        currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWon == 1) {
                    currency.setText("FOREIGN");
                } else {
                    currency.setText("🇰🇷 WON");
                }
                isWon = -(isWon);
            }
        });

        /*지출 내용 저장*/
        EditText editTextNumberSigned = getView().findViewById(R.id.editTextNumberSigned);
//        RadioButton rb_category0 = getView().findViewById(R.id.rb_category0);
//        RadioButton rb_category1 = getView().findViewById(R.id.rb_category1);
//        RadioButton rb_category2 = getView().findViewById(R.id.rb_category2);
//        RadioButton rb_category3 = getView().findViewById(R.id.rb_category3);
//        RadioButton rb_category4 = getView().findViewById(R.id.rb_category4);
//        RadioButton rb_category5 = getView().findViewById(R.id.rb_category5);
        RadioGroup rg_spend_category_group = getView().findViewById(R.id.rg_spend_category_group);
        rg_spend_category_group.setOnCheckedChangeListener(onCategoryCheckedListener);
        RadioGroup rg_spend_group_or_personal = getView().findViewById(R.id.rg_spend_group_or_personal);
        rg_spend_group_or_personal.setOnCheckedChangeListener(onPersonalCheckedListener);
        save_spend = getView().findViewById(R.id.btn_spend_save);
        save_spend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // activity reload
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    RadioGroup.OnCheckedChangeListener onCategoryCheckedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.rb_category0) { category = 0; }
            else if (i == R.id.rb_category1) { category = 1; }
            else if (i == R.id.rb_category2) { category = 2; }
            else if (i == R.id.rb_category3) { category = 3; }
            else if (i == R.id.rb_category4) { category = 4; }
            else if (i == R.id.rb_category5) { category = 5; }
        }
    };

    RadioGroup.OnCheckedChangeListener onPersonalCheckedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.rb_spend_personal)
                isPersonalSpend = true;
            else if (i == R.id.rb_spend_group)
                isPersonalSpend = false;
        }
    };

}