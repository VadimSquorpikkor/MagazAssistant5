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

        return dialog;
    }

}
