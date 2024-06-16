package com.example.rewardrover.Coins;

import static com.example.rewardrover.Constants.*;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rewardrover.Modals.AdNetModal;
import com.example.rewardrover.Modals.BannerModal;
import com.example.rewardrover.Modals.BuyCoinModal;
import com.example.rewardrover.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
                "https://www.shutterstock.com/shutterstock/photos/2041227701/display_1500/stock-vector-business-webinar-horizontal-banner-template-design-modern-banner-design-with-black-and-white-2041227701.jpg",
                "https://www.shutterstock.com/shutterstock/photos/2041227701/display_1500/stock-vector-business-webinar-horizontal-banner-template-design-modern-banner-design-with-black-and-white-2041227701.jpg"

        ));
        bannerModals.add(new BannerModal(
                "https://d1xv5jidmf7h0f.cloudfront.net/circleone/images/products_gallery_images/Outdoor-Banners.jpg",
                "https://d1xv5jidmf7h0f.cloudfront.net/circleone/images/products_gallery_images/Outdoor-Banners.jpg"

        ));
        bannerModals.add(new BannerModal(
                "https://www.shutterstock.com/shutterstock/photos/2041227701/display_1500/stock-vector-business-webinar-horizontal-banner-template-design-modern-banner-design-with-black-and-white-2041227701.jpg",
                "https://www.shutterstock.com/shutterstock/photos/2041227701/display_1500/stock-vector-business-webinar-horizontal-banner-template-design-modern-banner-design-with-black-and-white-2041227701.jpg"

        ));
        bannerModals.add(new BannerModal(
                "https://www.shutterstock.com/shutterstock/photos/2041227701/display_1500/stock-vector-business-webinar-horizontal-banner-template-design-modern-banner-design-with-black-and-white-2041227701.jpg",
                "https://www.shutterstock.com/shutterstock/photos/2041227701/display_1500/stock-vector-business-webinar-horizontal-banner-template-design-modern-banner-design-with-black-and-white-2041227701.jpg"

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
