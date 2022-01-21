/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
package ca.thecollective.it.onehome;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.github.muddz.styleabletoast.StyleableToast;

import static androidx.core.content.ContextCompat.getSystemService;

public class TemperatureFragment extends Fragment {

    TextView temperature, humidity, max_temp, max_humidity;
    SeekBar temp_seekbar, humidity_seekbar;
    private final String CHANNEL_ID = "Notification";
    private final String CHANNEL_ID2 = "Notification";
    Switch temp_switch, humidity_switch;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mrootReference = firebaseDatabase.getReference().child("DHT22");
    private DatabaseReference mchildReference = mrootReference.child("temperature");

    private FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
    private DatabaseReference mrootReference2 = firebaseDatabase.getReference().child("DHT22");
    private DatabaseReference mchildReference2 = mrootReference.child("humidity");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, container, false);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        temperature = view.findViewById(R.id.live_temp);
        humidity = view.findViewById(R.id.live_humidity);
        max_temp = (TextView) view.findViewById(R.id.max_temp);
        max_humidity = (TextView) view.findViewById(R.id.max_humidity);
        temp_seekbar = (SeekBar) view.findViewById(R.id.temp_seekbar);
        humidity_seekbar = (SeekBar) view.findViewById(R.id.humidity_seekbar);
        temp_switch = (Switch) view.findViewById(R.id.temp_switch);
        humidity_switch = (Switch) view.findViewById(R.id.humidity_switch);
        temp_switch.setEnabled(false);
        humidity_switch.setEnabled(false);

        max_temp.setText(getString(R.string.set_max_temperature) + temp_seekbar.getProgress() + "/" + temp_seekbar.getMax());
        temp_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress_value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                max_temp.setText(getString(R.string.set_max_temperature) + progress + "/" + temp_seekbar.getMax());
                temp_switch.setEnabled(false);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                max_temp.setText(getString(R.string.set_max_temperature) + progress_value + "/" + temp_seekbar.getMax());
                temp_switch.setEnabled(true);

            }
        });

        max_humidity.setText(getString(R.string.set_max_humidity) + humidity_seekbar.getProgress() + "/" + humidity_seekbar.getMax());
        humidity_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress_value2;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value2 = progress;
                max_humidity.setText(getString(R.string.set_max_humidity) + progress + "/" + humidity_seekbar.getMax());
                humidity_switch.setEnabled(false);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                max_humidity.setText(getString(R.string.set_max_humidity) + progress_value2 + "/" + humidity_seekbar.getMax());
                humidity_switch.setEnabled(true);

            }
        });

        temp_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    addNotificationTemperature();

                    Toast.makeText(getActivity(), getResources().getString(R.string.switch_max_temp), Toast.LENGTH_SHORT).show();


                } else {


                }
            }
        });

        humidity_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    addNotificationHumidity();

                     Toast.makeText(getActivity(), getResources().getString(R.string.switch_max_humidity), Toast.LENGTH_SHORT).show();



                } else {


                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        mchildReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double temp = snapshot.getValue(Double.class);
                temperature.setText(temp + "°C");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mchildReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double humi = snapshot.getValue(Double.class);
                humidity.setText(humi + "%");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addNotificationTemperature(){
        CreateNotificationChannelTemperature();
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getActivity(),CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.max_temp_notification));

        Intent notificationIntent = new Intent(getActivity(), TemperatureFragment.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
        manager.notify(0,builder.build());




    }

    private void addNotificationHumidity(){
        CreateNotificationChannelHumidity();
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getActivity(),CHANNEL_ID2)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.max_humidity_notification));

        Intent notificationIntent = new Intent(getActivity(), TemperatureFragment.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
        manager.notify(0,builder.build());




    }

    private void CreateNotificationChannelTemperature() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "You have successfully set the maximum temperature limit.";
            String description = "Maximum Temperature has been Set.";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }

    private void CreateNotificationChannelHumidity() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "You have successfully set the maximum humidity limit.";
            String description = "Maximum humidity has been Set.";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID2, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }
}
