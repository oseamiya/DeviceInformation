package com.oseamiya.deviceinformation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.app.Activity;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationInformation {
    private final Context context;
    private final Activity activity;
    public LocationInformation(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }
    public Location getLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }else if(location == null){
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
        return location;
    }
    public  double getCurrentLatitude() {
        if (getLocation() == null) {
            return 0;
        }
        return getLocation().getLatitude();
    }

    public double getCurrentLongitude() {
        if (getLocation() == null) {
            return 0;
        }
        return getLocation().getLongitude();
    }

    public String getStreetAddress(double latitude , double longitude) {
        return getStringAddress(latitude , longitude , 1);
    }

    public String getCity(double latitude , double longitude){
        return getStringAddress(latitude , longitude , 2);
    }
    public String getCountryName(double latitude , double longitude){
        return getStringAddress(latitude , longitude , 3);
    }
    public String getPostalCode(double latitude , double longitude){
        return getStringAddress(latitude , longitude , 4);
    }

    private String getStringAddress(double latitude , double longitude , int val){
        String address = "" ;
        Geocoder geocoder = new Geocoder(this.context , Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude , longitude , 1);
            Address add = addresses.get(0);
            if(val == 1){
                address = add.getAddressLine(0);
            }else if(val == 2){
                address = add.getLocality();
            }else if(val == 3){
                address = add.getCountryName();
            }else if(val == 4){
                address = add.getPostalCode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;

    }

}
