package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

public class OrderDialog extends BaseDialog {

    Order order;
    Employee employee;
    EditText name, price, count;
    TextView employeeName;

    public OrderDialog(Order order, Employee employee) {
        this.order = order;
        this.employee = employee;
    }

    public OrderDialog(Employee employee) {
        this.employee = employee;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.dialog_order);

        if (order==null) {
            String newOrderId = String.valueOf(mViewModel.getOrders().getValue().size());
            this.order = new Order(newOrderId, employee.getId());
        }

        name = view.findViewById(R.id.edit_name);
        price = view.findViewById(R.id.edit_price);
        count = view.findViewById(R.id.edit_count);
        employeeName = view.findViewById(R.id.employee_name);

        employeeName.setText(employee.getName());
        name.setText(order.getName());
        price.setText(""+order.getPrice());
        count.setText(""+order.getCount());

        view.findViewById(R.id.update).setOnClickListener(v -> update());
        view.findViewById(R.id.add).setOnClickListener(v -> add());

        return dialog;
    }

    private void setData() {
        order.setName(name.getText().toString());
        order.setPrice(Integer.parseInt(price.getText().toString()));
        order.setCount(Integer.parseInt(count.getText().toString()));
    }

    private void add() {
        setData();
        mViewModel.addNewOrder(order);
        dismiss();
    }

    private void update() {

    }

}
