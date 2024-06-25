package com.example.rewardrover.Coins;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rewardrover.Modals.AdNetModal;
import com.example.rewardrover.Modals.BannerModal;
import com.example.rewardrover.Modals.BuyCoinModal;
import com.example.rewardrover.R;

import java.util.ArrayList;

public class CoinsRepository {

    MutableLiveData<ArrayList<BannerModal>> banners = new MutableLiveData<>();
    MutableLiveData<ArrayList<BuyCoinModal>> buyCoinList = new MutableLiveData<>();
    MutableLiveData<ArrayList<AdNetModal>> watchVidList = new MutableLiveData<>();

    public LiveData<ArrayList<BannerModal>> getBannersList() {
        return banners;
    }

    void fetchBannerDataFromApi(Context context) {
        Log.d("BannerAdapterxx", "onBindViewHolder: fetching banner data: ");

        ArrayList<BannerModal> bannerModals = new ArrayList<>();

        bannerModals.add(new BannerModal(
                "https://images.pexels.com/photos/425241/pexels-photo-425241.jpeg?auto=compress&cs=tinysrgb&w=600",
                "https://images.pexels.com/photos/425241/pexels-photo-425241.jpeg?auto=compress&cs=tinysrgb&w=600"

        ));
        bannerModals.add(new BannerModal(
                "https://images.pexels.com/photos/956981/milky-way-starry-sky-night-sky-star-956981.jpeg?auto=compress&cs=tinysrgb&w=600",
                "https://images.pexels.com/photos/956981/milky-way-starry-sky-night-sky-star-956981.jpeg?auto=compress&cs=tinysrgb&w=600"

        ));
        bannerModals.add(new BannerModal(
                "https://images.pexels.com/photos/1779487/pexels-photo-1779487.jpeg?auto=compress&cs=tinysrgb&w=600",
                "https://images.pexels.com/photos/1779487/pexels-photo-1779487.jpeg?auto=compress&cs=tinysrgb&w=600"

        ));
        bannerModals.add(new BannerModal(
                "https://images.pexels.com/photos/352096/pexels-photo-352096.jpeg?auto=compress&cs=tinysrgb&w=600",
                "https://images.pexels.com/photos/352096/pexels-photo-352096.jpeg?auto=compress&cs=tinysrgb&w=600"

        ));
        bannerModals.add(new BannerModal(
                "https://images.pexels.com/photos/1435849/pexels-photo-1435849.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
                "https://images.pexels.com/photos/1435849/pexels-photo-1435849.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"

        ));

        banners.setValue(bannerModals);

    }

    LiveData<ArrayList<BuyCoinModal>> getBuyCoinsList() {
        return buyCoinList;
    }
    void fetchBuyCoinListFromApi(Context context) {
        ArrayList<BuyCoinModal> buyCoinModals = new ArrayList<>();

        buyCoinModals.add(new BuyCoinModal(
                9,10,0
        ));
        buyCoinModals.add(new BuyCoinModal(
                99,110,0
        ));
        buyCoinModals.add(new BuyCoinModal(
                999,1110,0
        ));
        buyCoinModals.add(new BuyCoinModal(
                9999,11110,0
        ));

        buyCoinList.setValue(buyCoinModals);
    }

    LiveData<ArrayList<AdNetModal>> getWatchVidList() {
        return watchVidList;
    }
    void fetchWatchVideoListFromApi(Context context) {
        ArrayList<AdNetModal> watchVidModals = new ArrayList<>();

        watchVidModals.add(new AdNetModal(
                R.drawable.video_svg, "Watch & Earn I"
        ));
        watchVidModals.add(new AdNetModal(
                R.drawable.video_svg, "Watch & Earn II"
        ));
        watchVidModals.add(new AdNetModal(
                R.drawable.video_svg, "Watch & Earn III"
        ));
        watchVidList.setValue(watchVidModals);
    }
}
