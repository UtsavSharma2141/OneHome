package ca.thecollective.it.onehome;
/* Utsav Sharma N01392141 Section A*/
/* Dhruv Dave N01401657 Section A
/* Sukhvir Brar N01395820 Section A
* Pratheep Chandrakumar N01376948 Section A*/
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewUs extends AppCompatActivity {

    RatingBar ratingBar;
    Button btnsubmit;
    EditText name;
    EditText phone;
    EditText email;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_us);

       //ActionBar actionBar = getSupportActionBar();
       //actionBar.setHomeButtonEnabled(true);
       //actionBar.setDisplayHomeAsUpEnabled(true);

        ratingBar = findViewById(R.id.rating_bar);
        btnsubmit = findViewById(R.id.submit_review_button);
        name = findViewById(R.id.Name);
        phone = findViewById(R.id.Phone);
        email = findViewById(R.id.EmailAddress);
        comment = findViewById(R.id.comment);

        //submit button
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = phone.getText().toString();
                String e = email.getText().toString();
                String c = comment.getText().toString();
                String s = String.valueOf(ratingBar.getRating());

                //if one or more fields are empty
                if(name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || email.getText().toString().isEmpty() || comment.getText().toString().isEmpty()){
                    Toast.makeText(ReviewUs.this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(ReviewUs.this, "Thank you for the review", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }

    /**
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
    **/
}