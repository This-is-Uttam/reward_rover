package com.example.rewardrover.Coins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rewardrover.Adapters.AdNetAdapter;
import com.example.rewardrover.Adapters.BannerAdapter;
import com.example.rewardrover.Modals.AdNetModal;
import com.example.rewardrover.Modals.BannerModal;
import com.example.rewardrover.R;
import com.example.rewardrover.databinding.FragmentCoinBinding;

import java.util.ArrayList;

public class CoinFragment extends Fragment {

    FragmentCoinBinding binding;
    CoinViewModal coinViewModal;

    public CoinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoinBinding.inflate(inflater, container, false);


        coinViewModal = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(CoinViewModal.class);

        coinViewModal.getBannersList().observe(getViewLifecycleOwner(), new Observer<ArrayList<BannerModal>>() {
            @Override
            public void onChanged(ArrayList<BannerModal> bannerModal) {
                if (bannerModal.isEmpty()) {
                    binding.bannerRv.setVisibility(View.GONE);
                } else {
                    BannerAdapter bannerAdapter = new BannerAdapter(bannerModal, getContext());
                    binding.bannerRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//                    binding.bannerRv.setNestedScrollingEnabled(false);
                    binding.bannerRv.setAdapter(bannerAdapter);
//                    binding.bannerRv.hideShimmerAdapter();
                }
            }
        });

        if (coinViewModal.getBannersList().getValue() == null){
//            binding.bannerRv.showShimmerAdapter();
            coinViewModal.fetchBannersList(requireContext());
        }


        ArrayList<AdNetModal> adNetList = new ArrayList<>();

        adNetList.add(new AdNetModal(R.drawable.bitlabs, "BitLabs"));
        adNetList.add(new AdNetModal(R.drawable.pollfish, "Pollfish"));
        adNetList.add(new AdNetModal(R.drawable.cpxresearch, "CPX Research"));
        adNetList.add(new AdNetModal(R.drawable.ayetstudios, "Ayet Studios"));
        adNetList.add(new AdNetModal(R.drawable.video_svg, "Watch Video"));

        binding.adNetRv.setAdapter(new AdNetAdapter(adNetList, requireContext()));
        binding.adNetRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        return binding.getRoot();
    }
}