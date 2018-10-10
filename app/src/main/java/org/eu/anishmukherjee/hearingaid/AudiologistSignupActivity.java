package org.eu.anishmukherjee.hearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AudiologistSignupActivity extends AppCompatActivity {

    Audiologist audiologist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiologist_signup);

        Button createAudiologistButton = (Button) findViewById(R.id.createAudiologistButton);
        createAudiologistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText audiologistNameEditText = (EditText) findViewById(R.id.audiologistNameEditText);
                String name = audiologistNameEditText.getText().toString();
                EditText audiologistIdEditText = (EditText) findViewById(R.id.audiologistIdEditText);
                String id = audiologistIdEditText.getText().toString();
                EditText audiologistPswdEditText = (EditText) findViewById(R.id.audiologistPswdEditText);
                String password = audiologistPswdEditText.getText().toString();

                audiologist = new Audiologist(name, id, password);

                PatientDBHandler patientDBHandler = new PatientDBHandler(getApplicationContext(), null);
                ArrayList<Patient> patients = patientDBHandler.findPatientWithAudiologist(audiologist.name);

                Intent audiologistIntent = new Intent(getApplicationContext(), AudiologistActivity.class);
                audiologistIntent.putParcelableArrayListExtra("patientList", patients);
//                audiologistIntent.putExtra("name", audiologist.name);

                startActivity(audiologistIntent);
            }
        });
    }
}
