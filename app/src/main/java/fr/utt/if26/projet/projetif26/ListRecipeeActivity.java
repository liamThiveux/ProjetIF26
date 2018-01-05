package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liamo on 14/11/2017.
 */

public class ListRecipeeActivity extends AppCompatActivity{

    private static final int REQUEST_EXTERNAL_STORAGE =1;
    private static String[] PERMISSIONS_STORAGE = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listrecipeeactivity);
        SQLiteDatabase.loadLibs(this);
        verifyStoragePermission(this);

        final ListView maListe = (ListView) findViewById(R.id.list);
        //RecetteIngredientPersistance db = new RecetteIngredientPersistance(this);
        final LinkTableWithForeignKey db = new LinkTableWithForeignKey(this);

        ArrayList<Recette2> recettes = (ArrayList<Recette2>)db.getAllRecettes();

        Log.d("Recette", recettes.toString());
        RecetteAdapter adapteur = new RecetteAdapter (this, recettes );
        maListe.setAdapter(adapteur);
        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent1 = new Intent(getApplicationContext(),DayMealActivity.class);
                Recette2 item = (Recette2)maListe.getItemAtPosition(position);
                List<Ingredient2_0> ingList = new ArrayList<Ingredient2_0>();
                ingList = db.getIngredientNameByRecette(item.getId());
                Log.d("Item",item.toString());
                intent1.putExtra("ID",String.valueOf(item.getId()));
                intent1.putExtra("TITLE",item.getTitre());
                intent1.putExtra("PHOTO",item.getPhoto());
                intent1.putExtra("ETAPES",item.getEtapes());
                for (int i=0;i<ingList.size();i++){
                    intent1.putExtra("INGREDIENT"+ (i+1), ingList.get(i).getNomIngredient());
                }
                /*intent1.putExtra("INGREDIENT1",item.getIngredient1());
                intent1.putExtra("INGREDIENT2",item.getIngredient2());
                intent1.putExtra("INGREDIENT3",item.getIngredient3());
                intent1.putExtra("INGREDIENT4",item.getIngredient4());
                intent1.putExtra("INGREDIENT5",item.getIngredient5());
                intent1.putExtra("INGREDIENT6",item.getIngredient6());
                intent1.putExtra("INGREDIENT7",item.getIngredient7());*/
                startActivity(intent1);
            }
        });

    }

    public static void verifyStoragePermission(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("verify","Avant le if du verify");
        if (permission != PackageManager.PERMISSION_GRANTED){
            Log.d("verify","DANS LE IF du verify");
            ActivityCompat.requestPermissions(activity,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode){
            case REQUEST_EXTERNAL_STORAGE:
                Log.d("if statement","DANS LE switch");
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("permissionG","La permission est donnée");
                }
                break;
        }
    }

}
