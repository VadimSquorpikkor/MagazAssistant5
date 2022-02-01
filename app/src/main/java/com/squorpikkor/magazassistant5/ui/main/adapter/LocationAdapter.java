package com.squorpikkor.magazassistant5.ui.main.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.AdapterViewHolder> {

    private ArrayList<Location> list;

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<Location> list) {
        if (list==null) list = new ArrayList<>();//Если list == null, то в ресайклер будет передан пустой лист
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Location location = list.get(position);
        holder.textName.setText(location.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView textName;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
        }
    }
}
