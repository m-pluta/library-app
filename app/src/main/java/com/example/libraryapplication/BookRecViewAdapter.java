package com.example.libraryapplication;

import android.content.Context;
import android.media.Image;
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

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder>{

    private static final String TAG = "BookRecViewAdapter";
    
    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;

    public BookRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtBookName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);
        holder.parent.setOnClickListener(v -> {
            Toast.makeText(mContext, books.get(position).getName() + " selected", Toast.LENGTH_SHORT).show();
            
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText((books.get(position)).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       private CardView parent;
       private ImageView imgBook;
       private TextView txtBookName;
       private ImageView downArrow, upArrow;
       private RelativeLayout expandedRelLayout;
       private TextView txtAuthor, txtShortDesc;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           parent = itemView.findViewById(R.id.parent);
           imgBook = itemView.findViewById(R.id.imgBook);
           txtBookName = itemView.findViewById(R.id.txtBookName);

           downArrow = itemView.findViewById(R.id.btnDownArrow);
           upArrow = itemView.findViewById(R.id.btnUpArrow);
           expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
           txtAuthor = itemView.findViewById(R.id.txtAuthor);
           txtShortDesc = itemView.findViewById(R.id.txtShortDesc);

           View.OnClickListener listener = v -> {
               Book book = books.get(getAdapterPosition());
               book.setExpanded(!book.isExpanded());
               notifyItemChanged(getAdapterPosition());
           };
           downArrow.setOnClickListener(listener);
           upArrow.setOnClickListener(listener);

       }
   }

}
