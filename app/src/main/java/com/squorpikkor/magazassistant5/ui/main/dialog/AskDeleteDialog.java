package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

public class AskDeleteDialog  extends BaseDialog{

    Employee employee;

    public AskDeleteDialog(Employee employee) {
        this.employee = employee;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.dialog_ask_delete);

        ((TextView)view.findViewById(R.id.text)).setText("Точно удалить "+employee.getName()+" ?");
        view.findViewById(R.id.cancel).setOnClickListener(v->dismiss());
        view.findViewById(R.id.delete).setOnClickListener(v->delete());

        return dialog;
    }

    private void delete() {
        mViewModel.deleteEmployee(employee);
        mViewModel.update();
        dismiss();
    }

}
