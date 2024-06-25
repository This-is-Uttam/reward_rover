package com.example.rewardrover.Fragments;

import static com.example.rewardrover.Utils.Constants.*;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rewardrover.Adapters.LeaderBoardAdapter;
import com.example.rewardrover.Adapters.ReferViewpagerAdapter;
import com.example.rewardrover.Modals.LeaderBoardModal;
import com.example.rewardrover.R;
import com.example.rewardrover.Utils.Constants;
import com.example.rewardrover.Utils.ControlRoom;
import com.example.rewardrover.databinding.FragmentReferBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ReferFragment extends Fragment {

    FragmentReferBinding binding;
    ArrayList<LeaderBoardModal> leaderBoardList;

    public ReferFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReferBinding.inflate(inflater, container, false);

        binding.referVp.setAdapter(new ReferViewpagerAdapter(this));
        new TabLayoutMediator(binding.referTabs,binding.referVp,(tab, position) -> {
            if (position == 0){
                tab.setText("Refer");
            }else {
                tab.setText("Milestones");
            }
        }).attach();

        return binding.getRoot();
    }


}