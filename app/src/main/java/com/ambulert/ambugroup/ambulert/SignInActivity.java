package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ambulert.ambugroup.ambulert.navigation.Home;

public class SignInActivity extends AppCompatActivity {

    TextInputLayout layoutSignInEmail;
    TextInputEditText inputSignInEmail;

    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(SignInActivity.this, Home.class);
                startActivity(intentHome);
            }
        });

    }
}
