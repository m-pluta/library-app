package com.example.libraryapplication;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favouriteBooks;

    private Utils() {
        // Initializes all the ArrayLists
        if (allBooks == null) {
            allBooks = new ArrayList<>();
            initData();
        }
        if (alreadyReadBooks == null) {
            alreadyReadBooks = new ArrayList<>();
        }
        if (wantToReadBooks == null) {
            wantToReadBooks = new ArrayList<>();
        }
        if (currentlyReadingBooks == null) {
            currentlyReadingBooks = new ArrayList<>();
        }
        if (favouriteBooks == null) {
            favouriteBooks = new ArrayList<>();
        }
    }


    private void initData() {
        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84-202x300.jpg",
                "A work of maddening brilliance", "Long description"));
        allBooks.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.", "Long Description"));
    }

    /**
     * Returns the only instance of the Utils class
     *
     * @return Singleton instance of the Utils class
     */
    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    /**
     * @return ArrayList with all the books
     */
    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    /**
     * Returns the Book object of a book given its' id
     *
     * @param id The id of the book
     * @return Book object of the book
     */
    public Book getBookByID(int id) {
        for (Book b : allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book) {
        return wantToReadBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean addToFavourite(Book book) {
        return favouriteBooks.add(book);
    }

    public boolean removeFromAlreadyRead(Book book) {
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyReading(Book book) {
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFromWantToRead(Book book) {
        return wantToReadBooks.remove(book);
    }

    public boolean removeFromFavourite(Book book) {
        return favouriteBooks.remove(book);
    }
}
