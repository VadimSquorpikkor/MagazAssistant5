package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;

public class InvoiceFragment extends Fragment {

    public static Fragment newInstance() {
        return new InvoiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice, container, false);
        MainViewModel mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        TextView juiceBigCount = view.findViewById(R.id.big_juice_count);
        TextView juiceSmallCount = view.findViewById(R.id.small_juice_count);
        TextView kefirBigCount = view.findViewById(R.id.big_kefir_count);
        TextView kefirSmallCount = view.findViewById(R.id.small_kefir_count);
        TextView workingDayCount = view.findViewById(R.id.working_day_count);
        TextView invoiceTotal = view.findViewById(R.id.invoice_total);

        mViewModel.getBigJuiceCount().observe(getViewLifecycleOwner(), bjcount -> juiceBigCount.setText(""+bjcount));
        mViewModel.getSmallJuiceCount().observe(getViewLifecycleOwner(), sjcount -> juiceSmallCount.setText(""+sjcount));
        mViewModel.getBigKefirCount().observe(getViewLifecycleOwner(), bkcount -> kefirBigCount.setText(""+bkcount));
        mViewModel.getSmallKefirCount().observe(getViewLifecycleOwner(), skcount -> kefirSmallCount.setText(""+skcount));
        mViewModel.getWorkingDays().observe(getViewLifecycleOwner(), wdays -> workingDayCount.setText(""+wdays));
        mViewModel.getInvoiceTotal().observe(getViewLifecycleOwner(), invoice -> invoiceTotal.setText(""+invoice));

        return view;
    }
}
