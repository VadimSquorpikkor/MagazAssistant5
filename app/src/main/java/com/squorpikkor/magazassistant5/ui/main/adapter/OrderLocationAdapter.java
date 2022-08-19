package com.squorpikkor.magazassistant5.ui.main.adapter;

import static com.squorpikkor.magazassistant5.ui.main.App.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.ui.main.pager.InfoActivity;
import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;

import java.util.ArrayList;

public class OrderLocationAdapter extends RecyclerView.Adapter<OrderLocationAdapter.AdapterViewHolder> {

    public static final String EXTRA_POSITION = "extra_position";

    private final FragmentManager manager;
    private final MainViewModel mainViewModel;
    private final Context context;

    public OrderLocationAdapter(MainViewModel mainViewModel, FragmentManager manager, Context context) {
        this.mainViewModel = mainViewModel;
        this.manager = manager;
        this.context = context;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Location location);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private ArrayList<Location> list;

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<Location> list) {
        if (list == null)
            list = new ArrayList<>();//Если list == null, то в ресайклер будет передан пустой лист
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
        ArrayList<Employee> allEmployees;

        allEmployees = mainViewModel.getPresentEmployeesByLocationWithUnited(location);

        RecyclerView recyclerView = holder.recyclerView;
        OrderEmployeeAdapter adapter = new OrderEmployeeAdapter(mainViewModel, manager);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setList(allEmployees);
        adapter.setOnItemLongClickListener(this::openInfo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView textName;
        RecyclerView recyclerView;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            recyclerView = itemView.findViewById(R.id.recycler);

            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(list.get(getAdapterPosition()));
            });
        }
    }

    private void openInfo(int position) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        context.startActivity(intent);
    }
}
