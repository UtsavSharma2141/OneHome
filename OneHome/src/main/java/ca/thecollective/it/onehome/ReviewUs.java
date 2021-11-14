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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewUs extends AppCompatActivity {

    RatingBar ratingBar;
    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_us);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);

        ratingBar = findViewById(R.id.rating_bar);
        btnsubmit = findViewById(R.id.submit_review);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(ReviewUs.this, s+"star", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}