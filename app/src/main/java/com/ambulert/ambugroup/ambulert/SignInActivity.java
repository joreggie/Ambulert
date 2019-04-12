package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.FCMToken;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataUser;
import com.ambulert.ambugroup.ambulert.model.SignInResponse;
import com.ambulert.ambugroup.ambulert.model.SignInUser;
import com.ambulert.ambugroup.ambulert.navigation.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private final String TAG = "SignInActivity";

    TextInputLayout layoutSignInEmail,layoutSignInPassword;
    TextInputEditText inputSignInEmail,inputSignInPassword;

    Button signIn;
    Intent intentLogin;
    private final AlphaAnimation btnClick = new AlphaAnimation(1F,0.8F);

    String fcmToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign in");

        signIn = findViewById(R.id.signIn);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign in");

        layoutSignInEmail = findViewById(R.id.layoutSignInEmail);
        layoutSignInPassword= findViewById(R.id.layoutSignInPassword);

        inputSignInEmail = findViewById(R.id.inputSignInEmail);
        inputSignInPassword = findViewById(R.id.inputSignInPassword);

        intentLogin = new Intent(SignInActivity.this, Home.class);
        if(PreferenceDataUser.getUserLoggedInStatus(SignInActivity.this)){
            startActivity(intentLogin);
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(btnClick);
                String emailSignIn,passwordSignIn;

                emailSignIn = inputSignInEmail.getText().toString();
                passwordSignIn = inputSignInPassword.getText().toString();

                if (validateForm(emailSignIn,passwordSignIn)){

                    FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "getInstanceId failed", task.getException());
                                    return;
                                }

                                // Get new Instance ID token
                                fcmToken = task.getResult().getToken();
                                PreferenceDataUser.setLoggedInFCM(SignInActivity.this,fcmToken);
                        }
                    });

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Ambulert.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Ambulert request = retrofit.create(Ambulert.class);
                    Call<SignInResponse> response = request.signinUser(new SignInUser(emailSignIn,passwordSignIn));
                    response.enqueue(new Callback<SignInResponse>() {
                        @Override
                        public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                            SignInResponse res = response.body();
                            if(res.getSignin().equals("success"))
                            {
                                PreferenceDataUser.setLoggedInUserid(SignInActivity.this, res.getUserid());
                                PreferenceDataUser.setLoggedInFirstname(SignInActivity.this, res.getUser_firstname());
                                PreferenceDataUser.setLoggedInMiddlename(SignInActivity.this, res.getUser_middlename());
                                PreferenceDataUser.setLoggedInLastname(SignInActivity.this, res.getUser_lastname());
                                PreferenceDataUser.setLoggedInEmail(SignInActivity.this, res.getUser_email());
                                PreferenceDataUser.setUserLoggedInStatus(SignInActivity.this,true);
                                startActivity(intentLogin);
                                saveFCMToken(res.getUserid());
                                Toast.makeText(SignInActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(SignInActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignInResponse> call, Throwable t) {
                            Toast.makeText(SignInActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            public void saveFCMToken(String userid){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Ambulert.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Ambulert request = retrofit.create(Ambulert.class);
                Call<ResponseBody> responseBodyCall = request.userFCM(new FCMToken(userid,fcmToken));
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.v(TAG,"FCM token saved");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }

            private boolean validateForm(String emailSignIn, String passwordSignIn) {
                boolean valid = true;
                if (TextUtils.isEmpty(passwordSignIn)) {
                    layoutSignInPassword.setError("Required");
                    layoutSignInPassword.requestFocus();
                    valid = false;
                } else {
                    layoutSignInPassword.setError(null);
                }

                if (TextUtils.isEmpty(emailSignIn)) {
                    layoutSignInEmail.setError("Required");
                    layoutSignInEmail.requestFocus();
                    valid = false;
                } else {
                    layoutSignInEmail.setError(null);
                }
                return valid;

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
