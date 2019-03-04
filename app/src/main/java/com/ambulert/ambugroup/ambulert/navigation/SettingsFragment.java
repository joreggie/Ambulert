package com.ambulert.ambugroup.ambulert.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.R;
import com.ambulert.ambugroup.ambulert.model.AmbulertList;
import com.ambulert.ambugroup.ambulert.model.ListAdapter;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private final String TAG = "SettingsFragment";
    ListView lv;
    ArrayList<AmbulertList> ambulertList = new ArrayList<>();
    ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = view.findViewById(R.id.listSettings);
        adapter = new ListAdapter(getActivity(), ambulertList);

        ambulertList.add(new AmbulertList(R.drawable.user,"Manage Profile"));
        ambulertList.add(new AmbulertList(R.drawable.preference,"User Preferences"));
        ambulertList.add(new AmbulertList(R.drawable.downloads,"Saved Reports"));


        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "cliked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
