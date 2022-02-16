package com.squorpikkor.magazassistant5.ui.main.adapter;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

import java.util.ArrayList;

public class OrderEmployeeAdapter extends RecyclerView.Adapter<OrderEmployeeAdapter.AdapterViewHolder>{

    private MainViewModel mainViewModel;

    public OrderEmployeeAdapter(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    private ArrayList<Employee> list;

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<Employee> list) {
        if (list == null)
            list = new ArrayList<>();//Если list == null, то в ресайклер будет передан пустой лист
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Employee employee = list.get(position);
        holder.textName.setText(employee.getName());
        holder.textMoney.setText(Utils.integerToMoneyString(employee.getMoneyLimit(mainViewModel.getMoneyForEmployeePerDay().getValue())));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView textName;
        private final TextView textMoney;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textMoney = itemView.findViewById(R.id.text_money);

        }
    }

}
