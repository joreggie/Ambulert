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
import com.ambulert.ambugroup.ambulert.model.SignUpResponse;
import com.ambulert.ambugroup.ambulert.model.SignUpUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private final String TAG = "SignUpActivity";
    TextInputLayout layoutFirstname, layoutMiddlename , layoutLastname, layoutEmail, layoutPassword;
    TextInputEditText inputFirstname, inputMiddlename, inputLastname, inputEmail, inputPassword;
    Button userSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userSignUp = findViewById(R.id.signUp);

        layoutFirstname = findViewById(R.id.layoutSignUpFirstName);
        layoutMiddlename = findViewById(R.id.layoutSignUpMiddleName);
        layoutLastname = findViewById(R.id.layoutSignUpLastName);
        layoutEmail = findViewById(R.id.layoutSignUpEmail);
        layoutPassword = findViewById(R.id.layoutSignUpPassword);

        inputFirstname = findViewById(R.id.inputSignUpFirstName);
        inputMiddlename = findViewById(R.id.inputSignUpMiddleName);
        inputLastname = findViewById(R.id.inputSignUpLastName);
        inputEmail = findViewById(R.id.inputSignUpEmail);
        inputPassword = findViewById(R.id.inputSignUpPassword);

        userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname,lastname,middlename,email,password;

                firstname=inputFirstname.getText().toString();
                middlename=inputMiddlename.getText().toString();
                lastname=inputLastname.getText().toString();
                email=inputEmail.getText().toString();
                password=inputPassword.getText().toString();

                if (validateForm(firstname,middlename,lastname,email,password)){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Ambulert.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Ambulert request = retrofit.create(Ambulert.class);
                    Call<SignUpResponse> response = request.signupUser(new SignUpUser(firstname,middlename,lastname,email,password));
                    response.enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            SignUpResponse res = response.body();
                            if(res.getSignup().equals("success"))
                            {
                                Toast.makeText(SignUpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                                inputFirstname.setText("");
                                inputMiddlename.setText("");
                                inputLastname.setText("");
                                inputEmail.setText("");
                                inputPassword.setText("");
                                inputFirstname.requestFocus();

                                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private boolean validateForm(String firstname, String middlename, String lastname,String email, String password){
        boolean valid = true;
        if (TextUtils.isEmpty(password)) {
            layoutPassword.setError("Required");
            layoutPassword.requestFocus();
            valid = false;
        } else {
            layoutPassword.setError(null);
        }

        if (TextUtils.isEmpty(email)) {
            layoutEmail.setError("Required");
            layoutEmail.requestFocus();
            valid = false;
        } else {
            layoutEmail.setError(null);
        }

        if (TextUtils.isEmpty(lastname)) {
            layoutLastname.setError("Required");
            layoutLastname.requestFocus();
            valid = false;
        } else {
            layoutLastname.setError(null);
        }

        if (TextUtils.isEmpty(middlename)) {
            layoutMiddlename.setError("Required");
            layoutMiddlename.requestFocus();
            valid = false;
        } else {
            layoutMiddlename.setError(null);
        }

        if (TextUtils.isEmpty(firstname)) {
            layoutFirstname.setError("Required");
            layoutFirstname.requestFocus();
            valid = false;
        } else {
            layoutFirstname.setError(null);
        }
        return valid;

    }
}
