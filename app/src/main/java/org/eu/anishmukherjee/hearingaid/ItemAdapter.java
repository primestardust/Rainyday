package org.eu.anishmukherjee.hearingaid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflator;

    String[] listSituations;
    boolean[] checkedSituations;

    public ItemAdapter(Context c, String[] situations, boolean[] checkedSituations) {
        listSituations = situations;
        this.checkedSituations = checkedSituations;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listSituations.length;
    }

    @Override
    public Object getItem(int position) {
        return listSituations[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean isChecked(int position) {
        return checkedSituations[position];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.listen_situations, null);
        TextView situationTextView = (TextView) v.findViewById(R.id.situationTextView);
        situationTextView.setText(listSituations[position]);
        CheckBox situationCheckBox = (CheckBox) v.findViewById(R.id.situationCheckBox);
        situationCheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean newState = !checkedSituations[position];
                checkedSituations[position] = newState;
            }
        });
        situationCheckBox.setChecked(checkedSituations[position]);
        return v;
    }
}
