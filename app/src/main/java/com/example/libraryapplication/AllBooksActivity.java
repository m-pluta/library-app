package com.example.libraryapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllBooksActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

//        overridePendingTransition(R.anim.forward_slide_in, R.anim.forward_slide_out);

        RecyclerView booksRecView = findViewById(R.id.booksRecView); // Initializes the Recycler View

        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "allBooks"); // Creates a new adapter passing in the current context
        booksRecView.setAdapter(adapter);               // Once it is created, the Recycler View in the current Activity receives it as it's adapter

        booksRecView.setLayoutManager(new LinearLayoutManager(this)); // The LayoutManager for holding all the seperate CardViews
        // Can't use GridLayoutManager as the collapsing and extending of the cards doesn't work with it

        adapter.setBooks(Utils.getAllBooks()); // Gets the instance of the Utils class and gets the ArrayList with all the books
    }

    //Code for making a window transition animation apply to only this activity and not globally
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
//    }
}