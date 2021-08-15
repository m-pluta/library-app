package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

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
                Book incomingBook = Utils.getInstance(this).getBookByID(bookID);// Gets the Book object with the wanted ID
                if (incomingBook != null) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToRead(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavourite(incomingBook);
                }
            }
        }
    }

    /**
     * Enable and disable the button for the Favourite option
     * Add the book to the Favourite Book ArrayList
     *
     * @param book
     */
    private void handleFavourite(final Book book) {
        ArrayList<Book> favouriteBooks = Utils.getInstance(this).getFavouriteBooks();

        boolean doesExist = false;
        for (Book b : favouriteBooks) {
            if (b.getId() == book.getId()) {
                doesExist = true;
            }
        }
        if (doesExist) {
            btnAddToFavourite.setEnabled(false);
        } else {
            btnAddToFavourite.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToFavourite(book)) {
                    Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    //TODO: Ask the user if they want to navigate
                    Intent intent = new Intent(BookActivity.this, FavouriteBookActivity.class);   // The context from which to launch the activity and the destination context
                    startActivity(intent); // Start the new activity
                } else {
                    Toast.makeText(BookActivity.this, "Error occurred when adding the book, Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Enable and disable the button for the Currently Reading option
     * Add the book to the Currently Reading Book ArrayList
     *
     * @param book
     */
    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean doesExist = false;
        for (Book b : currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                doesExist = true;
            }
        }
        if (doesExist) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)) {
                    Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    //TODO: Ask the user if they want to navigate
                    Intent intent = new Intent(BookActivity.this, CurrentlyReadingBookActivity.class);   // The context from which to launch the activity and the destination context
                    startActivity(intent); // Start the new activity
                } else {
                    Toast.makeText(BookActivity.this, "Error occurred when adding the book, Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Enable and disable the button for the Want To Read option
     * Add the book to the Want To Read Book ArrayList
     *
     * @param book
     */
    private void handleWantToRead(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWishlistBooks();

        boolean doesExist = false;
        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                doesExist = true;
            }
        }
        if (doesExist) {
            btnAddToWantToRead.setEnabled(false);
        } else {
            btnAddToWantToRead.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToWishlist(book)) {
                    Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    //TODO: Ask the user if they want to navigate
                    Intent intent = new Intent(BookActivity.this, WishlistBookActivity.class);   // The context from which to launch the activity and the destination context
                    startActivity(intent); // Start the new activity
                } else {
                    Toast.makeText(BookActivity.this, "Error occurred when adding the book, Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    /**
     * Enable and disable the button for the Already Read option
     * Add the book to the Already Read Book ArrayList
     *
     * @param book
     */
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean doesExist = false;
        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                doesExist = true;
            }
        }
        if (doesExist) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                    Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    //TODO: Ask the user if they want to navigate
                    Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);   // The context from which to launch the activity and the destination context
                    startActivity(intent); // Start the new activity
                } else {
                    Toast.makeText(BookActivity.this, "Error occurred when adding the book, Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
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

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourites);

        bookImage = findViewById(R.id.bookImage);
    }
}