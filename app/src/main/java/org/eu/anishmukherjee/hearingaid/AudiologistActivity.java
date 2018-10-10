package org.eu.anishmukherjee.hearingaid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AudiologistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiologist);

//        TextView nameTextView = (TextView) findViewById(R.id.aNameTextView);
//        nameTextView.setText(getIntent().getExtras().getString("name"));

        ArrayList<Patient> patients = getIntent().getParcelableArrayListExtra("patientList");
        ArrayList<String> patientNames = new ArrayList<String>();
        for (int i = 0; i < patients.size(); i++) {
            patientNames.add(patients.get(i).name);
        }
        ListView audiologistListView = (ListView) findViewById(R.id.audiologistListView);
        ArrayAdapter<String> patientArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, patientNames);
        audiologistListView.setAdapter(patientArrayAdapter);
    }
}
