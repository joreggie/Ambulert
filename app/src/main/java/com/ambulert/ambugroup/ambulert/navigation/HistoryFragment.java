package com.ambulert.ambugroup.ambulert.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ambulert.ambugroup.ambulert.R;
import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataUser;
import com.ambulert.ambugroup.ambulert.model.Report;
import com.ambulert.ambugroup.ambulert.model.ReportAdapter;
import com.ambulert.ambugroup.ambulert.model.Responders;
import com.ambulert.ambugroup.ambulert.model.UserId;
import com.ambulert.ambugroup.ambulert.model.UserIdResponse;
import com.ambulert.ambugroup.ambulert.model.reportItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryFragment extends Fragment {

    ListView lv ;
    ArrayList<Report> report_list = new ArrayList<>();
    ReportAdapter adapter ;
    LinearLayout loading;
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
        loading = view.findViewById(R.id.nameofProgress);
        loading.setVisibility(View.VISIBLE);

        String userid = PreferenceDataUser.getLoggedInUserid(getActivity());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Ambulert.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Ambulert request = retrofit.create(Ambulert.class);
        Log.d(TAG,"Userid"+ userid);
        Call<UserIdResponse> response = request.userId(new UserId(userid));
        response.enqueue(new Callback<UserIdResponse>() {
            @Override
            public void onResponse(Call<UserIdResponse> call, Response<UserIdResponse> response) {
                loading.setVisibility(View.GONE);
                UserIdResponse res = response.body();
                Responders r1 = new Responders("Joanna Reggie","09453453454");
                ArrayList<reportItem> reports = res.getReportItem();
                String report_location,report_type,report_created;
                int count = 1;
                for(int i=0; i<reports.size(); i++){
                    report_location = reports.get(i).getReport_location();
                    report_type = reports.get(i).getReport_type();
                    report_created = reports.get(i).getReport_created();
                    report_list.add(new Report("Report " + count,report_type,R.drawable.hospital,"Mambaling Cebu City",r1,report_created,""));
                    count += 1;
                }
                lv.setDivider(null);
                lv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UserIdResponse> call, Throwable t) {

            }
        });









    }


}
