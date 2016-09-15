package com.abdullahalshehabi.whatsfordinner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

/**
 * Created by Abdullah Al-Shehabi on 9/14/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    //Database version
    public static final int DATABASE_VERSION = 1;

    //Database instance
    public static DatabaseHandler dbInstance;

    //Database name
    public static final String DATABASE_NAME = "LocalStorage";

    //Logcat tag
    private static final String LOG = "LocalDataBaseManager";

    //Table names
    private static final String TABLE_RECIPES = "recipes";
    private static final String TABLE_INGREDIENTS = "ingredients";
    //Common column names
    private static final String KEY_ID = "id";

    //RECIPES Table - column names
    private static final String RECIPES_NAME = "name";
    private static final String RECIPES_DESCRIPTION = "description";
    private static final String RECIPES_URI = "uri";
    private static final String RECIPES_INGREDIENTS = "ingredients";


    //INGREDIENTS Table - column names
    private static final String INGREDIENTS_NAME = "name";

    //Table create statements
    private static final String CREATE_TABLE_RECIPES = "CREATE TABLE " + TABLE_RECIPES + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RECIPES_NAME + " TEXT, "
            + RECIPES_DESCRIPTION + " TEXT, "
            + RECIPES_URI + " TEXT, "
            + RECIPES_INGREDIENTS + " TEXT"
            + ")";

    private static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE " + TABLE_INGREDIENTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + INGREDIENTS_NAME + " TEXT"
            + ")";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context){
        if(dbInstance == null){
            dbInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase dB) {
        dB.execSQL(CREATE_TABLE_RECIPES);
        dB.execSQL(CREATE_TABLE_INGREDIENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dB, int oldVersion, int newVersion){
        dB.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        dB.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(dB);
    }

    public void wipeDataBase(){

        SQLiteDatabase dB = getWritableDatabase();

        onUpgrade(dB, 1, 1);
    }

    public void addRecipe(Recipe recipe){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        StringBuilder builder = new StringBuilder();
        String[] ingredients = recipe.getIngredients();
        for(int pos = 0; pos < ingredients.length; pos++){
            builder.append(ingredients[pos]);
            if(pos + 1 < ingredients.length)
                builder.append(", ");
        }

        contentValues.put(RECIPES_NAME, recipe.getRecipeName());
        contentValues.put(RECIPES_DESCRIPTION, recipe.getDescription());
        contentValues.put(RECIPES_URI, recipe.getUri());
        contentValues.put(RECIPES_INGREDIENTS, builder.toString());

        database.insert(TABLE_RECIPES, null, contentValues);
        database.close();
    }

    public LinkedList<Recipe> getRecipesList(){

        SQLiteDatabase database = getReadableDatabase();
        LinkedList<Recipe> recipeNames = new LinkedList<>();

        Cursor cursor = database.rawQuery("SELECT (*) FROM" + TABLE_RECIPES, null);

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            //User(String name, String phoneNumber, String imageURI, boolean locked, int id)
            while(cursor.moveToNext()) {
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String uri = cursor.getString(3);
                String[] myIngredients =  cursor.getString(4).split(", ");

                Recipe recipe = new Recipe(name, description, uri, myIngredients);
                recipeNames.add(recipe);
            }
        }
        cursor.close();
        database.close();

        return recipeNames;
    }


    public void addIngredient(String recipeName) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(INGREDIENTS_NAME, recipeName);

        database.insert(TABLE_INGREDIENTS, null, contentValues);
        database.close();
    }

    public LinkedList<String> getIngredientsList(){

        SQLiteDatabase database = getReadableDatabase();
        LinkedList<String> ingredientNames = new LinkedList<>();

        Cursor cursor = database.rawQuery("SELECT (*) FROM" + TABLE_INGREDIENTS, null);

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            //User(String name, String phoneNumber, String imageURI, boolean locked, int id)
            while(cursor.moveToNext()) {
                ingredientNames.add(cursor.getString(1));
            }
        }
        cursor.close();
        database.close();

        return ingredientNames;
    }
}

