package com.example.rewardrover.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rewardrover.Adapters.ReferViewpagerAdapter;
import com.example.rewardrover.R;
import com.example.rewardrover.databinding.FragmentReferBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ReferFragment extends Fragment {
    FragmentReferBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReferBinding.inflate(getLayoutInflater(),container,false);

        ViewPager2 viewPager2 = binding.referVp;
        viewPager2.setAdapter(new ReferViewpagerAdapter(this));
        new TabLayoutMediator(binding.referTab, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Refer");
                }else if(position== 1) {
                    tab.setText("Milestones");
                }
            }
        }).attach();

        return binding.getRoot();
    }
}