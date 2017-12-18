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


public class RecettePersistance extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Recettes.db"; // nom du fichier pour la base

    //Table recette
    private static final String TABLE_RECETTE = "recette"; // nom de la table
    private static final String ATTRIBUT_ID = "id"; // liste des attributs
    private static final String ATTRIBUT_TITRE = "titre";
    private static final String ATTRIBUT_INGREDIENTS = "ingredients";
    private static final String ATTRIBUT_PHOTO = "photo";
    private static final String ATTRIBUT_ETAPES = "etapes";
    private static final String ATTRIBUT_INGREDIENT_1 = "ingredient1";
    private static final String ATTRIBUT_INGREDIENT_2 = "ingredient2";
    private static final String ATTRIBUT_INGREDIENT_3 = "ingredient3";
    private static final String ATTRIBUT_INGREDIENT_4 = "ingredient4";
    private static final String ATTRIBUT_INGREDIENT_5 = "ingredient5";
    private static final String ATTRIBUT_INGREDIENT_6 = "ingredient6";
    private static final String ATTRIBUT_INGREDIENT_7 = "ingredient7";

    //Table ingrédients existant
    /*private static final String ING_EXISTE_TABLE = "ingredient_possible_tab";
    private static final String ID_EXISTANT = "id";
    private static final String INGREDIENT_EX = "ingredient";*/



    public RecettePersistance(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("debut","Debut du OnCreate");
        String TABLE_RECETTE_CREATE =
                "CREATE TABLE " + TABLE_RECETTE + "(" +
                        ATTRIBUT_ID + " INTEGER primary key AUTOINCREMENT," +
                        ATTRIBUT_TITRE + " TEXT, " +
                        ATTRIBUT_INGREDIENTS + " INTEGER, " +
                        ATTRIBUT_PHOTO + " TEXT," +
                        ATTRIBUT_ETAPES + "TEXT," +
                        ATTRIBUT_INGREDIENT_1 + "TEXT," +
                        ATTRIBUT_INGREDIENT_2 + "TEXT," +
                        ATTRIBUT_INGREDIENT_3 + "TEXT," +
                        ATTRIBUT_INGREDIENT_4 + "TEXT," +
                        ATTRIBUT_INGREDIENT_5 + "TEXT," +
                        ATTRIBUT_INGREDIENT_6 + "TEXT," +
                        ATTRIBUT_INGREDIENT_7 + "TEXT" +
                        ");";
        /*String TABLE_INGREDIENTS_RECETTE =
                "CREATE TABLE " + INGREDIENTS_TABLE + "(" +
                        ING_ID + "INTEGER primary key AUTOINCREMENT," +
                        INGREDIENT_1 + "INTEGER," +
                        INGREDIENT_2 + "INTEGER," +
                        INGREDIENT_3 + "INTEGER," +
                        INGREDIENT_4 + "INTEGER," +
                        INGREDIENT_5 + "INTEGER," +
                        INGREDIENT_6 + "INTEGER," +
                        INGREDIENT_7 + "INTEGER," +
                        "FOREIGN KEY ("+INGREDIENT_1+") REFERENCES " + ING_EXISTE_TABLE+"("+ID_EXISTANT+")," +
                        "FOREIGN KEY ("+INGREDIENT_2+") REFERENCES " + ING_EXISTE_TABLE+"("+ID_EXISTANT+")," +
                        "FOREIGN KEY ("+INGREDIENT_3+") REFERENCES " + ING_EXISTE_TABLE+"("+ID_EXISTANT+")" +
                        "FOREIGN KEY ("+INGREDIENT_4+") REFERENCES " + ING_EXISTE_TABLE+"("+ID_EXISTANT+")," +
                        "FOREIGN KEY ("+INGREDIENT_5+") REFERENCES " + ING_EXISTE_TABLE+"("+ID_EXISTANT+")," +
                        "FOREIGN KEY ("+INGREDIENT_6+") REFERENCES " + ING_EXISTE_TABLE+"("+ID_EXISTANT+")," +
                        "FOREIGN KEY ("+INGREDIENT_7+") REFERENCES " + ING_EXISTE_TABLE+"("+ID_EXISTANT+")" +
                        ");";

        String TABLE_INGREDIENTS_CREATE = " CREATE TABLE " + ING_EXISTE_TABLE +"(" +
                ID_EXISTANT + "INTEGER primary key AUTOINCREMENT," +
                INGREDIENT_EX + "TEXT" +");";*/

        sqLiteDatabase.execSQL(TABLE_RECETTE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECETTE);

        // Create tables again
        onCreate(db);
    }

    public void addRecette (Recette r){
        Log.d("Insert","Insertion nouvelle recette");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_TITRE,r.getTitre()); // Attribut titre
        values.put(ATTRIBUT_INGREDIENTS,r.getIngredients()); // Attribut catégorie
        values.put(ATTRIBUT_PHOTO,r.getPhoto()); // Attribut credit
        values.put(ATTRIBUT_ETAPES,r.getEtapes());
        values.put(ATTRIBUT_INGREDIENT_1,r.getIngredient1());
        values.put(ATTRIBUT_INGREDIENT_2,r.getIngredient2());
        values.put(ATTRIBUT_INGREDIENT_3,r.getIngredient3());
        values.put(ATTRIBUT_INGREDIENT_4,r.getIngredient4());
        values.put(ATTRIBUT_INGREDIENT_5,r.getIngredient5());
        values.put(ATTRIBUT_INGREDIENT_6,r.getIngredient6());
        values.put(ATTRIBUT_INGREDIENT_7,r.getIngredient7());



        // Inserting Row
        db.insert(TABLE_RECETTE, null, values);
        Log.d("Fin","Insertion terminée");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    Recette getRecette (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECETTE, new String[] { ATTRIBUT_ID,
                        ATTRIBUT_TITRE, ATTRIBUT_INGREDIENTS,ATTRIBUT_PHOTO,ATTRIBUT_ETAPES,ATTRIBUT_INGREDIENT_1,ATTRIBUT_INGREDIENT_2,ATTRIBUT_INGREDIENT_3,ATTRIBUT_INGREDIENT_4,ATTRIBUT_INGREDIENT_5,ATTRIBUT_INGREDIENT_6,ATTRIBUT_INGREDIENT_7 }, ATTRIBUT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Recette r = new Recette(cursor.getString(0),Integer.parseInt(cursor.getString(1)),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
        // return recette
        return r;
    }

    public List<Recette> getAllRecettes() {
        List<Recette> recetteList = new ArrayList<Recette>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RECETTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recette recette = new Recette("Poulet au curry", 2, "@drawable/cours1", "Poulet + du riz et du curry, ça donne du poulet au curry","poulet","riz","curry","crème","","","");
                recette.setTitre(cursor.getString(1));
                recette.setPhoto(cursor.getString(3));
                recette.setIngredients(Integer.parseInt(cursor.getString(2)));
                recette.setEtapes(cursor.getString(4));
                recette.setIngredient1(cursor.getString(5));
                recette.setIngredient2(cursor.getString(6));
                recette.setIngredient3(cursor.getString(7));
                recette.setIngredient4(cursor.getString(8));
                recette.setIngredient5(cursor.getString(9));
                recette.setIngredient6(cursor.getString(10));
                recette.setIngredient7(cursor.getString(11));

                // Adding contact to list
                recetteList.add(recette);
            } while (cursor.moveToNext());
        }

        // return toutes les recettes en table
        return recetteList;
    }

    public void deleteRecette(Recette recette) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECETTE, ATTRIBUT_TITRE + " = ?",
                new String[] { recette.getTitre()});
        db.close();
    }

    // Getting recettes Count
    public int getRecetteCount() {
        String countQuery = "SELECT "+ATTRIBUT_TITRE+" FROM " + TABLE_RECETTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
