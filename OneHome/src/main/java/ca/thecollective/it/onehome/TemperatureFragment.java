/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
package ca.thecollective.it.onehome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TemperatureFragment extends Fragment {

    TextView temperature,humidity,max_temp,max_humidity;
    SeekBar temp_seekbar, humidity_seekbar;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mrootReference = firebaseDatabase.getReference();
    private DatabaseReference mchildReference = mrootReference.child("message");

    private FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
    private DatabaseReference mrootReference2 = firebaseDatabase.getReference();
    private DatabaseReference mchildReference2 = mrootReference.child("message2");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature,container,false);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        temperature = view.findViewById(R.id.live_temp);
        humidity = view.findViewById(R.id.live_humidity);
        max_temp= (TextView) view.findViewById(R.id.max_temp);
        max_humidity= (TextView) view.findViewById(R.id.max_humidity);
        temp_seekbar = (SeekBar) view.findViewById(R.id.temp_seekbar);
        humidity_seekbar= (SeekBar) view.findViewById(R.id.humidity_seekbar);

        max_temp.setText(getString(R.string.set_max_temperature)+ temp_seekbar.getProgress() + "/"+temp_seekbar.getMax());
        temp_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                max_temp.setText(getString(R.string.set_max_temperature)+ progress + "/"+temp_seekbar.getMax());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                max_temp.setText(getString(R.string.set_max_temperature)+ progress_value + "/"+temp_seekbar.getMax());

            }
        });

        max_humidity.setText(getString(R.string.set_max_humidity)+ humidity_seekbar.getProgress() + "/"+humidity_seekbar.getMax());
        humidity_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress_value2;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value2 = progress;
                max_humidity.setText(getString(R.string.set_max_humidity)+ progress + "/"+humidity_seekbar.getMax());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                max_humidity.setText(getString(R.string.set_max_humidity)+ progress_value2 + "/"+humidity_seekbar.getMax());

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        mchildReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer message = snapshot.getValue(Integer.class);
                temperature.setText( message+ "°C");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mchildReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Integer message2 = snapshot.getValue(Integer.class);
                humidity.setText( message2 + "%");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
