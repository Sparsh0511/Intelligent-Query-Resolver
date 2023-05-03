package com.example.smartqueryresolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class SelectionScreen extends AppCompatActivity {

    // Declaring CardView for question and image
    CardView questionCardView, pictureCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_screen);

        // Associating Card View objects with card view in xml
        questionCardView = findViewById(R.id.questionCardView);
        pictureCardView = findViewById(R.id.pictureCardView);


        // Deciding what to do when user clicks on Question Card View
        questionCardView.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionScreen.this, Chat_Screen.class);
            startActivity(intent);
        });

        // Deciding what to do when user clicks on Image Card View
        pictureCardView.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionScreen.this, ImageGenerate.class);
            startActivity(intent);
        });

    }
}