package com.abdullahalshehabi.whatsfordinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NewDish extends AppCompatActivity {

    private EditText recipeName;
    private TextView warningMessage;
    private ImageView recipeImage;
    private Button addImage;
    private ListView ingredientsDropDownLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);

        initializeComponents();
        populateListView();
    }

    private void initializeComponents() {

        recipeName = (EditText) findViewById(R.id.new_dish_recipe_name);
        warningMessage = (TextView) findViewById(R.id.new_dish_warning_text_view);
        recipeImage = (ImageView) findViewById(R.id.new_dish_recipe_image);
        addImage = (Button) findViewById(R.id.new_dish_add_image);
        ingredientsDropDownLists = (ListView) findViewById(R.id.new_dish_listView);
    }

    private void populateListView() {



    }

}
