package com.ambulert.ambugroup.ambulert.navigation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ambulert.ambugroup.ambulert.R;
import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.Hospital;
import com.ambulert.ambugroup.ambulert.model.HospitalAdapter;
import com.ambulert.ambugroup.ambulert.model.HospitalNameResponse;
import com.ambulert.ambugroup.ambulert.model.ListHospital;
import com.ambulert.ambugroup.ambulert.userReport;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HospitalFragment extends Fragment implements OnMapReadyCallback {

    private final String TAG = "HospitalFragment";
    Context context;
    ArrayList<Marker> markers = new ArrayList<Marker>();
    private GoogleMap googleMap;
    ListView lv;
    ArrayList<Hospital> list = new ArrayList<>();
    HospitalAdapter adapter;

    AdapterView.AdapterContextMenuInfo info;
    Button alertHospital;
    TextView currentLocation;
    String lat, lng;
    LinearLayout loading;

    private final AlphaAnimation btnClick = new AlphaAnimation(1F, 0.8F);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hospital, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alertHospital = view.findViewById(R.id.alertHospitals);
        currentLocation = view.findViewById(R.id.currentLocation);
        alertHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(btnClick);
                Intent intent = new Intent(getActivity(), userReport.class);
                intent.putExtra("address", currentLocation.getText().toString());
                startActivity(intent);
            }
        });
        loading = view.findViewById(R.id.nameofProgress);
        loading.setVisibility(View.VISIBLE);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        lv = view.findViewById(R.id.listHospital);
        adapter = new HospitalAdapter(getActivity(), list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Ambulert.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Ambulert request = retrofit.create(Ambulert.class);
        Call<HospitalNameResponse> response = request.getHospital();
        response.enqueue(new Callback<HospitalNameResponse>() {
            @Override
            public void onResponse(Call<HospitalNameResponse> call, Response<HospitalNameResponse> response) {
                loading.setVisibility(View.GONE);
                HospitalNameResponse res = response.body();
                ArrayList<ListHospital> hospitals = res.getListHospital();
                String hospital_name,hospital_address,hospital_type;
                for (int i = 0; i < hospitals.size(); i++) {
                    hospital_name = hospitals.get(i).getHospital_name();
                    hospital_address=hospitals.get(i).getHospital_address();
                    hospital_type=hospitals.get(i).getHospital_type();

                    list.add(new Hospital(R.drawable.hospital, hospital_name, hospital_address, hospital_type));

                    lv.setAdapter(adapter);
                    registerForContextMenu(lv);

                }

            }

            @Override
            public void onFailure(Call<HospitalNameResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // locate current location of patient
        lat = getArguments().getString("lat");
        lng = getArguments().getString("lng");
        if (lat != null && lng != null) {
            goToLocationZoom(Double.parseDouble(lat), Double.parseDouble(lng), 15);
        } else {
            goToLocationZoom(10.3157, 123.8854, 15);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Ambulert.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Ambulert request = retrofit.create(Ambulert.class);
        Call<HospitalNameResponse> response = request.getHospital();
        response.enqueue(new Callback<HospitalNameResponse>() {
            @Override
            public void onResponse(Call<HospitalNameResponse> call, Response<HospitalNameResponse> response) {
                loading.setVisibility(View.GONE);
                HospitalNameResponse res = response.body();
                ArrayList<ListHospital> hospitals = res.getListHospital();
                String hospital_name;
                for (int i = 0; i < hospitals.size(); i++) {
                    hospital_name = hospitals.get(i).getHospital_name();
                    try {
                        //show all hospitals in the map
                        geoLocate(hospital_name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HospitalNameResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }

    public void geoLocate(String location) throws IOException {

        Geocoder gc = new Geocoder(getActivity());
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String addressLine = address.getAddressLine(0);

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        setMarker(addressLine, lat, lng);
    }

    public void getAddress(double lat, double lng) throws IOException {
        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
        Address obj = addresses.get(0);
        String add = obj.getAddressLine(0);
        currentLocation.setText(add);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        try {
            getAddress(lat, lng);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MarkerOptions mp = new MarkerOptions();
        mp.position(new LatLng(lat, lng));
        mp.title("My Current Location");
        mp.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(mp);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(list.get(info.position).getHospitalName());
        View view = v;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ;
        view = (view == null) ? inflater.inflate(R.layout.layout_hospital_info, null) : view;

        TextView hospitalName = view.findViewById(R.id.hospital_name);
        TextView hospitalLocation = view.findViewById(R.id.hospital_location);
        TextView hospitalType = view.findViewById(R.id.hospital_type);

        hospitalName.setText(list.get(info.position).getHospitalName());
        hospitalLocation.setText(list.get(info.position).getHospitalLocation());
        hospitalType.setText(list.get(info.position).getHospitalType());

        menu.setHeaderView(view);
    }
}
