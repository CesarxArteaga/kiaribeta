package com.moviles.kiari;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class gps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latlongVernaza = new LatLng(-2.1834531,-79.8814979);
        LatLng latlong2 = new LatLng(-2.1850126,-79.8868627);
        LatLng latlong3 = new LatLng(-2.2032021,-79.8903623);


//        CameraUpdate camUpd1 =
//                CameraUpdateFactory
//                        .newLatLngZoom(new LatLng(-2.1834531,-79.8814979), 11);
        mMap.addMarker(new MarkerOptions().position(latlongVernaza).title("Hospital Luis Vernaza \n Tlf:042521585"));
        mMap.addMarker(new MarkerOptions().position(latlong2).title("Lcdo Jhonny Alvear \n Tlf:098099638"));
        mMap.addMarker(new MarkerOptions().position(latlong3).title("Lcdo Luis Gonzales \n Tlf:0912556920"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.moveCamera(camUpd1);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlongVernaza,14),80,null);
    }
}
