package fr.utt.if26.projet.projetif26;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liamo on 21/11/2017.
 */


public class IngredientPersistance extends SQLiteOpenHelper {

    //DataBase Info
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Recettes.db"; // nom du fichier pour la base

    //Table ingrédients possédés
    private static final String ING_POSSEDE_TABLE = "ingredient_possede_tab";
    private static final String INGREDIENT_POSS = "ingredient";
    private static final String INGREDIENT_QTITE_G = "quantite_gramme";
    private static final String INGREDIENT_QTITE_NBR = "quantite_nombre";



    public IngredientPersistance(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_INGREDIENT_CREATE =
                "CREATE TABLE " + ING_POSSEDE_TABLE + "(" +
                        INGREDIENT_POSS + " TEXT primary key," +
                        INGREDIENT_QTITE_G + " INTEGER, " +
                        INGREDIENT_QTITE_NBR + " INTEGER " +
                        ")";
        sqLiteDatabase.execSQL(TABLE_INGREDIENT_CREATE);
        Log.i ("Persistance onCreate", "Table Ingredient");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ING_POSSEDE_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addIngredient (Ingredient i){
        Log.d("Insert","Insertion d'un ingrédient");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INGREDIENT_POSS,i.getNomIngredient()); // Attribut Nom Ingredient
        values.put(INGREDIENT_QTITE_G,i.getQuantiteG()); // Attribut Quantite en gramme
        values.put(INGREDIENT_QTITE_NBR,i.getQuantiteNbr()); // Attribut Quantite en Nombre


        // Inserting Row
        db.insert(ING_POSSEDE_TABLE, null, values);
        Log.d("Fin","Insertion d'ingrédients terminée");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    Ingredient getIngredient(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ING_POSSEDE_TABLE, new String[] { INGREDIENT_POSS,
                        INGREDIENT_QTITE_G,INGREDIENT_QTITE_NBR}, INGREDIENT_POSS + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Ingredient i = new Ingredient(cursor.getString(0),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)));
        // return Ingredient
        return i;
    }

    public List<Ingredient> getAllIngredient() {
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ING_POSSEDE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ingredient i = new Ingredient("oignon",0,2);
                i.setNomIngredient(cursor.getString(0));
                i.setQuantiteG(Integer.parseInt(cursor.getString(1)));
                i.setQuantiteNbr(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                ingredientList.add(i);
            } while (cursor.moveToNext());
        }

        // return la liste des ingredients
        return ingredientList;
    }

    public List<String> getAllIngredientTitle(){
        List<String> ingredientList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  ingredient FROM " + ING_POSSEDE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String i;
                i =cursor.getString(0);

                // Adding contact to list
                ingredientList.add(i);
            } while (cursor.moveToNext());
        }

        // return la liste des ingredients
        return ingredientList;
    }


    public void deleteIngredient(Ingredient i) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ING_POSSEDE_TABLE, INGREDIENT_POSS + " = ?",
                new String[] { i.getNomIngredient()});
        db.close();
    }

    // Getting contacts Count
    public int getIngredientCount() {
        String countQuery = "SELECT "+INGREDIENT_POSS+" FROM " + ING_POSSEDE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
