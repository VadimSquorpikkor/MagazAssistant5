package com.squorpikkor.magazassistant5.ui.main.adapter;

import static com.squorpikkor.magazassistant5.ui.main.App.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.dialog.OrderControlDialog;
import com.squorpikkor.magazassistant5.ui.main.dialog.OrderDialog;
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

    private final FragmentManager manager;
    private final MainViewModel mainViewModel;

    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public OrderEmployeeAdapter(MainViewModel mainViewModel, FragmentManager manager) {
        this.mainViewModel = mainViewModel;
        this.manager = manager;
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
        ArrayList<Order> orders = mainViewModel.getOrdersByEmployee(employee);
        holder.textName.setText(employee.getName());
        holder.textMoney.setText(Utils.calculateMoneyLeftByOrders(orders, moneyLimit));
        String orderState = Utils.getOrdersStatusString(orders);
        holder.textState.setText(orderState);
        if (Utils.allOrdersIsComplete(orderState)) {
            holder.isChecked.setVisibility(View.VISIBLE);
            holder.textState.setVisibility(View.GONE);
        } else {
            holder.isChecked.setVisibility(View.GONE);
            holder.textState.setVisibility(View.VISIBLE);
        }

        RecyclerView recyclerView = holder.recyclerView;
        if (employee.isShow()) {
            recyclerView.setVisibility(View.VISIBLE);
            OrderAdapter adapter = new OrderAdapter(mainViewModel);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            adapter.setList(orders);
            adapter.setOnItemClickListener(order -> new OrderDialog(order, employee).show(manager, null));
            adapter.setOnItemLongClickListener(order -> new OrderControlDialog(order, employee, manager).show(manager, null));

            holder.addOrder.setVisibility(View.VISIBLE);
//            if (orders.size()==0) holder.addOrder.setVisibility(View.VISIBLE);
//            else holder.addOrder.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            holder.addOrder.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView textName;
        private final TextView textMoney;
        private final TextView textState;
        private final RecyclerView recyclerView;
        private final ImageView isChecked;
        private final ImageView addOrder;

        @SuppressLint("NotifyDataSetChanged")
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textMoney = itemView.findViewById(R.id.text_money);
            textState = itemView.findViewById(R.id.text_state);
            recyclerView = itemView.findViewById(R.id.recycler);
            isChecked = itemView.findViewById(R.id.is_checked);
            addOrder = itemView.findViewById(R.id.add_order);

            addOrder.setOnClickListener(v -> {
                Log.e("TAG", "onClick: " + list.get(getAdapterPosition()).getId());
                new OrderDialog(list.get(getAdapterPosition())).show(manager, null);
            });

            itemView.setOnClickListener(view -> {
                Employee employee = list.get(getAdapterPosition());
                employee.setShow(!employee.isShow());
                notifyDataSetChanged();
            });
            itemView.setOnLongClickListener(view -> {
                if (onItemLongClickListener != null) onItemLongClickListener.onItemLongClick(list.get(getAdapterPosition()).getCountInList());
                return true;
            });


        }

    }

}
