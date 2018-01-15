package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liamo on 23/10/2017.
 */

public class IngredientActivity extends Activity implements OnItemSelectedListener{

    Button submitShopButton;
    TextView textView1;
    int compteur = 0;
    int selection =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final IngredientPersistance dbIng = new IngredientPersistance(this);
        SQLiteDatabase.loadLibs(this);

        if(dbIng.getAllIngredient() == null || dbIng.getAllIngredient().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"Bienvenu, avant de pouvoir faire quoi que ce soit vous devez renseigner ce que vous posséder dans votre frigo", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredientactivity);


        Spinner spinner = (Spinner) findViewById(R.id.ingredientsDD);
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData();

        // Ajout d'un textView récapitulant tous les ingrédients déjà possédés
        TextView ingView = new TextView(this);

        //Recupère tout ce qui est en table
        final LinearLayout ingLayout = (LinearLayout)findViewById(R.id.ingLayout);
        ingView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        ingLayout.setOrientation(LinearLayout.VERTICAL);
        List<String> ingredients = dbIng.getAllIngredientTitle();
        for (int i=0;i<ingredients.size();i++){
            final TextView testView = new TextView(this);
            testView.setLayoutParams(new LayoutParams(175,
                LayoutParams.MATCH_PARENT));
            testView.setGravity(Gravity.CENTER);
            final LinearLayout testLinear = new LinearLayout(this);
            final Button delIng = new Button(this);
            delIng.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.delete));
            delIng.setLayoutParams(new LayoutParams(100,100));
            testView.setText(ingredients.get(i));
            testView.setId(i);
            testView.setTag("ingredientTextView_"+i);
            testLinear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            testLinear.setId(i);
            delIng.setId(i);
            ingLayout.addView(testLinear);
            testLinear.addView(testView);
            testLinear.addView(delIng);
            delIng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testLinear.removeView(testView);
                    testLinear.removeView(delIng);
                    ingLayout.removeView(testLinear);
                    dbIng.deleteIngredient(dbIng.getIngredient(testView.getText().toString()));
                }
            });
        }

        //En cas de click sur envoyer
        submitShopButton = (Button)findViewById(R.id.shopSubmit);
        final LinearLayout dynamicLayout = (LinearLayout)findViewById(R.id.dynamicLayout);
        submitShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),WhatForActivity.class);
                if (textView1 != null){
                   for (int i=0;i<dynamicLayout.getChildCount();i++) {
                       TextView tv;
                       tv = (TextView)dynamicLayout.getChildAt(i);
                       dbIng.addIngredient(new Ingredient(tv.getText().toString(), 0, 0));
                    }
                }
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (++selection > 1) {
            final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dynamicLayout);

            //Retrieve spinner item selected text
            Spinner spinner = (Spinner) findViewById(R.id.ingredientsDD);
            String text = spinner.getSelectedItem().toString();

            // Add textview 1
            textView1 = new TextView(this);
            final Button del = new Button(this);
            textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));

            textView1.setText(text);
            textView1.setId(compteur);
            textView1.setTextSize(18);
            textView1.setTypeface(null, Typeface.BOLD);
            textView1.setTag(compteur);
            Log.d("textView", textView1.getTag().toString());

            //Add delete button
            del.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            del.setText("Supprimer");
            compteur = compteur + 1;
            del.setId(compteur);
            del.setTag(compteur);


            linearLayout.addView(textView1);
            //linearLayout.addView(del);
            compteur = compteur + 1;
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("children", String.valueOf(linearLayout.getChildCount()));
                    String search = del.getTag().toString();
                    Log.d("Search", search);
                    // for(int i = 0; i<linearLayout.getChildCount(); i++){
                    if (linearLayout.getChildAt(Integer.parseInt(search) - 1) instanceof TextView) {
                        TextView tTemp;
                        tTemp = (TextView) linearLayout.getChildAt(Integer.parseInt(search) - 1);
                        Log.d("tTemp", tTemp.getText().toString());
                        Log.d("If", "dans le if");
                        linearLayout.removeView(linearLayout.getChildAt(Integer.parseInt(search) - 1));
                    }
                    //  }
                    linearLayout.removeView(del);
                    compteur = 0;

                }
            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void loadSpinnerData(){
        Spinner spinner;
        spinner = (Spinner)findViewById(R.id.ingredientsDD);
        LinkTableWithForeignKey db = new LinkTableWithForeignKey(getApplicationContext());
        List<String> ingredientPossible = db.getAllIngredientTitle();
        ArrayAdapter<String> dataSpinner = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ingredientPossible);
        spinner.setAdapter(dataSpinner);
    }
}

