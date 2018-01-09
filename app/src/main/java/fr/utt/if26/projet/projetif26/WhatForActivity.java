package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by liamo on 14/11/2017.
 */

public class WhatForActivity extends Activity implements OnClickListener {

    Button bDayMeal;
    Button bAdd, bShop, bContact, bList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatforactivity);


       bDayMeal = (Button) findViewById(R.id.dayMealButton);
       bDayMeal.setOnClickListener(this);
       //bAdd = (Button) findViewById(R.id.addRecipeeButton);
       //bAdd.setOnClickListener(this);
       bList = (Button) findViewById(R.id.listButton);
       bList.setOnClickListener(this);
       bContact = (Button) findViewById(R.id.contactButton);
       bContact.setOnClickListener(this);
       bShop = (Button) findViewById(R.id.shopButton);
       bShop.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dayMealButton:
                goToDayMeal();
                break;
            case R.id.shopButton:
                goToAddIngredients();
                break;
            case R.id.listButton:
                goToListRecipee();
                break;
            case R.id.contactButton:
                goToContactPage();
                break;
        }
    }

    public void goToDayMeal(){
        Intent int1 = new Intent(this,DayMealActivity.class);
        startActivity(int1);
    }

    public void goToAddIngredients(){
        Intent int1 = new Intent(this,IngredientActivity.class);
        startActivity(int1);
    }

    public void goToListRecipee(){
        Intent int1 = new Intent(this,ListRecipeeActivity.class);
        startActivity(int1);
    }

    /*public void goToAddRecipee(){
        Intent int1 = new Intent(this,AddRecipeeActivity.class);
        startActivity(int1);
    }*/

    public void goToContactPage(){
        Intent int1 = new Intent(this,ContactActivity.class);
        startActivity(int1);
    }
}
