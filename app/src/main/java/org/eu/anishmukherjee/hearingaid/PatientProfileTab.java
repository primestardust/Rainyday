package org.eu.anishmukherjee.hearingaid;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PatientProfileTab extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Patient patient = (Patient) getArguments().getParcelable("patient");
        PatientDBHandler patientDBHandler = new PatientDBHandler(getActivity().getApplicationContext(), null);
        Patient patientNew = patientDBHandler.findHandler(patient.id);
        patient.progress = patientNew.progress;
        View view = inflater.inflate(R.layout.patient_profile_tab, container, false);
        TextView pNameTextView = (TextView) view.findViewById(R.id.pNameTextView);
        pNameTextView.setText("Name: " + patient.name);
        TextView pIdTextView = (TextView) view.findViewById(R.id.pIdTextView);
        pIdTextView.setText("ID: " + patient.id);
        TextView pProgressTextView = (TextView) view.findViewById(R.id.pProgressTextView);
        pProgressTextView.setText("Current Day: " + patient.progress);
        return view;
    }
}
