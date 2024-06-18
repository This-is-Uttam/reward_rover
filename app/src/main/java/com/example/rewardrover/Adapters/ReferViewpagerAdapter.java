package com.example.rewardrover.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rewardrover.Fragments.MilestoneFragment;
import com.example.rewardrover.Fragments.ReferChildFragment;

public class ReferViewpagerAdapter extends FragmentStateAdapter {


    public ReferViewpagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new ReferChildFragment();
        }else if (position == 1){
            return new MilestoneFragment();
        }else {
            return new ReferChildFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
