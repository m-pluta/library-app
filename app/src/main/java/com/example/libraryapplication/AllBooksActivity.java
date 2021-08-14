package com.example.libraryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        booksRecView = findViewById(R.id.booksRecView);

        adapter = new BookRecViewAdapter(this);
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84-202x300.jpg",
                "A work of maddening brilliance", "Long description"));
        books.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.", "Long Description"));
        adapter.setBooks(books);


    }
}