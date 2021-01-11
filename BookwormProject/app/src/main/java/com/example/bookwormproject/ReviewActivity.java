package com.example.bookwormproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bookwormproject.adapter.ReviewAdapter;
import com.example.bookwormproject.model.Review;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView reviewList;
    RecyclerView.Adapter adapter;

    String ReviewId, MemberId, LibraryId, ReviewTitle, ReviewDescription;
    ArrayList<Review> review = new ArrayList<>();

    DatabaseHelper db;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;
            case R.id.review:
                Intent intent2 = new Intent(this, ReviewActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        reviewList = findViewById(R.id.reviewList);
        reviewList.setHasFixedSize(true);
        reviewList.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        Cursor c = db.listReview();
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    ReviewId = c.getString(0);
                    MemberId = c.getString(1);
                    LibraryId = c.getString(2);
                    ReviewTitle = c.getString(3);
                    ReviewDescription = c.getString(4);

                    Review reviews = new Review(ReviewId, MemberId, LibraryId, ReviewTitle, ReviewDescription);
                    review.add(reviews);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(ReviewActivity.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        adapter = new ReviewAdapter(review,getApplicationContext());
        reviewList.setAdapter(adapter);
    }
}
