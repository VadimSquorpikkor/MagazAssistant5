package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;

import java.util.ArrayList;

public class EmployeeDialog extends BaseDialog {

    boolean forCreateEmployee;

    public EmployeeDialog() {
        forCreateEmployee = true;
        this.employee = new Employee("Новый", 1);
    }

    Spinner spinner;
    Employee employee;
    EditText name;

    public EmployeeDialog(Employee employee) {
        this.employee = employee;
        forCreateEmployee = false;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.employee_info);

        spinner = view.findViewById(R.id.edit_location);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //mViewModel.getLocations().observe(requireActivity(), this::updateCatList);
        updateCatList(mViewModel.getLocations().getValue());
        spinner.setSelection(employee.getLocationId()-1);

        name = view.findViewById(R.id.edit_name);
        name.setText(employee.getName());

        if (forCreateEmployee) {
            view.findViewById(R.id.save).setVisibility(View.GONE);
            view.findViewById(R.id.create).setVisibility(View.VISIBLE);
            view.findViewById(R.id.delete).setVisibility(View.GONE);
            view.findViewById(R.id.text).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.save).setVisibility(View.VISIBLE);
            view.findViewById(R.id.create).setVisibility(View.GONE);
            view.findViewById(R.id.delete).setVisibility(View.VISIBLE);
            view.findViewById(R.id.text).setVisibility(View.GONE);
        }

        view.findViewById(R.id.cancel).setOnClickListener(v->dismiss());
        view.findViewById(R.id.save).setOnClickListener(v->save());
        view.findViewById(R.id.create).setOnClickListener(v->create());
        view.findViewById(R.id.delete).setOnClickListener(v->delete());



        return dialog;
    }

    private void delete() {
        new AskDeleteDialog(employee).show(getParentFragmentManager(), null);
        dismiss();
    }

    private void create() {
        mViewModel.addNewEmployee(new Employee(name.getText().toString(), spinner.getSelectedItemPosition()+1));
        mViewModel.update();
        dismiss();
    }

    private void save() {
        employee.setName(name.getText().toString());
        employee.setLocationId(spinner.getSelectedItemPosition()+1);
        mViewModel.updateEmployee(employee);
        mViewModel.update();
        dismiss();
    }

    private void updateCatList(ArrayList<Location> list) {
        ArrayList<String> stringList = new ArrayList<>();
        for (Location location:list) {
            stringList.add(location.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
