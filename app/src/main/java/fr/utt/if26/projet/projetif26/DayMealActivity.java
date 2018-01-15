package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.jar.Manifest;

import javax.xml.datatype.Duration;

/**
 * Created by liamo on 14/11/2017.
 */

public class DayMealActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daymealactivity);
        SQLiteDatabase.loadLibs(this);

        LinkTableWithForeignKey ltwf = new LinkTableWithForeignKey(this);
        List<Ingredient2_0> lIng = new ArrayList<Ingredient2_0>();
        lIng = ltwf.getIngByRecettes();
        Log.d("JOINTURE",lIng.toString());
        List<Recette2> allRecetteTest = ltwf.getAllRecettes();
        List<List<String>> listTest= new ArrayList<>();
        for (int i =0; i<ltwf.getAllRecettes().size();i++){
            List<String> ingredientRecette = new ArrayList<String>();
            for (int j=0; j<lIng.size();j++){
                if (lIng.get(j).getId() == allRecetteTest.get(i).getId()){
                    ingredientRecette.add(lIng.get(j).getNomIngredient());
                }
            }
            listTest.add(i,ingredientRecette);
        }


        //Initialisation des variables
        TextView tvTitle;
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        ImageView mainPhoto;
        mainPhoto = (ImageView)findViewById(R.id.ivMainPhoto);
        LinearLayout layoutForIng;
        TextView etEtapes;
        layoutForIng = (LinearLayout)findViewById(R.id.layoutForIng);
        etEtapes = (TextView)findViewById(R.id.etEtapes);
        //RecetteIngredientPersistance db = new RecetteIngredientPersistance(this);
        //Recette recetteDuJour = null;
        Recette2 recetteDuJour2 = null;

        //Recherche des recettes réalisable
        //Si on ne provient pas de l'intent
         if (getIntent().getStringExtra("TITLE") != null){
            Log.d("DANSLEINTENT","dans le intent");
             List<Ingredient2_0> ingListRecipee = new ArrayList<Ingredient2_0>();
             Log.d("GETINTENT",getIntent().getStringExtra("ID"));
            recetteDuJour2 = new Recette2(Integer.parseInt(getIntent().getStringExtra("ID")),getIntent().getStringExtra("TITLE"),getIntent().getStringExtra("PHOTO"),getIntent().getStringExtra("ETAPES"));
         }
         else {
             List<Recette2> allRecette = ltwf.getAllRecettes();
             List<Recette2> recettesPossible = getRecetteConcordante2();
             int nbRecette = recettesPossible.size();
             if (recettesPossible.size() == 0){
                 Toast toast = Toast.makeText(getApplicationContext(),"Vous n'avez pas assez d'ingrédient pour réaliser une de nos recettes", Toast.LENGTH_LONG);
                 toast.setGravity(Gravity.CENTER, 0, 0);
                 toast.show();
                 Intent goToFrigo = new Intent(this,IngredientActivity.class);
                 startActivity(goToFrigo);
             }
             else {
                 Log.d("IDRECETTECONCORDANTE",String.valueOf(recettesPossible.get(0).getId()));
                 Log.d("RECETTECONCORDANTE",recettesPossible.toString());
                 Random r = new Random();
                 Log.d("NBRECETTE", String.valueOf(nbRecette));
                 int dayMeal = r.nextInt(nbRecette - 0);
                 Log.d("Random", String.valueOf(dayMeal));
                 recetteDuJour2 = recettesPossible.get(dayMeal);
                 Log.d("Recette", recetteDuJour2.toString());
             }
         }

        if(recetteDuJour2 != null) {
            etEtapes.setText("   " + recetteDuJour2.getEtapes());
            etEtapes.setTypeface(null, Typeface.BOLD);
            tvTitle.setText(recetteDuJour2.getTitre());
            tvTitle.setTextSize(18);
            tvTitle.setTypeface(null, Typeface.BOLD);

            File imgFile = new File(recetteDuJour2.getPhoto());

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                mainPhoto.setImageBitmap(myBitmap);

            }
            List<Ingredient2_0> ingredientRecetteChoisie = new ArrayList<>();
            ingredientRecetteChoisie = ltwf.getIngredientNameByRecette(recetteDuJour2.getId());
            Log.d("RECETTEDUJOUR", String.valueOf(recetteDuJour2.getId()));
            Log.d("INGREDIENTSRECETTEDU", ingredientRecetteChoisie.toString());
            for (int i = 0; i < ingredientRecetteChoisie.size(); i++) {
                TextView tvIng = new TextView(this);
                int j = i + 1;
                tvIng.setText("            " + j + " - " + ingredientRecetteChoisie.get(i).getNomIngredient());
                tvIng.setTextSize(14);
                tvIng.setTypeface(null, Typeface.BOLD);
                layoutForIng.addView(tvIng);
            }
        }
    }

    //GetRecetteConcordante
    public List<Recette2> getRecetteConcordante2(){
        LinkTableWithForeignKey ltwf = new LinkTableWithForeignKey(this);
        IngredientPersistance dbIng = new IngredientPersistance(this);
        List<String> nomIngredientPossede = dbIng.getAllIngredientTitle();
        List<Ingredient2_0> lIng = new ArrayList<Ingredient2_0>();
        lIng = ltwf.getIngByRecettes();
        Log.d("LING",lIng.toString());
        List<Recette2> allRecetteTest = ltwf.getAllRecettes();
        List<List<String>> listTest= new ArrayList<>();
        List<Integer> idRecettes = new ArrayList<>();
        for (int i =0; i<ltwf.getAllRecettes().size();i++){
            List<String> ingredientRecette = new ArrayList<String>();
            for (int j=0; j<lIng.size();j++){
                Log.d("LINGGETJ",lIng.get(j).toString());
                if (lIng.get(j).getId() == allRecetteTest.get(i).getId()){
                    idRecettes.add(lIng.get(j).getId());
                    ingredientRecette.add(lIng.get(j).getNomIngredient());
                }
            }
            listTest.add(i,ingredientRecette);
        }

        // permet de supprimer les doublons dans la liste
        Set<Integer> hs = new HashSet<>();
        hs.addAll(idRecettes);
        idRecettes.clear();
        idRecettes.addAll(hs);
        Log.d("idRecetteString", idRecettes.toString());

        List<Integer> choixPossible2 = new ArrayList<Integer>();
        for (int i=0; i<listTest.size();i++){
            Log.d("AVANTLEIF",listTest.get(i).toString());
        if (nomIngredientPossede.containsAll(listTest.get(i))){
            choixPossible2.add(idRecettes.get(i));
            Log.d("DANSLEIF",nomIngredientPossede.toString());
            Log.d("DANSLE",idRecettes.get(i).toString());
        }
    }
    Log.d("choixPOSSIBLE",choixPossible2.toString());
    List<Recette2> recetteEventuelle = new ArrayList<Recette2>();
        for (int j=0;j<choixPossible2.size();j++){
        recetteEventuelle.add(ltwf.getRecetteById(choixPossible2.get(j)));
    }
        Log.d("listRecette",recetteEventuelle.toString());
        return recetteEventuelle;
    }
}
