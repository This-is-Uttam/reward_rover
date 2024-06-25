package com.example.rewardrover.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

//import com.example.rewardrover.databinding.ActivityMainBinding;
import com.example.rewardrover.Adapters.MainViewPagerAdapter;
import com.example.rewardrover.Coins.CoinFragment;
import com.example.rewardrover.Fragments.HighCoinsFragment;
import com.example.rewardrover.Fragments.MoreFragment;
import com.example.rewardrover.Fragments.RedeemFragment;
import com.example.rewardrover.Fragments.ReferFragment;
import com.example.rewardrover.R;
import com.example.rewardrover.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {

    private static final int LOCATION_REQ_CODE = 1001;
    private static final String TAG = "MainActivityLocation";
    ActivityMainBinding binding;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<Fragment> fragments;
    Fragment currentFrg;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragments = new ArrayList<>();
        fragments.add(new CoinFragment());
        fragments.add(new HighCoinsFragment());
        fragments.add(new ReferFragment());
        fragments.add(new RedeemFragment());
        fragments.add(new MoreFragment());

//        ViewPager2 mainViewPager = binding.mainViewPager;

//        mainViewPager.setAdapter(new MainViewPagerAdapter(this, fragments));
//        mainViewPager.setOffscreenPageLimit(5);

            fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


            fragmentTransaction.add(R.id.fragment_container, fragments.get(0), "coinFragment");

            fragmentTransaction.add(R.id.fragment_container, fragments.get(1), "highCoinFragment");
            fragmentTransaction.hide(fragments.get(1));

            fragmentTransaction.add(R.id.fragment_container, fragments.get(2), "referFragment");
            fragmentTransaction.hide(fragments.get(2));

            fragmentTransaction.add(R.id.fragment_container, fragments.get(3), "giftFragment");
            fragmentTransaction.hide(fragments.get(3));

            fragmentTransaction.add(R.id.fragment_container, fragments.get(4), "moreFragment");
            fragmentTransaction.hide(fragments.get(4));

            fragmentTransaction.commit();

            currentFrg = fragments.get(0);
        }else {
            fragments.set(0, getSupportFragmentManager().findFragmentByTag("coinFragment"));
            fragments.set(1, getSupportFragmentManager().findFragmentByTag("highCoinFragment"));
            fragments.set(2, getSupportFragmentManager().findFragmentByTag("referFragment"));
            fragments.set(3, getSupportFragmentManager().findFragmentByTag("giftFragment"));
            fragments.set(4, getSupportFragmentManager().findFragmentByTag("moreFragment"));

            String curFragTag = savedInstanceState.getString("currentFragmentTag");
            currentFrg = getSupportFragmentManager().findFragmentByTag(curFragTag);

            if (curFragTag == null){
                Toast.makeText(this, "Current fragment null", Toast.LENGTH_SHORT).show();
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.show(currentFrg).commit();
        }







        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.coinFragmentMenu) {
                    changeFragment( currentFrg,  fragments.get(0));
                    currentFrg = fragments.get(0);
                } else if (item.getItemId() == R.id.highCoinsFragmentMenu) {
                    changeFragment( currentFrg,  fragments.get(1));
                    currentFrg = fragments.get(1);
                } else if (item.getItemId() == R.id.referFragmentMenu) {
                    changeFragment( currentFrg,  fragments.get(2));
                    currentFrg = fragments.get(2);
                } else if (item.getItemId() == R.id.redeemFragmentMenu) {
                    changeFragment( currentFrg,  fragments.get(3));
                    currentFrg = fragments.get(3);
                } else if (item.getItemId() == R.id.moreFragmentMenu) {
                    changeFragment( currentFrg,  fragments.get(4));
                    currentFrg = fragments.get(4);
                }
                return true;
            }
            });



//        Getting Location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQ_CODE);
        } else {
            getLastLocation();
        }



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("currentFragmentTag", currentFrg.getTag());
    }

    private void changeFragment(Fragment currentFragment, Fragment nextFragment){

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(currentFragment);
        fragmentTransaction.show(nextFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == LOCATION_REQ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        } else {
            Toast.makeText(this, "Location permission required!", Toast.LENGTH_SHORT).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1, addresses -> {
                                Toast.makeText(this, "getLastLocation: \n Latitude: "+ addresses.get(0).getLatitude()+
                                        "\n Longitude: "+ addresses.get(0).getLongitude()+
                                        "\n Address: "+ addresses.get(0).getAddressLine(0)+
                                        "\n Locality: "+ addresses.get(0).getLocality()+
                                        "\n Area: "+ addresses.get(0).getAdminArea()+
                                        "\n Country: "+ addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

                                Log.d(TAG, "getLastLocation: \n Latitude: "+ addresses.get(0).getLatitude()+
                                        "\n Longitude: "+ addresses.get(0).getLongitude()+
                                        "\n Address: "+ addresses.get(0).getAddressLine(0)+
                                        "\n Locality: "+ addresses.get(0).getLocality()+
                                        "\n Area: "+ addresses.get(0).getAdminArea()+
                                        "\n Country: "+ addresses.get(0).getCountryName());
                                
                            });
                        }else {
                            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                            if (addressList != null) {
                                Log.d(TAG, "getLastLocation: \n Latitude: " + addressList.get(0).getLatitude() +
                                        "\n Longitude: " + addressList.get(0).getLongitude() +
                                        "\n Address: " + addressList.get(0).getAddressLine(0) +
                                        "\n Locality: " + addressList.get(0).getLocality() +
                                        "\n Area: " + addressList.get(0).getAdminArea() +
                                        "\n Country: " + addressList.get(0).getCountryName());
                            }else {
                                Toast.makeText(this, "AddressList NULL", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (IOException e) {
                        Toast.makeText(this, "Something went wrong! "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        throw new RuntimeException(e);
                    }
                }

            });
        }
      
    }

    /*public void jumpToHighCoins() {
        binding.mainViewPager.setCurrentItem(binding.mainViewPager.getCurrentItem()+1);
    }*/
}