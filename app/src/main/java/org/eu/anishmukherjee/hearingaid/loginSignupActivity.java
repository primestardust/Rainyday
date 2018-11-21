package org.eu.anishmukherjee.hearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class loginSignupActivity extends AppCompatActivity {

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

        Button signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(loginSignupActivity.this, signupButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.pop_signup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.patient) {
                            Intent patientSignupIntent = new Intent(getApplicationContext(), PatientSignupActivity.class);
                            startActivity(patientSignupIntent);
                        } else if (item.getItemId() == R.id.audiologist) {
                            Intent audiologistSignupIntent = new Intent(getApplicationContext(), AudiologistSignupActivity.class);
                            startActivity(audiologistSignupIntent);
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

    }
}
