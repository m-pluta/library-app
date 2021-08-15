package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookID";                      // The key for the bookID in the intent

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;                                      // Initializing GUI component variables
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavourite;
    private ImageView bookImage;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);                             // Sets the layout of the Activity

        initViews();

        Intent intent = getIntent();                                        // Gets the intent that was used to launch this Activity
        if (intent != null) {                                               // Null check

            int bookID = intent.getIntExtra(BOOK_ID_KEY, -1);     // Gets the bookID from the key-value pair stored in the Intent
            if (bookID != -1) {                                             // Default value check
                Book incomingBook = Utils.getInstance().getBookByID(bookID);// Gets the Book object with the wanted ID
                if (incomingBook != null) {
                    setData(incomingBook);
                }
            }
        }
    }

    /**
     * Given a Book object, this method takes all the attributes and sets the GUI element text values with them
     *
     * @param book Any Book object
     */
    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText((book.getAuthor()));
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    /**
     * Initializes all the GUI components using the findViewById method
     */
    private void initViews() {
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtLongDescription);

        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourites);

        bookImage = findViewById(R.id.bookImage);
    }
}