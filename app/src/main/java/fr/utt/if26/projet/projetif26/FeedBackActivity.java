package fr.utt.if26.projet.projetif26;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by liamo on 20/11/2017.
 */

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {

    Button homePage;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedbackactivity);
        homePage = (Button) findViewById(R.id.homePage);
        homePage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent int1 = new Intent(this,WhatForActivity.class);
        startActivity(int1);
    }
}
