package com.example.rewardrover.Coins;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rewardrover.Modals.BannerModal;

import java.util.ArrayList;

public class CoinViewModal extends ViewModel {
    CoinsRepository coinsRepository;
    CoinViewModal() {
        coinsRepository = new CoinsRepository();

    }

    void fetchBannersList(Context context) {
        coinsRepository.fetchBannerDataFromApi(context);
    }

    LiveData<ArrayList<BannerModal>> getBannersList() {
        return coinsRepository.getBannersList();
    }
}
