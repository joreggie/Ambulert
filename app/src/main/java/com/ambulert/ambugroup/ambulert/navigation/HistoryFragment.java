package com.ambulert.ambugroup.ambulert.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ambulert.ambugroup.ambulert.R;
import com.ambulert.ambugroup.ambulert.model.Report;
import com.ambulert.ambugroup.ambulert.model.ReportAdapter;
import com.ambulert.ambugroup.ambulert.model.Responders;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    ListView lv ;
    ArrayList<Report> report_list = new ArrayList<>();
    ReportAdapter adapter ;
    private final String TAG="HistoryFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = view.findViewById(R.id.listHistory);
        adapter = new ReportAdapter(getActivity(), report_list);

        Responders r1 = new Responders("Joanna Reggie","09453453454");
        Responders r2 = new Responders("Timmy","09236999321");
        Responders r3 = new Responders("Charcae","09453345454");
        Responders r4 = new Responders("Mar","0945756754454");
        report_list.add(new Report("Report 1","Accident",R.drawable.hospital,"Mambaling Cebu City",r1,"12:45pm","January 12,2018"));
        report_list.add(new Report("Report 2","Accident",R.drawable.hospital,"Surigao City",r2,"8:45am","March 1,2019"));
        report_list.add(new Report("Report 3","Accident",R.drawable.hospital,"Mambaling Cebu City",r3,"4:45pm","January 16,2018"));
        report_list.add(new Report("Report 4","Accident",R.drawable.hospital,"Mambaling Cebu City",r4,"12:45pm","January 12,2018"));

        lv.setDivider(null);
        lv.setAdapter(adapter);


    }


}
