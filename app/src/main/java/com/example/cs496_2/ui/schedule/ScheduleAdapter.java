package com.example.cs496_2.ui.schedule;

import static com.example.cs496_2.MainActivity.travel_id;
import static com.example.cs496_2.MainActivity.user_id;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_2.R;
import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.ui.dashboard.DashboardItem;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleAdapter extends  RecyclerView.Adapter<com.example.cs496_2.ui.schedule.ScheduleAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ScheduleItem> scheduleItemArrayList;

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "hh:mm:ss";

    public ScheduleAdapter(Context context, ArrayList<ScheduleItem> scheduleItemArrayList) {
        this.context = context;
        this.scheduleItemArrayList = scheduleItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new com.example.cs496_2.ui.schedule.ScheduleAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schedules, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleItem item = scheduleItemArrayList.get(position);
        holder.itemView.setVisibility(View.VISIBLE);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("지출 아이템 클릭", position + item.getScheduleName());

                RetrofitAPI api = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);

                Call<JsonObject> call = api.deleteSchedule(user_id, travel_id, item.getScheduleId());

                Log.e("path", user_id + "," + travel_id + "," + item.getScheduleId());

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.i("response", String.valueOf(response));
                        Log.e("지출 삭제", "성공");
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("지출 삭제", "실패");
                    }
                });
                return true;
            }
        });
        holder.schedule_location.setText(item.getLocation());
        holder.schedule_name.setText(item.getScheduleName());
        holder.schedule_date.setText(item.getDate());
        holder.schedule_time.setText(item.getTime());

//        SimpleDateFormat dateFormat = new SimpleDateFormat(USER_FORMAT);
//        holder.spend_date_time.setText(dateFormat.format(item.getCreated_date();
//
//        SimpleTimeFormat dateFormat = new SimpleDateFormat(USER_FORMAT);
//        holder.spend_date_time.setText(dateFormat.format(item.getCreated_date();

    }

    @Override
    public int getItemCount() {
        return scheduleItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView schedule_location;
        private TextView schedule_name;
        private TextView schedule_date;
        private TextView schedule_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            schedule_location = itemView.findViewById(R.id.tv_schedule_location);
            schedule_name = itemView.findViewById(R.id.tv_schedule_name);
            schedule_date = itemView.findViewById(R.id.tv_schedule_date);
            schedule_time = itemView.findViewById(R.id.tv_schedule_time);
        }
    }
}
