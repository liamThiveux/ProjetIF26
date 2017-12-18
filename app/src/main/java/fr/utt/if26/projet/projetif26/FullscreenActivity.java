package fr.utt.if26.projet.projetif26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class FullscreenActivity extends AppCompatActivity implements OnClickListener {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreenactivity);

        b1 = (Button) findViewById(R.id.startButton);
        b1.setOnClickListener(this);
    }

    public void onClick(View view) {
        IngredientPersistance db = new IngredientPersistance(getApplicationContext());
        if (db.getAllIngredient() == null || db.getAllIngredient().isEmpty()){
            Intent intent1 = new Intent(this,IngredientActivity.class);
            startActivity(intent1);
        }
        else {
            Intent intent1 = new Intent(this,WhatForActivity.class);
        startActivity(intent1);
        }
    }
}