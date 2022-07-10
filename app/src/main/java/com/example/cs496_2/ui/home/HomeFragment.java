package com.example.cs496_2.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cs496_2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {


    private TextView travel_name;

    private TextView start_date;
    private TextView end_date;
    Calendar calendar = Calendar.getInstance();

    private TextView country;

    private RecyclerView rv_members;
    private MemberAdapter mAdapter;
    private ArrayList<String> invited_member_list;

    private ImageView travel_cover;
    private String imagePath;
    private final int GALLERY = 100; // 갤러리 선택 시 인텐트로 보내는 값

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

                txt_save_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!txt_input.getText().toString().equals("")) { travel_name.setText(txt_input.getText()); }
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
                start_date.setText(i + "년 " + (i1 + 1) + "월 " + i2 + "일");
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
                end_date.setText(i + "년 " + (i1 + 1) + "월 " + i2 + "일");
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
        country = getView().findViewById(R.id.tv_country);

        /*초대 멤버 관리*/
        invited_member_list = new ArrayList<>();
        invited_member_list.add("11111@gmail.com");
        invited_member_list.add("22222@gmail.com");
        invited_member_list.add("33333@gmail.com");
        invited_member_list.add("44444@gmail.com");
        invited_member_list.add("55555@gmail.com");
        invited_member_list.add("66666@gmail.com");
        invited_member_list.add("77777@gmail.com");
        invited_member_list.add("88888@gmail.com");
        invited_member_list.add("99999@gmail.com");

        rv_members = (RecyclerView) getView().findViewById(R.id.rv_invited_member);

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_members.setLayoutManager(layoutManager);

        // 리사이클러뷰에 travelAdapter 객체 지정.
        mAdapter = new MemberAdapter(getActivity(), invited_member_list);
        rv_members.setAdapter(mAdapter);

        /*여행 커버 이미지*/
        travel_cover = getView().findViewById(R.id.iv_travel_cover_image);
        travel_cover.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY);
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
                    imagePath = cursor.getString(index); // "/media/external/images/media/7215"
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