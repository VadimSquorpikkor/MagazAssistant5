package com.squorpikkor.magazassistant5.ui.main.adapter;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;
import static com.squorpikkor.magazassistant5.ui.main.App.getContext;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;

import java.util.ArrayList;

public class OrderLocationAdapter extends RecyclerView.Adapter<OrderLocationAdapter.AdapterViewHolder> {

    private final MainViewModel mainViewModel;
    private int workingDays;

    public OrderLocationAdapter(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
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
        workingDays = mainViewModel.getWorkingDays().getValue();
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

        if (location.isUnitedEmployees()) {
            Log.e(TAG, ""+location.getName()+" (united)");
            allEmployees = new ArrayList<>();
            allEmployees.add(UniteEmployees.unitedEmployeesInOne(mainViewModel.getEmployeesByLocation(location), location.getName(), workingDays));
            for (Employee employee:allEmployees) Log.e(TAG, ": "+employee.getName()+" всего дней: "+employee.getDays(workingDays));
        } else {
            Log.e(TAG, ""+location.getName());
            allEmployees = mainViewModel.getEmployeesByLocation(location);
            for (Employee employee:allEmployees) Log.e(TAG, ": "+employee.getName()+" всего дней: "+employee.getDays(workingDays));
        }

        RecyclerView recyclerView = holder.recyclerView;
        OrderEmployeeAdapter adapter = new OrderEmployeeAdapter(mainViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setList(allEmployees);

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
}
