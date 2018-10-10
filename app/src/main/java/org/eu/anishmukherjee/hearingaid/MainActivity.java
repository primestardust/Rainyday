package org.eu.anishmukherjee.hearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ListView listen_situations;
    String[] listSituations;
    boolean[] checkedSituations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientDBHandler patientDBHandler = new PatientDBHandler(getApplicationContext(), null);
                TextView userEditText = (TextView) findViewById(R.id.userEditText);
                String patientId = userEditText.getText().toString();
                Patient patient = patientDBHandler.findHandler(patientId);
                ArrayList<Patient> patients = patientDBHandler.findPatientWithAudiologist(patientId);
                if (patient != null) {
                    Intent patientIntent = new Intent(getApplicationContext(), PatientActivity.class);
                    patientIntent.putExtra("patient", patient);
                    startActivity(patientIntent);
                } else if (patients != null) {
                    Intent audiologistIntent = new Intent(getApplicationContext(), AudiologistActivity.class);
                    audiologistIntent.putParcelableArrayListExtra("patientList", patients);
                    startActivity(audiologistIntent);
                }
            }
        });

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

//        listen_situations = (ListView) findViewById(R.id.listen_situations);
//        listSituations = getResources().getStringArray(R.array.listening_situations);
//        checkedSituations = new boolean[listSituations.length];
//        for (int i = 0; i < listSituations.length; i++) {
//            checkedSituations[i] = false;
//        }
//
//        ItemAdapter itemAdapter = new ItemAdapter(this, listSituations, checkedSituations);
//        listen_situations.setAdapter(itemAdapter);

    }
}
