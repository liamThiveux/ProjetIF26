package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

/**
 * Created by liamo on 14/11/2017.
 */

public class ContactActivity extends AppCompatActivity implements OnClickListener {

    Button b1;
    Button insta, fb, twitter;
    RatingBar ratingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactactivity);

        b1 = (Button) findViewById(R.id.submitButton);
        b1.setOnClickListener(this);
        insta = (Button) findViewById(R.id.insta);
        insta.setOnClickListener(this);
        fb = (Button) findViewById(R.id.fb);
        fb.setOnClickListener(this);
        twitter = (Button) findViewById(R.id.twitter);
        twitter.setOnClickListener(this);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //ratingBar.setOnClickListener(this);
}



    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitButton:
                Intent intent1 = new Intent(this,FeedBackActivity.class);
                startActivity(intent1);
                break;
            case R.id.insta:
                Intent browserIntentInsta = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/liam.thiveux/"));
                startActivity(browserIntentInsta);
                break;
            case R.id.fb:
                Intent browserIntentFb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/liam.thiveux"));
                startActivity(browserIntentFb);
                break;
            case R.id.twitter:
                Intent browserIntentTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/thiveux"));
                startActivity(browserIntentTwitter);
                break;
            /*case R.id.ratingBar:
                if (ratingBar.getRating() == 5.0){
                    ratingBar.setRating(0);
                }
                else {
            ratingBar.setRating(ratingBar.getRating()+1);
                }
                break;*/
        }
    }
}
