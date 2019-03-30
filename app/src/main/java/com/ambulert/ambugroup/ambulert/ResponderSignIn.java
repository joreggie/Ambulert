package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataResponder;
import com.ambulert.ambugroup.ambulert.model.ResponderSignInResponse;
import com.ambulert.ambugroup.ambulert.model.SignInResponder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResponderSignIn extends AppCompatActivity {

    TextInputEditText responderId;
    TextInputLayout layoutResponderId;
    Button responderSignIn;

    private final String TAG = "ResponderSignIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_sign_in);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Responder Sign in");

        responderSignIn =findViewById(R.id.responderSignIn);
        responderId = findViewById(R.id.responderId);
        layoutResponderId = findViewById(R.id.layoutResponderId);

        responderSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = responderId.getText().toString();

                if (validateForm(id)) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Ambulert.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Ambulert service = retrofit.create(Ambulert.class);
                    Call<ResponderSignInResponse> respodersignin = service.signinResponder(new SignInResponder(id));
                    respodersignin.enqueue(new Callback<ResponderSignInResponse>() {
                        @Override
                        public void onResponse(Call<ResponderSignInResponse> call, Response<ResponderSignInResponse> response) {
                            ResponderSignInResponse res = response.body();
                            Intent intent = new Intent(ResponderSignIn.this, ResponderHome.class);
                            if (res.getSignin().equals("success")) {
                                PreferenceDataResponder.setLoggedInResponderId(ResponderSignIn.this, res.getResponder_id());
                                PreferenceDataResponder.setLoggedInResponderFirstname(ResponderSignIn.this, res.getResponder_firstname());
                                PreferenceDataResponder.setLoggedInResponderMiddlename(ResponderSignIn.this, res.getResponder_middlename());
                                PreferenceDataResponder.setLoggedInResponderLastname(ResponderSignIn.this, res.getResponder_lastname());
                                PreferenceDataResponder.setResponderLoggedInStatus(ResponderSignIn.this, true);
                                Toast.makeText(ResponderSignIn.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                Toast.makeText(ResponderSignIn.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponderSignInResponse> call, Throwable t) {

                        }
                    });
                }


            }
        });
    }

    private boolean validateForm(String id) {
        boolean valid = true;
        if (TextUtils.isEmpty(id)) {
            layoutResponderId.setError("Required");
            layoutResponderId.requestFocus();
            valid = false;
        } else {
            layoutResponderId.setError(null);
        }
        return valid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
