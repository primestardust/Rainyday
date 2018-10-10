package org.eu.anishmukherjee.hearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PatientSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Patient patient;
    String lossType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.loss_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        Button createPatientButton = (Button) findViewById(R.id.createPatientButton);
        createPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
                String name = nameEditText.getText().toString();
                EditText idEditText = (EditText) findViewById(R.id.idEditText);
                String id = idEditText.getText().toString();
                EditText pswdEditText = (EditText) findViewById(R.id.pswdEditText);
                String password = pswdEditText.getText().toString();
                EditText confPswdEditText = (EditText) findViewById(R.id.confPswdEditText);
                String confPassword = confPswdEditText.getText().toString();
                if (!password.equals(confPassword)) {
                    // do something
                }
                EditText detailsEditText= (EditText) findViewById(R.id.detailsEditText);
                String hearingAidDetails = detailsEditText.getText().toString();
                EditText appDateEditText= (EditText) findViewById(R.id.apptDateEditText);
                String appointmentDate = appDateEditText.getText().toString();
                EditText audioNameEditText= (EditText) findViewById(R.id.audioNameEditText);
                String audiologistName = audioNameEditText.getText().toString();
                EditText snLeftEditText = (EditText) findViewById(R.id.snLeftEditText);
                String snLeftHearingAid = snLeftEditText.getText().toString();
                EditText snRightEditText = (EditText) findViewById(R.id.snRightEditText);
                String snRightHearingAid = snLeftEditText.getText().toString();
                patient = new Patient(name, id, hearingAidDetails, password, appointmentDate, audiologistName,
                        snLeftHearingAid, snRightHearingAid, lossType);

                Intent patientIntent = new Intent(getApplicationContext(), PatientActivity.class);
                patientIntent.putExtra("patient", patient);

                PatientDBHandler patientDBHandler = new PatientDBHandler(getApplicationContext(), null);
                patientDBHandler.addHandler(patient);

                startActivity(patientIntent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(position)
        this.lossType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
