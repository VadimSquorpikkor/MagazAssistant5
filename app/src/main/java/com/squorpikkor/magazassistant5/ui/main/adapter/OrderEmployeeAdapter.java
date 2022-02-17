package com.squorpikkor.magazassistant5.ui.main.adapter;

import static com.squorpikkor.magazassistant5.ui.main.App.getContext;

import android.annotation.SuppressLint;
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
import com.squorpikkor.magazassistant5.ui.main.entities.Order;
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

import java.util.ArrayList;

/**
 * Список работников, которые будут отображаться в итеме локации. Для большинства локаций лист
 * будет содержать всех работников этой локации. Для Корелинских будет добавляться только один
 * работник ("Все работники"), который будет формироваться через специальный метод и будет
 * например содержать сумму всех человекодней всех работников локаций
 */
public class OrderEmployeeAdapter extends RecyclerView.Adapter<OrderEmployeeAdapter.AdapterViewHolder>{

    private final MainViewModel mainViewModel;

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
        int moneyLimit = mainViewModel.getMoneyLimit(employee);
        holder.textName.setText(employee.getName());
        holder.textMoney.setText(Utils.integerToMoneyString(moneyLimit));

        ArrayList<Order> orders = mainViewModel.getOrdersByEmployee(employee);

        RecyclerView recyclerView = holder.recyclerView;
        OrderAdapter adapter = new OrderAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setList(orders);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView textName;
        private final TextView textMoney;
        private final RecyclerView recyclerView;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textMoney = itemView.findViewById(R.id.text_money);
            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }

}
