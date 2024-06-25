package com.example.rewardrover.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rewardrover.R;
import com.example.rewardrover.databinding.CarouselItemBinding;
import com.example.rewardrover.databinding.PromotionRvItemBinding;
import com.squareup.picasso.Picasso;
import com.example.rewardrover.Modals.BannerModal;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.Viewholder> {
    ArrayList<BannerModal> bannerList;
    Context context;
    boolean centerCrop = true;


    public BannerAdapter(ArrayList<BannerModal> bannerList, Context context) {
        this.bannerList = bannerList;
        this.context = context;


    }


    @NonNull
    @Override
    public BannerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_item,parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.Viewholder holder, int position) {
        BannerModal bannerModal = bannerList.get(position);

        Picasso.get()
                .load(bannerModal.getBannerImg())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.carouselImage);

//        holder.binding.promotionBannerUrl.setText(bannerModal.getBannerImgLink());

        holder.binding.carouselImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bannerIntent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(bannerModal.getBannerImgLink());
                bannerIntent.setData(uri);
                context.startActivity(bannerIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
//        PromotionRvItemBinding binding;
        CarouselItemBinding binding;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
//            promotionImg = itemView.findViewById(R.id.promotionImg);
            binding = CarouselItemBinding.bind(itemView);
//            binding = PromotionRvItemBinding.bind(itemView);
        }

    }
}
