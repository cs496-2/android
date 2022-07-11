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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cs496_2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddFragment extends Fragment {

    int isWon = 1;

    private Button save_spend;
    private Calendar calendar = Calendar.getInstance();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView currency = getView().findViewById(R.id.tv_show_currency);
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

        /*입력 날짜*/
        TextView spend_create_date = getView().findViewById(R.id.tv_spend_create_date);
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
        TextView spend_create_time = getView().findViewById(R.id.tv_spend_create_time);
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

        /*지출 내용 저장*/
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

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        Toast.makeText(getActivity(), "Date: " + dateMessage, Toast.LENGTH_SHORT).show();
    }
}