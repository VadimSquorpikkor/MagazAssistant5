package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

public class AskDeleteOrderDialog extends BaseDialog{

    Order order;

    public AskDeleteOrderDialog(Order order) {
        this.order = order;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.dialog_ask_delete_order);

        ((TextView)view.findViewById(R.id.text)).setText("Точно удалить "+order.getName()+" ?");
        view.findViewById(R.id.cancel).setOnClickListener(v->dismiss());
        view.findViewById(R.id.delete).setOnClickListener(v->delete());

        return dialog;
    }

    private void delete() {
        mViewModel.deleteOrder(order);
        mViewModel.update();
        dismiss();
    }

}
