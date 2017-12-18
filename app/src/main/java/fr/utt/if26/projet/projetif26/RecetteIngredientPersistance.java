package fr.utt.if26.projet.projetif26;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.version;

    /**
     * Created by liamo on 07/11/2017.
     */

    public class RecetteIngredientPersistance extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "Cookin.db"; // nom du fichier pour la base
        private static final String TABLE_REC = "recettesIngredients"; // nom de la table
        private static final String ATTRIBUT_TITRE = "sigle"; // liste des attributs
        private static final String ATTRIBUT_NBING = "nbIngredient";
        private static final String ATTRIBUT_DESC = "description";
        private static final String ATTRIBUT_PHOTO = "photo";
        private static final String ATTRIBUT_ING1 = "ingredient1";
        private static final String ATTRIBUT_ING2 = "ingredient2";
        private static final String ATTRIBUT_ING3 = "ingredient3";
        private static final String ATTRIBUT_ING4 = "ingredient4";
        private static final String ATTRIBUT_ING5 = "ingredient5";
        private static final String ATTRIBUT_ING6 = "ingredient6";
        private static final String ATTRIBUT_ING7 = "ingredient7";



        public RecetteIngredientPersistance(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String TABLE_REC_CREATE =
                    "CREATE TABLE " + TABLE_REC + "(" +
                            ATTRIBUT_TITRE + " TEXT primary key," +
                            ATTRIBUT_NBING + " INTEGER, " +
                            ATTRIBUT_DESC + " TEXT, " +
                            ATTRIBUT_PHOTO + " TEXT, " +
                            ATTRIBUT_ING1 + " TEXT, " +
                            ATTRIBUT_ING2 + " TEXT, " +
                            ATTRIBUT_ING3 + " TEXT, " +
                            ATTRIBUT_ING4 + " TEXT, " +
                            ATTRIBUT_ING5 + " TEXT, " +
                            ATTRIBUT_ING6 + " TEXT, " +
                            ATTRIBUT_ING7 + " TEXT " +
                            ")";
            sqLiteDatabase.execSQL(TABLE_REC_CREATE);
            Log.i ("Persistance de la table", "Table créée");



        }

        public void init(){

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_REC);

            // Create tables again
            onCreate(db);
        }

        public void addRecette (Recette r){
            Log.d("Insert","Insertion de module");
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ATTRIBUT_TITRE,r.getTitre());
            values.put(ATTRIBUT_NBING,r.getIngredients());
            values.put(ATTRIBUT_DESC,r.getEtapes());
            values.put(ATTRIBUT_PHOTO,r.getPhoto());
            values.put(ATTRIBUT_ING1,r.getIngredient1());
            values.put(ATTRIBUT_ING2,r.getIngredient2());
            values.put(ATTRIBUT_ING3,r.getIngredient3());
            values.put(ATTRIBUT_ING4,r.getIngredient4());
            values.put(ATTRIBUT_ING5,r.getIngredient5());
            values.put(ATTRIBUT_ING6,r.getIngredient6());
            values.put(ATTRIBUT_ING7,r.getIngredient7());


            // Inserting Row
            db.insert(TABLE_REC, null, values);
            Log.d("Fin","Insertion terminée");
            //2nd argument is String containing nullColumnHack
            db.close(); // Closing database connection
        }

        /*Recette getRecette (int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_REC, new String[] { ATTRIBUT_TITRE,
                            ATTRIBUT_NBING, ATTRIBUT_DESC,ATTRIBUT_PHOTO, ATTRIBUT_ING1, ATTRIBUT_ING2, ATTRIBUT_ING3, ATTRIBUT_ING4, ATTRIBUT_ING5, ATTRIBUT_ING6, ATTRIBUT_ING7 }, ATTRIBUT_TITRE + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Recette r = new Recette(cursor.getString(0),Integer.parseInt(cursor.getString(1)), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
            return r;
        }*/

        Recette getRecette (String id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_REC, new String[] { ATTRIBUT_TITRE,
                            ATTRIBUT_NBING, ATTRIBUT_DESC,ATTRIBUT_PHOTO, ATTRIBUT_ING1, ATTRIBUT_ING2, ATTRIBUT_ING3, ATTRIBUT_ING4, ATTRIBUT_ING5, ATTRIBUT_ING6, ATTRIBUT_ING7 }, ATTRIBUT_TITRE + "=?",
                    new String[] { id }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Recette r = new Recette(cursor.getString(0),Integer.parseInt(cursor.getString(1)), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
            return r;
        }

        public List<Recette> getAllRecettes() {
            List<Recette> recetteList = new ArrayList<Recette>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_REC;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Recette r = new Recette("test",2,"PLEASE","test","courage"," "," "," "," "," "," ");
                    r.setTitre(cursor.getString(0));
                    r.setIngredients(Integer.parseInt(cursor.getString(1)));
                    r.setEtapes(cursor.getString(2));
                    r.setPhoto(cursor.getString(3));
                    r.setIngredient1(cursor.getString(4));
                    r.setIngredient2(cursor.getString(5));
                    r.setIngredient3(cursor.getString(6));
                    r.setIngredient4(cursor.getString(7));
                    r.setIngredient5(cursor.getString(8));
                    r.setIngredient6(cursor.getString(9));
                    r.setIngredient7(cursor.getString(10));
                    // Adding contact to list
                    recetteList.add(r);
                } while (cursor.moveToNext());
            }

            // return contact list
            return recetteList;
        }

        public List<List<String>> getAllIngredients() {
            List<List<String>> ingList = new ArrayList<List<String>>();
            // Select All Query
            String selectQuery = "SELECT DISTINCT ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7 FROM " + TABLE_REC;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    List<String> list = new ArrayList<String>();
                    if (cursor.getString(0).length() > 0) {
                        list.add(cursor.getString(0));}
                    if (cursor.getString(1).length() > 0) {
                        list.add(cursor.getString(1));}
                    if (cursor.getString(2).length() > 0) {
                        list.add(cursor.getString(2));}
                    if (cursor.getString(3).length() > 0) {
                        list.add(cursor.getString(3));}
                    if (cursor.getString(4).length() > 0) {
                        list.add(cursor.getString(4));}
                    if (cursor.getString(5).length() > 0) {
                        list.add(cursor.getString(5));}
                    if (cursor.getString(6).length() > 0) {
                        list.add(cursor.getString(6));}
                ingList.add(list);
                }
                while (cursor.moveToNext());
            }
            return ingList;
        }

        public void deleteRecette(Recette r) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_REC, ATTRIBUT_TITRE + " = ?",
                    new String[] { r.getTitre()});
            db.close();
        }

        // Getting contacts Count
        public int getRecetteCount() {
            String countQuery = "SELECT "+ATTRIBUT_TITRE+" FROM " + TABLE_REC;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            // return count
            return cursor.getCount();
        }
    }
