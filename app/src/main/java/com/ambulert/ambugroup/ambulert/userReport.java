package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ambulert.ambugroup.ambulert.api.Ambulert;
import com.ambulert.ambugroup.ambulert.model.AlertHospital;
import com.ambulert.ambugroup.ambulert.model.PreferenceDataUser;
import com.ambulert.ambugroup.ambulert.model.UserReportResponse;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userReport extends AppCompatActivity {

    private StorageReference storageReference;
    StorageReference filepath;
    UploadTask uploadTask;

    Button submit;
    FloatingActionButton btnAccident,btnPregnancy,btnIllness;
    TextView textAccident,textPregnancy,textIllness,currentLocation1;
    TextInputEditText reportOthers;
    String userid,location,emergencyType,others,photoName;
    ImageView reportImage;
    Uri imagePath;


    private final String TAG = "userReport";
    CameraPhoto cameraPhoto;
    final int CAMERA_REQUEST = 1100;

    final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);

        //firebase storage reference
        storageReference = FirebaseStorage.getInstance().getReference();
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
        reportImage=findViewById(R.id.reportImage);

        userid = PreferenceDataUser.getLoggedInUserid(userReport.this);

        cameraPhoto = new CameraPhoto(userReport.this);


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

        //call it to open the camera
        try {
            startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cameraPhoto.addToGallery();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location=currentLocation1.getText().toString();
                others = reportOthers.getText().toString();

                filepath = storageReference.child("ProfilePicture/"+randomString(16)+"/"+photoName);
                uploadFile(imagePath);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Ambulert.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Ambulert request = retrofit.create(Ambulert.class);
                Call<UserReportResponse> response = request.alertHospital(new AlertHospital(userid,location,emergencyType,others));
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_REQUEST){
                String photoPath = cameraPhoto.getPhotoPath();
                imagePath = Uri.fromFile(new File(photoPath));
                String[] bits = photoPath.split("/");
                photoName = bits[bits.length-1];
                Log.d(TAG,"photopath: "+photoPath);
                Log.d(TAG,"photoname: "+photoName);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    reportImage.setImageBitmap(bitmap); //imageView is your ImageView
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }//end if resultCode
    }

    //     file upload
    private void uploadFile(Uri fileUrl){
        uploadTask = filepath.putFile(fileUrl);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return filepath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    final String picuri = downloadUri.toString();


                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong while uploading your profile.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    // for generating name for folder upon uploading to cloud storage
    public String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
