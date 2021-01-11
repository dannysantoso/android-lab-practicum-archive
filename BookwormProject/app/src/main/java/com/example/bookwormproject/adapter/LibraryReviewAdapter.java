package com.example.bookwormproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookwormproject.DatabaseHelper;
import com.example.bookwormproject.R;
import com.example.bookwormproject.model.LibraryReview;

import java.util.ArrayList;

public class LibraryReviewAdapter extends RecyclerView.Adapter<LibraryReviewAdapter.MyViewHolder> {

    ArrayList<LibraryReview> libraryreview;

    Context context;

    public LibraryReviewAdapter(ArrayList<LibraryReview> libraryreview, Context context) {
        this.libraryreview = libraryreview;
        this.context = context;
    }

    @NonNull
    @Override
    public LibraryReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_libraryreview_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryReviewAdapter.MyViewHolder holder, int position) {
        LibraryReview libreviews = libraryreview.get(position);
        holder.reviewId.setText(libreviews.ReviewId);
        holder.memberId.setText(libreviews.MemberId);
        holder.libraryId.setText(libreviews.LibraryId);
        holder.reviewTitle.setText(libreviews.ReviewTitle);
        holder.reviewDescription.setText(libreviews.ReviewDescription);
    }

    @Override
    public int getItemCount() {
        return libraryreview.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView libraryId, memberId, reviewId, reviewTitle, reviewDescription;
        public LinearLayout libreviewlist;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewId = itemView.findViewById(R.id.reviewId);
            memberId = itemView.findViewById(R.id.memberId);
            libraryId = itemView.findViewById(R.id.libraryId);
            reviewTitle = itemView.findViewById(R.id.reviewTitle);
            reviewDescription = itemView.findViewById(R.id.reviewDescription);
            libreviewlist = itemView.findViewById(R.id.libreviewList);
        }
    }
}
