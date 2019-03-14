package com.ambulert.ambugroup.ambulert.navigation;

import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.R;
import com.ambulert.ambugroup.ambulert.model.Hospital;
import com.ambulert.ambugroup.ambulert.model.HospitalAdapter;
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
    Context context;
    ArrayList<Marker> markers = new ArrayList<Marker>();
    private GoogleMap googleMap;
    ListView lv ;
    ArrayList<Hospital> list = new ArrayList<>();
    HospitalAdapter adapter ;

    AdapterView.AdapterContextMenuInfo info;


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

        lv = view.findViewById(R.id.listHospital);
        adapter = new HospitalAdapter(getActivity(), list);

        list.add(new Hospital(R.drawable.hospital,"Chong Hua Hospital","Chong Hua Hospital Location","Private"));
        list.add(new Hospital(R.drawable.hospital,"St. Vincent Hospital","St. Vincent Hospital Location","Private"));

        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
                ab.setTitle("adasdda");
                ab.setMessage("asdad");
                ab.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.show();
            }
        });
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
        goToLocationZoom(10.2820, 123.8813,13);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(list.get(info.position).getHospitalName());
        View view = v;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        view = (view == null) ? inflater.inflate(R.layout.layout_hospital_info,null) : view;
//
//        TextView hospitalName = view.findViewById(R.id.hospital_name);
//        TextView hospitalLocation = view.findViewById(R.id.hospital_location);
//        TextView hospitalType = view.findViewById(R.id.hospital_type);
//
//        hospitalName.setText(list.get(info.position).getHospitalName());
//        hospitalLocation.setText(list.get(info.position).getHospitalLocation());
//        hospitalType.setText(list.get(info.position).getHospitalType());
//
        menu.setHeaderView(view);
    }
}
