package com.example.rewardrover.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rewardrover.Modals.AdNetModal;
import com.example.rewardrover.R;
import com.example.rewardrover.databinding.AdNetRvItemBinding;

import java.util.ArrayList;

public class AdNetAdapter extends RecyclerView.Adapter<AdNetAdapter.Viewholder> {
    ArrayList<AdNetModal> adNetList;
    Context context;

    public AdNetAdapter(ArrayList<AdNetModal> adNetList, Context context) {
        this.adNetList = adNetList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdNetAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ad_net_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdNetAdapter.Viewholder holder, int position) {
        AdNetModal adNetModal = adNetList.get(position);

        holder.binding.adNetImg.setImageResource(adNetModal.getAdNetImg());
        holder.binding.adNetName.setText(adNetModal.getAdNetName());

        holder.binding.claimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0 :
                        Toast.makeText(context, "BitLabs", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "Pollfish", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, "CPX Research", Toast.LENGTH_SHORT).show();
                        break;
                    case 3 :
                        Toast.makeText(context, "Ayet Studios", Toast.LENGTH_SHORT).show();
                        break;
//                    case 4:
//                        break;
//                    case 5:
//                        break;
//                    case 6 :
//                        break;
//                    case 7:
//                        break;
//                    case 8:
//                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return adNetList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        AdNetRvItemBinding binding;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            binding = AdNetRvItemBinding.bind(itemView);
        }
    }
}
