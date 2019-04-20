package com.ambulert.ambugroup.ambulert;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataUser;
import com.ambulert.ambugroup.ambulert.model.Profile;
import com.ambulert.ambugroup.ambulert.model.ProfileResponse;
import com.ambulert.ambugroup.ambulert.navigation.Home;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    TextInputEditText inputFirstname,inputMiddlename,inputLastname,inputEmail;
    TextInputLayout layoutFirstname,layoutMiddlename,layoutLastname,layoutEmail;
    Button saveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile Management");

        saveChanges = findViewById(R.id.saveChanges);
        inputFirstname = findViewById(R.id.inputUserFname);
        inputMiddlename = findViewById(R.id.inputUserMname);
        inputLastname = findViewById(R.id.inputUserLname);
        inputEmail = findViewById(R.id.inputUserEmail);

        inputFirstname.setText(PreferenceDataUser.getLoggedInFirstname(ProfileActivity.this));
        inputMiddlename.setText(PreferenceDataUser.getLoggedInMiddlename(ProfileActivity.this));
        inputLastname.setText(PreferenceDataUser.getLoggedInLastname(ProfileActivity.this));
        inputEmail.setText(PreferenceDataUser.getLoggedInEmail(ProfileActivity.this));

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id,user_firstname,user_middlename,user_lastname,user_email;
                user_id = PreferenceDataUser.getLoggedInUserid(ProfileActivity.this);
                user_firstname = inputFirstname.getText().toString();
                user_middlename = inputMiddlename.getText().toString();
                user_lastname = inputLastname.getText().toString();
                user_email =inputEmail.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Ambulert.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Ambulert service = retrofit.create(Ambulert.class);
                final Call<ProfileResponse> profileResponseCall = service.updateProfile(new Profile(user_id,user_firstname,user_middlename,user_lastname,user_email));
                profileResponseCall.enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                        ProfileResponse resp = response.body();
                        if(resp.getProfile().equals("updated")){
                            Toast.makeText(ProfileActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                            PreferenceDataUser.setLoggedInFirstname(ProfileActivity.this,user_firstname) ;
                            PreferenceDataUser.setLoggedInMiddlename(ProfileActivity.this,user_middlename);
                            PreferenceDataUser.setLoggedInLastname(ProfileActivity.this,user_lastname);
                            PreferenceDataUser.setLoggedInEmail(ProfileActivity.this,user_email);
                        } else {
                            Toast.makeText(ProfileActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileResponse> call, Throwable t) {
                        t.printStackTrace();
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
