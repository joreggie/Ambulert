package com.ambulert.ambugroup.ambulert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SignInUp extends AppCompatActivity {
    Button btnSignIn , btnSignUp;
    Spinner spinnerChoices;
    String spinner;
    Intent intentSignIn,intentSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        spinnerChoices = findViewById(R.id.spinnerChoices);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner = spinnerChoices.getSelectedItem().toString();
                if(spinner.equals("User")){
                    intentSignIn = new Intent(SignInUp.this,SignInActivity.class);
                    startActivity(intentSignIn);
                }else if(spinner.equals("Responder")){
                    intentSignIn = new Intent(SignInUp.this,ResponderSignIn.class);
                    startActivity(intentSignIn);
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner = spinnerChoices.getSelectedItem().toString();
                if(spinner.equals("User")){
                    intentSignUp = new Intent(SignInUp.this,SignUpActivity.class);
                    startActivity(intentSignUp);
                }else if(spinner.equals("Responder")){
                    intentSignUp = new Intent(SignInUp.this,ResponderSignUp.class);
                    startActivity(intentSignUp);
                }

            }
        });



    }
}
