package com.example.bookwormproject;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookwormproject.adapter.LibraryReviewAdapter;
import com.example.bookwormproject.model.Library;
import com.example.bookwormproject.model.LibraryReview;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class LibraryLocation extends FragmentActivity implements OnMapReadyCallback {

    RecyclerView libraryreviewList;
    RecyclerView.Adapter adapter;

    String ReviewId, MemberId, LibraryId, ReviewTitle, ReviewDescription;
    ArrayList<LibraryReview> libraryreview = new ArrayList<>();

    DatabaseHelper db;


    TextView viewLibraryName, viewLibraryAddress, viewLibraryPhone, viewLatitude, viewLongitude, viewLibraryId;
    String libName, libAddress, libPhone, latitude, longitude, libId;

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
        setContentView(R.layout.activity_library_location);


        viewLibraryId = findViewById(R.id.libraryId);
        viewLibraryName = findViewById(R.id.libraryName);
        viewLibraryAddress = findViewById(R.id.libraryAddress);
        viewLibraryPhone = findViewById(R.id.libraryPhone);
        viewLatitude = findViewById(R.id.latitude);
        viewLongitude = findViewById(R.id.longitude);

        libId = getIntent().getStringExtra("libraryId");
        libName = getIntent().getStringExtra("libraryName");
        libAddress = getIntent().getStringExtra("libraryAddress");
        libPhone = getIntent().getStringExtra("libraryPhone");
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");

        viewLibraryId.setText(libId);
        viewLibraryName.setText(libName);
        viewLibraryAddress.setText(libAddress);
        viewLibraryPhone.setText(libPhone);
        viewLatitude.setText(latitude);
        viewLongitude.setText(longitude);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Gmap_fragment);

        mapFragment.getMapAsync(this);

        libraryreviewList = findViewById(R.id.libreviewList);
        libraryreviewList.setHasFixedSize(true);
        libraryreviewList.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);

        Cursor c = db.listReviewLibrary(libId);
        if (c.getCount()>0){
            if (c.moveToFirst()){
                do{
                    ReviewId = c.getString(0);
                    MemberId = c.getString(1);
                    LibraryId = c.getString(2);
                    ReviewTitle = c.getString(3);
                    ReviewDescription = c.getString(4);

                    LibraryReview libreviews = new LibraryReview(ReviewId, MemberId, LibraryId, ReviewTitle, ReviewDescription);
                    libraryreview.add(libreviews);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(LibraryLocation.this, "There is no data in database", Toast.LENGTH_SHORT).show();
        }

        adapter = new LibraryReviewAdapter(libraryreview,getApplicationContext());
        libraryreviewList.setAdapter(adapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coordinate = new LatLng(parseDouble(latitude), parseDouble(longitude));
        googleMap.addMarker(new MarkerOptions().position(coordinate).title(libName));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
    }
}
