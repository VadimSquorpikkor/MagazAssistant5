package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

public class OrderDialog extends BaseDialog {

    private Order order;
    private final Employee employee;
    private EditText name, price, count;
    private TextView employeeName;
    private Button btnUpdate, btnAddNew;

    /**Открыть существующий ордер*/
    public OrderDialog(Order order, Employee employee) {
        this.order = order;
        this.employee = employee;
        setAsNew(false);
    }

    /**Создать новый ордер*/
    public OrderDialog(Employee employee) {
        this.employee = employee;
        setAsNew(true);
    }

    private void setAsNew(boolean isNew) {
        if (isNew) {
            btnAddNew.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.GONE);
        } else {
            btnAddNew.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.dialog_order);

        if (order==null) this.order = new Order(employee.getId());

        name = view.findViewById(R.id.edit_name);
        price = view.findViewById(R.id.edit_price);
        count = view.findViewById(R.id.edit_count);
        employeeName = view.findViewById(R.id.employee_name);

        employeeName.setText(employee.getName());
        name.setText(order.getName());
        price.setText(""+order.getPrice());
        count.setText(""+order.getCount());

        btnUpdate = view.findViewById(R.id.update);
        btnAddNew = view.findViewById(R.id.add);

        btnUpdate.setOnClickListener(v -> update());
        btnAddNew.setOnClickListener(v -> addNew());

        return dialog;
    }

    private void setData() {
        order.setName(name.getText().toString());
        order.setPrice(Integer.parseInt(price.getText().toString()));
        order.setCount(Integer.parseInt(count.getText().toString()));
    }

    private void addNew() {
        setData();
        mViewModel.addNewOrder(order);
        dismiss();
    }

    private void update() {
        setData();
        mViewModel.updateOrder(order);
        dismiss();
    }

}
