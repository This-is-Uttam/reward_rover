package com.example.rewardrover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.example.rewardrover.databinding.ActivityMainBinding;
import com.example.rewardrover.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_REQ_CODE = 1001;
    private static final String TAG = "MainActivityLocation";
    ActivityMainBinding binding;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

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
} 