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
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayFragment extends Fragment {

    SwitchMaterial switch1;
    SwitchMaterial switch2;
    SwitchMaterial switch3;
    SwitchMaterial switch4;
    Button EnterButton;
    CheckBox checkbox;
    View.OnClickListener mOnClickListener;
    EditText Text;
    String TextValue;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display,container,false);

        checkbox = (CheckBox)view.findViewById(R.id.checkoption);
        switch1 = (SwitchMaterial) view.findViewById(R.id.option2);
        switch2 = (SwitchMaterial) view.findViewById(R.id.option3);
        switch3 = (SwitchMaterial) view.findViewById(R.id.option4);
        switch4 = (SwitchMaterial) view.findViewById(R.id.option5);
        EnterButton = (Button)view.findViewById(R.id.EnterButton);
        Text = (EditText)view.findViewById(R.id.edit);

        //set base state of switches and buttons
        switch1.setEnabled(false);
        switch2.setEnabled(false);
        switch3.setEnabled(false);
        switch4.setEnabled(false);
        EnterButton.setEnabled(false);


        //onclick for undo snackbar button
        mOnClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox.setChecked(false);
            }
        };

        //checkbox code
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                            "Temperature Display is On", Toast.LENGTH_SHORT).show();
                    firebaseDatabase.getReference().child("LCD").child("Status").setValue(1);
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Temperature Display is Off", Toast.LENGTH_SHORT).show();
                    firebaseDatabase.getReference().child("LCD").child("Status").setValue(0);
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
                            "Motion Display is On", Toast.LENGTH_SHORT).show();
                    firebaseDatabase.getReference().child("LCD").child("Motion").setValue(1);
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Motion Display is Off", Toast.LENGTH_SHORT).show();
                    firebaseDatabase.getReference().child("LCD").child("Motion").setValue(0);
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
                            "Curtain Status On", Toast.LENGTH_SHORT).show();
                    firebaseDatabase.getReference().child("LCD").child("Curtain").setValue(1);
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Curtain Status Off", Toast.LENGTH_SHORT).show();
                    firebaseDatabase.getReference().child("LCD").child("Curtain").setValue(0);
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
                            "Custom Text Display On", Toast.LENGTH_SHORT).show();
                    EnterButton.setEnabled(true);
                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(),
                            "Custom Text Display Off", Toast.LENGTH_SHORT).show();
                    EnterButton.setEnabled(false);
                }
            }
        });

        //enterbutton code
        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Message = Text.getText().toString();
                if(Text.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Field is Empty", Toast.LENGTH_LONG).show();
                }else {
                    //if textbox is not empty
                    Toast.makeText(getActivity(), "Text: " + Message + "\n is sent to display", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;

    }
}