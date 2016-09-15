package com.abdullahalshehabi.whatsfordinner;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();

        Log.d("Testing", "Main UI");

        ImageButton bannerButton = (ImageButton) findViewById(R.id.main_banner);

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup, null);

        new LoadDrawable(popupView).execute();

        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        final Button dismissButton = (Button) popupView.findViewById(R.id.popup_dismiss_button);
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
