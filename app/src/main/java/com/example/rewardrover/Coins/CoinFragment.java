package com.example.rewardrover.Coins;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rewardrover.Adapters.AdNetAdapter;
import com.example.rewardrover.Adapters.BannerAdapter;
import com.example.rewardrover.Adapters.BuyCoinAdapter;
import com.example.rewardrover.Adapters.WatchVidAdapter;
import com.example.rewardrover.Modals.AdNetModal;
import com.example.rewardrover.Modals.BannerModal;
import com.example.rewardrover.R;
import com.example.rewardrover.databinding.FragmentCoinBinding;

import java.util.ArrayList;

public class CoinFragment extends Fragment {

    private static final String TAG = "CoinFragment";
    FragmentCoinBinding binding;
    CoinViewModal coinViewModal;
    static boolean shouldRun = true;
    ViewPager2 viewPager;

    public CoinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoinBinding.inflate(inflater, container, false);

        binding.swipeRefresh.setOnRefreshListener(() -> {
            binding.swipeRefresh.setRefreshing(false);
        });

        viewPager = binding.bannerVp;
        Log.d(TAG, "onCreateView: shouldRun:" + shouldRun);

        coinViewModal = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(CoinViewModal.class);

//        Banners Data
        coinViewModal.getBannersList().observe(getViewLifecycleOwner(), new Observer<ArrayList<BannerModal>>() {
            @Override
            public void onChanged(ArrayList<BannerModal> bannerList) {
                if (bannerList.isEmpty()) {
                    binding.bannerVp.setVisibility(View.GONE);
                } else {
//                    BannerAdapter bannerAdapter = new BannerAdapter(bannerList, getContext());
//                    binding.bannerRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
////                    binding.bannerRv.setNestedScrollingEnabled(false);
//                    binding.bannerRv.setAdapter(bannerAdapter);
////                    binding.bannerRv.hideShimmerAdapter();


                    viewPager.setAdapter(new BannerAdapter(bannerList, requireContext()));

                    viewPager.setOffscreenPageLimit(3);
                    viewPager.setClipChildren(false);
                    viewPager.setClipToPadding(false);

//                    For running banners
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (shouldRun) {
                                int position = viewPager.getCurrentItem();
                                position = position + 1;
                                if (position < bannerList.size()) {

                                } else {
                                    position = 0;
                                }
                                viewPager.setCurrentItem(position, true);
                            }
                            handler.postDelayed(this, 3000);

                        }
                    };
                    handler.postDelayed(runnable, 4000);


                }
            }
        });


        /*binding.bannerVp.getChildAt(0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(requireContext(), "Action Down", Toast.LENGTH_SHORT).show();
                        shouldRun = false;
                        return true;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(requireContext(), "Action Up", Toast.LENGTH_SHORT).show();
                        shouldRun = true;
                        Log.d(TAG, "onTouch: action UP");
                        return true;
                }
                return false;
            }
        });*/

        /*(v, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
//                    Toast.makeText(requireContext(), "Action Down", Toast.LENGTH_SHORT).show();
                    System.out.println("Action Down occurs!!");
                    return true;
                case MotionEvent.ACTION_UP:
                    Toast.makeText(requireContext(), "Action Up", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }*/


        if (coinViewModal.getBannersList().getValue() == null) {
//            binding.bannerRv.showShimmerAdapter();
            coinViewModal.fetchBannersList(requireContext());
        }

//        Buy Coins Data
        coinViewModal.getBuyCoinList().observe(getViewLifecycleOwner(), buyCoinList -> {
            if (buyCoinList.isEmpty()) {
                binding.buyCoinRv.setVisibility(View.GONE);
            } else {
                binding.buyCoinRv.setAdapter(new BuyCoinAdapter(buyCoinList, requireContext()));
                binding.buyCoinRv.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
        });

        if (coinViewModal.getBuyCoinList().getValue() == null) {
            coinViewModal.fetchBuyCoinList(requireContext());
        }

//        Watch Videos List Data
        coinViewModal.getWatchVideoList().observe(getViewLifecycleOwner(), watchVidList -> {
            if (watchVidList.isEmpty()) {
                binding.watchVidRv.setVisibility(View.GONE);
            } else {
                binding.watchVidRv.setAdapter(new WatchVidAdapter(watchVidList, requireContext()));
                binding.watchVidRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
            }
        });

        if (coinViewModal.getWatchVideoList().getValue() == null) {
            coinViewModal.fetchWatchVideoList(requireContext());
        }
//        Ad Networks
        ArrayList<AdNetModal> adNetList = new ArrayList<>();

        adNetList.add(new AdNetModal(R.drawable.bitlabs, "BitLabs"));
        adNetList.add(new AdNetModal(R.drawable.pollfish, "Pollfish"));
        adNetList.add(new AdNetModal(R.drawable.cpxresearch, "CPX Research"));
        adNetList.add(new AdNetModal(R.drawable.ayetstudios, "Ayet Studios"));
        adNetList.add(new AdNetModal(R.drawable.scratch_win, "Scratch & Win"));
        adNetList.add(new AdNetModal(R.drawable.spin_win, "Spin & Win"));

        binding.adNetRv.setAdapter(new AdNetAdapter(adNetList, requireContext()));
        binding.adNetRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));


        return binding.getRoot();
    }
}