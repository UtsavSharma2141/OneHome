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
import android.widget.TextView;

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

    private TextView temperature,humidity;
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
