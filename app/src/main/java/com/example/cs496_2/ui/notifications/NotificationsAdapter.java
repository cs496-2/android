package com.example.cs496_2.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_2.R;
import com.example.cs496_2.ui.dashboard.DashboardAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{

    private Context context;
    private ArrayList<NotificationsItem> notificationsItemArrayList;

    public NotificationsAdapter(Context context, ArrayList<NotificationsItem> notificationsItemArrayList) {
        this.context = context;
        this.notificationsItemArrayList = notificationsItemArrayList;
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_analysis, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        NotificationsItem item = notificationsItemArrayList.get(position);
        holder.itemView.setVisibility(View.VISIBLE);

        holder.anlz_category_text.setText(item.getCategory());
        holder.anlz_amount.setText("₩"+Math.round(item.getSpend()));
    }

    @Override
    public int getItemCount() {
        return notificationsItemArrayList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        //todo:: 이미지뷰에 카테고리 별 아이콘 넣기
        ImageView anlz_category_image;
        TextView anlz_category_text;
        TextView anlz_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            anlz_category_image = itemView.findViewById(R.id.iv_anlz_category);
            anlz_category_text = itemView.findViewById(R.id.tv_anlz_category);
            anlz_amount = itemView.findViewById(R.id.tv_anlz_amount);
        }
    }
}
