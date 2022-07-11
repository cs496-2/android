package com.example.cs496_2.ui.add;

import static com.example.cs496_2.MainActivity.user_id;
import static com.example.cs496_2.TravelActivity.travel_id;

import androidx.annotation.Nullable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.google.gson.JsonObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddFragment extends Fragment {

    private Boolean isWon = true;
    private int category = 0;
    private Boolean isPersonalSpend = true;


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
        RetrofitAPI api = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);

        /*입력 날짜*/
        spend_create_date = getView().findViewById(R.id.tv_spend_create_date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                spend_create_date.setText(i + "." + (i1 + 1) + "." + i2  );
            }
        }, year, month, day);
        datePickerDialog.updateDate(year, month, day);
        spend_create_date.setText(year + "." + (month + 1) + "." + day);
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
                spend_create_time.setText(i + ":" + i1);
            }
        }, hour, minute, false);
        timePickerDialog.updateTime(hour, minute);
        spend_create_time.setText(hour + ":" + minute);
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
                if (isWon) {
                    currency.setText("FOREIGN");
                    isWon = false;
                } else {
                    currency.setText("🇰🇷 WON");
                    isWon = true;
                }
            }
        });

        /* 금액 */
        EditText editTextNumberSigned = getView().findViewById(R.id.editTextNumberSigned);
        /* 내용 */
        EditText spend_name = getView().findViewById(R.id.et_spend_name);

        /*라디오 버튼*/
        RadioGroup rg_spend_category_group = getView().findViewById(R.id.rg_spend_category_group);
        rg_spend_category_group.setOnCheckedChangeListener(onCategoryCheckedListener);
        RadioGroup rg_spend_group_or_personal = getView().findViewById(R.id.rg_spend_group_or_personal);
        rg_spend_group_or_personal.setOnCheckedChangeListener(onPersonalCheckedListener);

        /*지출 내용 저장*/
        save_spend = getView().findViewById(R.id.btn_spend_save);
        save_spend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject spend = new JsonObject();
                spend.addProperty("isUserSpend", isPersonalSpend);
                spend.addProperty("spendName", spend_name.getText()+"");//꼭 getText 안하면 인스턴스 이름을 반환함
                String dateStr = spend_create_date.getText() + " " + spend_create_time.getText();
                spend.addProperty("createdDate", dateStr);//날짜 형식 텍스트여도 됨.
                spend.addProperty("spendAmount", editTextNumberSigned.getText().toString());
                spend.addProperty("useWon", isWon);
                spend.addProperty("spendCategory",category);
                Call<JsonObject> jsonObjectCall = api.postNewSpend(user_id, travel_id, spend);
                jsonObjectCall.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e("지출내용", "저장성공");
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("지출내용", "저장실패");
                    }
                });
                // activity reload
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    RadioGroup.OnCheckedChangeListener onCategoryCheckedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.rb_category0) {
                category = 0;
            } else if (i == R.id.rb_category1) {
                category = 1;
            } else if (i == R.id.rb_category2) {
                category = 2;
            } else if (i == R.id.rb_category3) {
                category = 3;
            } else if (i == R.id.rb_category4) {
                category = 4;
            } else if (i == R.id.rb_category5) {
                category = 5;
            }
            Log.e("카테고리 라디오 버튼", String.valueOf(category));
        }
    };
    RadioGroup.OnCheckedChangeListener onPersonalCheckedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.rb_spend_personal) isPersonalSpend = true;
            else if (i == R.id.rb_spend_group) isPersonalSpend = false;
            Log.e("지출 분류 라디오 버튼", String.valueOf(isPersonalSpend));
        }
    };
}