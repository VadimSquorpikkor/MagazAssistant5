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
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

public class OrdersHead extends Fragment {
   public static Fragment newInstance() {
      return new OrdersHead();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_order_head, container, false);
      MainViewModel mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

      TextView invoiceTotal = view.findViewById(R.id.invoice_total);
      TextView invoiceLeft = view.findViewById(R.id.invoice_left);
      TextView moneyPerDay = view.findViewById(R.id.money_per_day);

      mViewModel.getInvoiceTotal().observe(getViewLifecycleOwner(), total -> invoiceTotal.setText(Utils.integerToMoneyString(total)));
      mViewModel.getInvoiceLeft().observe(getViewLifecycleOwner(), left -> invoiceLeft.setText(Utils.integerToMoneyString(left)));
      mViewModel.getMoneyForEmployeePerDay().observe(getViewLifecycleOwner(), day -> moneyPerDay.setText(Utils.integerToMoneyString(day)));

      return view;
   }
}
