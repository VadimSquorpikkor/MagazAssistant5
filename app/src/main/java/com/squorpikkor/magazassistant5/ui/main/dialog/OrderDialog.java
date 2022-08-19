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
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

public class OrderDialog extends BaseDialog {

    private Order order;
    private final Employee employee;
    private EditText name, price, count;
    private TextView employeeName;
    private TextView btnUpdate, btnAddNew;
    private boolean isNew;

    /**Открыть существующий ордер*/
    public OrderDialog(Order order, Employee employee) {
        this.order = order;
        this.employee = employee;
        this.isNew = false;
        //setAsNew(false);
    }

    /**Создать новый ордер*/
    public OrderDialog(Employee employee) {
        this.employee = employee;
        this.isNew = true;
        //setAsNew(true);
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

        btnUpdate = view.findViewById(R.id.update);
        btnAddNew = view.findViewById(R.id.add);

        if (order==null) {
            this.order = new Order(employee.getId());
            setAsNew(true);
        } else setAsNew(false);

        name = view.findViewById(R.id.edit_name);
        price = view.findViewById(R.id.edit_price);
        count = view.findViewById(R.id.edit_count);
        employeeName = view.findViewById(R.id.employee_name);

        employeeName.setText(employee.getName());
        name.setText(order.getName());
        price.setText(Utils.integerToMoneyString(order.getPrice()));
        count.setText(""+order.getCount());

        view.findViewById(R.id.edit_price_dec).setOnClickListener(v->decreaseIfCorrect(price, 0f));
        view.findViewById(R.id.edit_price_inc).setOnClickListener(v->increaseIfCorrect(price, 1000f));
        view.findViewById(R.id.edit_count_dec).setOnClickListener(v->decreaseIfCorrect(count, 0));
        view.findViewById(R.id.edit_count_inc).setOnClickListener(v->increaseIfCorrect(count, 100));

        btnUpdate.setOnClickListener(v -> update());
        btnAddNew.setOnClickListener(v -> addNew());
        view.findViewById(R.id.cancel).setOnClickListener(v->dismiss());

        return dialog;
    }

    private void setData() {
        order.setName(name.getText().toString());
        order.setPrice(Utils.stringMoneyToInteger(price));
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
