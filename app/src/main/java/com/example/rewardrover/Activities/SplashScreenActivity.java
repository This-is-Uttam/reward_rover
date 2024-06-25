package com.example.rewardrover.Activities;


import static com.example.rewardrover.Utils.Constants.AUTHORISATION;
import static com.example.rewardrover.Utils.Constants.BEARER;
import static com.example.rewardrover.Utils.Constants.CONTENT_TYPE;
import static com.example.rewardrover.Utils.Constants.CONTENT_TYPE_VALUE;
import static com.example.rewardrover.Utils.Constants.USER_API_URL;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rewardrover.R;
import com.example.rewardrover.Utils.ControlRoom;
import com.example.rewardrover.databinding.ActivitySplashScreenBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    String accessToken;
    private String unityGameID = "5369215";
    ActivityResultLauncher<IntentSenderRequest> activityResultLauncher;
    public static final int IMMEDIATE_REQUEST_CODE = 106;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent, getTheme()));
        FirebaseApp.initializeApp(this);

        binding.refreshActivity.setVisibility(View.GONE);
        binding.splashProgress.setVisibility(View.GONE);
        binding.logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in_anim));
        binding.appName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_down_anim));

//        accessToken = getSharedPreferences("ACCESS_TOKEN", MODE_PRIVATE)
//                        .getString("accessToken", "niHaiToken shared Preference");

        accessToken = ControlRoom.getInstance().getAccessToken(SplashScreenActivity.this);
//        accessToken = "81448|4Kta9lgEZI0FuOQ1nQSQAF8tpp7Kvh9XvRJtnWaB";
        Log.d("splash Token", "Splash Screen onCreate: My Access Token: " + accessToken);
        fetchUserData(accessToken);
        //        fetchUserData(ControlRoom.getInstance().getAccessToken(SplashScreenActivity.this));
//
//
//        binding.refreshActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                binding.splashProgress.setVisibility(View.VISIBLE);
//                fetchUserData(ControlRoom.getInstance().getAccessToken(SplashScreenActivity.this));
//            }
//        });
//
//
    }

    private void fetchUserData(String accessToken) {
        if (!ControlRoom.isNetworkConnected(SplashScreenActivity.this)) {
            Log.d("isNetworkConnected", "run: No Internet available..");
            Toast.makeText(SplashScreenActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            binding.refreshActivity.setVisibility(View.VISIBLE);
            binding.splashProgress.setVisibility(View.GONE);
        } else {
            binding.splashProgress.setVisibility(View.VISIBLE);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, USER_API_URL, null,
                    response -> {
                        try {
                            if (response.getBoolean("status") && response.getInt("code") == 200) {
//                                Log.d("fetchUserDetails", "onResponse: Sucessfull...:" + response.getString("data"));
                                // User is logged in go to MainActivity.
                                JSONObject userJsonObject = response.getJSONObject("data");
                                ControlRoom.getInstance().setUserData(userJsonObject, SplashScreenActivity.this);

                                // finish page
                                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                                finish();
                                binding.splashProgress.setVisibility(View.GONE);

                            } else if (!response.getBoolean("status") && response.getInt("code") == 201) {
//                                Log.d("checkUserData", "onResponse: data: " + response.getString("data"));

//                                User is not logged in go to Login Activity.
                                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Log.d("fetchUserDetails", "onResponse: Failed..." + response.getString("data"));
                                new LoginActivity().showErrorDialog(response.getString("data"));

                                binding.splashProgress.setVisibility(View.GONE);
                                Snackbar snackbar = Snackbar.make(findViewById(R.id.splashScreenRoot), "Something went wrong.", LENGTH_INDEFINITE)
                                        .setAction("Retry", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                fetchUserData(accessToken);
                                            }
                                        });
                                snackbar.show();

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("fetchUserDetails", "onResponse: error Response: " + error.getMessage());
                    binding.splashProgress.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.splashScreenRoot), "Something went wrong.", LENGTH_INDEFINITE)
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    fetchUserData(accessToken);
                                }
                            });
                    snackbar.show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> header = new HashMap<>();
                    header.put(CONTENT_TYPE, CONTENT_TYPE_VALUE);
                    header.put(AUTHORISATION, BEARER + SplashScreenActivity.this.accessToken);
                    return header;
                }
            };
            Volley.newRequestQueue(this).add(jsonObjectRequest);
        }

    }


}

