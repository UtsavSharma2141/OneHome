/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
package ca.thecollective.it.onehome;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    Switch switch1;
    Switch switch2;
    Switch switch3;
    Switch switch4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch1 = (Switch) getView().findViewById(R.id.option2);
        switch2 = (Switch) getView().findViewById(R.id.option3);
        switch3 = (Switch) getView().findViewById(R.id.option4);
        switch4 = (Switch) getView().findViewById(R.id.option5);



        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If the switch button is on
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Temperature Display is On", Toast.LENGTH_LONG).show();
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Temperature Display is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If the switch button is on
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Motion Display is On", Toast.LENGTH_LONG).show();
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Motion Display is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If the switch button is on
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Curtain Status On", Toast.LENGTH_LONG).show();
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Curtain Status Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If the switch button is on
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Custom Text Display On", Toast.LENGTH_LONG).show();
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Custom Text Display Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        return inflater.inflate(R.layout.fragment_display,container,false);

    }
}
