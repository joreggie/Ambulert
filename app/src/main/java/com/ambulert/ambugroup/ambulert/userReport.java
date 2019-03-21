package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.AlertHospital;
import com.ambulert.ambugroup.ambulert.model.UserReportResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userReport extends AppCompatActivity {

    Button submit;
    FloatingActionButton btnAccident,btnPregnancy,btnIllness;
    TextView textAccident,textPregnancy,textIllness,currentLocation1;
    TextInputEditText reportOthers;
    String location,emergencyType,others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);

        Intent prev = getIntent();
        String address=prev.getStringExtra("address");

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Report");

        currentLocation1=findViewById(R.id.currentLocation1);
        currentLocation1.setText(address);
        reportOthers =findViewById(R.id.reportOthers);
        btnAccident=findViewById(R.id.btnAccident);
        btnPregnancy=findViewById(R.id.btnPregnancy);
        btnIllness=findViewById(R.id.btnIllness);
        textAccident=findViewById(R.id.textAccident);
        textPregnancy=findViewById(R.id.textPregnancy);
        textIllness=findViewById(R.id.textIllness);
        submit=findViewById(R.id.btnSubmitReport);

        btnAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyType="Accident";
                textAccident.setTextColor(Color.RED);
                textPregnancy.setTextColor(Color.GRAY);
                textIllness.setTextColor(Color.GRAY);
            }
        });
        btnPregnancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyType="Pregnancy";
                textAccident.setTextColor(Color.GRAY);
                textPregnancy.setTextColor(Color.RED);
                textIllness.setTextColor(Color.GRAY);
            }
        });
        btnIllness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyType="Illness";
                textAccident.setTextColor(Color.GRAY);
                textPregnancy.setTextColor(Color.GRAY);
                textIllness.setTextColor(Color.RED);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location=currentLocation1.getText().toString();
                others = reportOthers.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Ambulert.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Ambulert request = retrofit.create(Ambulert.class);
                Call<UserReportResponse> response = request.alertHospital(new AlertHospital(location,emergencyType,others));
                response.enqueue(new Callback<UserReportResponse>() {
                    @Override
                    public void onResponse(Call<UserReportResponse> call, Response<UserReportResponse> response) {
                        UserReportResponse res = response.body();
                        if(res.getAdd_report().equals("success")){
                            Toast.makeText(userReport.this, "Report submitted, please wait for a hospital to respond.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(userReport.this, "Report was not submitted, please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserReportResponse> call, Throwable t) {
                        Toast.makeText(userReport.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
