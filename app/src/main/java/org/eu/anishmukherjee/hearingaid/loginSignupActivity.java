package org.eu.anishmukherjee.hearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class loginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Button patientSignupButton = (Button) findViewById(R.id.patientSignupButton);
        patientSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientSignupIntent = new Intent(getApplicationContext(), PatientSignupActivity.class);
                startActivity(patientSignupIntent);
            }
        });
        Button audiologistSignupButton = (Button) findViewById(R.id.audiologistSignupButton);
        audiologistSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audiologistSignupIntent = new Intent(getApplicationContext(), AudiologistSignupActivity.class);
                startActivity(audiologistSignupIntent);
            }
        });
    }
}
