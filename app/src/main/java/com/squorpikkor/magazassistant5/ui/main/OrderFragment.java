package com.squorpikkor.magazassistant5.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squorpikkor.magazassistant5.R;

public class OrderFragment  extends Fragment {


   public static Fragment newInstance() {
      return new OrderFragment();
   }

   private MainViewModel mViewModel;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_order, container, false);
      mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

      return view;
   }
}
