package com.abdullahalshehabi.whatsfordinner;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private ImageButton bannerButton;
    private ImageButton mealsButton;
    private ImageButton recipesButton;
    private ImageButton groceriesButton;
    private ImageButton newDishButton;

    private Button dismissButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        setupNavigation();

        final Handler handler = new Handler();

        Log.d("Testing", "Main UI");

        bannerButton = (ImageButton) findViewById(R.id.main_banner);

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup, null);

        new LoadDrawable(popupView).execute();

        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        dismissButton = (Button) popupView.findViewById(R.id.popup_dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        bannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });

    }

    private void initializeComponents(){
        mealsButton = (ImageButton) findViewById(R.id.main_meals);
        recipesButton = (ImageButton) findViewById(R.id.main_recipes);
        groceriesButton = (ImageButton) findViewById(R.id.main_shopping);
        newDishButton = (ImageButton) findViewById(R.id.main_new_dish);
    }

    private void setupNavigation(){
        mealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        groceriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        newDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), NewDish.class);
                startActivity(intent);
            }
        });
    }

    private class LoadDrawable extends AsyncTask<Drawable, Void, Drawable> {

        View myView;

        LoadDrawable(View view){
            myView = view;
        }

        @Override
        protected Drawable doInBackground(Drawable... drawables) {
            final Drawable bgImage = ResourcesCompat.getDrawable(getResources(), R.drawable.popup_background, null);
            return bgImage;
        }

        @Override
        protected void onPostExecute(Drawable loaded){
            LinearLayout myLayout = (LinearLayout) myView.findViewById(R.id.popup_linear_layout);
            myLayout.setBackgroundDrawable(loaded);
        }
    }

}
