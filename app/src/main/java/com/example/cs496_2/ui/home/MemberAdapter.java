package com.example.cs496_2.ui.home;

import static com.example.cs496_2.MainActivity.user_id;
import static com.example.cs496_2.MainActivity.travel_id;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_2.R;
import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.TravelActivity;
import com.example.cs496_2.ui.dashboard.DashboardAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    private final String TAG = "멤버 리스트";

    private Context context;
    private ArrayList<String> stringArrayList;

    public MemberAdapter(Context context, ArrayList<String> stringArrayList) {
        this.context = context;
        this.stringArrayList = stringArrayList;
    }

    @NonNull
    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemberAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.ViewHolder holder, int position) {
        String member = stringArrayList.get(position);
        holder.itemView.setVisibility(View.VISIBLE);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                RetrofitAPI api = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
                Call<JsonObject> call = api.deleteUserFromTravel(user_id, travel_id, member);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e(TAG, "멤버 삭제 성공");
                        Toast.makeText(view.getContext(), member + "가 그룹에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        // activity reload
                        ((TravelActivity)context).finish();
                        ((TravelActivity)context).overridePendingTransition(0, 0);
                        context.startActivity(((TravelActivity)context).getIntent());
                        ((TravelActivity)context).overridePendingTransition(0, 0);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e(TAG, "멤버 삭제 실패");
                        Toast.makeText(view.getContext(), member + "를 삭제하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
        holder.member_invited.setText(stringArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView member_invited;
        private MemberAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            member_invited = itemView.findViewById(R.id.tv_invited_member);
        }

        public ViewHolder linkAdapter(MemberAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}
