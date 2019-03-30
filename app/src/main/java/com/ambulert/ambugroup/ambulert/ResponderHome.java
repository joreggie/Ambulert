package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResponderHome extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_home);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        LatLng latlngPatient = null;
        try {
            latlngPatient = geoCode("St. Vincent General Hospital");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Marker patient = map.addMarker(new MarkerOptions()
                .position(latlngPatient)
                .title("Patient"));
        patient.showInfoWindow();

        LatLng latlngResponder = null;
        try {
            latlngResponder = geoCode("Chong Hua Hospital");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Marker responder = map.addMarker(new MarkerOptions()
                .position(latlngResponder)
                .title("Your Location"));
        responder.showInfoWindow();

        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = null; // Closes the polyline.
        try {
            rectOptions = new PolylineOptions()
                    .add(geoCode("Chong Hua Hospital"))
                    .add(geoCode("St. Vincent General Hospital"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Get back the mutable Polyline
        Polyline polyline = map.addPolyline(rectOptions);
    }

    public LatLng geoCode(String location) throws IOException {
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String addressLine = address.getAddressLine(0);

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        return new LatLng(lat,lng);
    }

    private void getReport(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Ambulert.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Ambulert service = retrofit.create(Ambulert.class);
//        Call<>
    }
}
