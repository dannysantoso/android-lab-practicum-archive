package com.example.bookwormproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookwormproject.adapter.LibraryAdapter;
import com.example.bookwormproject.model.Library;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView libraryList;
    ArrayList<Library> library;
    RecyclerView.Adapter adapter;
    Button dataFetch;

    private RequestQueue requestQueue;

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
        setContentView(R.layout.activity_home);

        requestQueue = Volley.newRequestQueue(this);
        library = new ArrayList<>();

        dataFetch = findViewById(R.id.dataFetchbtn);
        libraryList = findViewById(R.id.libraryList);
        libraryList.setHasFixedSize(true);
        libraryList.setLayoutManager(new LinearLayoutManager(this));

        dataFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataFetch.setVisibility(View.GONE);
                getData();
            }
        });

    }

    private void getData() {
        String url = "https://api.myjson.com/bins/pobqg";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resultArray = response.getJSONArray("data");
                    for(int i=0; i<resultArray.length();i++){
                        JSONObject object = resultArray.getJSONObject(i);


                        library.add(new Library(
                                object.getString("LibraryId"),
                                object.getString("LibraryName"),
                                object.getString("LibraryAddress"),
                                object.getString("LibraryPhone"),
                                object.getString("Latitude"),
                                object.getString("Longitude")
                        ));
                    }
                    adapter = new LibraryAdapter(library,getApplicationContext());
                    libraryList.setAdapter(adapter);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
