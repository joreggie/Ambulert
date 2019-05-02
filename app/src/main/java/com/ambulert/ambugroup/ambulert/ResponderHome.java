package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.LocationResponse;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataResponder;
import com.ambulert.ambugroup.ambulert.model.ReportInfo;
import com.ambulert.ambugroup.ambulert.model.ResponderId;
import com.ambulert.ambugroup.ambulert.model.ResponderIdResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResponderHome extends AppCompatActivity implements OnMapReadyCallback {

    LinearLayout loading;
    private GoogleMap googleMap;
    String patient, responder;
    TextView user_location;
    Button complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_home);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        supportMapFragment.getMapAsync(this);

        user_location = findViewById(R.id.user_location);
        complete = findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Ambulert.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Ambulert service = retrofit.create(Ambulert.class);

                Call<ResponderIdResponse> ResponderId = service.getResponderId(new ResponderId(PreferenceDataResponder.getLoggedInResponderId(ResponderHome.this)));
                ResponderId.enqueue(new Callback<ResponderIdResponse>() {
                    @Override
                    public void onResponse(Call<ResponderIdResponse> call, Response<ResponderIdResponse> response) {
                        ResponderIdResponse resp = response.body();
                        Toast.makeText(ResponderHome.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponderIdResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


            }
        });
        loading = findViewById(R.id.nameofProgress);
        loading.setVisibility(View.VISIBLE);
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
        getReport();
    }

    public LatLng geoCode(String location) throws IOException {
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String addressLine = address.getAddressLine(0);

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        return new LatLng(lat, lng);
    }

    private void getReport() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Ambulert.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Ambulert service = retrofit.create(Ambulert.class);

        Call<LocationResponse> locationResponseCall = service.getTwoLocation(new ResponderId(PreferenceDataResponder.getLoggedInResponderId(ResponderHome.this)));
        locationResponseCall.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                loading.setVisibility(View.GONE);
                LocationResponse resp = response.body();

                ReportInfo r = resp.getReport_info();

                if(r.equals("")){
                    user_location.setText("There is have no assigned patient yet");
                }else{
                    patient = r.getReport_info().getReport_location();
                    responder = r.getHospital().getHospital_name();
                    user_location.setText(patient);
                    // Instantiates a new Polyline object and adds points to define a rectangle
                    PolylineOptions rectOptions = null;
                    try {
                        Toast.makeText(ResponderHome.this, responder, Toast.LENGTH_SHORT).show();
                        rectOptions = new PolylineOptions()
                                .add(geoCode(responder))
                                .add(geoCode(patient));
                        googleMap.addMarker(new MarkerOptions()
                                .position(geoCode(responder))
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                                .title("You"));
                        googleMap.addMarker(new MarkerOptions()
                                .position(geoCode(patient))
                                .title("The Patient"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(geoCode(responder)));


                        googleMap.animateCamera(CameraUpdateFactory.zoomBy(15));
                        Polyline polyline = googleMap.addPolyline(rectOptions);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home, menu);
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
