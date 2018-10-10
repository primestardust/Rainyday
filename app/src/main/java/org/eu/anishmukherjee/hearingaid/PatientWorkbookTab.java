package org.eu.anishmukherjee.hearingaid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class PatientWorkbookTab extends Fragment implements AdapterView.OnItemSelectedListener {
    String wearHour;
    String wearMinute;
    String removeHour;
    String removeMinute;
    String isComfortable;
    String timeFrame;
    String satisfaction;
    boolean situations[];
    ListView listen_situations;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Getting patient data
        final Patient patient = (Patient) getArguments().getParcelable("patient");

        View view = inflater.inflate(R.layout.patient_workbook_tab, container, false);
        listen_situations = (ListView) view.findViewById(R.id.listen_situations);
        String[] listSituations = getResources().getStringArray(R.array.listening_situations);
        situations = new boolean[listSituations.length];
        for (int i = 0; i < listSituations.length; i++) {
            situations[i] = false;
        }

        Button submitButton = (Button) view.findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient patientUpdated = patient;
                patientUpdated.progress = patient.progress + 1;
//                TextView wearHrEditText = (EditText) v.findViewById(R.id.wearHrEditText);
//                wearHour = wearHrEditText.getText().toString();
//                TextView wearMinuteEditText = (EditText) v.findViewById(R.id.wearMinEditText);
//                wearMinute = wearMinuteEditText.getText().toString();
//                TextView removeHrEditText = (EditText) v.findViewById(R.id.removeHrEditText);
//                removeHour = removeHrEditText.getText().toString();
//                TextView removeMinuteEditText = (EditText) v.findViewById(R.id.removeMinEditText);
//                removeMinute = wearMinuteEditText.getText().toString();
//                for (int i = 0; i < situations.length; i++) {
//                    situations[i] = listen_situations.isItemChecked(i);
//                }
//                Workbook workbook = new Workbook(wearHour, wearMinute, removeHour, removeMinute,
//                        situations, isComfortable, timeFrame, satisfaction);
//                patientUpdated.workbooks[patientUpdated.progress] = workbook;
                PatientDBHandler patientDBHandler = new PatientDBHandler(getActivity().getApplicationContext(), null);
                patientDBHandler.updateHandler(patientUpdated);
            }
        });

        TextView dayTextView = (TextView) view.findViewById(R.id.dayTextView);
        dayTextView.setText("Day " + ((Patient) getArguments().getParcelable("patient")).progress);
        ItemAdapter itemAdapter = new ItemAdapter(getContext(), listSituations, situations);
        listen_situations.setAdapter(itemAdapter);

        String[] isComfortableOptions = {"Yes", "No"};
        Spinner isComfortableSpinner = (Spinner) view.findViewById(R.id.isComfortableSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> isComfortableAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, isComfortableOptions);
        // Specify the layout to use when the list of choices appears
        isComfortableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        isComfortableSpinner.setAdapter(isComfortableAdapter);
        isComfortableSpinner.setOnItemSelectedListener(this);

        String[] timeFrameOptions = {"Too long", "Too short", "Just right"};
        Spinner timeFrameSpinner = (Spinner) view.findViewById(R.id.timeFrameSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> timeFrameAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, timeFrameOptions);
        // Specify the layout to use when the list of choices appears
        timeFrameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        timeFrameSpinner.setAdapter(timeFrameAdapter);
        timeFrameSpinner.setOnItemSelectedListener(this);

        String[] satisfactionOptions = {"1 (Very low)", "2 (Low)", "3 (Medium)", "4 (High)", "5 (Very high)"};
        Spinner satisfactionSpinner = (Spinner) view.findViewById(R.id.satisfactionSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> satisfactionAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, satisfactionOptions);
        // Specify the layout to use when the list of choices appears
        satisfactionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        satisfactionSpinner.setAdapter(satisfactionAdapter);
        satisfactionSpinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(position)
        switch (parent.getId()) {
            case R.id.isComfortableSpinner:
                isComfortable = parent.getItemAtPosition(position).toString();
            case R.id.timeFrameSpinner:
                timeFrame = parent.getItemAtPosition(position).toString();
            case R.id.satisfactionSpinner:
                satisfaction = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //
    }
}
