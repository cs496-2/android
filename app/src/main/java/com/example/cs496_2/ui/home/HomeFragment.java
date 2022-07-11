package com.example.cs496_2.ui.home;

import static com.example.cs496_2.MainActivity.user_id;
import static com.example.cs496_2.TravelActivity.travel_id;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
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
import com.example.cs496_2.R;
import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.data.DTO.PostNewTravel;
import com.example.cs496_2.data.DTO.Travel;
import com.example.cs496_2.data.DTO.UpdateTravel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private String TAG = "--------------HomeFragment";

    private TextView travel_name;

    private TextView start_date;
    private TextView end_date;
    Calendar calendar = Calendar.getInstance();

    private TextView pick_country;
    private TextView set_currency;
    private static final String TEXTVIEW_DEFAULT_COLOR = "#808080";

    private RecyclerView rv_members;

    private TextView invite_member;
    private ArrayList<String> invited_member_list;

    private ImageView travel_cover;
    private String imagePath;
    private final int GALLERY = 100; // 갤러리 선택 시 인텐트로 보내는 값
    private boolean imgChanged = false;

    private Button travel_delete;
    private Button travel_save;
    private Intent intent;
    private String travelIdExist;

    private static final String DB_FORMAT = "";
    private static final String USER_FORMAT = "yyyy.MM.dd";
    private String token = "oooo";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        RetrofitAPI retrofitAPI = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);

        /*여행 제목*/
        travel_name = getView().findViewById(R.id.tv_travel_name);
        travel_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("HomeFrag", "여행 이름 입력 텍뷰 터치");
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_input_text, null);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setView(dialog_view)
                        .show();

                TextView txt_head = dialog_view.findViewById(R.id.tv_dialog_text_input);
                EditText txt_input = dialog_view.findViewById(R.id.et_dialog_text_input);
                Button txt_save_btn = dialog_view.findViewById(R.id.btn_dialog_input_save);
                txt_head.setText("여행 이름");
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

        /*여행 시작 날짜*/
        start_date = getView().findViewById(R.id.tv_start_date);
        int start_year = calendar.get(Calendar.YEAR);
        int start_month = calendar.get(Calendar.MONTH);
        int start_day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog startDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                start_date.setText(i + "." + (i1 + 1) + "." + i2);
                start_date.setBackground(null);
            }
        }, start_year, start_month, start_day);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDatePicker.show();
            }
        });

        /*여행 종료 날짜*/
        end_date = getView().findViewById(R.id.tv_end_date);
        int end_year = calendar.get(Calendar.YEAR);
        int end_month = calendar.get(Calendar.MONTH);
        int end_day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog endDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                end_date.setText(i + "." + (i1 + 1) + "." + i2);
                end_date.setBackground(null);
            }
        }, end_year, end_month, end_day);
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDatePicker.show();
            }
        });

        /*국가 선택*/
        pick_country = getView().findViewById(R.id.tv_country);
        set_currency = getView().findViewById(R.id.tv_currency);
        pick_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("HomeFrag", "여행 국가 입력 텍뷰 터치");
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
                        // todo: 국가, 화폐, 환율 json 리스트 생성
                        // todo: 국가 선택 string list 넣기
                        pick_country.setText(String.valueOf(country_picker.getValue()));
                        // todo: 화폐 자동 완성
                        set_currency.setText("USD");
                        country_picker_dialog.dismiss();
                    }
                });
            }
        });

        /*todo:멤버 초대*/
        invite_member = getView().findViewById(R.id.tv_invite_member);
        invite_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("HomeFrag", "멤버 초대 텍뷰 클릭");
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_input_text, null);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setView(dialog_view)
                        .show();
                TextView txt_head = dialog_view.findViewById(R.id.tv_dialog_text_input);
                EditText txt_input = dialog_view.findViewById(R.id.et_dialog_text_input);
                Button txt_save_btn = dialog_view.findViewById(R.id.btn_dialog_input_save);
                txt_head.setText("같이 여행 할 멤버를 초대하세요");
                txt_input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
                Pattern p = Pattern.compile(regex);
                txt_save_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        JsonObject token = new JsonObject();
                        token.addProperty("token", "oooo");
                        Call<JsonObject> jsonObjectCall = retrofitAPI.joinNewUserToTravel(user_id, String.valueOf(travel_id), txt_input.getText().toString(), token);
                        jsonObjectCall.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                Log.e(TAG, "retrofit success");
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Log.e(TAG, "retrofit failed");
                            }
                        });
//                        Matcher m = p.matcher(txt_input.getText());
//                        if (m.matches()) {
//                            if (!txt_input.getText().toString().equals("")){
//                                Log.e("멤버 초대", "이메일 " + txt_input.getText());
//                                //todo 이메일로 유저아이디 보내서 초대하기
//                            }
//                        }else {
//                            Toast.makeText(getContext(), "이메일 형식에 맞게 입력하세요", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });
            }
        });
        /*초대 멤버 관리*/
        invited_member_list = new ArrayList<>();
        rv_members = getView().findViewById(R.id.rv_invited_member);
        rv_members.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_members.setAdapter(new MemberAdapter(getActivity(), invited_member_list));

        /*여행 커버 이미지*/
        travel_cover = getView().findViewById(R.id.iv_travel_cover_image);
        travel_cover.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY);
        });

        /*기존 여행 선택 시*/
        intent = getActivity().getIntent();
        SimpleDateFormat dateFormat = new SimpleDateFormat(USER_FORMAT);
        travelIdExist = intent.getStringExtra("travelId");
        Log.e(TAG, "I got travelId! " + travelIdExist);
        Call<JsonObject> travelJson = retrofitAPI.getTravelProject(user_id, travelIdExist);
        travelJson.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(TAG, "retrofit success");
                JsonObject body = response.body();
                JsonObject data = body.getAsJsonObject("data");
                JsonObject travelJson = data.getAsJsonObject("resultTravelData");
                Travel travel = new Gson().fromJson(travelJson, Travel.class);
                // 기존 데이터 불러오기
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
                    JsonObject object1 = object.getAsJsonObject("user");
                    JsonElement element = object1.get("userId");
                    user_id = element.getAsString();
                    invited_member_list.add(user_id);
                    rv_members.setAdapter(new MemberAdapter(getActivity(), invited_member_list));
                }

                //이미지 로드
//                    Glide.with(HomeFragment.this)
//                            .load("http://192.249.21.206:3000/src/images/933374-viewom.jpg")
//                            .error(R.drawable.ic_baseline_clear_24)
//                            .centerCrop()
//                            .into(travel_cover);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "retrofit failed");
            }
        });

        //todo:: 여행 정보 삭제
        travel_delete = getView().findViewById(R.id.btn_travel_delete);
        travel_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) travel_cover.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Call<JsonObject> jsonObjectCall = retrofitAPI.deleteTravel(user_id, travel_id);
                jsonObjectCall.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e(TAG, "여행 삭제 success");
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e(TAG, "여행 삭제 failed");
                    }
                });
                getActivity().finish();
            }
        });
        //todo:: 여행 정보 업데이트
        travel_save = getView().findViewById(R.id.btn_travel_update);
        travel_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (travelIdExist != null) {//기존 정보 있음 -> 업데이트
                    Log.e(TAG, "btn_travel_update");
                    JsonObject updateTravel = new JsonObject();
                    updateTravel.addProperty("travelName", travel_name.getText().toString());
                    updateTravel.addProperty("travelCountry", pick_country.getText().toString());
                    updateTravel.addProperty("startDate", start_date.getText().toString());
                    updateTravel.addProperty("endDate", end_date.getText().toString());
                    updateTravel.addProperty("foreignCurrency", set_currency.getText().toString());
                    updateTravel.addProperty("coverImg", "none");
                    updateTravel.addProperty("token", "token");
                    Call<JsonObject> jsonObjectCall = retrofitAPI.updateTravel(user_id, travel_id, updateTravel);
                    jsonObjectCall.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            Log.e(TAG, "여행 수정 success");
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e(TAG, "여행 수정 failed");
                        }
                    });
                    getActivity().finish();
                    getActivity().overridePendingTransition(0, 0);
                    startActivity(getActivity().getIntent());
                    getActivity().overridePendingTransition(0, 0);
                } else {//기존 정보 없음 -> 새로 POST
                    JsonObject newTravel = new JsonObject();
                    newTravel.addProperty("travelName", travel_name.getText().toString());
                    newTravel.addProperty("travelCountry", pick_country.getText().toString());
                    newTravel.addProperty("startDate", start_date.getText().toString());
                    newTravel.addProperty("endDate", end_date.getText().toString());
                    newTravel.addProperty("foreignCurrency", set_currency.getText().toString());
                    newTravel.addProperty("coverImg", "none");
                    newTravel.addProperty("exchangeRate", "90.2");
                    newTravel.addProperty("token", "token");
                    Log.e(TAG, "new travel : " + newTravel);
                    Call<JsonObject> jsonObjectCall = retrofitAPI.postNewTravel(user_id, newTravel);
                    jsonObjectCall.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            Log.e(TAG, "여행 생성 success");
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e(TAG, "여행 생성 failed");
                        }
                    });
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /*갤러리에서 사진 가져오기*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // 결과가 있을 경우
            if (requestCode == GALLERY) { // 갤러리 선택한 경우
//				1) data의 주소 사용하는 방법
                imagePath = data.getDataString(); // "content://media/external/images/media/7215"
//				2) 절대경로 사용하는 방법
                Cursor cursor = getContext().getContentResolver().query(data.getData(), null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    imagePath = cursor.getString(index); // 예) "/media/external/images/media/7215"
                    cursor.close();
                }
            }

            if (imagePath.length() > 0) {
                Glide.with(this)
                        .load(imagePath)
                        .error(R.drawable.ic_baseline_clear_24)
                        .centerCrop()
                        .into(travel_cover);
            }
            imgChanged = true;
        }
    }
}