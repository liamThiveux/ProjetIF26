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

public class RecettePersistance2_0 extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Cook.db"; // nom du fichier pour la base
    private static final String TABLE_REC = "recettesfinal"; // nom de la table
    private static final String ATTRIBUT_ID = "id";
    private static final String ATTRIBUT_TITRE = "titre"; // liste des attributs
    private static final String ATTRIBUT_DESC = "description";
    private static final String ATTRIBUT_PHOTO = "photo";


    public RecettePersistance2_0(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_REC_CREATE =
                "CREATE TABLE " + TABLE_REC + "(" +
                        ATTRIBUT_ID + " INTEGER primary key autoincrement, " +
                        ATTRIBUT_TITRE + " TEXT not null unique," +
                        ATTRIBUT_DESC + " TEXT, " +
                        ATTRIBUT_PHOTO + " TEXT " +
                        ")";
        sqLiteDatabase.execSQL(TABLE_REC_CREATE);

        Log.i ("Persistance de la table", "Table Recette 2.0 créée");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REC);

        // Create tables again
        onCreate(db);
    }

    public void addRecette (Recette2 r){
        Log.d("Insert","Insertion de recette 2.0");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_TITRE,r.getTitre());
        values.put(ATTRIBUT_DESC,r.getEtapes());
        values.put(ATTRIBUT_PHOTO,r.getPhoto());


        // Inserting Row
        db.insert(TABLE_REC, null, values);
        Log.d("Fin","Insertion terminée");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public void init(){

        addRecette(new Recette2("Boeuf bourguignon","storage/emulated/0/Download/Screenshot_1511806168.png","Hacher les oignons. Peler l'ail.\n" +
                "Dans une cocotte minute, faire roussir la viande et les lardons dans l’huile ou le beurre.\n" +
                "Ajouter les oignons, les champignons égouttés et saupoudrer de fariner. Mélanger et laisser dorer un instant.\n" +
                "Mouiller avec le vin rouge qui doit recouvrir la viande.\n" +
                "Saler et poivrer.\n" +
                "Ajouter l’ail et le bouquet garni." +
                "Fermer la cocotte minute"+
                "Laisser cuire doucement 60 min à partir de la mise en rotation de la soupape."));
        addRecette(new Recette2("Poulet curry","storage/emulated/0/Download/Screenshot_1511806142.png","Mettre une grande poêle à chauffer." +
                "Couper les oignons en petits morceaux, et les faire cuire à feu assez fort." +
                "Remuer, en ajoutant du curry et du cumin." +
                "Couper les blancs de poulet en morceaux, les ajouter dans la poêle et remettre des épices; tourner." +
                "Baisser le feu, et ajouter 2 cuillères à soupe de crème." +
                "Après 5 min de cuisson, remettre 2 cuillères à soupe de crème et des épices (si nécessaire)" +
                "Si le plat est fait à l'avance, remettre un peu de crème au moment de réchauffer car la sauce s'évapore."));
    }

    Recette2 getRecette (String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REC, new String[] { ATTRIBUT_TITRE, ATTRIBUT_DESC,ATTRIBUT_PHOTO}, ATTRIBUT_TITRE + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Recette2 r = new Recette2(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        return r;
    }

    public List<Recette2> getAllRecettes() {
        List<Recette2> recetteList = new ArrayList<Recette2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REC;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recette2 r = new Recette2("test","url","PLEASE");
                r.setId(Integer.parseInt(cursor.getString(0)));
                r.setTitre(cursor.getString(1));
                r.setPhoto(cursor.getString(3));
                r.setEtapes(cursor.getString(2));
                recetteList.add(r);
            } while (cursor.moveToNext());
        }

        // return contact list
        return recetteList;
    }

    public Recette2 getRecetteById(int id){
        Log.d("Choix","Quel recette voulez-vous ?");
        String selectQuery = "SELECT * FROM " + TABLE_REC;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Recette2 r = null;
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(0)) == id){
                    r= new Recette2(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                }
            }
            while (cursor.moveToNext());

        }
        return r;
    }

   /* public List<List<String>> getAllIngredients() {
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
    }*/

    public void deleteRecette(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REC, ATTRIBUT_TITRE + " = ?",
                new String[] { id });
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
