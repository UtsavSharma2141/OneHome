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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class DisplayFragment extends Fragment {

    Switch switch1;
    Switch switch2;
    Switch switch3;
    Switch switch4;
    Button EnterButton;
    CheckBox checkBox1;
    View.OnClickListener mOnClickListener;
    EditText Text;
    String TextValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        checkBox1 = (CheckBox)getView().findViewById(R.id.option1);
        switch1 = (Switch)getView().findViewById(R.id.option2);
        switch2 = (Switch)getView().findViewById(R.id.option3);
        switch3 = (Switch)getView().findViewById(R.id.option4);
        switch4 = (Switch)getView().findViewById(R.id.option5);
        EnterButton = (Button)getView().findViewById(R.id.EnterButton);


        //onclick for undo snackbar button
        mOnClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox1.setChecked(false);
            }
        };

        //checkbox code
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //enable switches
                    switch1.setEnabled(true);
                    switch2.setEnabled(true);
                    switch3.setEnabled(true);
                    switch4.setEnabled(true);

                    //show snackbar
                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(android.R.id.content),"LCD Display ON",Snackbar.LENGTH_LONG)
                            .setAction("undo", mOnClickListener);
                            snackbar.show();
                }else{
                    //disable switches
                    switch1.setEnabled(false);
                    switch2.setEnabled(false);
                    switch3.setEnabled(false);
                    switch4.setEnabled(false);
                }
            }
        });



        //switches code
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

        //enterbutton code
        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextValue = Text.getText().toString();
                if(Text.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Text Box is Empty",Toast.LENGTH_LONG);
                }else{
                    Toast.makeText(getActivity(),"Text: "+ TextValue +"\n is sent to display",Toast.LENGTH_LONG);
                }
            }
        });

        return inflater.inflate(R.layout.fragment_display,container,false);

    }
}
