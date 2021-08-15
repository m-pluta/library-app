package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnAlreadyRead, btnWishlist, btnCurrentlyReading, btnFavourite, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        // Makes the AllBooks button clickable
        btnAllBooks.setOnClickListener(v -> {
            // Creates a new Intent which will cause another activity to be launched
            Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);  // The context from which to launch the activity
            // The destination context
            startActivity(intent); // Start the new activity
        });

        // Makes the CurrentlyReading button clickable
        btnCurrentlyReading.setOnClickListener(v -> {
            // Creates a new Intent which will cause another activity to be launched
            Intent intent = new Intent(MainActivity.this, CurrentlyReadingBookActivity.class);  // The context from which to launch the activity
            // The destination context
            startActivity(intent); // Start the new activity
        });

        // Makes the AlreadyRead button clickable
        btnAlreadyRead.setOnClickListener(v -> {
            // Creates a new Intent which will cause another activity to be launched
            Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);  // The context from which to launch the activity
            // The destination context
            startActivity(intent); // Start the new activity
        });

        // Makes the AlreadyRead button clickable
        btnWishlist.setOnClickListener(v -> {
            // Creates a new Intent which will cause another activity to be launched
            Intent intent = new Intent(MainActivity.this, WishlistBookActivity.class);  // The context from which to launch the activity
            // The destination context
            startActivity(intent); // Start the new activity
        });

        // Makes the AlreadyRead button clickable
        btnFavourite.setOnClickListener(v -> {
            // Creates a new Intent which will cause another activity to be launched
            Intent intent = new Intent(MainActivity.this, FavouriteBookActivity.class);  // The context from which to launch the activity
            // The destination context
            startActivity(intent); // Start the new activity
        });

        Utils.getInstance();    // Creates the initial instance of the Utils class

    }

    /**
     * Initializes all the GUI components in the Main Activity by using findViewById()
     */
    private void initViews() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnFavourite = findViewById(R.id.btnFavourites);
        btnAbout = findViewById(R.id.btnAbout);
    }
}