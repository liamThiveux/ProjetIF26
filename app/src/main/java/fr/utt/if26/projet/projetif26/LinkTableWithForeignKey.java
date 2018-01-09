package fr.utt.if26.projet.projetif26;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import net.sqlcipher.Cursor;
import net.sqlcipher.DatabaseUtils;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liamo on 21/11/2017.
 */


public class LinkTableWithForeignKey extends SQLiteOpenHelper{


    //DataBase Info
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "cookingBase.db"; // nom du fichier pour la base

    //Table d'association
    private static final String LINK_TABLE = "linkTable";
    private static final String ID_RECETTE = "idRecette";
    private static final String ID_INGREDIENT = "idIng";

    private static final String TABLE_REC = "recettesfinal"; // nom de la table
    private static final String ATTRIBUT_ID = "id";
    private static final String ATTRIBUT_TITRE = "titre"; // liste des attributs
    private static final String ATTRIBUT_DESC = "description";
    private static final String ATTRIBUT_PHOTO = "photo";

    private static final String ING_TABLE = "ingredientRecetteTable";
    private static final String INGREDIENT_ID = "id";
    private static final String INGREDIENT = "ingredient";

    public LinkTableWithForeignKey(Context context){
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

        String TABLE_INGREDIENT_CREATE =
                "CREATE TABLE " + ING_TABLE + "(" +
                        INGREDIENT_ID + " INTEGER primary key autoincrement," +
                        INGREDIENT + " TEXT not null unique " +
                        ")";
        sqLiteDatabase.execSQL(TABLE_INGREDIENT_CREATE);
        Log.i ("Persistance onCreate", "Table Ingredient");

        String TABLE_LINK_CREATE =
                "CREATE TABLE " + LINK_TABLE + "(" +
                        ID_RECETTE + " INTEGER," +
                        ID_INGREDIENT + " INTEGER, " +
                        "PRIMARY KEY ("+ ID_RECETTE+ "," +ID_INGREDIENT+")" +
                        "FOREIGN KEY(" + ID_RECETTE + ") REFERENCES "
                        + TABLE_REC + "("+ ATTRIBUT_ID + "),"+
                        "FOREIGN KEY(" + ID_INGREDIENT + ") REFERENCES "
                        + ING_TABLE + "("+ INGREDIENT_ID + ")"+
                        ")";
        sqLiteDatabase.execSQL(TABLE_LINK_CREATE);
        Log.i ("Persistance onCreate", "Table link created successfully");


    }

    public void globalInit(){
        initRecette();
        initIngredient();
        initLink();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public void initLink(){
        addLink(new Link(1,1)); //Oignons
        addLink(new Link(1,2)); //Boeuf
        addLink(new Link(1,3)); //Carotte
        addLink(new Link(1,4)); //Bouquet garni
        addLink(new Link(1,5)); //Vin Rouge
        addLink(new Link(1,6)); //Beurre
        addLink(new Link(1,7)); //Sel
        addLink(new Link(1,8)); //Poivre

        addLink(new Link(2,1)); //Oignons
        addLink(new Link(2,7)); //Sel
        addLink(new Link(2,8)); //Poivre
        addLink(new Link(2,9)); //Poulet
        addLink(new Link(2,10)); //Creme fraiche
        addLink(new Link(2,11)); //Curry
        addLink(new Link(2,12)); //Cumin
        addLink(new Link(2,13)); //Piment

        addLink(new Link(5,7));
        addLink(new Link(5,8));
        addLink(new Link(5,10));
        addLink(new Link(5,14));
        addLink(new Link(5,15));
        addLink(new Link(5,16));
        addLink(new Link(5,17));

        addLink(new Link(3,2));
        addLink(new Link(3,3));
        addLink(new Link(3,18));
        addLink(new Link(3,19));
        addLink(new Link(3,20));
        addLink(new Link(3,21));
        addLink(new Link(3,22));
        addLink(new Link(3,23));
        addLink(new Link(3,24));
        addLink(new Link(3,25));

        addLink(new Link(4,7));
        addLink(new Link(4,8));
        addLink(new Link(4,26));
        addLink(new Link(4,27));
        addLink(new Link(4,28));
        addLink(new Link(4,29));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + LINK_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void initIngredient() {
        addIngredient(new Ingredient2_0("boeuf"));
        addIngredient(new Ingredient2_0("oignon"));
        addIngredient(new Ingredient2_0("carotte"));
        addIngredient(new Ingredient2_0("bouquet garni"));
        addIngredient(new Ingredient2_0("vin rouge"));
        addIngredient(new Ingredient2_0("beurre"));
        addIngredient(new Ingredient2_0("sel"));
        addIngredient(new Ingredient2_0("poivre")); //8

        addIngredient(new Ingredient2_0("blancs de poulet"));
        addIngredient(new Ingredient2_0("crème fraiche"));
        addIngredient(new Ingredient2_0("curry"));
        addIngredient(new Ingredient2_0("cumin"));
        addIngredient(new Ingredient2_0("piment")); //13

        addIngredient(new Ingredient2_0("tagliatelle"));
        addIngredient(new Ingredient2_0("lardon"));
        addIngredient(new Ingredient2_0("oeuf"));
        addIngredient(new Ingredient2_0("gruyère")); //17

        addIngredient(new Ingredient2_0("boeuf haché"));
        addIngredient(new Ingredient2_0("céleri"));
        addIngredient(new Ingredient2_0("sauce tomate"));
        addIngredient(new Ingredient2_0("concentré de tomate"));
        addIngredient(new Ingredient2_0("gousse d'ail"));
        addIngredient(new Ingredient2_0("huile d'olive"));
        addIngredient(new Ingredient2_0("spaghetti"));
        addIngredient(new Ingredient2_0("laurier")); //25

        addIngredient(new Ingredient2_0("canette"));
        addIngredient(new Ingredient2_0("orange"));
        addIngredient(new Ingredient2_0("vin blanc"));
        addIngredient(new Ingredient2_0("bouillon cube")); //29

    }

    public void initRecette() {

        addRecette(new Recette2("Boeuf bourguignon", "storage/emulated/0/Download/Screenshot_1511806168.png", "Hacher les oignons. Peler l'ail.\n" +
                "Dans une cocotte minute, faire roussir la viande et les lardons dans l’huile ou le beurre.\n" +
                "Ajouter les oignons, les champignons égouttés et saupoudrer de fariner. Mélanger et laisser dorer un instant.\n" +
                "Mouiller avec le vin rouge qui doit recouvrir la viande.\n" +
                "Saler et poivrer.\n" +
                "Ajouter l’ail et le bouquet garni." +
                "Fermer la cocotte minute" +
                "Laisser cuire doucement 60 min à partir de la mise en rotation de la soupape."));
        addRecette(new Recette2("Poulet curry", "storage/emulated/0/Download/Screenshot_1511806142.png", "Mettre une grande poêle à chauffer." +
                "Couper les oignons en petits morceaux, et les faire cuire à feu assez fort." +
                "Remuer, en ajoutant du curry et du cumin." +
                "Couper les blancs de poulet en morceaux, les ajouter dans la poêle et remettre des épices; tourner." +
                "Baisser le feu, et ajouter 2 cuillères à soupe de crème." +
                "Après 5 min de cuisson, remettre 2 cuillères à soupe de crème et des épices (si nécessaire)" +
                "Si le plat est fait à l'avance, remettre un peu de crème au moment de réchauffer car la sauce s'évapore."));
        addRecette(new Recette2("Spaghetti bolognaise","storage/emulated/0/Download/i84653-spaghettis-bolognaise-rapides.jpg","Pelez et émincez l'ail et les oignons, puis faites-les revenir dans une cocotte avec un filet d'huile d'olive."
+        "Ajoutez la viande et laissez cuire quelques minutes." +
        "Ajoutez la sauce tomate, Salez, poivrez et laissez mijoter 5 min." +
        "Faites cuire les pâtes et mélangez avec la sauce"));
        addRecette(new Recette2("Canard à l'orange","storage/emulated/0/Download/recette-e17754-canard-a-l-orange-express.jpg","Préchauffer le four à 200 °C (th. 6/7). \n" +
                "Couper les cuisses de canard en 2 au niveau de l'articulation. Éplucher les oignons et les tailler en gros morceaux. Couper l'orange en tranches.\n" +
                "Dans une cocotte avec couvercle, colorer les morceaux de canard sans matière grasse, puis les réserver. \n" +
                "Enlever la graisse de la cocotte et colorer les morceaux d'oignons et les tranches d'orange. Ajouter le sucre, le vinaigre, et caraméliser. Déglacer ensuite avec le jus d'orange.\n" +
                "Remettre les morceaux de canard et ajouter l'ail non épluché, le thym et le laurier. Saler et poivrer, ajouter le jus de volaille et 1/4 de litre d'eau, puis couvrir et cuire pendant 45 min au four et à couvert.\n" +
                "Dresser les cuisses de canard en assiette plate, accompagner de pommes de terre sautées et de légumes verts!"));
        addRecette(new Recette2("Pâtes carbonara","storage/emulated/0/Download/comment-realiser-des-pates-carbonara-comme-en-italie.jpg","Cuire les pâtes.\n" +
                "Pendant ce temps, faire dorer les lardons dans une poêle à sec.\n" +
                "Lorsqu'ils sont dorés, ajouter la crème et laisser mijoter durant 10 minutes.\n" +
                "Egoutter les pâtes, les verser dans la sauce, ajouter l'oeuf battu, mélanger et servir saupoudrer de fromage."));
    }

    public void addLink (Link i){
        Log.d("Insert","Insertion d'un ingrédient");
        SQLiteDatabase db = this.getWritableDatabase("");
        ContentValues values = new ContentValues();
        Log.d("idInsertING",String.valueOf(i.getIdIngredient()));
        values.put(ID_RECETTE,i.getIdRecette());
        values.put(ID_INGREDIENT,i.getIdIngredient());

        // Inserting Row
        db.insert(LINK_TABLE, null, values);
        Log.d("Fin","Insertion d'ingrédients terminée");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public void addRecette (Recette2 r){
        Log.d("Insert","Insertion de recette 2.0");
        SQLiteDatabase db = this.getWritableDatabase("");
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

    public void addIngredient (Ingredient2_0 i){
        Log.d("Insert","Insertion d'un ingrédient2.0");
        SQLiteDatabase db = this.getWritableDatabase("");
        ContentValues values = new ContentValues();
        values.put(INGREDIENT,i.getNomIngredient()); // Attribut Nom Ingredient

        // Inserting Row
        db.insert(ING_TABLE, null, values);
        Log.d("Fin","Insertion d'ingrédients terminée");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    Link getLink(int idRecette) {
        SQLiteDatabase db = this.getReadableDatabase("");

        Cursor cursor = db.query(LINK_TABLE, new String[] { ID_RECETTE,
                        ID_INGREDIENT}, ID_RECETTE + "=?",
                new String[] { (String.valueOf(idRecette)) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Link i = new Link(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)));
        // return Ingredient
        return i;
    }

    public Recette2 getRecetteById(int id){
        Log.d("Choix","Quel recette voulez-vous ?");
        String selectQuery = "SELECT * FROM " + TABLE_REC;
        SQLiteDatabase db = this.getWritableDatabase("if2617");
        Cursor cursor = db.rawQuery(selectQuery, null);
        Recette2 r = null;
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(0)) == id){
                    r= new Recette2(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),cursor.getString(2));
                }
            }
            while (cursor.moveToNext());

        }
        return r;
    }

    public List<Ingredient2_0> getIngByRecettes(){

        String selectQuery = "SELECT recettesfinal.id, ingredientRecetteTable.ingredient FROM " + TABLE_REC +", "+ LINK_TABLE + ", " + ING_TABLE +
                " WHERE recettesfinal.id = linkTable.idRecette AND linkTable.idIng = ingredientRecetteTable.id;";
        SQLiteDatabase db = this.getWritableDatabase("");
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Ingredient2_0> listIng = new ArrayList<Ingredient2_0>();
        if (cursor.moveToFirst()){
            do {
                Ingredient2_0 i = new Ingredient2_0("fill");
                i.setId(Integer.parseInt(cursor.getString(0)));
                i.setNomIngredient(cursor.getString(1));
                Log.d("INGREDIENTSSS",i.toString());
                Log.d("CURSOR0",cursor.getString(0));
                Log.d("CURSOR1",cursor.getString(1));
                listIng.add(i);
            }
            while (cursor.moveToNext());
        }
        return listIng;
    }

    public void updateLink(int id) {
        SQLiteDatabase db = this.getWritableDatabase("");
        ContentValues data = new ContentValues();
        data.put("photo","storage/emulated/0/Download/i84653-spaghettis-bolognaise-rapides.jpg");
        db.update(TABLE_REC,data," id=" + id, null);
        Log.d("Update", "DONE");
    }

    public List<Recette2> getAllRecettes() {
        List<Recette2> recetteList = new ArrayList<Recette2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REC;

        SQLiteDatabase db = this.getWritableDatabase("");
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recette2 r = new Recette2(0,"test","url","PLEASE");
                r.setId(Integer.parseInt(cursor.getString(0)));
                r.setTitre(cursor.getString(1));
                r.setEtapes(cursor.getString(2));
                r.setPhoto(cursor.getString(3));
                recetteList.add(r);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return recetteList;
    }

    public List<Ingredient2_0> getAllIngredient() {
        List<Ingredient2_0> ingredientList = new ArrayList<Ingredient2_0>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ING_TABLE;

        SQLiteDatabase db = this.getWritableDatabase("");
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ingredient2_0 i = new Ingredient2_0("oignon");
                i.setId(Integer.parseInt(cursor.getString(0)));
                i.setNomIngredient(cursor.getString(1));
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
        String selectQuery = "SELECT  ingredient FROM " + ING_TABLE;

        SQLiteDatabase db = this.getWritableDatabase("");
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


    public List<Link> getAllLink() {
        List<Link> linkList = new ArrayList<Link>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + LINK_TABLE;

        SQLiteDatabase db = this.getReadableDatabase("if2617");
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Link i = new Link(1,1);

                i.setIdRecette(Integer.parseInt(cursor.getString(0)));
                i.setIdIngredient(Integer.parseInt(cursor.getString(1)));
                linkList.add(i);
            } while (cursor.moveToNext());
        }

        // return la liste des ingredients
        return linkList;
    }

    public List<Ingredient2_0> getIngredientNameByRecette(int idRecette){

        String selectQuery = "SELECT recettesfinal.id, ingredientRecetteTable.ingredient FROM " + TABLE_REC +", "+ LINK_TABLE + ", " + ING_TABLE +
                " WHERE recettesfinal.id = linkTable.idRecette AND linkTable.idIng = ingredientRecetteTable.id ;";
        SQLiteDatabase db = this.getWritableDatabase("");
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Ingredient2_0> listIng = new ArrayList<Ingredient2_0>();
        if (cursor.moveToFirst()){
            do {
                Log.d("CURSORNAME",cursor.getString(1) +" " + cursor.getString(0));
                if (Integer.parseInt(cursor.getString(0)) == idRecette) {
                    Ingredient2_0 i = new Ingredient2_0("fill");
                    i.setId(Integer.parseInt(cursor.getString(0)));
                    i.setNomIngredient(cursor.getString(1));
                    Log.d("INGREDIENTSNAME", i.toString());
                    listIng.add(i);
                }
            }
            while (cursor.moveToNext());
        }
        Log.d("LISTING",listIng.toString());
        return listIng;
    }

    public ArrayList<Integer> getIngredientIDByRecette(int idRecette){
        ArrayList<Integer> ingredientList = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + LINK_TABLE;

        SQLiteDatabase db = this.getWritableDatabase("");
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int i;
                if (Integer.parseInt(cursor.getString(0)) == idRecette)
                {
                    i = Integer.parseInt(cursor.getString(1));
                    // Adding ingredient to list
                    ingredientList.add(i);
                }
            } while (cursor.moveToNext());
        }

        // return la liste des ingredients
        return ingredientList;
    }

    /*public ArrayList<Ingredient2_0> getIngredientNameByRecette(int idRecette){
        ArrayList<Ingredient2_0> ingredientList = new ArrayList<Ingredient2_0>();
        // Select All Query
        Log.d("IDRECETTE",String.valueOf(idRecette));
        String selectQuery = "SELECT * FROM " + LINK_TABLE + ", " + ING_TABLE + ", " + TABLE_REC + " WHERE recettesfinal.id = linkTable.idRecette AND linkTable.idIng = ingredientRecetteTable.id;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d("CURSORMAMENE",cursor.getString(1) + " " + cursor.getString(2));
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.d("CURSORMAMENE",cursor.getString(3) + " " + cursor.getString(4));
                Ingredient2_0 i = new Ingredient2_0("fill");
                    i.setId(Integer.parseInt(cursor.getString(0)));
                    i.setNomIngredient(cursor.getString(1));
                    ingredientList.add(i);
            } while (cursor.moveToNext());
        }

        // return la liste des ingredients
        return ingredientList;
    }*/


    public void deleteLink(int i) {
        SQLiteDatabase db = this.getWritableDatabase("");
        db.delete(LINK_TABLE, ID_RECETTE + " = ?",
                new String[] { String.valueOf(i)});
        db.close();
    }

    // Getting contacts Count
    public int getIngredientCount() {
        String countQuery = "SELECT "+ID_INGREDIENT+" FROM " + LINK_TABLE;
        SQLiteDatabase db = this.getReadableDatabase("");
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
