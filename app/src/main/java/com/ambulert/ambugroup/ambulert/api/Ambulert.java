package com.ambulert.ambugroup.ambulert.api;

import com.ambulert.ambugroup.ambulert.model.AlertHospital;
import com.ambulert.ambugroup.ambulert.model.SignInResponse;
import com.ambulert.ambugroup.ambulert.model.SignInUser;
import com.ambulert.ambugroup.ambulert.model.SignUpResponse;
import com.ambulert.ambugroup.ambulert.model.SignUpUser;
import com.ambulert.ambugroup.ambulert.model.UserReportResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Ambulert {

    String BASE_URL = "http://ambulert.appspot.com/";

    @Headers("Content-Type: application/json")
    @POST("signup/user")
    Call<SignUpResponse> signupUser(@Body SignUpUser body);

    @Headers("Content-Type: application/json")
    @POST("signin/user")
    Call<SignInResponse> signinUser(@Body SignInUser body);

    @Headers("Content-Type: application/json")
    @POST("alert")
    Call<UserReportResponse> alertHospital(@Body AlertHospital body);
}
