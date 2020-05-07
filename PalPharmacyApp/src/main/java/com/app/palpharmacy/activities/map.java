package com.app.palpharmacy.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.app.palpharmacy.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap mapAPI;
    SupportMapFragment mapFragment;
    private String ltd, lng, name;

    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ltd = getIntent().getExtras().getString("latitude");
        lng = getIntent().getExtras().getString("longitude");
        name = getIntent().getExtras().getString("pharmacy_name");

        if (isNullOrEmpty(ltd)) {
            ltd = "31.715083";
        }
        if (isNullOrEmpty(lng)) {
            lng = "35.202585";
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapAPI = googleMap;
        double x = Double.parseDouble(ltd);
        double y = Double.parseDouble(lng);

        LatLng latLng = new LatLng(x, y);
        mapAPI.addMarker(new MarkerOptions().position(latLng).title(name));
        CameraPosition location = CameraPosition.builder().target(new LatLng(x, y)).zoom(16).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(location));
        // mapAPI.animateCamera(CameraUpdateFactory.newLatLngZoom(BethlehemUniversity,18),10,null);

    }
}
