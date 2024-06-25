package com.example.rewardrover.Fragments;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rewardrover.R;
import com.example.rewardrover.databinding.FragmentMilestoneBinding;

import mishmarot.guy.com.timelineunit.StepLineView;

public class MilestoneFragment extends Fragment {
    FragmentMilestoneBinding binding;
    private MilestoneViewModel mViewModel;


    public static MilestoneFragment newInstance() {
        return new MilestoneFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMilestoneBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MilestoneViewModel.class);
        // TODO: Use the ViewModel
    }

}