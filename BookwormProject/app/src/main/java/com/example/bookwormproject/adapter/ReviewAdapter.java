package com.example.bookwormproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookwormproject.DatabaseHelper;
import com.example.bookwormproject.R;
import com.example.bookwormproject.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    ArrayList<Review> review;

    Context context;

    DatabaseHelper db;

    public ReviewAdapter(ArrayList<Review> review, Context context) {
        this.review = review;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_review_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        db = new DatabaseHelper(context);
        Review reviews = review.get(position);
        holder.reviewId.setText(reviews.ReviewId);
        holder.memberId.setText(reviews.MemberId);
        holder.libraryId.setText(reviews.LibraryId);
        holder.reviewTitle.setText(reviews.ReviewTitle);
        holder.reviewDescription.setText(reviews.ReviewDescription);
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = review.get(position).ReviewId;
                Integer status = db.deleteReview(id);
                if (status > 0){
                    review.remove(review.get(position));
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return review.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView libraryId, memberId, reviewId, reviewTitle, reviewDescription;
        Button removeButton;
        public LinearLayout reviewCell;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewId = itemView.findViewById(R.id.reviewId);
            memberId = itemView.findViewById(R.id.memberId);
            libraryId = itemView.findViewById(R.id.libraryId);
            reviewTitle = itemView.findViewById(R.id.reviewTitle);
            reviewDescription = itemView.findViewById(R.id.reviewDescription);
            reviewCell = itemView.findViewById(R.id.reviewCell);
            removeButton = itemView.findViewById(R.id.removeButton);

        }
    }
}

