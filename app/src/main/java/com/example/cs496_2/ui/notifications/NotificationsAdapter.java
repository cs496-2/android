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

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{

    private Context context;
    private List<String> category;

    public NotificationsAdapter(Context context, List<String> category) {
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_analysis, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        String str = category.get(position);
        holder.itemView.setVisibility(View.VISIBLE);

        holder.anlz_category_text.setText(str);
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

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
