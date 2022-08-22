package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.dialog.AskResetShopping;
import com.squorpikkor.magazassistant5.ui.main.dialog.EmployeeDialog;
import com.squorpikkor.magazassistant5.ui.main.dialog.SetPriceDialog;
import com.squorpikkor.magazassistant5.ui.main.dialog.SetWorkDaysDialog;

public class ButtonsFragment extends Fragment {

    public static Fragment newInstance() {
        return new ButtonsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buttons, container, false);

        view.findViewById(R.id.reset_shopping).setOnClickListener(  v->new AskResetShopping().show(getParentFragmentManager(), null));
        view.findViewById(R.id.change_price).setOnClickListener(    v->new SetPriceDialog().show(getParentFragmentManager(), null));
        view.findViewById(R.id.set_days).setOnClickListener(        v->new SetWorkDaysDialog().show(getParentFragmentManager(), null));
        view.findViewById(R.id.add_employee).setOnClickListener(    v->new EmployeeDialog().show(getParentFragmentManager(), null));

        return view;
    }
}
