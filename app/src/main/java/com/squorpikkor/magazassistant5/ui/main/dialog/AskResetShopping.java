package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

public class AskResetShopping extends BaseDialog{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.dialog_ask_reset);

        view.findViewById(R.id.cancel).setOnClickListener(v->dismiss());
        view.findViewById(R.id.ok).setOnClickListener(v->{mViewModel.resetShopping();dismiss();});

        return dialog;
    }

}
