package com.example.cs496_2.ui.dashboard;

import static com.example.cs496_2.MainActivity.user_id;
import static com.example.cs496_2.TravelActivity.travel_id;

import android.annotation.SuppressLint;
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
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DashboardItem> dashboardItemArrayList;

    private static final String USER_FORMAT = "yyyy.MM.dd";


    public DashboardAdapter(Context context, ArrayList<DashboardItem> dashboardItemArrayList) {
        this.context = context;
        this.dashboardItemArrayList = dashboardItemArrayList;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DashboardAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_spends, parent, false));
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.ViewHolder holder, int position) {
        DashboardItem item = dashboardItemArrayList.get(position);
        holder.itemView.setVisibility(View.VISIBLE);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("지출 아이템 클릭", position + item.getSpend_name());
                RetrofitAPI api = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
                Call<JsonObject> call = api.deleteSpend(user_id, travel_id, item.getId(), item.isUserSpend());
                Log.e("path", user_id + "," + travel_id + "," + item.getId() + "," + item.isUserSpend());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
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
        holder.spend_name.setText(item.getSpend_name());
        holder.spend_money.setText(String.format("₩%s", item.getSpend_amount()));

        SimpleDateFormat dateFormat = new SimpleDateFormat(USER_FORMAT);
        holder.spend_date_time.setText(dateFormat.format(item.getCreated_date()));
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return dashboardItemArrayList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView spend_category;
        private TextView spend_name;
        private TextView spend_money;
        private TextView spend_date_time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            spend_category = itemView.findViewById(R.id.iv_spend_category);
            spend_name = itemView.findViewById(R.id.tv_spend_name);
            spend_money = itemView.findViewById(R.id.tv_spend_money);
            spend_date_time = itemView.findViewById(R.id.tv_spend_date_time);
        }
    }
}
