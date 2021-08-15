package com.example.libraryapplication;

import static com.example.libraryapplication.BookActivity.BOOK_ID_KEY;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();                  // ArrayList for all the books in the Recycler View. (This is the RecView's dataset)
    private Context mContext;                                           // The context from which the Recycler View was created
    private String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false); // Creates a new view of the CardView that will hold each book
        return new ViewHolder(view);                                    // Returns the ViewHolder instance of that CardView view
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");                     // Debug showing which method was called

        holder.txtBookName.setText(books.get(position).getName());      //
        Glide.with(mContext)                                            // Loads the image in the given book into the ImageView component inside the ViewHolder
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.txtAuthor.setText(books.get(position).getAuthor());          //Showing the author's name in the CardView
        holder.txtShortDesc.setText((books.get(position)).getShortDesc());  // Showing the Short Desc in the Card View

        TransitionManager.beginDelayedTransition(holder.parent);        // TransitionManager for showing the 'opening' animation

        if (books.get(position).isExpanded()) {                         // If the card is expanded
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {
                holder.btnRemove.setVisibility(View.GONE);
            } else if (parentActivity.equals("alreadyReadBooks")) {
                holder.btnRemove.setOnClickListener(v -> {
                    String bookName = books.get(position).getName();

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to remove the book - '" + bookName + "'?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        if (Utils.getInstance().removeFromAlreadyRead(books.get(position))) {
                            Toast.makeText(mContext, bookName + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mContext, "Error removing book, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", ((dialog, which) -> {
                    }));
                    builder.create().show();
                });
            } else if (parentActivity.equals("currentlyReadingBooks")) {
                holder.btnRemove.setOnClickListener(v -> {
                    String bookName = books.get(position).getName();

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to remove the book - '" + bookName + "'?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        if (Utils.getInstance().removeFromCurrentlyReading(books.get(position))) {
                            Toast.makeText(mContext, bookName + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mContext, "Error removing book, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", ((dialog, which) -> {
                    }));
                    builder.create().show();
                });
            } else if (parentActivity.equals("favouriteBooks")) {
                holder.btnRemove.setOnClickListener(v -> {
                    String bookName = books.get(position).getName();

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to remove the book - '" + bookName + "'?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        if (Utils.getInstance().removeFromFavourite(books.get(position))) {
                            Toast.makeText(mContext, bookName + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mContext, "Error removing book, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", ((dialog, which) -> {
                    }));
                    builder.create().show();
                });
            } else if (parentActivity.equals("wishlistBooks")) {
                holder.btnRemove.setOnClickListener(v -> {
                    String bookName = books.get(position).getName();

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Are you sure you want to remove the book - '" + bookName + "'?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        if (Utils.getInstance().removeFromWantToRead(books.get(position))) {
                            Toast.makeText(mContext, bookName + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mContext, "Error removing book, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", ((dialog, which) -> {
                    }));
                    builder.create().show();
                });
            }

        } else {                                                        // If the card is collapsed
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

        holder.parent.setOnClickListener(v -> {                         // Adds an OnClickListener to the entire CardView

            Intent intent = new Intent(mContext, BookActivity.class);   // Creates a new Intent which will cause the activity for a single book to be opened

            intent.putExtra(BOOK_ID_KEY, books.get(position).getId());  // Passes a key-value pair with the bookID into the the intent so the activity knows which book to show
            mContext.startActivity(intent);                             // Starts the activity which shows one book

        });
    }

    // Required abstract method
    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;                                             // Sets the books into the RecView dataset
        notifyDataSetChanged();                                         // Notifies the recycler view that the dataset has changed
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;                                         // Initializes all the GUI component variable
        private ImageView imgBook;
        private TextView txtBookName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;
        private TextView txtAuthor, txtShortDesc;
        private TextView btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);                 // Initializes all the GUI components
            imgBook = itemView.findViewById(R.id.imgBook);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtShortDesc = itemView.findViewById(R.id.txtShortDesc);
            btnRemove = itemView.findViewById(R.id.btnRemove);

            View.OnClickListener listener = v -> {                       // Creates an OnClickListener for the upArrow and downArrow buttons in the CardViews for collapsing and expanding the cardviews
                Book book = books.get(getAdapterPosition());             // Gets the position of the book that the Adapter is currently 'looking' at
                book.flipExpanded();                                     // Flips the value of the isExpanded variable in each CardView
                notifyItemChanged(getAdapterPosition());                 // Notifies the Adapter that this book's CardView has changed
            };
            downArrow.setOnClickListener(listener);                      // Adds this listener to the buttons
            upArrow.setOnClickListener(listener);

        }
    }

}
