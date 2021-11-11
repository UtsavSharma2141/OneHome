package ca.thecollective.it.onehome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
}