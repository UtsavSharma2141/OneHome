package ca.thecollective.it.onehome;
/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewUs extends AppCompatActivity {


//    CardView cardView;
    RatingBar ratingBar;
    Button btnsubmit;
    EditText name,phone,email,comment;
    DatabaseReference reference;
    Member member;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_us);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReviewUs.this, "This will take to the Google play store to rate and write review of this app", Toast.LENGTH_SHORT);
            }
        });

       ActionBar actionBar = getSupportActionBar();
       actionBar.setHomeButtonEnabled(true);
       actionBar.setDisplayHomeAsUpEnabled(true);

        ratingBar = findViewById(R.id.rating_bar);
        btnsubmit = findViewById(R.id.submit_review_button);
        name = findViewById(R.id.Name);
        phone = findViewById(R.id.Phone);
        email = findViewById(R.id.EmailAddress);
        comment = findViewById(R.id.comment);
//        cardView= findViewById(R.id.cardView);
        reference = FirebaseDatabase.getInstance().getReference().child("Member");
        member = new Member();
         LoadingBar loadingBar = new LoadingBar(ReviewUs.this);

        //submit button
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name.getText().toString().trim();
                Long p = Long.parseLong(phone.getText().toString().trim());
                String e = email.getText().toString().trim();
                String c = comment.getText().toString().trim();
                String s = String.valueOf(ratingBar.getRating());



                //if one or more fields are empty
                if(name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || email.getText().toString().isEmpty() || comment.getText().toString().isEmpty()){
                     Toast.makeText(ReviewUs.this, getResources().getString(R.string.field_empty), Toast.LENGTH_SHORT).show();
                }

                else{
                    loadingBar.showDialog();
                     Handler handler = new Handler();
                     handler.postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             loadingBar.dismissbar();
                             Toast.makeText(ReviewUs.this, getResources().getString(R.string.field_success),Toast.LENGTH_SHORT).show();

                         }
                     },3000);
                    member.setName(name.getText().toString().trim());
                    member.setComment(c);
                    member.setEmail(e);
                    member.setNumber(p);
                    member.setRating(s);
                    reference.push().setValue(member);
                }
            }
        });
    }
}