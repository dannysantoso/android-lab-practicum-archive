package com.example.bookwormproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookwormproject.LibraryLocation;
import com.example.bookwormproject.R;
import com.example.bookwormproject.model.Library;

import java.util.ArrayList;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.MyViewHolder> {

    ArrayList<Library> library;

    Context context;


    public LibraryAdapter(ArrayList<Library> library, Context context) {
        this.library = library;
        this.context = context;
    }

    @NonNull
    @Override
    public LibraryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_library_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapter.MyViewHolder holder, final int position) {
        holder.libraryId.setText(library.get(position).LibraryId);
        holder.libraryName.setText(library.get(position).LibraryName);
        holder.libraryAddress.setText(library.get(position).LibraryAddress);
        holder.libraryPhone.setText(library.get(position).LibraryPhone);
        holder.latitude.setText(library.get(position).Latitude);
        holder.longitude.setText(library.get(position).Longitude);
        holder.libraryCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LibraryLocation.class);
                intent.putExtra("libraryId", library.get(position).getLibraryId());
                intent.putExtra("libraryName", library.get(position).getLibraryName());
                intent.putExtra("libraryAddress", library.get(position).getLibraryAddress());
                intent.putExtra("libraryPhone", library.get(position).getLibraryPhone());
                intent.putExtra("latitude", library.get(position).getLatitude());
                intent.putExtra("longitude", library.get(position).getLongitude());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return library.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView libraryId, libraryName, libraryAddress, libraryPhone, latitude, longitude;
        public LinearLayout libraryCell;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            libraryId = itemView.findViewById(R.id.libraryId);
            libraryName = itemView.findViewById(R.id.libraryName);
            libraryAddress = itemView.findViewById(R.id.libraryAddress);
            libraryPhone = itemView.findViewById(R.id.libraryPhone);
            latitude = itemView.findViewById(R.id.latitude);
            longitude = itemView.findViewById(R.id.longitude);
            libraryCell = itemView.findViewById(R.id.libraryCell);
        }
    }
}
