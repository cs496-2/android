package com.example.cs496_2;

import static com.example.cs496_2.MainActivity.travel_id;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TravelsAdapter extends RecyclerView.Adapter<TravelsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TravelsModel> travelsModelArrayList;

    // constructor
    public TravelsAdapter(Context context, ArrayList<TravelsModel> travelsModelArrayList) {
        this.context = context;
        this.travelsModelArrayList = travelsModelArrayList;//static?
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_travels, parent, false));
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TravelsModel model = travelsModelArrayList.get(position);
        holder.itemView.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(view -> {    // 여행 리사이클러뷰 누르면 해당 여행 프로젝트 열기
            Intent intent = new Intent(context, TravelActivity.class);
            travel_id= model.getTravel_id();
            context.startActivity(intent);
        });
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
//        holder.travel_cover.setImageResource(R.drawable.default_flights);   // 기본 이미지
        holder.travel_cover.setImageBitmap(getBitmapFromBase64ForIV(model.getCoverImg()));
        holder.travel_name.setText(model.getName());
        holder.travel_date.setText(String.format("%s ~ %s", dateFormat.format(model.getStart_date()), dateFormat.format(model.getEnd_date())));
        holder.travel_total_spend.setText("₩"+ model.getTotal_spend());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return travelsModelArrayList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView travel_cover;
        private TextView travel_name;
        private TextView travel_date;
        private TextView travel_total_spend;
        private ImageView travel_flag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            travel_cover = itemView.findViewById(R.id.idIVtravelCover);
            travel_name = itemView.findViewById(R.id.idTVtravelName);
            travel_date = itemView.findViewById(R.id.idTVtraveldate);
            travel_total_spend = itemView.findViewById(R.id.idTVtravelTotalSpend);
            travel_flag = itemView.findViewById(R.id.idIVtravelFlag);
        }
    }

    //imageView->drawable->byte[]->String(Base64)
    public String getStringFromIVForSQLDB(ImageView iv) {
        iv.buildDrawingCache();
        Bitmap bitmap = iv.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
        byte[] bytes = stream.toByteArray();
        return Base64.encodeToString(bytes, 0);
    }

    //String(base64)->Bitmap =>imageView
    public Bitmap getBitmapFromBase64ForIV(String base64) {
        byte[] decoded = Base64.decode(base64, Base64.DEFAULT);
        Bitmap byteToBitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        return byteToBitmap;
    }
}
