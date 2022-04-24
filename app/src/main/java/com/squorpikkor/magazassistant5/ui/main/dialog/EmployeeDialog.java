package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

public class EmployeeDialog extends BaseDialog {


    private EmployeeDialog() {
    }

    private static class EmployeeDialogHolder {
//        public static final EmployeeDialog HOLDER_INSTANCE = new EmployeeDialog();

//        public static final EmployeeDialog HOLDER_INSTANCE(Employee employee) {
//            return new EmployeeDialog(employee);
//        }
    }

//    public static EmployeeDialog getInstance(Employee e) {
//        return EmployeeDialogHolder.HOLDER_INSTANCE(e);
//    }

//    private static final EmployeeDialog INSTANCE = new EmployeeDialog();
//
//    public static EmployeeDialog getInstance(Employee employee) {
//
//    }

    Spinner spinner;

    Employee employee;

    EditText name;


    public EmployeeDialog(Employee employee) {
        this.employee = employee;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.employee_order);

        spinner = view.findViewById(R.id.location_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                mViewModel.setCategory(mViewModel.getCatList().getValue().get(position));
//                mViewModel.getEntitiesList().setValue(mViewModel.getEntitiesList().getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        mViewModel.getLocations().observe(getViewLifecycleOwner(), this::updateCatList);

        name = view.findViewById(R.id.edit_name);
        name.setText(employee.getName());

        return dialog;
    }

    private void updateCatList(ArrayList<Location> list) {
        ArrayAdapter<Location> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setData() {
//        order.setName(name.getText().toString());
//        order.setPrice(Integer.parseInt(price.getText().toString()));
//        order.setCount(Integer.parseInt(count.getText().toString()));
    }

    private void addNew() {
        setData();
//        mViewModel.addNewOrder(order);
        dismiss();
    }

    private void update() {
        setData();
//        mViewModel.updateOrder(order);
        dismiss();
    }

}
