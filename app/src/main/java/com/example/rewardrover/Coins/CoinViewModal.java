package com.example.rewardrover.Coins;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rewardrover.Modals.AdNetModal;
import com.example.rewardrover.Modals.BannerModal;
import com.example.rewardrover.Modals.BuyCoinModal;

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

    void fetchBuyCoinList(Context context) {
        coinsRepository.fetchBuyCoinListFromApi(context);
    }

    LiveData<ArrayList<BuyCoinModal>> getBuyCoinList() {
        return coinsRepository.getBuyCoinsList();
    }

    void fetchWatchVideoList(Context context) {
        coinsRepository.fetchWatchVideoListFromApi(context);
    }

    LiveData<ArrayList<AdNetModal>> getWatchVideoList() {
        return coinsRepository.getWatchVidList();
    }
}
