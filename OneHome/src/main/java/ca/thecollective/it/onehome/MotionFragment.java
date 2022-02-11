/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
package ca.thecollective.it.onehome;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.SimpleDateFormat;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import ca.thecollective.it.onehome.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.BundleCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.internal.GetServiceRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.connection.RequestResultCallback;

import java.util.Calendar;


public class  MotionFragment extends Fragment {

    Button SetTimeButton;
    Button ResetTimeButton;
    Switch toggleSwitch;

   /* private TextView position_text;              //added all these private types
    private Sensor proximitySensor;
    private Boolean isProximityAvailable;
    private SensorManager sensorManager;
    private Vibrator vibrator;*/

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mrootReference = firebaseDatabase.getReference().child("Motion");
    private DatabaseReference mchildReference = mrootReference.child("control");


    @SuppressLint("ServiceCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_motion, container, false);

        toggleSwitch = (Switch) view.findViewById(R.id.ToggleSwitch);
        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    firebaseDatabase.getReference().child("Motion").child("control").setValue("ON");

                } else {
                    firebaseDatabase.getReference().child("Motion").child("control").setValue("OFF");


                }
            }
        });


            SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            final Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            SensorEventListener sensorEventListener = new SensorEventListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if(event.values[0]<proximitySensor.getMaximumRange()){
                        getActivity().getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
                    }
                    else
                    {
                        getActivity().getWindow().getDecorView().setBackgroundColor(R.color.amber);
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            sensorManager.registerListener(sensorEventListener,proximitySensor,2*1000*1000);

     /*    //this is where my code began
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.fragment_motion);
            getActivity().setContentView(R.layout.fragment_motion);

        position_text = position_text.findViewById(R.id.position_text);
           // sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager= (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService((Context.VIBRATOR_SERVICE));*/


            Button SetTimeButton = (Button) view .findViewById(R.id.SetTimeButton);
            SetTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Setting new time", Toast.LENGTH_SHORT).show();

                }
            });
            Button ResetTimeButton = (Button) view.findViewById(R.id.ResetTimeButton);
            ResetTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(),"Resetting the time", Toast.LENGTH_SHORT).show();
                }
            });

           /* if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!=null)
            {
                proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                isProximityAvailable = true;
            } else {
                position_text.setText("Proximity sensor is not available");
                isProximityAvailable = false;
            }*/

            return view;

    }




   /* @Override
    public void onSensorChanged(SensorEvent event) {
        position_text.setText(event.values[0] + "cm");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        } else
        {
            vibrator.vibrate(500);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if(isProximityAvailable)
        {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isProximityAvailable)
        {
            sensorManager.registerListener(this, proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        }


    }*/
}
