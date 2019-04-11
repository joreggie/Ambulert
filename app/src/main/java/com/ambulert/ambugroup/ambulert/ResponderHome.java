package com.ambulert.ambugroup.ambulert;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.HospitalNameResponse;
import com.ambulert.ambugroup.ambulert.model.LocationResponse;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataResponder;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataUser;
import com.ambulert.ambugroup.ambulert.model.Report;
import com.ambulert.ambugroup.ambulert.model.Responders;
import com.ambulert.ambugroup.ambulert.model.UserId;
import com.ambulert.ambugroup.ambulert.model.UserIdResponse;
import com.ambulert.ambugroup.ambulert.model.reportItem;
import com.ambulert.ambugroup.ambulert.navigation.Home;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResponderHome extends AppCompatActivity implements OnMapReadyCallback {

    LinearLayout loading;
    private GoogleMap googleMap;
    LocationManager locman;
    LocationListener locationListener;
    String lat, lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_home);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        supportMapFragment.getMapAsync(this);

        loading = findViewById(R.id.nameofProgress);
        loading.setVisibility(View.VISIBLE);

        locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
//                Log.d(TAG, "Location: " + location.toString());
                if(location != null){
                    lat = String.format("%.4f",location.getLatitude());
                    lng = String.format("%.4f",location.getLongitude());

                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(ResponderHome.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ResponderHome.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(ResponderHome.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET},100);
            return;
        } else {
            locman.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

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
            Marker patient = map.addMarker(new MarkerOptions()
                    .position(latlngPatient)
                    .title("Patient"));
            patient.showInfoWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(lat != null && lng != null){
            loading.setVisibility(View.GONE);
            goToLocationZoom(Double.parseDouble(lat),Double.parseDouble(lng),15,"St. Vincent General Hospital");

        }
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
        Ambulert request = retrofit.create(Ambulert.class);
        Log.d(TAG,"Userid"+ userid);
        Call<UserIdResponse> response = request.userId(new UserId(userid));
    

        }
    public void getAddress(double lat, double lng) throws IOException {
        Geocoder geocoder = new Geocoder(ResponderHome.this);
        List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
        Address obj = addresses.get(0);
        String add = obj.getAddressLine(0);
    }

    private void goToLocationZoom(double lat, double lng, float zoom,String location) {
        LatLng ll = new LatLng(lat, lng);
        try {
            getAddress(lat, lng);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = null; // Closes the polyline.
        try {
            rectOptions = new PolylineOptions()
                    .add(ll)
                    .add(geoCode(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Get back the mutable Polyline
        Polyline polyline = googleMap.addPolyline(rectOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 12.0f));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.logout) {
            PreferenceDataResponder.clearLoggedInResponder(ResponderHome.this);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
