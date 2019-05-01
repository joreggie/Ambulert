package com.ambulert.ambugroup.ambulert.api;

import com.ambulert.ambugroup.ambulert.model.AlertHospital;
import com.ambulert.ambugroup.ambulert.model.FCMToken;
import com.ambulert.ambugroup.ambulert.model.HospitalNameResponse;
import com.ambulert.ambugroup.ambulert.model.LocationResponse;
import com.ambulert.ambugroup.ambulert.model.Profile;
import com.ambulert.ambugroup.ambulert.model.ProfileResponse;
import com.ambulert.ambugroup.ambulert.model.ResponderId;
import com.ambulert.ambugroup.ambulert.model.ResponderSignInResponse;
import com.ambulert.ambugroup.ambulert.model.SignInResponder;
import com.ambulert.ambugroup.ambulert.model.SignInResponse;
import com.ambulert.ambugroup.ambulert.model.SignInUser;
import com.ambulert.ambugroup.ambulert.model.SignUpResponse;
import com.ambulert.ambugroup.ambulert.model.SignUpUser;
import com.ambulert.ambugroup.ambulert.model.UserId;
import com.ambulert.ambugroup.ambulert.model.UserIdResponse;
import com.ambulert.ambugroup.ambulert.model.UserReportResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    @POST("signin/responder")
    Call<ResponderSignInResponse> signinResponder (@Body SignInResponder body);

    @Headers("Content-Type: application/json")
    @POST("alert")
    Call<UserReportResponse> alertHospital(@Body AlertHospital body);

    @Headers("Content-Type: application/json")
    @POST("user-fcm")
    Call<ResponseBody> userFCM(@Body FCMToken body);

    @Headers("Content-Type: application/json")
    @POST("user/reports")
    Call<UserIdResponse> userId(@Body UserId body);

    @Headers("Content-Type: application/json")
    @GET("hospitals")
    Call<HospitalNameResponse> getHospital();

    @Headers("Content-Type: application/json")
    @POST("responder/reports")
    Call<LocationResponse> getTwoLocation(@Body ResponderId body);

    @Headers("Content-Type: application/json")
    @POST("responder/completion")
    Call<LocationResponse> getResponderId(@Body ResponderId body);

    @Headers("Content-Type: application/json")
    @POST("profile")
    Call<ProfileResponse> updateProfile(@Body Profile body);

}
