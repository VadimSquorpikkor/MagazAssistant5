package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.squorpikkor.magazassistant5.R;

public class SetWorkDaysDialog extends BaseDialog {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.dialog_set_work_days);

        EditText daysText = view.findViewById(R.id.days_count);
        daysText.setText(mViewModel.getWorkingDays().getValue().toString());

        view.findViewById(R.id.days_count_dec).setOnClickListener(v->decreaseIfCorrect(daysText, 0));
        view.findViewById(R.id.days_count_inc).setOnClickListener(v->increaseIfCorrect(daysText, 7));

        view.findViewById(R.id.save).setOnClickListener(v->save(Integer.parseInt(daysText.getText().toString())));
        view.findViewById(R.id.cancel).setOnClickListener(v->dismiss());

        return dialog;
    }

    private void save(int days) {
        mViewModel.saveWorkingDays(days);
        dismiss();
    }

}
