package com.abdullahalshehabi.whatsfordinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

public class NewDish extends AppCompatActivity {

    private EditText recipeName;
    private TextView warningMessage;
    private ImageView recipeImage;
    private Button addImage;
    private ListView ingredientsSpinnerListView;

    private DatabaseHandler myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);

        initializeComponents();
        populateListView();

    }

    private void initializeComponents() {

        myDatabase = DatabaseHandler.getInstance(this);

        recipeName = (EditText) findViewById(R.id.new_dish_recipe_name_edit_text);
        warningMessage = (TextView) findViewById(R.id.new_dish_warning_text_view);
        recipeImage = (ImageView) findViewById(R.id.new_dish_recipe_image);
        addImage = (Button) findViewById(R.id.new_dish_add_image_button);
        ingredientsSpinnerListView = (ListView) findViewById(R.id.new_dish_listView);
    }

    private void populateListView() {

        LinkedList<Spinner> mySpinners = new LinkedList<>();
        for(int i = 0; i < 10; i++) {
            Spinner ingredientSpinner = new Spinner(this);
            LinkedList<String> myIngredients = myDatabase.getIngredientsList();
            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, myIngredients);
            ingredientSpinner.setAdapter(spinnerArrayAdapter);
            mySpinners.add(ingredientSpinner);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<Spinner>(this, R.layout.activity_new_dish_list_view_spinner, mySpinners);
        ingredientsSpinnerListView.setAdapter(arrayAdapter);
    }
}
