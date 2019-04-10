package com.ambulert.ambugroup.ambulert;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;


import com.ambulert.ambugroup.ambulert.model.PreferenceDataUser;
import com.ambulert.ambugroup.ambulert.navigation.Home;

public class ProfileActivity extends AppCompatActivity {
    TextInputEditText inputFirstname,inputMiddlename,inputLastname,inputEmail;
    TextInputLayout layoutFirstname,layoutMiddlename,layoutLastname,layoutEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile Management");

        inputFirstname = findViewById(R.id.inputUserFname);
        inputMiddlename = findViewById(R.id.inputUserMname);
        inputLastname = findViewById(R.id.inputUserLname);
        inputEmail = findViewById(R.id.inputUserEmail);

        inputFirstname.setText(PreferenceDataUser.getLoggedInFirstname(ProfileActivity.this));
        inputMiddlename.setText(PreferenceDataUser.getLoggedInMiddlename(ProfileActivity.this));
        inputLastname.setText(PreferenceDataUser.getLoggedInLastname(ProfileActivity.this));
        inputEmail.setText(PreferenceDataUser.getLoggedInEmail(ProfileActivity.this));

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
