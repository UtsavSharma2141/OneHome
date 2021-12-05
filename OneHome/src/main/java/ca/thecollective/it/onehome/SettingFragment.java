/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
package ca.thecollective.it.onehome;

import static android.content.Context.AUDIO_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Calendar;

public class SettingFragment extends Fragment {

    ToggleButton toggle;
    SwitchMaterial darkswitch;
    Button reset;
    Button slientmode;
    Button ringermode;
    private AudioManager myAudioManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);

        toggle = (ToggleButton) view.findViewById(R.id.toggleButton);
        darkswitch = (SwitchMaterial) view.findViewById(R.id.DarkModeSwitch);
        reset = (Button) view.findViewById(R.id.ResetButton);
        slientmode = (Button) view.findViewById(R.id.silentmode);
        ringermode = (Button) view.findViewById(R.id.ringermode);

        //dark mode switch
        darkswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If the switch button is on
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(), getResources().getString(R.string.dark_enable), Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    // If the switch button is off
                    // Show the switch button checked status as toast message
                    Toast.makeText(getActivity(), getResources().getString(R.string.dark_disable), Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });

        //togglebutton
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                    Toast.makeText(getActivity(), getResources().getString(R.string.screeen_locked), Toast.LENGTH_SHORT).show();
                } else {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                    Toast.makeText(getActivity(), getResources().getString(R.string.screen_unlocked), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //slientmode button
        slientmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(getActivity(), "Phone is slient", Toast.LENGTH_SHORT).show();
            }
        });

        //ringermode button
        ringermode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(getActivity(), "Phone is on ringer", Toast.LENGTH_SHORT).show();
            }
        });


        //reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = getActivity().getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    getActivity().overridePendingTransition(0, 0);
                    getActivity().finish();
                    Toast.makeText(getActivity(), getResources().getString(R.string.app_reset), Toast.LENGTH_SHORT).show();
                    getActivity().overridePendingTransition(0, 0);
                    startActivity(intent);
            }
        });


        return view;
    }


}
