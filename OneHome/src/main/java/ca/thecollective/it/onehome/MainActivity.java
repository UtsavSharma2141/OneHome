/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
package ca.thecollective.it.onehome;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;

public class
MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int REQUEST_PERMISSION=1;
    private final String CHANNEL_ID = "Notification";
    private final int NOTIFICATION_ID =001;
    TextView msgTxt;
    Button rateusButton;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mrootReference = firebaseDatabase.getReference();
    private DatabaseReference mchildReference = mrootReference.child("message");


    NavigationView navV;

    ImageView imgHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rateusButton = findViewById(R.id.review_us);

        msgTxt = findViewById(R.id.welcome_screen_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navV = findViewById(R.id.nav_view);

        View imgView = navV.getHeaderView(0);
        imgHead = imgView.findViewById(R.id.headImg);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });

        rateusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReviewUs.class));

            }
        });

//        if(savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TemperatureFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_temperature);
//
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_aboutus:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_link));
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

                return true;

            case R.id.menu_contactus:
                permission_fn();
                return true;

            case R.id.menu_feedback:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:feedback@onehome.ca"));
                startActivity(Intent.createChooser(emailIntent, getString(R.string.feedback_menu)));

                return true;

            case R.id.menu_usefullinks:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.consumeraffairs.com/homeowners/how-to-secure-your-home.html")));
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case R.id.nav_temperature:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TemperatureFragment()).commit();
                getSupportActionBar().setTitle(R.string.temperature_humidity_menu);
                break;

            case R.id.nav_motion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MotionFragment()).commit();
                getSupportActionBar().setTitle(R.string.motion_menu);
                break;

            case R.id.nav_curtain:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CurtainFragment()).commit();
                getSupportActionBar().setTitle(R.string.curtain_menu);
                break;

            case R.id.nav_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingFragment()).commit();
                getSupportActionBar().setTitle(R.string.setting_menu);
                break;

            case R.id.nav_display:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DisplayFragment()).commit();
                getSupportActionBar().setTitle(R.string.display_menu);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(R.string.alertbar_confirm_exit);
            builder.setMessage(R.string.exit_dialouge);
            builder.setIcon(R.drawable.ic_action_exit);
            builder.setPositiveButton(R.string.exit_yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finishAffinity();
                    finish();
                }
            });
            builder.setNegativeButton(R.string.exit_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }


    private  void requestCallPermission()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, CALL_PHONE))
        {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permissions2)
                    .setMessage(R.string.permissions3)
                    .setPositiveButton(R.string.alertbar_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{CALL_PHONE},REQUEST_PERMISSION);
                        }
                    })
                    .setNegativeButton(R.string.alertbar_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{CALL_PHONE},REQUEST_PERMISSION);
        }
    }

    private void permission_fn()
    {
        if(ContextCompat.checkSelfPermission(MainActivity.this, CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
        {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:6476199611")));

            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, CALL_PHONE))
            {

            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{CALL_PHONE},REQUEST_PERMISSION);

            }
        }
        else {

            requestCallPermission();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), R.string.snackbar_granted_permissions, Snackbar.LENGTH_LONG);
                snackbar.show();
                addNotification();


            } else {

                Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), R.string.snackbar_denied_permissions, Snackbar.LENGTH_LONG);
                snackbar.show();


            }
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: utsav.sharma1211@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void addNotification(){
        CreateNotificationChannel();
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("You have allowed the Call Permission");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());




    }

    private void CreateNotificationChannel(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "You have allower the Call Permission";
            String description = "Permission granted for calling";
            int importance = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }


}