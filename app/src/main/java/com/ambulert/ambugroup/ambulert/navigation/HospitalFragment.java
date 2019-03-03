package com.ambulert.ambugroup.ambulert.navigation;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HospitalFragment  extends Fragment implements OnMapReadyCallback {

    private final String TAG = "HospitalFragment";
    ArrayList<Marker> markers = new ArrayList<Marker>();
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hospital,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        try {
            //getHospitals();
            geoLocate("St. Vincent General Hospital");
            geoLocate("Chong Hua Hospital");
        } catch (IOException e) {
            e.printStackTrace();
        }
        goToLocationZoom(10.2820, 123.8813,10);
    }

    public void geoLocate(String location) throws IOException {

        Geocoder gc = new Geocoder(getActivity());
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String locality = address.getLocality();

        Toast.makeText(getActivity(), locality, Toast.LENGTH_LONG).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToLocationZoom(lat, lng, 15);

        setMarker(locality, lat, lng);

    }



    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        googleMap.moveCamera(update);

    }

    private void setMarker(String locality, double lat, double lng) {
        MarkerOptions options = new MarkerOptions()
                .title(locality)
                .draggable(true)
                .position(new LatLng(lat, lng));

        markers.add(googleMap.addMarker(options));
    }

}
