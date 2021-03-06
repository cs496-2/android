package com.example.cs496_2.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cs496_2.MainActivity;
import com.example.cs496_2.R;
import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.TravelActivity;
import com.example.cs496_2.data.DTO.Travel;
import com.example.cs496_2.data.DTO.TravelUserPair;
import com.example.cs496_2.data.DTO.UserSpend;
import com.example.cs496_2.data.TravelViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private String TAG = "--------------HomeFragment";

    private String user_id;
    private int travel_id;
    private TextView travel_name;

    private TextView start_date;
    private TextView end_date;
    Calendar calendar = Calendar.getInstance();

    private TextView pick_country;
    private TextView set_currency;
    private static final String TEXTVIEW_DEFAULT_COLOR = "#808080";

    private RecyclerView rv_members;

    private ArrayList<String> invited_member_list;

    private ImageView travel_cover;
    private String imagePath;
    private final int GALLERY = 100; // ????????? ?????? ??? ???????????? ????????? ???

    private Button travel_delete;
    private Button travel_save;

    private static final String DB_FORMAT = "";
    private static final String USER_FORMAT = "yyyy??? MM??? dd???";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        /*?????? ??????*/
        travel_name = getView().findViewById(R.id.tv_travel_name);
        travel_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("HomeFrag", "?????? ?????? ?????? ?????? ??????");
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_input_text, null);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setView(dialog_view)
                        .show();

                TextView txt_head = dialog_view.findViewById(R.id.tv_dialog_text_input);
                EditText txt_input = dialog_view.findViewById(R.id.et_dialog_text_input);
                Button txt_save_btn = dialog_view.findViewById(R.id.btn_dialog_input_save);
                txt_head.setText("?????? ??????");
                txt_save_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!txt_input.getText().toString().equals("")) {
                            travel_name.setText(txt_input.getText());
                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });

        /*?????? ?????? ??????*/
        start_date = getView().findViewById(R.id.tv_start_date);
        int start_year = calendar.get(Calendar.YEAR);
        int start_month = calendar.get(Calendar.MONTH);
        int start_day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog startDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                start_date.setText(i + "??? " + (i1 + 1) + "??? " + i2 + "???");
                start_date.setBackground(null);
            }
        }, start_year, start_month, start_day);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDatePicker.show();
            }
        });

        /*?????? ?????? ??????*/
        end_date = getView().findViewById(R.id.tv_end_date);
        int end_year = calendar.get(Calendar.YEAR);
        int end_month = calendar.get(Calendar.MONTH);
        int end_day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog endDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                end_date.setText(i + "??? " + (i1 + 1) + "??? " + i2 + "???");
                end_date.setBackground(null);
            }
        }, end_year, end_month, end_day);
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDatePicker.show();
            }
        });

        /*?????? ??????*/
        pick_country = getView().findViewById(R.id.tv_country);
        set_currency = getView().findViewById(R.id.tv_currency);
        pick_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("HomeFrag", "?????? ?????? ?????? ?????? ??????");
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_country_picker, null);
                AlertDialog country_picker_dialog = new AlertDialog.Builder(getActivity())
                        .setView(dialog_view)
                        .show();

                TextView country_head = dialog_view.findViewById(R.id.tv_dialog_country);
                NumberPicker country_picker = dialog_view.findViewById(R.id.np_dialog_country);
                Button country_select_btn = dialog_view.findViewById(R.id.btn_dialog_country);
                country_picker.setMinValue(0);
                country_picker.setMaxValue(9);
                country_picker.setValue(0);
                country_picker.setWrapSelectorWheel(false);
                country_select_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("Country Picker Dialog", String.valueOf(country_picker.getValue()));
                        // todo: ??????, ??????, ?????? json ????????? ??????
                        // todo: ?????? ?????? string list ??????
                        pick_country.setText(String.valueOf(country_picker.getValue()));
                        // todo: ?????? ?????? ??????
                        set_currency.setText("???????? USD");
                        country_picker_dialog.dismiss();
                    }
                });
            }
        });

        /*?????? ?????? ??????*/
        invited_member_list = new ArrayList<>();
        rv_members = getView().findViewById(R.id.rv_invited_member);
        rv_members.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_members.setAdapter(new MemberAdapter(getActivity(), invited_member_list));

        /*?????? ?????? ?????????*/
        travel_cover = getView().findViewById(R.id.iv_travel_cover_image);
        travel_cover.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY);
        });

        /*?????? ?????? ?????? ???*/
        RetrofitAPI retrofitAPI = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
        Intent intent = getActivity().getIntent();
        SimpleDateFormat dateFormat = new SimpleDateFormat(USER_FORMAT);
        if (intent != null) {
            Log.e(TAG, "I got travelId! " + intent.getStringExtra("travelId"));
            Call<JsonObject> travelJson = retrofitAPI.getTravelProject(MainActivity.user_id, intent.getStringExtra("travelId"));
            travelJson.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.e(TAG, "retrofit success");
                    JsonObject body = response.body();
                    JsonObject data = body.getAsJsonObject("data");
                    JsonObject travelJson = data.getAsJsonObject("resultTravelData");
                    Travel travel = new Gson().fromJson(travelJson, Travel.class);
                    travel_id = Integer.parseInt(travel.getTravelId());
                    travel_name.setText(travel.getTravelName());
                    start_date.setText(dateFormat.format(travel.getStartDate()));
                    start_date.setBackground(null);
                    end_date.setText(dateFormat.format(travel.getEndDate()));
                    end_date.setBackground(null);
                    pick_country.setText(travel.getTravelCountry());
                    pick_country.setTextColor(Color.parseColor(TEXTVIEW_DEFAULT_COLOR));
                    Log.e(TAG, String.valueOf(travelJson));
                    JsonArray JoinedUserList = data.getAsJsonArray("joinedUserList");
                    for (int i = 0; i < JoinedUserList.size(); i++) {
                        JsonObject object = JoinedUserList.get(i).getAsJsonObject();
//                        Log.e(TAG, String.valueOf(object));
                        JsonObject object1 = object.getAsJsonObject("user");
//                        Log.e(TAG, String.valueOf(object1.get("userId")));
                        JsonElement element = object1.get("userId");
                        invited_member_list.add(element.getAsString());
                        rv_members.setAdapter(new MemberAdapter(getActivity(), invited_member_list));
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(TAG, "retrofit failed");
                }
            });
        }

        //todo:: ?????? ?????? ??????
        travel_delete = getView().findViewById(R.id.btn_travel_delete);
        travel_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        //todo:: ?????? ?????? ????????????
        travel_save = getView().findViewById(R.id.btn_travel_update);
        travel_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "btn_travel_update");

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

    /*??????????????? ?????? ????????????*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // ????????? ?????? ??????
            if (requestCode == GALLERY) { // ????????? ????????? ??????
//				1) data??? ?????? ???????????? ??????
                imagePath = data.getDataString(); // "content://media/external/images/media/7215"
//				2) ???????????? ???????????? ??????
                Cursor cursor = getContext().getContentResolver().query(data.getData(), null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    imagePath = cursor.getString(index); // ???) "/media/external/images/media/7215"
                    cursor.close();
                }
            }

            if (imagePath.length() > 0) {
                Glide.with(this)
                        .load(imagePath)
                        .centerCrop()
                        .into(travel_cover);
            }
        }
    }
}