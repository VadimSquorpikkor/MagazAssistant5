package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.squorpikkor.magazassistant5.R;

public class CradleFragment extends Fragment {

    public static Fragment newInstance() {
        return new CradleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cradle, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_price, PriceFragment.newInstance()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_invoice, InvoiceFragment.newInstance()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_employees, EmployeesFragment.newInstance()).commit();
        return view;
    }

}
